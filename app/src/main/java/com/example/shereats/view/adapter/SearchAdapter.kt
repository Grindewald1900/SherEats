package com.example.shereats.view.adapter

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shereats.R
import com.example.shereats.model.entity.Dish


/**
 * Created by Yee on 2021-12-18.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class SearchAdapter(var dishes: List<Dish>): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    class SearchViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.tv_item_search_title)
        var info: TextView = view.findViewById(R.id.tv_item_search_info)
        var permission: TextView = view.findViewById(R.id.tv_item_search_permission)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_adapter_search, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val dish = dishes[position]
        holder.title.text = dish.item_name
        holder.info.text = dish.item_genre
        holder.permission.text = dish.restaurant_name
    }

    override fun getItemCount(): Int {
        return dishes.size
    }
}