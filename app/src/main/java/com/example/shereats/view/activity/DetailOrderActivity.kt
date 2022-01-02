package com.example.shereats.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shereats.R
import com.example.shereats.databinding.ActivityDetailOrderBinding
import com.example.shereats.model.entity.Order
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.TextUtil
import com.example.shereats.view.adapter.CartAdapter

class DetailOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailOrderBinding
    private lateinit var mOrder: Order

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_order)
        initView()
    }

    private fun initView(){
        if(intent.extras != null){
            mOrder = intent.getSerializableExtra(ConstantUtil.ENTITY_ORDER) as Order
        }
        binding.rvActivityOrder.layoutManager = LinearLayoutManager(this)
        binding.rvActivityOrder.adapter = CartAdapter(mOrder.items, null, false)
        setPrice(mOrder.price.toDouble())

    }

    private fun setPrice(originPrice: Double) {
        binding.tvShoppingOrderPrice.text = TextUtil.getItemPrice(originPrice)
        binding.tvShoppingOrderGst.text = TextUtil.getItemPrice(originPrice * 0.15f)
        binding.tvShoppingOrderTotalPrice.text = TextUtil.getItemPrice(originPrice * 1.15f)
    }
}