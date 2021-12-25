package com.example.shereats.view.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shereats.R
import com.example.shereats.databinding.DishFragmentBinding
import com.example.shereats.model.viewmodel.DishViewModel
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.view.adapter.DishAdapter
import com.example.shereats.view.custom.SearchFilterLayout

class DishFragment : Fragment() {
    private lateinit var bingding: DishFragmentBinding
    private lateinit var viewModel: DishViewModel
    private lateinit var topLayout: LinearLayout
    private lateinit var sfFilterOne: SearchFilterLayout
    private lateinit var sfFilterTwo: SearchFilterLayout

    private var moveDistance = 60f
    private var isAnimation = false

    companion object {
        fun newInstance() = DishFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bingding = DataBindingUtil.inflate(inflater, R.layout.dish_fragment, container, false)
        return bingding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DishViewModel::class.java)
        viewModel.setDishes()
        topLayout = bingding.llFragmentDishTop
        sfFilterOne = bingding.sfFragmentDishOne
        sfFilterTwo = bingding.sfFragmentDishTwo
        bingding.switchFragmentDish.switchPadding = 10
        bingding.rvFragmentDish.layoutManager = LinearLayoutManager(context)
        bingding.rvFragmentDish.itemAnimator = DefaultItemAnimator()
        /**
         * When RecycleView is scrolling, we'll hide the top filter bar,
         * The bar get back to top.until the view stops {@link #RecyclerView.SCROLL_STATE_IDLE}
         */
        bingding.rvFragmentDish.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(view: RecyclerView, newState: Int){
                when(newState){
                    RecyclerView.SCROLL_STATE_IDLE -> {
                        topLayout.animate().translationY(0f).alpha(1.0f).setDuration(500).setListener(object: AnimatorListenerAdapter(){
                            override fun onAnimationEnd(animation: Animator){
                                super.onAnimationEnd(animation)
                                topLayout.visibility = View.VISIBLE
                            }
                        })
                    }
                    RecyclerView.SCROLL_STATE_SETTLING -> {
                        topLayout.animate().cancel()
                    }
                    RecyclerView.SCROLL_STATE_DRAGGING -> {
                        topLayout.animate().translationY(topLayout.height.toFloat() * -1).alpha(0.0f).setDuration(500).setListener(object: AnimatorListenerAdapter(){
                            override fun onAnimationEnd(animation: Animator){
                                super.onAnimationEnd(animation)
                                topLayout.visibility = View.GONE
                            }
                        })
                    }
                }
            }
        })
        viewModel.getDishes().observe(viewLifecycleOwner, {
            bingding.rvFragmentDish.adapter = DishAdapter(it)
        })

        sfFilterOne.setOnClickListener {
            if (sfFilterTwo.getState() != ConstantUtil.FILTER_STATE_NONE){
                sfFilterTwo.setStateNone()
            }
            setFilterState(sfFilterOne)
        }
        sfFilterTwo.setOnClickListener {
            if(sfFilterOne.getState() != ConstantUtil.FILTER_STATE_NONE){
                sfFilterOne.setStateNone()
            }
            setFilterState(sfFilterTwo)
        }
    }

    private fun setFilterState(view: SearchFilterLayout){
        when(view.getState()){
            ConstantUtil.FILTER_STATE_NONE -> {
                view.setStateUp()
            }
            ConstantUtil.FILTER_STATE_UP -> {
                view.setStateDown()
            }
            ConstantUtil.FILTER_STATE_DOWN -> {
                view.setStateNone()
            }
        }
    }

}