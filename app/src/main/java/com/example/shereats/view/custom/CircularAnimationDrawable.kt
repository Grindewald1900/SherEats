package com.example.shereats.view.custom

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.*
import android.graphics.drawable.AnimationDrawable
import android.util.Log
import android.util.Property
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator

class CircularAnimationDrawable(private val mColor: Int, private val borderWidth: Float): AnimationDrawable(){
    private val ANGLE_ANIMATOR_DURATION = 2000
    private val SWEEP_ANIMATOR_DURATION = 600
    private val MIN_SWEEP_ANGLE = 30
    private val LINEAR_INTERPOLATOR = LinearInterpolator()
    private val SWEEP_INTERPOLATOR = DecelerateInterpolator()
    private lateinit var mObjectAnimatorAngle: ObjectAnimator
    private lateinit var mObjectAnimatorSweep: ObjectAnimator
    private var mBound: RectF = RectF()
    private var mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mCurrentGlobalAngle: Float = 0f
    private var mCurrentSweepAngle: Float = 0f
    private var mCurrentGlobalAngleOffset: Float = 0f
    private var mModeAppearing: Boolean = false
    private var mIsRunning = false
    private var mAngleProperty: Property<CircularAnimationDrawable, Float> = object: Property<CircularAnimationDrawable, Float>(Float::class.java, "angle"){
        override fun get(p0: CircularAnimationDrawable): Float {
            return p0.getCurrentGlobalAngle()
        }
        override fun set(p0: CircularAnimationDrawable, value: Float) {
            p0.setCurrentGlobalAngle(value)
        }
    }
    private var mSweepProperty: Property<CircularAnimationDrawable, Float> = object: Property<CircularAnimationDrawable, Float>(Float::class.java, "arc"){
        override fun get(p0: CircularAnimationDrawable): Float {
            return p0.getCurrentSweepAngle()
        }

        override fun set(p0: CircularAnimationDrawable, value: Float) {
            p0.setCurrentSweepAngle(value)
        }
    }

    /**
     * Initialize
     */
    init {
        mPaint.apply {
            style = Paint.Style.STROKE
            strokeWidth = borderWidth
            color = mColor
        }
        setupAnimations()
    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSPARENT
    }
    override fun draw(canvas: Canvas) {
        // ↑ indicates -90° while ↓ indicates 90°
        var startAngle = mCurrentGlobalAngle - mCurrentGlobalAngleOffset
        // The angle to be swept
        var sweepAngle = mCurrentSweepAngle
        if (!mModeAppearing){
            startAngle = sweepAngle + sweepAngle
            sweepAngle = 360 - sweepAngle - MIN_SWEEP_ANGLE
        }else{
            sweepAngle += MIN_SWEEP_ANGLE
        }
        canvas.drawArc(mBound, startAngle, sweepAngle, false, mPaint)
    }

    override fun isRunning(): Boolean {
        return mIsRunning
    }

    /**
     * Start the animation
     */
    override fun start() {
        if (mIsRunning){
            return
        }
        mIsRunning = true
        mObjectAnimatorAngle.start()
        mObjectAnimatorSweep.start()
        invalidateSelf()
    }

    /**
     * Stop the animation
     */
    override fun stop() {
        if (!mIsRunning){
            return
        }
        mIsRunning = false
        mObjectAnimatorAngle.cancel()
        mObjectAnimatorSweep.cancel()
        invalidateSelf()
    }

    /**
     * Override this method to to change appearance if you vary based on the bounds.
     */
    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        mBound.left = bounds.left + borderWidth / 2f + 0.5f
        mBound.right = bounds.right - borderWidth / 2f - 0.5f
        mBound.top = bounds.top + borderWidth / 2f + 0.5f
        mBound.bottom = bounds.bottom - borderWidth / 2f - 0.5f
    }

    private fun setupAnimations(){
        /**
         * When the animation reaches the end and repeatCount is INFINITE or a positive value, the animation restarts from the beginning.
         */
        mObjectAnimatorAngle = ObjectAnimator.ofFloat(this, mAngleProperty, 360f)
        mObjectAnimatorAngle.apply {
            interpolator = LINEAR_INTERPOLATOR
            repeatMode = ValueAnimator.RESTART
            repeatCount = ValueAnimator.INFINITE
            duration = ANGLE_ANIMATOR_DURATION.toLong()
        }

        mObjectAnimatorSweep = ObjectAnimator.ofFloat(this, mSweepProperty, 360f - MIN_SWEEP_ANGLE * 2)
        mObjectAnimatorSweep.apply {
            interpolator = SWEEP_INTERPOLATOR
            repeatMode = ValueAnimator.RESTART
            repeatCount = ValueAnimator.INFINITE
            duration = SWEEP_ANIMATOR_DURATION.toLong()
            addListener(object: Animator.AnimatorListener{
                override fun onAnimationStart(p0: Animator?) {
                }

                override fun onAnimationEnd(p0: Animator?) {
                }

                override fun onAnimationCancel(p0: Animator?) {
                }

                override fun onAnimationRepeat(p0: Animator?) {
                    toggleAppearingMode()
                }

            })
        }


    }

    private fun toggleAppearingMode(){
        mModeAppearing = !mModeAppearing
        if(mModeAppearing){
            mCurrentGlobalAngleOffset = (mCurrentGlobalAngleOffset + MIN_SWEEP_ANGLE * 2) % 360

        }
    }

    private fun setCurrentSweepAngle(currentSweepAngle: Float){
        mCurrentSweepAngle = currentSweepAngle
        invalidateSelf()
    }

    private fun setCurrentGlobalAngle(currentGlobalAngle: Float){
        mCurrentGlobalAngle = currentGlobalAngle
        invalidateSelf()
    }

    private fun getCurrentGlobalAngle(): Float{
        return mCurrentGlobalAngle
    }

    private fun getCurrentSweepAngle(): Float{
        return mCurrentSweepAngle
    }
}