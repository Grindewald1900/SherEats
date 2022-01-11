package com.grindewald1900.shereats.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.grindewald1900.shereats.R
import com.grindewald1900.shereats.databinding.ActivityUserInfoBinding
import com.grindewald1900.shereats.model.entity.FirebaseUser
import com.grindewald1900.shereats.model.viewmodel.UserInfoViewModel
import com.grindewald1900.shereats.utils.ConstantUtil
import com.grindewald1900.shereats.utils.LoginStatusUtil
import com.grindewald1900.shereats.view.adapter.BadgeAdapter
import com.grindewald1900.shereats.view.fragment.DialogUploadImageFragment

/**
 * User Information page, when user profile picture is clicked
 */
class UserInfoActivity : BaseActivityBar(), DialogUploadImageFragment.OnRefreshImage{
    private lateinit var binding: ActivityUserInfoBinding
    private lateinit var viewModel: UserInfoViewModel
    private lateinit var mDialog: DialogUploadImageFragment
    private lateinit var mUser: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
        hideActionBar()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_info)
        initView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.setFirebaseBadge()
    }

    private fun initView(){
        mUser = LoginStatusUtil.getUser()
        viewModel = ViewModelProvider(this).get(UserInfoViewModel::class.java)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        mDialog = DialogUploadImageFragment()
//        setDialogListener(user.user_name)

        binding.tvActivityUserInfoName.text = mUser.userName
        binding.tvActivityUserId.text = mUser.userMail
        binding.rvActivityUserInfo.layoutManager = LinearLayoutManager(this)
        binding.rvActivityUserInfo.itemAnimator = DefaultItemAnimator()
        binding.btnActivityUserInfoBack.setOnClickListener { onBackPressed() }
        binding.btnActivityUserInfoPortrait.setOnClickListener {
            mDialog.show(supportFragmentManager, ConstantUtil.TAG_DIALOG_UPLOAD_IMAGE)
        }
        binding.btnActivityUserInfoSetting.setOnClickListener {
            startActivity(Intent(this, SettingActivity::class.java))
        }

        viewModel.setFirebaseBadge()
        viewModel.getFirebaseBadge().observe(this) {
            binding.rvActivityUserInfo.adapter = BadgeAdapter(it)
        }
        viewModel.setProfileImage(mUser.userName!!, binding.btnActivityUserInfoPortrait, this)
        viewModel.getProfileImage().observe(this) {
            Glide.with(this)
                .load(it)
                .placeholder(R.drawable.loading_spinner_1s_200px)
                .into(binding.btnActivityUserInfoPortrait)
        }
    }


    private fun hideActionBar(){
        supportActionBar?.hide()
    }


    override fun refreshImage() {
        viewModel.setProfileImage(mUser.userName!!, binding.btnActivityUserInfoPortrait, this)
    }


}