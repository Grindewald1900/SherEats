package com.example.shereats.view.adapter

import android.animation.Animator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shereats.R
import com.example.shereats.model.entity.Restaurant
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.ToastUtil
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
        //Todo: add rate badge
//        var portrait: SelectableRoundedImageView = view.findViewById(R.id.iv_normal_card_portrait)
        val heart: ImageView = view.findViewById(R.id.ib_restaurant_collect)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantHolder {
        mContext = parent.context
        return RestaurantHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_adapter_restaurant, parent, false))
    }

    override fun onBindViewHolder(holder: RestaurantHolder, position: Int) {
        val dataSlice = data[position]
        ConstantUtil.LIST_IS_FAVORITE.add(position, dataSlice.restaurant_isfav == "T")
        holder.title.text = dataSlice.restaurant_name
        holder.location.text = dataSlice.restaurant_address
        holder.price.text = dataSlice.restaurant_average.toString()
        if (ConstantUtil.LIST_IS_FAVORITE[position]){
            holder.heart.setImageResource(R.drawable.ic_baseline_favorite_48)
        }else{
            holder.heart.setImageResource(R.drawable.ic_baseline_favorite_border_48)
        }
        holder.heart.setOnClickListener {
            if (ConstantUtil.LIST_IS_FAVORITE[position]){
                ConstantUtil.LIST_IS_FAVORITE[position] = false
                val animation = holder.heart.animate().alpha(0f).scaleX(0f).scaleY(0f).setDuration(300)
                animation.setListener(object: Animator.AnimatorListener{
                    override fun onAnimationStart(p0: Animator?) {
                    }
                    override fun onAnimationEnd(p0: Animator?) {
                        // Remove the listener, or this method could be called multi times
                        animation.setListener(null)
                        holder.heart.setImageResource(R.drawable.ic_baseline_favorite_border_48)
                        ToastUtil.showShortMessage("Anime 1", mContext)
                        holder.heart.animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(300).start()
                    }
                    override fun onAnimationCancel(p0: Animator?) {
                    }
                    override fun onAnimationRepeat(p0: Animator?) {
                    }
                }).start()
            }else{
                ConstantUtil.LIST_IS_FAVORITE[position] = true
                val animation = holder.heart.animate().alpha(0f).scaleX(0f).scaleY(0f).setDuration(300)
                animation.setListener(object: Animator.AnimatorListener{
                    override fun onAnimationStart(p0: Animator?) {
                    }
                    override fun onAnimationEnd(p0: Animator?) {
                        animation.setListener(null)
                        holder.heart.setImageResource(R.drawable.ic_baseline_favorite_48)
                        ToastUtil.showShortMessage("Anime 2", mContext)
                        holder.heart.animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(300).start()
                    }
                    override fun onAnimationCancel(p0: Animator?) {
                    }
                    override fun onAnimationRepeat(p0: Animator?) {
                    }
                }).start()
            }
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
                .placeholder(R.drawable.img_no_image)
                .into(view)
        }.addOnFailureListener {
            it.stackTrace
        }.addOnCompleteListener {
        }.addOnCanceledListener {
        }
    }

}