package com.grindewald1900.shereats.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.grindewald1900.shereats.R
import com.grindewald1900.shereats.databinding.OrderFragmentBinding
import com.grindewald1900.shereats.model.viewmodel.OrderViewModel
import com.grindewald1900.shereats.utils.LoginStatusUtil
import com.grindewald1900.shereats.view.adapter.OrderAdapter

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
        viewModel.getOrders().observe(viewLifecycleOwner) {
            binding.rvFragmentOrder.adapter = OrderAdapter(it)
            setPlaceHolder(it.size)
        }
        binding.rvFragmentOrder.layoutManager = LinearLayoutManager(context)
        binding.rvFragmentOrder.itemAnimator = DefaultItemAnimator()

        initPlaceHolder()
    }

    private fun initPlaceHolder(){
        if(!LoginStatusUtil.isLogin()){
            setPlaceHolder(0)
        }
    }

    private fun setPlaceHolder(orderSize: Int){
        if (orderSize <= 0){
            binding.ivFragmentOrderPlaceholder.visibility = View.VISIBLE
            binding.tvFragmentOrderPlaceholder.visibility = View.VISIBLE
            binding.ivFragmentOrderPlaceholder.setImageResource(R.drawable.ic_iconmonstr_delivery_10)
            binding.tvFragmentOrderPlaceholder.text = resources.getString(R.string.order_no_order)
        }else{
            binding.ivFragmentOrderPlaceholder.visibility = View.GONE
            binding.tvFragmentOrderPlaceholder.visibility = View.GONE
        }
    }



}