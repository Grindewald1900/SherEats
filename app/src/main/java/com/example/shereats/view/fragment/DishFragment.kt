package com.example.shereats.view.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
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
import com.example.shereats.model.entity.FirebaseDish
import com.example.shereats.model.viewmodel.DishViewModel
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.EntityUtil
import com.example.shereats.view.adapter.DishAdapter
import com.example.shereats.view.custom.SearchFilterLayout

class DishFragment : Fragment() {
    private lateinit var binding: DishFragmentBinding
    private lateinit var viewModel: DishViewModel
    private lateinit var topLayout: LinearLayout
    private lateinit var sfFilterOne: SearchFilterLayout
    private lateinit var sfFilterTwo: SearchFilterLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.dish_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DishViewModel::class.java)
        viewModel.setAllFirebaseDish()
        topLayout = binding.llFragmentDishTop
        sfFilterOne = binding.sfFragmentDishOne
        sfFilterTwo = binding.sfFragmentDishTwo
        binding.switchFragmentDish.switchPadding = 10
        binding.rvFragmentDish.layoutManager = LinearLayoutManager(context)
        binding.rvFragmentDish.itemAnimator = DefaultItemAnimator()

        viewModel.getFirebaseDish().observe(viewLifecycleOwner) {
            binding.rvFragmentDish.adapter = DishAdapter(it)
        }
        binding.swipeFragmentDish.setOnRefreshListener {
            viewModel.setAllFirebaseDish()
        }

        setScrollListener()
        setFilterListener()
        setOnCheckedListener()
    }

    /**
     * Price and Rate filter, if this filter is selected, we set other filters unselected
     */
    private fun setFilterListener(){
        sfFilterOne.setOnClickListener {
            if (sfFilterTwo.getState() != ConstantUtil.FILTER_STATE_NONE){
                sfFilterTwo.setStateNone()
            }
            setFilterState(sfFilterOne)
            val currentData = viewModel.getFirebaseDish().value as List<FirebaseDish>
            when(sfFilterOne.getState()){
                ConstantUtil.FILTER_STATE_UP -> {
                    binding.switchFragmentDish.isChecked = false
                    viewModel.resetDishes(EntityUtil.sortDishByPrice(currentData.toMutableList(), true))
                }
                ConstantUtil.FILTER_STATE_DOWN -> {
                    binding.switchFragmentDish.isChecked = false
                    viewModel.resetDishes(EntityUtil.sortDishByPrice(currentData.toMutableList(), false))
                }
            }
        }
        sfFilterTwo.setOnClickListener {
            if(sfFilterOne.getState() != ConstantUtil.FILTER_STATE_NONE){
                sfFilterOne.setStateNone()
            }
            setFilterState(sfFilterTwo)
            val currentData = viewModel.getFirebaseDish().value as List<FirebaseDish>
            when(sfFilterTwo.getState()){
                ConstantUtil.FILTER_STATE_UP -> {
                    binding.switchFragmentDish.isChecked = false
                    viewModel.resetDishes(EntityUtil.sortDishByRate(currentData.toMutableList(), true))
                }
                ConstantUtil.FILTER_STATE_DOWN -> {
                    binding.switchFragmentDish.isChecked = false
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
        binding.switchFragmentDish.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                val currentData = viewModel.getFirebaseDish().value as List<FirebaseDish>
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
        binding.rvFragmentDish.addOnScrollListener(object: RecyclerView.OnScrollListener(){
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