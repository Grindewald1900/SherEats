package com.example.shereats.view.custom

import android.animation.Animator
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import com.example.shereats.R
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.ImageUtil
import com.example.shereats.view.activity.MainActivity
import com.example.shereats.view.activity.SearchActivity
import com.example.shereats.view.activity.SearchFriendActivity
import kotlin.math.abs

/**
 * A draggable floating action button
 */
class RoundFab : androidx.appcompat.widget.AppCompatImageView{
    companion object {
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
    private var fabSize = 0

    // The position when event down
    private var downX = 0f
    private var downY = 0f

    private var destination: Int = ConstantUtil.ACTIVITY_SEARCH_DISH


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
    private fun obtainAttributes(context: Context, attrs: AttributeSet, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundFab, defStyle, 0)
        if (typedArray.hasValue(R.styleable.RoundFab_fab_type)) {
            currentMode = typedArray.getInt(R.styleable.RoundFab_fab_type, MODE_CIRCLE)
        }
        if (typedArray.hasValue(R.styleable.RoundFab_fab_corner_radius)) {
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
        if (currentMode == MODE_CIRCLE) {
            val size = measuredHeight.coerceAtMost(measuredWidth)
            setMeasuredDimension(size, size)
        }
        screenWidth = ImageUtil.getScreenWidth(mContext)
        screenHeight = ImageUtil.getScreenHeight(context)
        fabWidth = measuredWidth
        fabHeight = measuredHeight
        if (fabHeight == fabWidth) {
            fabSize = fabWidth/2
        }
    }


    /**
     * Deal with different motion event
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return true

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.rawX
                downY = event.rawY
                if (ConstantUtil.DEBUG_MODE){
                    Log.d("LogRoundFab", "ACTION_DOWN")
                    Log.d("LogRoundFab", "x ${event.rawX.toString()}")
                    Log.d("LogRoundFab", "y ${event.rawY.toString()}")
                }
            }
            MotionEvent.ACTION_UP -> {
                if(ConstantUtil.DEBUG_MODE){
                    Log.d("LogRoundFab", "ACTION_UP")
                    Log.d("LogRoundFab", "x ${event.rawX.toString()}")
                    Log.d("LogRoundFab", "y ${event.rawY.toString()}")
                }
                // Move the FAB to the proper position, according to the Mode
                val position = ImageUtil.getPositionByMode(
                    event.rawX.toInt(),
                    event.rawY.toInt(),
                    fabWidth,
                    fabHeight,
                    ConstantUtil.MODE_EDGE,
                    mContext
                )
                this.layout(position[0], position[1], position[2], position[3])
                if(abs(downX - event.rawX) < 10 && abs(downY - event.rawY) < 10){
                    doClick()
                }

            }
            MotionEvent.ACTION_MOVE -> {
                var left: Int; var right: Int; var top: Int; var bottom: Int
                left = event.rawX.toInt()
                right = left + fabWidth
                top = event.rawY.toInt()
                bottom = top + fabHeight
                // Avoid FAB out of the screen area
                if (left < 0) {
                    left = 0
                    right = left + fabWidth
                } else if (right > screenWidth + fabWidth) {
                    right = screenWidth
                    left = right - fabWidth
                }
                if (top < 0) {
                    top = 0
                    bottom = top + fabHeight
                } else if (bottom > screenHeight + fabHeight) {
                    bottom = screenHeight
                    top = bottom - fabHeight
                }
                // Move the view a little bit to top left, which means the touching point is at the center of FAB
                this.layout(left-fabSize , top-fabHeight, right-fabSize, bottom-fabHeight)
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        if (drawable.intrinsicWidth == 0 || drawable.intrinsicHeight == 0 || drawable == null) {
            return
        }
        if (imageMatrix == null && paddingTop == 0 && paddingLeft == 0) {
            drawable.draw(canvas)
        } else {
            val saveCount = canvas.saveCount
            canvas.save()
            canvas.translate(paddingLeft.toFloat(), paddingTop.toFloat())
            if (currentMode == MODE_CIRCLE) {
                val bitmap = ImageUtil.drawableToBitmap(drawable, imageMatrix, width, height)
                mPaint.shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
                canvas.drawCircle(width / 2f, height / 2f, width / 2f, mPaint)
            } else if (currentMode == MODE_ROUND_CORNER) {
                val bitmap = ImageUtil.drawableToBitmap(drawable, imageMatrix, width, height)
                mPaint.shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
                canvas.drawRoundRect(RectF(paddingLeft.toFloat(), paddingTop.toFloat(), (width - paddingRight).toFloat(), (height - paddingBottom).toFloat()), cornerRadius, cornerRadius, mPaint)
            } else {
                if (imageMatrix != null) {
                    canvas.concat(imageMatrix)
                    drawable.draw(canvas)
                }
            }
            canvas.restoreToCount(saveCount)

        }

    }

    fun setDestination(dest: Int){
        destination = dest
    }

    private fun doClick(){
        val animator = this.animate().alpha(0f).scaleX(0f).scaleY(0f).setDuration(300)
        animator.setListener(object: Animator.AnimatorListener{
            override fun onAnimationEnd(p0: Animator?) {
                val intent: Intent = when(destination){
                    ConstantUtil.ACTIVITY_SEARCH_DISH -> {
                        Intent(mContext, SearchActivity::class.java)
                    }
                    ConstantUtil.ACTIVITY_SEARCH_FRIEND -> {
                        Intent(mContext, SearchFriendActivity::class.java)
                    }
                    else -> {
                        Intent(mContext, SearchActivity::class.java)
                    }
                }
                mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mContext as MainActivity).toBundle())
                animator.setListener(null)
                animator.alpha(1f).scaleX(1f).scaleY(1f).setDuration(100).start()
            }
            override fun onAnimationStart(p0: Animator?) {}
            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationRepeat(p0: Animator?) {}

        }).start()
    }

}