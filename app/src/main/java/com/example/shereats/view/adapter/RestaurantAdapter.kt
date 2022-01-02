package com.example.shereats.view.adapter

import android.animation.Animator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shereats.R
import com.example.shereats.model.entity.FirebaseRestaurant
import com.example.shereats.model.entity.SingletonUtil
import com.example.shereats.model.interfaces.RefreshData
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.TextUtil
import com.example.shereats.utils.ToastUtil
import com.example.shereats.utils.firebase.StorageUtil
import com.example.shereats.view.custom.FavoriteButton


/**
 * Created by Yee on 2021-10-25.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class RestaurantAdapter(var data: List<FirebaseRestaurant>): RecyclerView.Adapter<RestaurantAdapter.RestaurantHolder>() {
    private lateinit var mContext: Context

    class RestaurantHolder(view: View): RecyclerView.ViewHolder(view), RefreshData{
        var layout: View = view.findViewById(R.id.view_restaurant_background)
        var image: ImageView = view.findViewById(R.id.iv_restaurant_image)
        var title: TextView = view.findViewById(R.id.tv_restaurant_title)
        var location: TextView = view.findViewById(R.id.tv_restaurant_location)
        val price: TextView = view.findViewById(R.id.tv_restaurant_price)
        var isFavorite: Boolean = false
        //Todo: add rate badge
//        var portrait: SelectableRoundedImageView = view.findViewById(R.id.iv_normal_card_portrait)
        val heart: FavoriteButton = view.findViewById(R.id.ib_restaurant_collect)

        override fun refreshData(isFav: Boolean) {
            isFavorite = isFav
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantHolder {
        mContext = parent.context
        return RestaurantHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_adapter_restaurant, parent, false))
    }

    override fun onBindViewHolder(holder: RestaurantHolder, position: Int) {
        val dataSlice = data[position]
        SingletonUtil.LIST_IS_FAVORITE_REST.add(position, dataSlice.restaurantIsfav == "T")
        holder.title.text = dataSlice.restaurantName
        holder.location.text = dataSlice.restaurantAddress
        holder.price.text = TextUtil.getItemPrice(dataSlice.restaurantAverage!!)
        holder.heart.setHolder(holder)
        holder.heart.setImage(SingletonUtil.LIST_IS_FAVORITE_REST[position])
        SingletonUtil.LIST_IS_FAVORITE_REST[position] = holder.isFavorite
        setRestaurantImage(dataSlice.restaurantId!!.toInt(), holder.image)
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
                .placeholder(R.drawable.img_no_image)
                .into(view)
        }.addOnFailureListener {
            it.stackTrace
        }.addOnCompleteListener {
        }.addOnCanceledListener {
        }
    }

}