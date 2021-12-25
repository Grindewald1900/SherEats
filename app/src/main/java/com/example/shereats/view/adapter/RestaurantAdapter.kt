package com.example.shereats.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shereats.R
import com.example.shereats.model.entity.Restaurant
import com.example.shereats.utils.firebase.StorageUtil


/**
 * Created by Yee on 2021-10-25.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class RestaurantAdapter(var data: List<Restaurant>): RecyclerView.Adapter<RestaurantAdapter.RestaurantHolder>() {
    private lateinit var mContext: Context

    class RestaurantHolder(view: View): RecyclerView.ViewHolder(view){
        var layout: View = view.findViewById(R.id.view_restaurant_background)
        var image: ImageView = view.findViewById(R.id.iv_restaurant_image)
        var title: TextView = view.findViewById(R.id.tv_restaurant_title)
        var location: TextView = view.findViewById(R.id.tv_restaurant_location)
        val price: TextView = view.findViewById(R.id.tv_restaurant_price)
        //Todo: add profile pic
//        var portrait: SelectableRoundedImageView = view.findViewById(R.id.iv_normal_card_portrait)
        val heart: ImageButton = view.findViewById(R.id.ib_restaurant_collect)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantHolder {
        mContext = parent.context
        return RestaurantHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_adapter_restaurant, parent, false))
    }

    override fun onBindViewHolder(holder: RestaurantHolder, position: Int) {
        val dataSlice = data[position]
        holder.title.text = dataSlice.restaurant_name
        holder.location.text = dataSlice.restaurant_address
        holder.price.text = dataSlice.restaurant_average.toString()
        if (dataSlice.restaurant_isfav == "T"){
            holder.heart.background = mContext.getDrawable(R.drawable.ic_baseline_favorite_48)
        }else{
            holder.heart.background = mContext.getDrawable(R.drawable.ic_baseline_favorite_border_48)
        }
        setRestaurantImage(dataSlice.restaurant_id, holder.image)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun setRestaurantImage(id: Int, view: ImageView){
        val childPath = "restaurant/$id.jpg"
        val pathReference = StorageUtil.reference.child(childPath)
        pathReference.downloadUrl.addOnSuccessListener {
            Glide.with(mContext)
                .load(it.toString())
                .placeholder(R.drawable.ic_dining_room_48)
                .into(view)
        }.addOnFailureListener {
            it.stackTrace
        }.addOnCompleteListener {
            val a = 0
        }.addOnCanceledListener {
            val b = 0
        }
    }


}