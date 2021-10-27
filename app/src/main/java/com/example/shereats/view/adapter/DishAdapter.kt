package com.example.shereats.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shereats.R
import com.example.shereats.model.entity.Dish


/**
 * Created by Yee on 2021-10-26.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class DishAdapter(var data: List<Dish>): RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    class DishViewHolder(view: View): RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        return DishViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_adapter_dish, parent, false))
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return data.size
    }
}