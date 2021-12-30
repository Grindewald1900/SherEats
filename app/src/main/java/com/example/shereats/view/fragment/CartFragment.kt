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
import com.example.shereats.utils.TextUtil
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
        viewModel.setOrderItems()
        viewModel.setTotalPrice()
        viewModel.getOrderItems().observe(viewLifecycleOwner) {
            binding.rvFragmentCart.adapter = CartAdapter(it, viewModel)
        }
        viewModel.getTotalPrice().observe(viewLifecycleOwner){
            setPrice(it)
        }
        binding.rvFragmentCart.layoutManager = LinearLayoutManager(context)
        binding.rvFragmentCart.itemAnimator = DefaultItemAnimator()

    }

    private fun setPrice(originPrice: Float){
        binding.tvShoppingCartPrice.text = TextUtil.getItemPrice(originPrice)
        binding.tvShoppingCartGst.text = TextUtil.getItemPrice(originPrice * 0.15f)
        binding.tvShoppingCartTotalPrice.text = TextUtil.getItemPrice(originPrice * 1.15f)
    }
}