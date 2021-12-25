package com.example.shereats.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.shereats.R
import com.example.shereats.utils.ConstantUtil


/**
 * Created by Yee on 2021-12-24.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class SearchFilterLayout: ConstraintLayout {
    var mState = ConstantUtil.FILTER_STATE_NONE
    private var mTitle: String? = "text"
    private lateinit var mTextView: TextView
    private lateinit var mViewUp: ImageView
    private lateinit var mViewDown: ImageView

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs){
        initView(context, attrs, 0)
    }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle){
        initView(context,attrs,defStyle)
    }

    private fun initView(context: Context, attrs: AttributeSet, defStyle: Int){
        val attr = context.obtainStyledAttributes(attrs, R.styleable.SearchFilterLayout)
        val view = LayoutInflater.from(context).inflate(R.layout.layout_search_filter, this, true)

        mTitle = attr.getString(R.styleable.SearchFilterLayout_search_filter_text)

        mTextView = view.findViewById(R.id.tv_layout_search_filter)
        mViewUp = view.findViewById(R.id.iv_layout_search_filter_up)
        mViewDown = view.findViewById(R.id.iv_layout_search_filter_down)
        if(!mTitle.isNullOrEmpty()){
            mTextView.text = mTitle
        }
    }

    // Highlight the up button
    fun setStateUp(){
        mViewUp.setBackgroundResource(R.drawable.ic_iconmonstr_triangle_up_color)
        mViewDown.setBackgroundResource(R.drawable.ic_iconmonstr_care_down_thin)
        this.setBackgroundResource(R.drawable.shape_text_view_selected)
        mState = ConstantUtil.FILTER_STATE_UP
    }

    // Highlight the down button
    fun setStateDown(){
        mViewUp.setBackgroundResource(R.drawable.ic_iconmonstr_care_up_thin)
        mViewDown.setBackgroundResource(R.drawable.ic_iconmonstr_triangle_down_color)
        this.setBackgroundResource(R.drawable.shape_text_view_selected)
        mState = ConstantUtil.FILTER_STATE_DOWN
    }

    // Set the view to unchecked
    fun setStateNone(){
        mViewUp.setBackgroundResource(R.drawable.ic_iconmonstr_care_up_thin)
        mViewDown.setBackgroundResource(R.drawable.ic_iconmonstr_care_down_thin)
        this.background = null
        mState = ConstantUtil.FILTER_STATE_NONE
    }

    fun getTitle(): String?{
        return mTitle
    }

    fun getState(): Int{
        return mState
    }
}