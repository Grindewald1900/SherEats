package com.example.shereats.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shereats.R
import com.example.shereats.model.entity.Badge


/**
 * Created by Yee on 2021-12-26.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class BadgeAdapter(var data: List<Badge>): RecyclerView.Adapter<BadgeAdapter.BadgeViewHolder>() {
    private lateinit var mContext: Context
    class BadgeViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image: ImageView = view.findViewById(R.id.iv_adapter_badge_logo)
        val tvContent: TextView = view.findViewById(R.id.tv_adapter_badge_content)
        val tvDate: TextView = view.findViewById(R.id.tv_adapter_badge_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BadgeViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.view_adapter_badge, parent, false)
        return BadgeViewHolder(view)
    }

    override fun onBindViewHolder(holder: BadgeViewHolder, position: Int) {
        val dataSlice = data[position]
        holder.tvContent.text = dataSlice.badge_content
        holder.tvDate.text = dataSlice.badge_date
    }

    override fun getItemCount(): Int {
        return data.size
    }
}