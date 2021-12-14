package com.example.shereats.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.shereats.R
import com.example.shereats.databinding.ActivityUserInfoBinding
import com.example.shereats.utils.LoginStatusUtil

class UserInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_info)
        initView()
    }

    private fun initView(){
        val user = LoginStatusUtil.getUser()
        binding.tvActivityUserInfoName.text = user.user_name
        binding.tvActivityUserId.text = user.user_mail
    }
}