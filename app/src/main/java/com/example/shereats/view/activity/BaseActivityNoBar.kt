package com.example.shereats.view.activity

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.shereats.R
import com.example.shereats.utils.ConstantUtil

open class BaseActivityNoBar: FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        initTheme()
        super.onCreate(savedInstanceState)
    }

//    private fun initTheme(){
//        val sharedTheme = getSharedPreferences(ConstantUtil.SHARED_THEME, Context.MODE_PRIVATE)
//        when(sharedTheme.getString(ConstantUtil.CURRENT_THEME, "1")){
//            "1" -> {theme.applyStyle(R.style.AppTheme1, true)}
//            "2" -> {theme.applyStyle(R.style.AppTheme2, true)}
//            "3" -> {theme.applyStyle(R.style.AppTheme3, true)}
//            "4" -> {theme.applyStyle(R.style.AppTheme4, true)}
//            "5" -> {theme.applyStyle(R.style.AppTheme5, true)}
//            else -> {theme.applyStyle(R.style.AppTheme1, true)}
//        }
//    }
}