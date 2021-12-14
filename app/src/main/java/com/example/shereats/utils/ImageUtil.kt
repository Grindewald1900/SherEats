package com.example.shereats.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets
import android.view.WindowManager


/**
 * Created by Yee on 2021-12-13.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class ImageUtil {
    companion object{
        /**
         * convert drawable to bitmap
         */
        fun drawableToBitmap(drawable: Drawable, imageMatrix: Matrix, width: Int, height: Int): Bitmap{
            if (drawable == null){
                return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            }
            val bitmap =  Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            if (imageMatrix != null){
                canvas.concat(imageMatrix)
            }
            drawable.draw(canvas)
            return bitmap
        }

//        fun getScreenWidth(activity: Activity): Int{
//            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
//                val windowMetrics = activity.windowManager.currentWindowMetrics
//                val insets = windowMetrics.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
//                windowMetrics.bounds.width() - insets.left - insets.right
//            }else{
//                val displayMatrix = DisplayMetrics()
//                activity.windowManager.defaultDisplay.getMetrics((displayMatrix))
//                displayMatrix.widthPixels
//            }
//        }
//
//        fun getScreenHeight(activity: Activity): Int{
//            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
//                val windowMetrics = activity.windowManager.currentWindowMetrics
//                val insets = windowMetrics.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
//                windowMetrics.bounds.height() - insets.top - insets.bottom
//            }else{
//                val displayMatrix = DisplayMetrics()
//                activity.windowManager.defaultDisplay.getMetrics((displayMatrix))
//                displayMatrix.heightPixels
//            }
//        }

        fun getScreenWidth(context: Context): Int {
            val width: Int
            val manager = context
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = manager.defaultDisplay
            width = display.width
            return width
        }

        fun getScreenHeight(context: Context): Int {
            val height: Int
            val manager = context
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = manager.defaultDisplay
            height = display.height
            return height
        }
    }
}