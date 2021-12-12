package com.example.shereats.view.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.shereats.R
import com.example.shereats.model.entity.Dish
import com.example.shereats.utils.TextUtil
import kotlin.random.Random


/**
 * Created by Yee on 2021-10-26.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class DishAdapter(var data: List<Dish>): RecyclerView.Adapter<DishAdapter.DishViewHolder>() {
    private lateinit var mContext: Context

    class DishViewHolder(view: View): RecyclerView.ViewHolder(view){
        val layout: View = view.findViewById(R.id.view_dish_background)
        val title: TextView = view.findViewById(R.id.tv_dish_title)
        val price: TextView = view.findViewById(R.id.tv_dish_price)
        val newPrice: TextView = view.findViewById(R.id.tv_dish_new_price)
        val promotion: TextView = view.findViewById(R.id.tv_dish_promotion)
        val promotionBackGround: View = view.findViewById(R.id.view_dish_promotion)
        val rate: LinearLayout = view.findViewById(R.id.ll_dish_rate)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        mContext = parent.context
        return DishViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_adapter_dish, parent, false))
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val dataSlice = data[position]
        // We don't have rate data in our database, so fake some data here
        // From 2.0 to 5.0
        var starCount = 2 + Random.nextFloat() * 3
//        var starCount = (dataSlice.item_service + dataSlice.item_environment + dataSlice.item_taste)/3
        holder.title.text = dataSlice.item_name
        holder.price.text = TextUtil.getItemPrice(dataSlice.item_price)
        if (dataSlice.item_discount < 1f){
            setVisibility(holder, View.VISIBLE)
            holder.price.setTextColor(Color.GRAY)
            holder.price.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            holder.newPrice.text = TextUtil.getItemPrice(dataSlice.item_price * dataSlice.item_discount)
            holder.promotion.text = TextUtil.getPromotion(dataSlice.item_discount)
        }else{
            setVisibility(holder, View.INVISIBLE)
            holder.price.setTextColor(mContext.getColor(R.color.colorPrimary))
            holder.price.paint.flags = 0
        }

        holder.rate.removeAllViews()
        for (i in 1..5){
            starCount -= 1
            if (starCount >= 0.75) {
                holder.rate.addView(addStar(3))
            }
            if (starCount > 0.25 && starCount < 0.75){
                holder.rate.addView(addStar(2))
            }
            if(starCount < 0.25){
                holder.rate.addView(addStar(1))
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    /**
     * Set visibility of some components
     */
    private fun setVisibility(holder: DishViewHolder, visible: Int){
        holder.newPrice.visibility = visible
        holder.promotionBackGround.visibility = visible
        holder.promotion.visibility = visible
    }

    /**
     * Add stars to LinearLayout dynamically
     */
    private fun addStar(type: Int): ImageView {
        val ivStar = ImageView(mContext)
        val size = 60
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(size, size)
        params.setMargins(0, 0, 20, 0)
        ivStar.background = AppCompatResources.getDrawable(mContext, R.drawable.ic_star1)
        if (type == 1){
            ivStar.layoutParams = params
            ivStar.background = AppCompatResources.getDrawable(mContext, R.drawable.ic_star1)
        }
        if (type == 2){
            ivStar.layoutParams = params
            ivStar.background = AppCompatResources.getDrawable(mContext, R.drawable.ic_star2)
        }
        if (type == 3){
            ivStar.layoutParams = params
            ivStar.background = AppCompatResources.getDrawable(mContext, R.drawable.ic_star3)
        }
        return ivStar
    }
}