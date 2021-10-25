package com.example.shereats.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.shereats.R
import com.example.shereats.databinding.ActivityMainBinding
import com.example.shereats.view.fragment.*


/**
 * The Entry activity of the app, including 5 tabs in the main page
 */
class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private var isInitial = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initView()
    }

    private fun initView(){
        navController = findNavController(R.id.nav_host_main)
        // Bottom nav controller, which also handle the click event of bottom nav
        binding.bottomNavMain.setupWithNavController(navController)
        // Drawer controller
        binding.leftNavMain.setupWithNavController(navController)
        // Set: The set of destinations by id considered at the top level of your information hierarchy.
        // Here we need to keep the id of destination fragments same as menu.xml e.g. dishFragment has the same id in menu.xml and navigation.xml
        appBarConfiguration = AppBarConfiguration(setOf(R.id.dishFragment, R.id.cartFragment, R.id.homeFragment, R.id.orderFragment, R.id.friendFragment), binding.drawerLayoutMain)

        setupActionBarWithNavController(navController, appBarConfiguration)
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