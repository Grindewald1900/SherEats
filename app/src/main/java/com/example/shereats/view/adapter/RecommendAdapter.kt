package com.example.shereats.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.shereats.R
import com.example.shereats.model.entity.Dish


/**
 * Created by Yee on 2021-12-18.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class RecommendAdapter(val dish: List<Dish>): RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder>() {
    class RecommendViewHolder(view: View): RecyclerView.ViewHolder(view){
        var title: TextView = view.findViewById(R.id.tv_viewpager_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_viewpager_search, parent, false)
        return RecommendViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        holder.title.text = dish[position].item_name
    }

    override fun getItemCount(): Int {
        return dish.size
    }
}