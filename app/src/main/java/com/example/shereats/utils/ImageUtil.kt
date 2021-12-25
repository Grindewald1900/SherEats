package com.example.shereats.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.*
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

        /**
         * get width of screen in pixel
         */
        fun getScreenWidth(context: Context): Int {
            val width: Int
            val manager = context
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = manager.defaultDisplay
            width = display.width
            return width
        }

        /**
         * get height of screen in pixel
         */
        fun getScreenHeight(context: Context): Int {
            val height: Int
            val manager = context
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = manager.defaultDisplay
            height = display.height
            return height
        }

        /**
         * Convert dp to pixel
         */
        fun dpToPx(dp: Float): Float{
            val density = Resources.getSystem().displayMetrics.density
            return dp*density
        }

        /**
         * Return a position(left, top, right, bottom) of a image when action up.
         * @param x: x position of the image
         * @param y: y position of the image
         * @param viewWidth: width of the image
         * @param viewHeight: height of the image
         * @param mode: MODE_EDGE- go to the nearest edge, MODE_POINT- go to a certain point on the screen
         */
        fun getPositionByMode(x: Int, y: Int, viewWidth: Int, viewHeight: Int, mode: Int, context: Context): List<Int>{
            val width = getScreenWidth(context)
            val height = getScreenHeight(context)
            // By default, go to the middle of right side.
            var result: MutableList<Int> = mutableListOf(width - viewWidth, height * 0.5.toInt(), width,  height * 0.5.toInt() + viewHeight)
            when(mode){
                ConstantUtil.MODE_EDGE -> {
                    result = if(x > width/2){
                        mutableListOf(width - viewWidth, y - viewHeight/2, width, y + viewHeight/2)
                    }else{
                        mutableListOf(0, y - viewHeight/2, viewWidth, y + viewHeight/2)
                    }
                }
                ConstantUtil.MODE_POINT -> {
                    val top = height * 0.6.toInt()
                    result = mutableListOf(width - viewWidth, top, width, top + viewHeight)
                }
            }
            // If out of bottom bound, go to bottom.
            if (result[3] > height){
                result[3] = height
                result[1] = height - viewHeight
            }
            return result
        }

        /**
         * Return a circle bitmap with given size and resource
         * @param source: source bitmap
         * @param radius: the radius of the circle
         * @param size: the diameter of the circle
         */
        fun getCircleImage(source: Bitmap, radius: Float, x: Int, y: Int, size: Int): Bitmap{
            val paint = Paint(Paint.ANTI_ALIAS_FLAG)
            val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            val scaledSource = Bitmap.createScaledBitmap(source, size, size, true)
            canvas.drawCircle(radius, radius, radius, paint)
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
            canvas.drawBitmap(scaledSource,0f,0f,paint)
            return bitmap
        }

    }
}