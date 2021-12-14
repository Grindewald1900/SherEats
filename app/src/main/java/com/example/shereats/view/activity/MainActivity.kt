package com.example.shereats.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.shereats.R
import com.example.shereats.databinding.ActivityMainBinding
import com.example.shereats.model.entity.Restaurant
import com.example.shereats.utils.HttpUtil
import com.example.shereats.utils.LoginStatusUtil
import com.example.shereats.utils.network.EndPointInterface
import com.example.shereats.utils.network.ServiceBuilder
import com.example.shereats.view.fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * The Entry activity of the app, including 5 tabs in the main page
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var headerView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initView()
    }

    private fun initView() {
        setSupportActionBar(findViewById(R.id.toolbar))
        // Remove the default toolbar title
        supportActionBar?.setDisplayShowTitleEnabled(false)
        navController = findNavController(R.id.nav_host_main)
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

    override fun onStart() {
        super.onStart()
        if(LoginStatusUtil.isLogin()){
            val user = LoginStatusUtil.getUser()
            headerView.findViewById<TextView>(R.id.tv_main_header_name).text = user.user_name
            headerView.findViewById<TextView>(R.id.tv_main_header_email).text = user.user_mail
        }
    }

    // Bottom nav
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) ||
                super.onOptionsItemSelected(item)
    }

    // Open drawer when drawer icon clicked and back btn presse
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_main).navigateUp(appBarConfiguration)
    }
}