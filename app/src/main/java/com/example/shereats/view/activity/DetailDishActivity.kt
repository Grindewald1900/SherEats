package com.example.shereats.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.shereats.R
import com.example.shereats.databinding.ActivityDetailDishBinding
import com.example.shereats.model.entity.Dish
import com.example.shereats.model.entity.Restaurant
import com.example.shereats.model.viewmodel.DetailDishViewModel
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.LoginStatusUtil
import com.example.shereats.utils.firebase.StorageUtil
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DetailDishActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityDetailDishBinding
    private lateinit var viewModel: DetailDishViewModel
    private lateinit var mMapFragment: SupportMapFragment
    private lateinit var mGoogleMap: GoogleMap
    private lateinit var mDish: Dish
    private lateinit var mRestaurant: Restaurant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (null != intent.extras){
            mDish = intent.getSerializableExtra(ConstantUtil.ENTITY_DISH) as Dish
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_dish)
        viewModel = ViewModelProvider(this).get(DetailDishViewModel::class.java)
        mMapFragment = supportFragmentManager.findFragmentById(R.id.fragment_activity_dish_map) as SupportMapFragment
        mMapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap
        viewModel.setRestaurant(mDish.restaurant_id)
        viewModel.getRestaurant().observe(this) {
            mRestaurant= it[0]
            showPoint()
            initView()
            setUserInfo()
            setRestaurantImage(mRestaurant.restaurant_id, binding.ivActivityDishImage)
        }
    }

    override fun onResume() {
        super.onResume()
        setUserInfo()
    }

    private fun initView(){
        binding.tvActivityDishDescription.text = mRestaurant.restaurant_genre
        binding.tvActivityDishPrice.text = "${mRestaurant.restaurant_average.toString()} $"
        binding.tvActivityDishLocation.text = mRestaurant.restaurant_address
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
    }

    private fun showPoint(){
        val point = LatLng(mRestaurant.restaurant_lat.toDouble(), mRestaurant.restaurant_long.toDouble())
        mGoogleMap.apply {
            addMarker(
                MarkerOptions()
                    .position(point)
                    .title(mRestaurant.restaurant_name)
                    .snippet("${mRestaurant.restaurant_genre}: ${mRestaurant.restaurant_average}$")
            )
            moveCamera(CameraUpdateFactory.newLatLng(point))
            setMinZoomPreference(10f)
            setMaxZoomPreference(20f)
        }
    }

    private fun setUserInfo(){
        if(LoginStatusUtil.isLogin()){
            val user = LoginStatusUtil.getUser()
            viewModel.setProfileImage(user.user_name, binding.ivActivityDishPortrait, this)
            binding.tvActivityDishInitiator.text = user.user_name
        }else{
            binding.tvActivityDishInitiator.text = getString(R.string.visitor)
        }
    }
    private fun setRestaurantImage(id: Int, view: ImageView){
        val childPath = "restaurant/$id.jpg"
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

}