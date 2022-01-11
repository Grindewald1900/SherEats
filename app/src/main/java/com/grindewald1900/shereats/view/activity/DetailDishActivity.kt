package com.grindewald1900.shereats.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.grindewald1900.shereats.databinding.ActivityDetailDishBinding
import com.grindewald1900.shereats.model.entity.*
import com.grindewald1900.shereats.model.interfaces.RefreshData
import com.grindewald1900.shereats.model.viewmodel.DetailDishViewModel
import com.grindewald1900.shereats.utils.ConstantUtil
import com.grindewald1900.shereats.utils.LoginStatusUtil
import com.grindewald1900.shereats.utils.TextUtil
import com.grindewald1900.shereats.utils.firebase.StorageUtil
import com.grindewald1900.shereats.view.custom.TransitionButton
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.grindewald1900.shereats.R


class DetailDishActivity : BaseActivityBar(), OnMapReadyCallback, RefreshData{
    private lateinit var binding: ActivityDetailDishBinding
    private lateinit var viewModel: DetailDishViewModel
    private lateinit var mMapFragment: SupportMapFragment
    private lateinit var mGoogleMap: GoogleMap
    private lateinit var mTransitionBtn: TransitionButton
    private lateinit var mDish: FirebaseDish
    private lateinit var mRestaurant: FirebaseRestaurant
    private var isClickedTransitionBtn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (null != intent.extras){
            mDish = intent.getSerializableExtra(ConstantUtil.ENTITY_DISH) as FirebaseDish
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_dish)
        initViewBeforeMap()
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap
        viewModel.setFirebaseRestaurant(mDish.restaurantId!!)
        viewModel.getFirebaseRestaurant().observe(this) {
            mRestaurant = it
            showPoint()
            initView()
            setUserInfo()
            setDishImage(mDish.itemId!!, binding.ivActivityDishImage)
        }
    }

    override fun onResume() {
        super.onResume()
        setUserInfo()
        // Reset Animation only from OnBackPressed of new activity
        if(isClickedTransitionBtn){
            mTransitionBtn.resetAnimation(null, false)
        }
    }

    /**
     * Some view should be initialized before the map is ready
     */
    private fun initViewBeforeMap(){
        viewModel = ViewModelProvider(this).get(DetailDishViewModel::class.java)
        mMapFragment = supportFragmentManager.findFragmentById(R.id.fragment_activity_dish_map) as SupportMapFragment
        mMapFragment.getMapAsync(this)
    }

    private fun initView(){
        binding.tvActivityDishDescription.text = mRestaurant.restaurantGenre
        binding.tvActivityDishPrice.text = TextUtil.getItemPriceEach(mRestaurant.restaurantAverage!!)
        binding.tvActivityDishLocation.text = mRestaurant.restaurantAddress
        binding.btnActivityDishBack.setOnClickListener {
            onBackPressed()
        }
        binding.ivActivityDishPortrait.setOnClickListener {
            if(LoginStatusUtil.isLogin()){
                startActivity(Intent(this, UserInfoActivity::class.java))
            }else{
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
        setHeartListener()
        initTransitionButton()
    }

    private fun initTransitionButton(){
        mTransitionBtn = binding.btnActivityDishAddCart
        mTransitionBtn.setOnClickListener {
            // Start animation when clicked
            mTransitionBtn.startAnimation()
            lifecycleScope.launch {
                delay(ConstantUtil.ANIMATION_DELAY)
                val intent: Intent

                if (LoginStatusUtil.isLogin()){
                    SingletonUtil.addToCart(LoginStatusUtil.getUser(), mDish, 1, true)
                    intent = Intent(baseContext, ResultActivity::class.java)
                    intent.putExtra(ConstantUtil.STRING_RESULT_ACTIVITY, ConstantUtil.RESULT_CORRECT)
                }else{
                    intent = Intent(baseContext, LoginActivity::class.java)
                }
                mTransitionBtn.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND, object: TransitionButton.OnAnimationStopEndListener{
                    override fun onAnimationStopEnd() {
                        isClickedTransitionBtn = true
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(intent)
                    }
                })
            }
        }
    }

    /**
     * Show a mark on the google map fragment
     */
    private fun showPoint(){
        val point = LatLng(mRestaurant.restaurantLat!!.toDouble(), mRestaurant.restaurantLong!!.toDouble())
        mGoogleMap.apply {
            addMarker(
                MarkerOptions()
                    .position(point)
                    .title(mRestaurant.restaurantName)
                    .snippet("${mRestaurant.restaurantGenre}: ${mRestaurant.restaurantAverage}$")
            )
            moveCamera(CameraUpdateFactory.newLatLng(point))
            setMinZoomPreference(10f)
            setMaxZoomPreference(20f)
        }
    }

    /**
     * Set text for user info on the top
     */
    private fun setUserInfo(){
        if(LoginStatusUtil.isLogin()){
            val user = LoginStatusUtil.getUser()
            viewModel.setProfileImage(user.userName!!, binding.ivActivityDishPortrait, this)
            binding.tvActivityDishInitiator.text = user.userName
        }else{
            binding.tvActivityDishInitiator.text = getString(R.string.visitor)
        }
    }

    /**
     * Retrieve image for top image
     */
    private fun setDishImage(id: Long, view: ImageView){
        val childPath = "item/$id.jpg"
        val pathReference = StorageUtil.reference.child(childPath)
        pathReference.downloadUrl.addOnSuccessListener {
            Glide.with(this)
                .load(it.toString())
                .placeholder(R.drawable.loading_spinner_1s_200px)
                .into(view)
        }.addOnFailureListener {
            it.stackTrace
        }
    }

    /**
     * Initialize src and set is_favorite button click listener
     */
    private fun setHeartListener(){
        if (!SingletonUtil.MAP_FAVORITE_DISH.containsKey(mDish.itemId)){
            SingletonUtil.MAP_FAVORITE_DISH[mDish.itemId!!] = false
        }
        binding.btnActivityDishCollect.setHolder(this)
        binding.btnActivityDishCollect.setImage(SingletonUtil.MAP_FAVORITE_DISH[mDish.itemId]!!)
    }

    override fun refreshData(isFav: Boolean) {
        SingletonUtil.MAP_FAVORITE_DISH[mDish.itemId!!] = isFav
    }

}