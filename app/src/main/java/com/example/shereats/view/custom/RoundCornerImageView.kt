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

/**
 * TODO: document your custom view class.
 */
open class RoundCornerImageView(context: Context, attrs: AttributeSet?): androidx.appcompat.widget.AppCompatImageView(context, attrs) {
    private var mGradientDrawable: GradientDrawable? = null
    private var paint: Paint? = null
    private val bkColor = Color.WHITE
    private var cornerRadius: Float
    init {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.RoundCornerImageView)
        cornerRadius = attr.getFloat(R.styleable.RoundCornerImageView_mCornerRadius, 20f)
        paint = Paint()
    }


    private fun initView(){
        mGradientDrawable = GradientDrawable()
        mGradientDrawable!!.cornerRadius = cornerRadius
        background = mGradientDrawable
    }

    override fun onDraw(canvas: Canvas?) {
        val mDrawable: Drawable? = drawable
        if(null != mDrawable){
            val bitmap: Bitmap = mDrawable.toBitmap()
            val b: Bitmap =getRoundBitmap(bitmap, cornerRadius)
            val rectSrc = Rect(0,0,b.width,b.height)
            val rectDest = Rect(0,0,width, height)
            paint!!.reset()
            canvas!!.drawBitmap(b, rectSrc, rectDest , paint)
        }else{
            super.onDraw(canvas)
        }

    }

    private fun getRoundBitmap(bitmap: Bitmap, radius: Float): Bitmap {
        var output: Bitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        var canvas: Canvas = Canvas(output)
        val rect: Rect = Rect(0, 0, bitmap.width, bitmap.height)
        val rectF: RectF = RectF(rect)
        paint!!.isAntiAlias = true
        paint?.let { canvas.drawRoundRect(rectF, radius, radius, it) }
        paint!!.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
        canvas.drawBitmap(bitmap, rect, rect, paint)
        return output
    }
}