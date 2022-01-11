package com.grindewald1900.shereats.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grindewald1900.shereats.R
import com.grindewald1900.shereats.model.entity.FirebaseDish


/**
 * Created by Yee on 2021-12-18.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class RecommendAdapter(val dish: List<FirebaseDish>): RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder>() {
    class RecommendViewHolder(view: View): RecyclerView.ViewHolder(view){
        var title: TextView = view.findViewById(R.id.tv_viewpager_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_viewpager_search, parent, false)
        return RecommendViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        holder.title.text = dish[position].itemName
    }

    override fun getItemCount(): Int {
        return dish.size
    }
}