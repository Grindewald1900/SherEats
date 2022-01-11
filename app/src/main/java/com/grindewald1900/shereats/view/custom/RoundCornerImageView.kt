package com.grindewald1900.shereats.view.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.core.graphics.drawable.toBitmap
import com.grindewald1900.shereats.R
import com.grindewald1900.shereats.utils.ImageUtil

/**
 * Round Corner ImageView
 */
open class RoundCornerImageView(context: Context, attrs: AttributeSet?): androidx.appcompat.widget.AppCompatImageView(context, attrs) {
    private var paint: Paint? = null
    private var cornerRadius: Float
    private lateinit var mBitmap: Bitmap
    // rectSrc: the area for the bitmap, rectDest: the location of bitmap
    private lateinit var rectSrc: Rect
    private lateinit var rectDest: Rect
    private var isLeftTop: Boolean
    private var isLeftBottom: Boolean
    private var isRightTop: Boolean
    private var isRightBottom: Boolean

    init {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.RoundCornerImageView)
        val dpRadius = attr.getFloat(R.styleable.RoundCornerImageView_mCornerRadius, 20f)
        isLeftTop = attr.getBoolean(R.styleable.RoundCornerImageView_isLeftTop, true)
        isLeftBottom = attr.getBoolean(R.styleable.RoundCornerImageView_isLeftBottom, true)
        isRightTop = attr.getBoolean(R.styleable.RoundCornerImageView_isRightTop, true)
        isRightBottom = attr.getBoolean(R.styleable.RoundCornerImageView_isRightBottom, true)

        cornerRadius = ImageUtil.dpToPx(dpRadius)
        paint = Paint()
        attr.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        val mDrawable = drawable
        if(null != mDrawable){
            mBitmap = mDrawable.toBitmap()
            val b: Bitmap =getRoundBitmap(mBitmap, cornerRadius)
            rectSrc = Rect(0,0,b.width,b.height)
            rectDest = Rect(0,0,width, height)
            paint!!.reset()
            canvas!!.drawBitmap(b, rectSrc, rectDest, paint)
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
            if (!isLeftTop)
                canvas.drawRect(0f, 0f, radius, radius, it)
            if (!isLeftBottom)
                canvas.drawRect(0f, height-radius, radius, height.toFloat(), it)
            if(!isRightTop)
                canvas.drawRect(width-radius, 0f, width.toFloat(), radius, it)
            if (!isRightBottom)
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