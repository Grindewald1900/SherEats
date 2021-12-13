package com.example.shereats.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.shereats.R
import com.example.shereats.databinding.ActivityLoginBinding
import com.example.shereats.model.viewmodel.LoginViewModel
import com.example.shereats.utils.LoginStatusUtil
import com.example.shereats.utils.ToastUtil

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        initView()
    }

    private fun initView(){
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.btnLoginLogin.setOnClickListener {
            if (binding.etLoginName.text.isNullOrEmpty()){
                ToastUtil.showShortMessage(getString(R.string.hint_no_email), this)
                return@setOnClickListener
            }
            if(binding.etLoginPwd.text.isNullOrEmpty()){
                ToastUtil.showShortMessage(getString(R.string.hint_no_password), this)
                return@setOnClickListener
            }
            viewModel.setUser(binding.etLoginName.text.toString(), binding.etLoginPwd.text.toString())
            viewModel.getUser().observe(this,{
                if (it[0].user_name == binding.etLoginName.text.toString()){
                    LoginStatusUtil.mUser = it[0]
                    ToastUtil.showShortMessage(getString(R.string.hint_login_success), this)
                    finish()
                }
            })

        }
    }
}