package com.grindewald1900.shereats.view.transformer

import android.animation.Animator
import android.graphics.drawable.Drawable
import android.transition.Transition
import android.transition.TransitionValues
import android.view.ViewGroup

class PointExplodeTransition: Transition() {
    private val BACKGROUND_KEY = "shereats.PointExplodeTransition:background"
    override fun captureStartValues(p0: TransitionValues?) {
        captureValues(p0)
    }

    override fun captureEndValues(p0: TransitionValues?) {
        captureValues(p0)
    }

    override fun createAnimator(
        sceneRoot: ViewGroup?,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator? {
        if (startValues == null || endValues == null){
            return null
        }
        val startDrawable = startValues.values[BACKGROUND_KEY] as Drawable
        val endDrawable = endValues.values[BACKGROUND_KEY] as Drawable
        return null
    }

    private fun captureValues(transitionValues: TransitionValues?){
        if (transitionValues != null) {
            transitionValues.values[BACKGROUND_KEY] = transitionValues.view.background
        }
    }
}