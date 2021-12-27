package com.example.shereats.view.custom

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.graphics.drawable.toBitmap
import com.example.shereats.R
import com.example.shereats.utils.ImageUtil

/**
 * Round Corner ImageView
 */
open class RoundCornerImageView(context: Context, attrs: AttributeSet?): androidx.appcompat.widget.AppCompatImageView(context, attrs) {
    private var paint: Paint? = null
    private var cornerRadius: Float
    private lateinit var mBitmap: Bitmap
    init {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.RoundCornerImageView)
        val dpRadius = attr.getFloat(R.styleable.RoundCornerImageView_mCornerRadius, 20f)
        cornerRadius = ImageUtil.dpToPx(dpRadius)
        paint = Paint()
    }

    override fun onDraw(canvas: Canvas?) {
        val mDrawable = drawable
        if(null != mDrawable){
            mBitmap = mDrawable.toBitmap()
            val b: Bitmap =getRoundBitmap(mBitmap, cornerRadius)
            val rectSrc = Rect(0,0,b.width,b.height)
            val rectDest = Rect(0,0,width, height)
            paint!!.reset()
            canvas!!.drawBitmap(b, rectSrc, rectDest , paint)
        }else{
            super.onDraw(canvas)
        }

    }

    private fun getRoundBitmap(bitmap: Bitmap, radius: Float): Bitmap {
        val output: Bitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        val rectF = RectF(rect)
        paint!!.isAntiAlias = true
        paint?.let {
            canvas.drawRoundRect(rectF, radius, radius, it)
            canvas.drawRect(0f, height-radius, radius, height.toFloat(), it)
            canvas.drawRect(rect.right-radius, rect.bottom-radius, rect.right.toFloat(), rect.bottom.toFloat(), it)
        }
        paint!!.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)
        return output
    }

    fun setImage(bitmap: Bitmap){
        mBitmap = bitmap
        invalidate()
    }
}