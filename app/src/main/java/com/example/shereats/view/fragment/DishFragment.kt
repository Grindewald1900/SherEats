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
import android.widget.CompoundButton
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shereats.R
import com.example.shereats.databinding.DishFragmentBinding
import com.example.shereats.model.entity.Dish
import com.example.shereats.model.viewmodel.DishViewModel
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.EntityUtil
import com.example.shereats.view.adapter.DishAdapter
import com.example.shereats.view.custom.SearchFilterLayout

class DishFragment : Fragment() {
    private lateinit var bingding: DishFragmentBinding
    private lateinit var viewModel: DishViewModel
    private lateinit var topLayout: LinearLayout
    private lateinit var sfFilterOne: SearchFilterLayout
    private lateinit var sfFilterTwo: SearchFilterLayout

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
        viewModel.setDishes(bingding.swipeFragmentDish)
        topLayout = bingding.llFragmentDishTop
        sfFilterOne = bingding.sfFragmentDishOne
        sfFilterTwo = bingding.sfFragmentDishTwo
        bingding.switchFragmentDish.switchPadding = 10
        bingding.rvFragmentDish.layoutManager = LinearLayoutManager(context)
        bingding.rvFragmentDish.itemAnimator = DefaultItemAnimator()

        viewModel.getDishes().observe(viewLifecycleOwner, {
            bingding.rvFragmentDish.adapter = DishAdapter(it)
        })
        bingding.swipeFragmentDish.setOnRefreshListener {
            viewModel.setDishes(bingding.swipeFragmentDish)
        }

        setScrollListener()
        setFilterListener()
        setOnCheckedListener()
    }

    /**
     * Price filter, if this filter is selected, we set other filters unselected
     * Rate filter, if this filter is selected, we set other filters unselected
     */
    private fun setFilterListener(){
        sfFilterOne.setOnClickListener {
            if (sfFilterTwo.getState() != ConstantUtil.FILTER_STATE_NONE){
                sfFilterTwo.setStateNone()
            }
            setFilterState(sfFilterOne)
            val currentData = viewModel.getDishes().value as List<Dish>
            when(sfFilterOne.getState()){
                ConstantUtil.FILTER_STATE_UP -> {
                    bingding.switchFragmentDish.isChecked = false
                    viewModel.resetDishes(EntityUtil.sortDishByPrice(currentData.toMutableList(), true))
                }
                ConstantUtil.FILTER_STATE_DOWN -> {
                    bingding.switchFragmentDish.isChecked = false
                    viewModel.resetDishes(EntityUtil.sortDishByPrice(currentData.toMutableList(), false))
                }
            }
        }
        sfFilterTwo.setOnClickListener {
            if(sfFilterOne.getState() != ConstantUtil.FILTER_STATE_NONE){
                sfFilterOne.setStateNone()
            }
            setFilterState(sfFilterTwo)
            val currentData = viewModel.getDishes().value as List<Dish>
            when(sfFilterTwo.getState()){
                ConstantUtil.FILTER_STATE_UP -> {
                    bingding.switchFragmentDish.isChecked = false
                    viewModel.resetDishes(EntityUtil.sortDishByRate(currentData.toMutableList(), true))
                }
                ConstantUtil.FILTER_STATE_DOWN -> {
                    bingding.switchFragmentDish.isChecked = false
                    viewModel.resetDishes(EntityUtil.sortDishByRate(currentData.toMutableList(), false))
                }
            }
        }
    }

    /**
     * Search filter changed listener: promotion
     * When the promotion button is checked, we'll set other filters unselected
     */
    private fun setOnCheckedListener(){
        bingding.switchFragmentDish.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                val currentData = viewModel.getDishes().value as List<Dish>
                viewModel.resetDishes(EntityUtil.sortDishByPromo(currentData.toMutableList()))
                sfFilterOne.setStateNone()
                sfFilterTwo.setStateNone()
            }
        }
    }

    /**
     * When RecycleView is scrolling, we'll hide the top filter bar,
     * The bar get back to top.until the view stops {@link #RecyclerView.SCROLL_STATE_IDLE}
     */
    private fun setScrollListener(){
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
    }

    /**
     * Set the filter state according to the current state
     */
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