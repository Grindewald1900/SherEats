package com.example.shereats.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shereats.R
import com.example.shereats.databinding.DishFragmentBinding
import com.example.shereats.model.viewmodel.DishViewModel
import com.example.shereats.view.adapter.DishAdapter

class DishFragment : Fragment() {
    private lateinit var bingding: DishFragmentBinding
    private lateinit var viewModel: DishViewModel

    companion object {
        fun newInstance() = DishFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bingding = DataBindingUtil.inflate(inflater, R.layout.dish_fragment, container, false)
        viewModel = DishViewModel()
        return bingding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DishViewModel::class.java)
        viewModel.setDishes()
        bingding.rvFragmentDish.layoutManager = LinearLayoutManager(context)
        bingding.rvFragmentDish.itemAnimator = DefaultItemAnimator()
        viewModel.getDishes().observe(viewLifecycleOwner, {
            bingding.rvFragmentDish.adapter = DishAdapter(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}