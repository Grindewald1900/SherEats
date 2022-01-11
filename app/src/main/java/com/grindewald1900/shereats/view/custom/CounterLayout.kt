package com.grindewald1900.shereats.view.custom

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.grindewald1900.shereats.R
import com.grindewald1900.shereats.model.entity.FirebaseOrderItem
import com.grindewald1900.shereats.model.entity.SingletonUtil
import com.grindewald1900.shereats.model.interfaces.RefreshCart

/**
 * A layout for cart adapter, including plusButton, countView, minusButton.
 */
class CounterLayout(private val mContext: Context, private val attrs: AttributeSet): ConstraintLayout(mContext, attrs){
    private lateinit var mHolder: RefreshCart
    private lateinit var mOrderItem: FirebaseOrderItem
    private var ivPlus: ImageView
    private var ivMinus: ImageView
    private var tvCount: TextView
    private var mCount: Long = 0

    init {
        val view: View = LayoutInflater.from(mContext).inflate(R.layout.layout_counter, this, true)
        ivPlus = view.findViewById(R.id.iv_layout_counter_plus)
        ivMinus = view.findViewById(R.id.iv_layout_counter_minus)
        tvCount = view.findViewById(R.id.tv_layout_counter)
        setListener()
    }

    private fun setListener(){
        val animatorSet = AnimatorInflater.loadAnimator(mContext, R.animator.animator_counter_button).setDuration(500) as AnimatorSet
        animatorSet.addListener(object: Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator?) {}
            override fun onAnimationRepeat(p0: Animator?) {}
            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationEnd(p0: Animator?) {
                updateCount(null)
            }
        })
        ivPlus.setOnClickListener {
            animatorSet.setTarget(it)
            animatorSet.start()
            mCount ++
        }
        ivMinus.setOnClickListener {
            animatorSet.setTarget(it)
            animatorSet.start()
            mCount --
        }
    }


    /**
     * If set null as parameter, update the textview according to {@see mCount}
     */
    private fun updateCount(count: Long?){
        if(count != null){
            mCount = count
        }
        tvCount.text = mCount.toString()
        mOrderItem.itemAmount = mCount
        SingletonUtil.updateCart(mOrderItem)
        if (mCount <= 0){
            mHolder.refreshData()
        }
        mHolder.refreshPrice()
    }

    fun setHolder(holder: RefreshCart){
        mHolder = holder
    }

    fun setOrderItem(orderItem: FirebaseOrderItem){
        mOrderItem = orderItem
        mCount = mOrderItem.itemAmount!!
        tvCount.text = mCount.toString()
    }
}