package com.example.shereats.view.custom

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import com.example.shereats.R
import com.example.shereats.utils.ImageUtil
import kotlin.math.abs

/**
 * A draggable floating action button
 */
class RoundFab : androidx.appcompat.widget.AppCompatImageView {
    companion object{
        const val MODE_SQUARE = 0
        const val MODE_CIRCLE = 1
        const val MODE_ROUND_CORNER = 2
    }

    // Set FAB as circle by default
    private lateinit var mContext: Context
    private var currentMode = MODE_CIRCLE
    private var cornerRadius = 0f
    private lateinit var mPaint: Paint
    // The size of screen
    private var screenWidth = 0
    private var screenHeight = 0
    // The size of Fab
    private var fabWidth = 0
    private var fabHeight = 0

    constructor(context: Context) : super(context) {
        initView(null, 0, context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(attrs, 0, context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        obtainAttributes(context, attrs, defStyle)
        initView(attrs, defStyle, context)
    }

    /**
     * Retrieve the custom attributes
     */
    private fun obtainAttributes(context: Context, attrs: AttributeSet, defStyle: Int){
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundFab, defStyle,0)
        if(typedArray.hasValue(R.styleable.RoundFab_fab_type)){
            currentMode = typedArray.getInt(R.styleable.RoundFab_fab_type, MODE_CIRCLE)
        }
        if (typedArray.hasValue(R.styleable.RoundFab_fab_corner_radius)){
            cornerRadius = typedArray.getFloat(R.styleable.RoundFab_fab_corner_radius, 20f)
        }
        typedArray.recycle()
    }
    private fun initView(attrs: AttributeSet?, defStyle: Int, context: Context) {
        mContext = context
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    }



    /**
     * Set the width and height of the view
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // requested width and mode
        if (currentMode == MODE_CIRCLE){
            val size = measuredHeight.coerceAtMost(measuredWidth)
            setMeasuredDimension(size, size)
        }
        screenWidth = ImageUtil.getScreenWidth(mContext)
        screenHeight = ImageUtil.getScreenHeight(context)
        fabWidth = measuredWidth
        fabHeight = measuredHeight

    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return true
        var downX = 0f
        var downY = 0f

        val y = event.y

        when(event.action){
            MotionEvent.ACTION_DOWN ->{
                downX = event.x
                downY = event.y
            }
            MotionEvent.ACTION_MOVE ->{
                var left: Int
                var right: Int
                var top: Int
                var bottom: Int
                // Moved distance
                val distanceX = event.x - downX
                val distanceY = event.y - downY
                if (abs(distanceX) > 10 || abs(distanceY) > 10){
                    left = getLeft() + distanceX.toInt()
                    right = left + fabWidth
                    top = getTop() + distanceY.toInt()
                    bottom = top + height
                    if(left < 0){
                        left = 0
                        right = left + fabWidth
                    }else if(right > screenWidth){
                        right = screenWidth
                        left = right - fabWidth
                    }
                    if (top < 0){
                        top = 0
                        bottom = top + fabHeight
                    }else if(bottom > screenHeight){
                        bottom = screenHeight
                        top = bottom - fabHeight
                    }
                    this.layout(left, top, right, bottom)
                }
            }
        }
        return true
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (drawable.intrinsicWidth == 0 || drawable.intrinsicHeight == 0 || drawable == null){
            return
        }
        if (imageMatrix == null && paddingTop == 0 && paddingLeft == 0){
            drawable.draw(canvas)
        }else{
            val saveCount = canvas.saveCount
            canvas.save()
            canvas.translate(paddingLeft.toFloat(), paddingTop.toFloat())
            if (currentMode == MODE_CIRCLE){
                val bitmap = ImageUtil.drawableToBitmap(drawable, imageMatrix, width, height)
                mPaint.shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
                canvas.drawCircle(width/2f, height/2f, width/2f, mPaint)
            }else if (currentMode == MODE_ROUND_CORNER){
                val bitmap = ImageUtil.drawableToBitmap(drawable, imageMatrix, width, height)
                mPaint.shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
                canvas.drawRoundRect(RectF(paddingLeft.toFloat(), paddingTop.toFloat(), (width - paddingRight).toFloat(), (height - paddingBottom).toFloat()), cornerRadius, cornerRadius, mPaint)
            }else{
                if (imageMatrix != null){
                    canvas.concat(imageMatrix)
                    drawable.draw(canvas)
                }
            }
            canvas.restoreToCount(saveCount)

        }

    }
}