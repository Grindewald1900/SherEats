package com.example.shereats.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

class WindowUtils {
    companion object {
        var displayMetrics: DisplayMetrics? = null
        fun getWidth(context: Context): Int {
            setupDisplayMetrics(context)
            return displayMetrics!!.widthPixels
        }

        fun getHeight(context: Context): Int {
            setupDisplayMetrics(context)
            return displayMetrics!!.heightPixels
        }
        private fun setupDisplayMetrics(context: Context) {
            if (displayMetrics == null)
                displayMetrics = DisplayMetrics()
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            display.getMetrics(displayMetrics)
        }

    }

}