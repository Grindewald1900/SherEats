package com.example.shereats.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.shereats.R
import com.example.shereats.databinding.ActivityLoginBinding
import com.example.shereats.model.entity.SingletonUtil
import com.example.shereats.model.viewmodel.LoginViewModel
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.LoginStatusUtil
import com.example.shereats.utils.ToastUtil
import com.example.shereats.view.custom.TransitionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var mTransitionBtn: TransitionButton
    private var isClickedTransitionBtn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        initView()
    }

    private fun initView(){
        supportActionBar?.setDisplayShowTitleEnabled(false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.tvLoginRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        viewModel.getState().observe(this){
            if (it == ConstantUtil.ACTIVITY_STATE_UI_THREAD){
                if (!startLogin()){
                    // We only deal with fail here, cause there's still a few steps before totally succeed
                    stopAnimeByIsSuccess(false)
                }
            }
        }
        viewModel.getUser().observe(this) {
            // If valid user, go back to main page, else go to register page
            if (it.isNotEmpty()) {
                LoginStatusUtil.mUser = it[0]
                stopAnimeByIsSuccess(true)
                ToastUtil.showShortMessage(getString(R.string.hint_login_success), this)
                finish()
            } else {
                stopAnimeByIsSuccess(false)
                ToastUtil.showShortMessage(getString(R.string.hint_invalid_user), this)
            }
        }
        initTransitionButton()
    }

    private fun initTransitionButton(){
        mTransitionBtn = binding.btnLoginLogin
        mTransitionBtn.setOnClickListener {
            mTransitionBtn.startAnimation()
            GlobalScope.launch {
                viewModel.setState(ConstantUtil.ACTIVITY_STATE_COROUTINE)
                delay(ConstantUtil.ANIMATION_DELAY)
                viewModel.setState(ConstantUtil.ACTIVITY_STATE_UI_THREAD)
            }
        }
    }

    private fun stopAnimeByIsSuccess(isSuccess: Boolean){
        if(isSuccess){
            mTransitionBtn.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND, object: TransitionButton.OnAnimationStopEndListener{
                override fun onAnimationStopEnd() {
                    isClickedTransitionBtn = true
                }
            })
        }else{
            mTransitionBtn.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, object: TransitionButton.OnAnimationStopEndListener{
                override fun onAnimationStopEnd() {}
            })
        }
    }
    private fun startLogin(): Boolean{
        if (binding.etLoginName.text.isNullOrEmpty()){
            ToastUtil.showShortMessage(getString(R.string.hint_no_name), this)
            return false
        }
        if(binding.etLoginPwd.text.isNullOrEmpty()){
            ToastUtil.showShortMessage(getString(R.string.hint_no_password), this)
            return false
        }
        viewModel.setUser(binding.etLoginName.text.toString(), binding.etLoginPwd.text.toString())
        return true
    }
}