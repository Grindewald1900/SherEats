package com.example.shereats.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shereats.R
import com.example.shereats.model.entity.Order


/**
 * Created by Yee on 2021-10-27.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class OrderAdapter(var data: List<Order>): RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    class OrderViewHolder(view: View): RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_adapter_order, parent, false))
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return data.size
    }


}