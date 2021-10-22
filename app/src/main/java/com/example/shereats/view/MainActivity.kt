package com.example.shereats.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.shereats.R
import com.example.shereats.databinding.ActivityMainBinding
import com.example.shereats.view.fragment.*
import java.lang.NullPointerException

/**
 * The Entry activity of the app, including 5 fragments in the main page
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isInitial = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initView()
    }

    private fun initView(){
        // Set default fragment
        var selectedFragment: Fragment = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main, selectedFragment).commit()
        // Set default navigation item
        binding.bottomNavMain.selectedItemId = R.id.nav_three
        binding.bottomNavMain.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.nav_one -> {
                    selectedFragment = DishFragment.newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main, selectedFragment).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.nav_two -> {
                    selectedFragment = CartFragment.newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main, selectedFragment).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.nav_three -> {
                    selectedFragment = HomeFragment.newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main, selectedFragment).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.nav_four -> {
                    selectedFragment = OrderFragment.newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main, selectedFragment).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.nav_five -> {
                    selectedFragment = FriendFragment.newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main, selectedFragment).commit()
                    return@setOnItemSelectedListener true
                }
                else -> {
                    return@setOnItemSelectedListener false
                }
            }

        }

    }
}