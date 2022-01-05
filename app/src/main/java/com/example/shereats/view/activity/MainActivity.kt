package com.example.shereats.view.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.bumptech.glide.Glide
import com.example.shereats.R
import com.example.shereats.databinding.ActivityMainBinding
import com.example.shereats.model.viewmodel.MainViewModel
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.ImageUtil
import com.example.shereats.utils.LoginStatusUtil
import com.example.shereats.utils.firebase.FireBasePopulateData
import com.example.shereats.utils.firebase.RealtimeUtil
import com.example.shereats.view.custom.RoundImageView


/**
 * The Entry activity of the app, including 5 tabs in the main page
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var headerView: View
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTransition()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initView()
        test()
    }

    private fun initView() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setSupportActionBar(findViewById(R.id.toolbar))
        // Remove the default toolbar title
        supportActionBar?.setDisplayShowTitleEnabled(false)
        navController = findNavController(R.id.nav_host_main)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id){
                R.id.friendFragment -> {
                    binding.fabMain.setDestination(ConstantUtil.ACTIVITY_SEARCH_FRIEND)
                }
                else -> {
                    binding.fabMain.setDestination(ConstantUtil.ACTIVITY_SEARCH_DISH)
                }
            }
        }
        // Bottom nav controller, which also handle the click event of bottom nav
        binding.bottomNavMain.setupWithNavController(navController)
        // Drawer controller
        binding.leftNavMain.setupWithNavController(navController)
        // Set: The set of destinations by id considered at the top level of your information hierarchy.
        // Here we need to keep the id of destination fragments same as menu.xml e.g. dishFragment has the same id in menu.xml and navigation.xml
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.dishFragment,
                R.id.cartFragment,
                R.id.homeFragment,
                R.id.orderFragment,
                R.id.friendFragment
            ), binding.drawerLayoutMain
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Set the header of left navigation
        headerView = binding.leftNavMain.inflateHeaderView(R.layout.nav_header_main)
        headerView.findViewById<ImageView>(R.id.iv_main_header_portrait).setOnClickListener {
            if (LoginStatusUtil.isLogin()){
                startActivity(Intent(this, UserInfoActivity::class.java))
            }else{
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }

    }

    private fun setTransition(){
        val xTransition = TransitionInflater.from(this).inflateTransition(R.transition.transition_from_button)
        with(window){
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
            enterTransition = android.transition.Fade()
            exitTransition = xTransition

        }
    }

    override fun onStart() {
        super.onStart()
        setLoginStatus()
    }

    private fun setLoginStatus(){
        if(LoginStatusUtil.isLogin()){
            val user = LoginStatusUtil.getUser()
            val headerBk = headerView.findViewById<ImageView>(R.id.iv_main_header_bk)
            val userImage: RoundImageView = headerView.findViewById(R.id.iv_main_header_portrait)
            headerView.findViewById<TextView>(R.id.tv_main_header_name).text = user.userName
            headerView.findViewById<TextView>(R.id.tv_main_header_email).text = user.userMail
            viewModel.setProfileImage(user.userName!!, userImage, this)
            Glide.with(this)
                .load(R.drawable.gif_header_background)
                .placeholder(R.color.colorPrimary)
                .into(headerBk)
        }
    }

    // Bottom nav
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val a = item.itemId
        val b = item
        val c = item.groupId
        return item.onNavDestinationSelected(navController) ||
                super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    // Open drawer when drawer icon clicked and back btn presse
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_main).navigateUp(appBarConfiguration)
    }

    fun test(){
//        FireBasePopulateData.populateBadge()
//        FireBasePopulateData.populateDish()
//        FireBasePopulateData.populateUser()
//        FireBasePopulateData.populateRestaurant()
//        val a = RealtimeUtil.getUser("000123456")
    }

}