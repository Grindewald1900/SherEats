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
import com.example.shereats.databinding.CartFragmentBinding
import com.example.shereats.model.viewmodel.CartViewModel
import com.example.shereats.view.adapter.CartAdapter

class CartFragment : Fragment() {
    private lateinit var binding: CartFragmentBinding
    private lateinit var viewModel: CartViewModel

    companion object {
        fun newInstance() = CartFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.cart_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        viewModel.setOrders()
        binding.rvFragmentCart.layoutManager = LinearLayoutManager(context)
        binding.rvFragmentCart.itemAnimator = DefaultItemAnimator()
        viewModel.getOrders().observe(viewLifecycleOwner) {
            binding.rvFragmentCart.adapter = CartAdapter(it)
        }

    }

}