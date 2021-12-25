package com.example.shereats.view.custom

import android.content.Context
import android.graphics.*
import android.graphics.drawable.GradientDrawable
import android.os.Debug
import android.util.AttributeSet
import android.widget.ImageView
import com.example.shereats.R
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.ImageUtil
import kotlin.math.min


/**
 * Created by Yee on 2021-12-20.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class RoundImageView(context: Context, attrs: AttributeSet?): androidx.appcompat.widget.AppCompatImageView(context, attrs) {
    private lateinit var mGradientDrawable: GradientDrawable
    private lateinit var mBitmap: Bitmap
    private var mSrc: Bitmap
    private var mWidth: Int = 100
    private var mHeight: Int = 100
    private var mImageSize = 100
    private var mBorderSize = 100

    private var mBorderPaint: Paint
    private var mBorderColor: Int
    private var mBorderWidth: Float
    init {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView)
        // Attributes related to border
        mBorderColor = attr.getColor(R.styleable.RoundImageView_image_border_color, Color.BLACK)
        mBorderWidth = attr.getDimension(R.styleable.RoundImageView_border_width, 0f)
        mBorderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mBorderPaint.style = Paint.Style.STROKE
        mBorderPaint.color = mBorderColor
        mBorderPaint.strokeWidth = mBorderWidth

        // The source image
        mSrc = BitmapFactory.decodeResource(resources, attr.getResourceId(R.styleable.RoundImageView_image_border_src, R.drawable.img_portrait))
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measuredWidth
        mHeight = measuredHeight
        if (mBorderWidth > 0){
            mBorderSize = min(mWidth, mHeight)
            mImageSize = mBorderSize - mBorderWidth.toInt()*2
        }else{
            mImageSize = min(mWidth, mHeight)
        }
        mBitmap = ImageUtil.getCircleImage(mSrc, mImageSize/2f, mImageSize/2, mImageSize/2, mImageSize)
    }



     override fun onDraw(canvas: Canvas){
         super.onDraw(canvas)
         canvas.drawBitmap(mBitmap, mBorderWidth, mBorderWidth, null)
         // If border width, draw a circle outside of the image
         if (mBorderWidth > 0){
             canvas.drawCircle(mBorderSize/2f, mBorderSize/2f, (mBorderSize - mBorderWidth)/2f , mBorderPaint)
         }
         if (ConstantUtil.DEBUG_MODE){
             canvas.drawText(mBorderWidth.toString(), 0f, 0f, Paint())
         }
    }

}