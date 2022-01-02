package com.example.shereats.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.shereats.R
import com.example.shereats.databinding.ActivityRegisterBinding
import com.example.shereats.model.viewmodel.RegisterViewModel
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.LoginStatusUtil
import com.example.shereats.utils.ToastUtil
import java.util.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    private lateinit var userData: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        initView()
    }

    private fun initView(){
        val uniqueId = UUID.randomUUID().toString()
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.btnRegisterRegister.setOnClickListener {
            if (binding.etRegisterName.text.isNullOrEmpty()){
                ToastUtil.showShortMessage(getString(R.string.hint_no_name), this)
                return@setOnClickListener
            }
            if (binding.etRegisterPwd1.text.isNullOrEmpty()){
                ToastUtil.showShortMessage(getString(R.string.hint_no_password),this)
                return@setOnClickListener
            }
            if (binding.etRegisterPwd2.text.isNullOrEmpty()){
                ToastUtil.showShortMessage(getString(R.string.hint_no_password),this)
                return@setOnClickListener
            }
            if (binding.etRegisterEmail.text.isNullOrEmpty()){
                ToastUtil.showShortMessage(getString(R.string.hint_no_email),this)
                return@setOnClickListener
            }
            if (binding.etRegisterPwd1.text.toString() != binding.etRegisterPwd2.text.toString()){
                ToastUtil.showShortMessage(getString(R.string.hint_pwd_different), this)
                return@setOnClickListener
            }
            if(!binding.etRegisterEmail.text.contains('@')){
                ToastUtil.showShortMessage(getString(R.string.hint_invalid_email), this)
                return@setOnClickListener
            }
            userData = mutableListOf(uniqueId, binding.etRegisterName.text.toString(), binding.etRegisterPwd1.text.toString(), binding.etRegisterEmail.text.toString())
            viewModel.addFirebaseUser(userData[0], userData[1], userData[2], userData[3])

        }

        viewModel.getState().observe(this){
            when(it){
                ConstantUtil.REGISTER_SUCCESS -> {
                    LoginStatusUtil.setUser(userData[0], userData[1], userData[2], userData[3])
                    startActivity(
                        Intent(
                            this,
                            ResultActivity::class.java
                        ).putExtra(ConstantUtil.STRING_RESULT_ACTIVITY, ConstantUtil.RESULT_CORRECT)
                    )
                }
                ConstantUtil.REGISTER_DEFAULT -> {
                    ToastUtil.showLongMessage(getString(R.string.register_fail), this)
                }
                ConstantUtil.REGISTER_DUPLICATE_ID -> {
                    ToastUtil.showShortMessage(getString(R.string.register_duplicate_id), this)
                    finish()
                }
                ConstantUtil.REGISTER_DUPLICATE_NAME -> {
                    ToastUtil.showShortMessage(getString(R.string.register_duplicate_name), this)
                }
                else -> {
                    ToastUtil.showLongMessage(getString(R.string.register_fail), this)
                }
            }
        }

    }
}