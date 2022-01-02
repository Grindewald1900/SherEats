package com.example.shereats.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shereats.R
import com.example.shereats.model.entity.Dish
import com.example.shereats.utils.firebase.StorageUtil
import com.example.shereats.view.custom.RoundCornerImageView


/**
 * Created by Yee on 2021-12-18.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class SearchAdapter(var dishes: List<Dish>): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private lateinit var mContext: Context
    class SearchViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.tv_item_search_title)
        var info: TextView = view.findViewById(R.id.tv_item_search_info)
        var permission: TextView = view.findViewById(R.id.tv_item_search_permission)
        var image: RoundCornerImageView = view.findViewById(R.id.iv_item_search)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_adapter_search, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val dataSlice = dishes[position]
        holder.title.text = dataSlice.item_name
        holder.info.text = dataSlice.item_genre
        holder.permission.text = dataSlice.restaurant_name
        setDishImage(dataSlice.item_id, holder.image)
    }

    override fun getItemCount(): Int {
        return dishes.size
    }

    /**
     * Get image for the dish
     */
    private fun setDishImage(id: Int, view: ImageView){
        val childPath = "item/$id.jpg"
        val pathReference = StorageUtil.reference.child(childPath)
        pathReference.downloadUrl.addOnSuccessListener {
            Glide.with(mContext)
                .load(it.toString())
                .placeholder(R.drawable.ic_dining_room_48)
                .into(view)
        }.addOnFailureListener {
            it.stackTrace
        }
    }
}