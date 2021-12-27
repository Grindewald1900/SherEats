package com.example.shereats.view.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.shereats.R
import com.example.shereats.databinding.ActivityUserInfoBinding
import com.example.shereats.model.entity.Badge
import com.example.shereats.model.viewmodel.UserInfoViewModel
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.LoginStatusUtil
import com.example.shereats.view.adapter.BadgeAdapter
import com.example.shereats.view.fragment.DialogUploadImageFragment

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
        binding.btnActivityUserInfoPortrait.setOnClickListener {
            val mDialog = DialogUploadImageFragment()
            mDialog.show(supportFragmentManager, ConstantUtil.TAG_DIALOG_UPLOAD_IMAGE)
        }
        viewModel.setBadges()
        viewModel.getBadges().observe(this, {
            binding.rvActivityUserInfo.adapter = BadgeAdapter(it)
        })
        viewModel.setProfileImage(user.user_name, binding.btnActivityUserInfoPortrait, this)
        viewModel.getProfileImage().observe(this, {
            Glide.with(this)
                .load(it)
                .placeholder(R.drawable.loading_spinner_1s_200px)
                .into(binding.btnActivityUserInfoPortrait)
        })
    }

    private fun hideActionBar(){
        supportActionBar?.hide()
    }

}