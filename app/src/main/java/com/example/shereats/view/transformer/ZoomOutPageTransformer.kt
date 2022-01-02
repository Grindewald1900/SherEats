package com.example.shereats.view.transformer

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.shereats.utils.ConstantUtil
import kotlin.math.abs
import kotlin.math.max


/**
 * Created by Yee on 2021-12-17.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class ZoomOutPageTransformer: ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.apply {
            when{
                position <= 2 -> {
                    // From right or left side, the view keep the scale of MIN_TRANSFORM_SCALE, unless it's near the center(position 0)
                    val scaleFactor = max(ConstantUtil.MIN_TRANSFORM_SCALE, 1 - abs(position))
                    val alphaFactor = max(ConstantUtil.MIN_TRANSFORM_ALPHA, 1 - abs(position))
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                    alpha = alphaFactor
                }
                else -> {}
            }
        }
    }
}