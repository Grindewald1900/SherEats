package com.example.shereats.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shereats.R
import com.example.shereats.databinding.OrderFragmentBinding
import com.example.shereats.model.viewmodel.OrderViewModel
import com.example.shereats.view.adapter.OrderAdapter

class OrderFragment : Fragment() {
    private lateinit var binding: OrderFragmentBinding
    private lateinit var viewModel: OrderViewModel

    companion object {
        fun newInstance() = OrderFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.order_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderViewModel::class.java)
        viewModel.setOrders()
        binding.rvFragmentOrder.layoutManager = LinearLayoutManager(context)
        binding.rvFragmentOrder.itemAnimator = DefaultItemAnimator()
        viewModel.getOrders().observe(viewLifecycleOwner, {
            binding.rvFragmentOrder.adapter = OrderAdapter(it)
        })
    }

}