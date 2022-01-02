package com.example.shereats.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shereats.R
import com.example.shereats.model.entity.OrderItem
import com.example.shereats.model.interfaces.RefreshCart
import com.example.shereats.model.viewmodel.CartViewModel
import com.example.shereats.utils.TextUtil
import com.example.shereats.utils.firebase.StorageUtil
import com.example.shereats.view.custom.CounterLayout
import com.example.shereats.view.fragment.CartFragment


/**
 * Created by Yee on 2021-10-28.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class CartAdapter(var data: List<OrderItem>, var viewModel: CartViewModel?, var isShowCounter: Boolean): RecyclerView.Adapter<CartAdapter.CartViewHolder>(){
    private lateinit var mContext: Context
    class CartViewHolder(view: View, var viewModel: CartViewModel?): RecyclerView.ViewHolder(view), RefreshCart {
        val image: ImageView = view.findViewById(R.id.iv_shopping_cart_icon)
        val title: TextView = view.findViewById(R.id.tv_shopping_cart_title)
        val description: TextView = view.findViewById(R.id.tv_shopping_cart_description)
        val price: TextView = view.findViewById(R.id.tv_shopping_cart_price)
        val counter: CounterLayout = view.findViewById(R.id.counter_shopping_cart)

        override fun refreshData() {
            if(null != viewModel){
                viewModel!!.setOrderItems()
            }
        }

        override fun refreshPrice() {
            if(null != viewModel){
                viewModel!!.setTotalPrice()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder{
        mContext = parent.context
        return CartViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_adapter_cart, parent, false), viewModel)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val dataSlice = data[position]
        holder.title.text = dataSlice.item_name
        holder.description.text = dataSlice.restaurant_name
        holder.price.text = TextUtil.getItemPriceEach(dataSlice.order_price)
        if(isShowCounter){
            holder.counter.setHolder(holder)
            holder.counter.setOrderItem(dataSlice)
        }else{
            holder.counter.visibility = View.GONE
        }
        getDishImage(dataSlice.item_id, holder.image)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    /**
     * Get image for the order
     */
    private fun getDishImage(id: Int, view: ImageView){
        val childPath = "item/$id.jpg"
        val pathReference = StorageUtil.reference.child(childPath)
        pathReference.downloadUrl.addOnSuccessListener {
            Glide.with(mContext)
                .load(it.toString())
                .placeholder(R.drawable.img_no_image)
                .into(view)
        }.addOnFailureListener {
            it.stackTrace
        }
    }
}