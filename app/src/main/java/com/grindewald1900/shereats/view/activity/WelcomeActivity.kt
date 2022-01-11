package com.grindewald1900.shereats.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.grindewald1900.shereats.R
import com.grindewald1900.shereats.utils.ConstantUtil
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