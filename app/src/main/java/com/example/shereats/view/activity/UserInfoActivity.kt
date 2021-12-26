package com.example.shereats.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shereats.R
import com.example.shereats.databinding.ActivityUserInfoBinding
import com.example.shereats.model.entity.Badge
import com.example.shereats.model.viewmodel.UserInfoViewModel
import com.example.shereats.utils.LoginStatusUtil
import com.example.shereats.view.adapter.BadgeAdapter

/**
 * User Information page, when user profile picture is clicked
 */
class UserInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserInfoBinding
    private lateinit var viewModel: UserInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
        hideActionBar()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_info)
        initView()
    }

    private fun initView(){
        val user = LoginStatusUtil.getUser()
        viewModel = ViewModelProvider(this).get(UserInfoViewModel::class.java)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.tvActivityUserInfoName.text = user.user_name
        binding.tvActivityUserId.text = user.user_mail
        binding.rvActivityUserInfo.layoutManager = LinearLayoutManager(this)
        binding.rvActivityUserInfo.itemAnimator = DefaultItemAnimator()
        binding.btnActivityUserInfoBack.setOnClickListener {
            onBackPressed()
        }

        viewModel.setBadges()
        viewModel.getBadges().observe(this, {
            binding.rvActivityUserInfo.adapter = BadgeAdapter(it)
        })
    }

    private fun hideActionBar(){
        supportActionBar?.hide()
    }
}