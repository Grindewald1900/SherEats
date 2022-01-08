package com.example.shereats.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.shereats.R
import com.example.shereats.utils.ConstantUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WelcomeActivity : BaseActivityBar() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        initView()
    }

    private fun initView(){
        lifecycleScope.launch {
            delay(500)
            withContext(Dispatchers.Main){
                val intent = Intent(this@WelcomeActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun initSharedPreference(){
        val sharedTheme = getSharedPreferences(ConstantUtil.SHARED_THEME, Context.MODE_PRIVATE)
    }
}