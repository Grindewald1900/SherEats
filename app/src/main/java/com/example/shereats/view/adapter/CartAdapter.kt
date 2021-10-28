package com.example.shereats.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shereats.R
import com.example.shereats.model.entity.OrderItem


/**
 * Created by Yee on 2021-10-28.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class CartAdapter(var data: List<OrderItem>): RecyclerView.Adapter<CartAdapter.CartViewHolder>(){
    class CartViewHolder(view: View): RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_adapter_cart, parent, false))
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return data.size
    }
}