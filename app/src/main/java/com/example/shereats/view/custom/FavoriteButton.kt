package com.example.shereats.view.custom

import android.animation.Animator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shereats.R
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.ImageUtil
import com.example.shereats.utils.ToastUtil

class FavoriteButton(private val mContext: Context, private val attrs: AttributeSet) : AppCompatImageView(mContext, attrs), View.OnClickListener {
    private var isFavorite: Boolean = false
    private var drawableTrue: Drawable?
    private var drawableFalse: Drawable?
    private var mPaint: Paint
    private var mHolder: RefreshData? = null
    init {
        val attr = mContext.obtainStyledAttributes(attrs, R.styleable.FavoriteButton)
        drawableTrue  = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_favorite_48, null)
        drawableFalse  = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_favorite_border_48, null)
        mPaint = Paint()
        setOnClickListener(this)
    }

    /**
     * Switch the image according to isFav.
     * This method will be called from Activity/Fragment/Adapter where the ViewGroup hold this view
     */
    fun setImage(isFav: Boolean){
        isFavorite = isFav
        if (isFavorite){
            setImageResource(R.drawable.ic_baseline_favorite_48)
        }else{
            setImageResource(R.drawable.ic_baseline_favorite_border_48)
        }
    }

    fun setHolder(holder: RefreshData){
        mHolder = holder
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    /**
     * Perform click, when clicked, switch the image and start a fade animation
     */
    override fun onClick(view: View?) {
        mHolder?.refreshData(!isFavorite)
        if (isFavorite){
            val animation = animate().alpha(0f).scaleX(0f).scaleY(0f).setDuration(300)
            animation.setListener(object: Animator.AnimatorListener{
                override fun onAnimationStart(p0: Animator?) {
                }
                override fun onAnimationEnd(p0: Animator?) {
                    ToastUtil.showShortMessage("Anime 1", mContext)
                    // Remove the listener, or this method could be called multi times
                    animation.setListener(null)
                    setImage(!isFavorite)
                    animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(300).start()
                }
                override fun onAnimationCancel(p0: Animator?) {
                }
                override fun onAnimationRepeat(p0: Animator?) {
                }
            }).start()
        }else{
            val animation = animate().alpha(0f).scaleX(0f).scaleY(0f).setDuration(300)
            animation.setListener(object: Animator.AnimatorListener{
                override fun onAnimationStart(p0: Animator?) {
                }
                override fun onAnimationEnd(p0: Animator?) {
                    ToastUtil.showShortMessage("Anime 2", mContext)
                    animation.setListener(null)
                    setImage(!isFavorite)
                    animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(300).start()
                }
                override fun onAnimationCancel(p0: Animator?) {
                }
                override fun onAnimationRepeat(p0: Animator?) {
                }
            }).start()
        }
    }

    interface RefreshData{
        fun refreshData(isFav: Boolean)
    }

}