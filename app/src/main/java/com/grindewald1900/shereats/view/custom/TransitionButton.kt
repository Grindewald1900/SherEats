package com.grindewald1900.shereats.view.custom

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.animation.Animation
import android.view.animation.CycleInterpolator
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatButton
import com.grindewald1900.shereats.R
import com.grindewald1900.shereats.utils.WindowUtils

class TransitionButton(private val mContext: Context, attrs: AttributeSet): AppCompatButton(mContext, attrs) {
    private lateinit var progressCircularAnimatedDrawable: CircularAnimationDrawable
    private val WIDTH_ANIMATION_DURATION = 200
    private val SCALE_ANIMATION_DURATION = 300
    private val SHAKE_ANIMATION_DURATION = 500
    private val COLOR_ANIMATION_DURATION = 350
    private val messageAnimationDuration = COLOR_ANIMATION_DURATION * 10
    private var isMorphingInProgress = false
    private var currentState = State.IDLE
    private var defaultColor: Int
    private var loaderColor: Int
    private var initialWidth: Int = 0
    private var initialHeight: Int = 0
    private var initialText: String? = null
    var isClicked: Boolean = false

    init {
        val attr = mContext.obtainStyledAttributes(attrs, R.styleable.TransitionButton)
        defaultColor = attr.getColor(R.styleable.TransitionButton_defaultColor, resources.getColor(R.color.color_error))
        loaderColor = attr.getColor(R.styleable.TransitionButton_loaderColor, resources.getColor(R.color.color_loader))
        backgroundTintList = ColorStateList.valueOf(defaultColor)
        background = AppCompatResources.getDrawable(mContext, R.drawable.ripple_transition_button)
        attr.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawIndeterminateProgress(canvas)
    }

    fun startAnimation(){
        currentState = State.PROGRESS
        isMorphingInProgress = true

        initialWidth = width
        initialHeight = height
        initialText = text.toString()
        text = null
        isClickable = false

        startWidthAnimation(initialHeight, object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationCancel(animation)
                isMorphingInProgress = false
            }
        })
    }

    fun stopAnimation(stopAnimationStyle: StopAnimationStyle, onAnimationStopEndListener: OnAnimationStopEndListener){
        when(stopAnimationStyle){
            StopAnimationStyle.SHAKE -> {
                resetAnimation(onAnimationStopEndListener, true)
            }

            StopAnimationStyle.EXPAND -> {
                currentState = State.TRANSITION

                startScaleAnimation(object: AnimationListenerAdapter(){
                    override fun onAnimationEnd(p0: Animation?) {
                        super.onAnimationEnd(p0)
                        isClicked = true
                        onAnimationStopEndListener.onAnimationStopEnd()
                    }
                })
            }
        }
    }

    /**
     * Reset the WidthAnimation and ScaleAnimation, when get back from NewActivity.
     * This method could be call from {@see onResume()} of Activity who hold this view.
     *
     * @param onAnimationStopEndListener: could be null if there's nothing to do after the SHAKE
     * @param isShake: Set this value true if you need a SHAKE after resize the button.
     */
    fun resetAnimation(onAnimationStopEndListener: OnAnimationStopEndListener?, isShake: Boolean) {
        startWidthAnimation(initialWidth, object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                text = initialText
                isClickable = true
                if (isShake) {
                    currentState = State.ERROR
                    startShakeAnimation(object : AnimationListenerAdapter() {
                        override fun onAnimationEnd(animation: Animation?) {
                            onAnimationStopEndListener?.onAnimationStopEnd()
                        }
                    })
                } else {
                    isClicked = false
                    currentState = State.IDLE
                }
            }
        })
        // Clear scale animation
        clearAnimation()
    }

    private fun startWidthAnimation(to: Int, onAnimationEnd: AnimatorListenerAdapter){
        startWidthAnimation(width, to, onAnimationEnd)
    }

    private fun startWidthAnimation(from: Int, to: Int, onAnimationEnd: AnimatorListenerAdapter){
        val widthAnimation = ValueAnimator.ofInt(from, to)
        /**
         * Adds a listener to the set of listeners that are sent update events through the life of an animation.
         * This method is called on all listeners for every frame of the animation, after the values for the animation have been calculated.
         */
        widthAnimation.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            val layoutParams = layoutParams
            layoutParams.width = value
            setLayoutParams(layoutParams)
        }

        val animatorSet = AnimatorSet()
        animatorSet.duration = WIDTH_ANIMATION_DURATION.toLong()
        animatorSet.playTogether(widthAnimation)
        animatorSet.addListener(onAnimationEnd)
        animatorSet.start()
    }

    private fun startShakeAnimation(animationListener: Animation.AnimationListener){
        val shake = TranslateAnimation(0f, 15f, 0f, 0f)
        shake.duration = SHAKE_ANIMATION_DURATION.toLong()
        // Sin/Cos Interpolator
        shake.interpolator = CycleInterpolator(4f)
        shake.setAnimationListener(animationListener)
        startAnimation(shake)
    }

    private fun startScaleAnimation(animationListener: Animation.AnimationListener){
        val ts = (WindowUtils.getHeight(mContext) / height * 2.1)
        val anim: Animation = ScaleAnimation(1f, ts.toFloat(), 1f, ts.toFloat(),
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        anim.duration = SCALE_ANIMATION_DURATION.toLong()
        anim.fillAfter = true
        anim.setAnimationListener(animationListener)
        startAnimation(anim)
    }

    private fun startColorAnimation(from: Int, to: Int){
        val anim = ValueAnimator.ofArgb(from, to)
        anim.addUpdateListener { valueAnimator ->
            backgroundTintList = ColorStateList.valueOf((valueAnimator.animatedValue as Int)!!)
            refreshDrawableState()
        }
        anim.duration = COLOR_ANIMATION_DURATION.toLong()
        anim.start()
    }

    private fun drawIndeterminateProgress(canvas: Canvas){
        if (currentState == State.PROGRESS && !isMorphingInProgress){
            if(!this::progressCircularAnimatedDrawable.isInitialized){
                initCircularDrawable()
            }else{
                if(!progressCircularAnimatedDrawable.isRunning){
                }else{
                    progressCircularAnimatedDrawable.draw(canvas)
                    invalidate()
                }
            }
        }
    }

    private fun initCircularDrawable(){
        val arcWidth = height / 18f
        val offset = (width - height) / 2
        val right = width - offset
        val top = 0
        val bottom = height
        progressCircularAnimatedDrawable = CircularAnimationDrawable(loaderColor, arcWidth)
        progressCircularAnimatedDrawable.setBounds(offset, top, right, bottom)
        progressCircularAnimatedDrawable.callback = this
        progressCircularAnimatedDrawable.start()
    }

    enum class State{
        PROGRESS, IDLE, ERROR, TRANSITION
    }
    enum class StopAnimationStyle{
        EXPAND, SHAKE
    }

    interface OnAnimationStopEndListener{
        fun onAnimationStopEnd()
    }

    /**
     * Animations are older versions of Animators. Animators where introduced in 3.0 to
     * help overcome some short-coming that Animations have.
     */
    open class AnimationListenerAdapter: Animation.AnimationListener{
        override fun onAnimationStart(p0: Animation?) {
        }

        override fun onAnimationEnd(p0: Animation?) {
        }

        override fun onAnimationRepeat(p0: Animation?) {
        }

    }
}