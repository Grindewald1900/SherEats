package com.example.shereats.view.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.shereats.R
import com.example.shereats.model.entity.Order
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.TextUtil
import com.example.shereats.utils.ToastUtil
import com.example.shereats.view.activity.DetailOrderActivity
import com.example.shereats.view.custom.RoundCornerButton
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.runtime.Permission


/**
 * Created by Yee on 2021-10-27.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class OrderAdapter(var data: List<Order>): RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    private lateinit var mContext: Context
    class OrderViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title: TextView = view.findViewById(R.id.tv_view_order_title)
        val price: TextView = view.findViewById(R.id.tv_view_order_subtitle)
        val content: TextView = view.findViewById(R.id.tv_view_order_content)
        val info: TextView = view.findViewById(R.id.tv_view_order_info)
        val contact: RoundCornerButton = view.findViewById(R.id.btn_view_order_contact)
        val view: ConstraintLayout = view.findViewById(R.id.view_order)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        mContext = parent.context
        return OrderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_adapter_order, parent, false))
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val dataSlice = data[position]
        holder.title.text = dataSlice.id
        holder.price.text = TextUtil.getItemPrice(dataSlice.price)
        holder.content.text = TextUtil.getOrderContent(dataSlice.items)
        holder.info.text = TextUtil.getOrderInfo(dataSlice)
        holder.contact.setOnClickListener {
            AndPermission.with(mContext)
                .runtime()
                .permission(Permission.CALL_PHONE)
                .onGranted{
                    val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:+18197912438"))
                    mContext.startActivity(intent)
                }
                .onDenied {
                    ToastUtil.showShortMessage(R.string.hint_no_perm, mContext)
                }
                .start()
        }
        holder.view.setOnClickListener {
            val intent = Intent(mContext, DetailOrderActivity::class.java)
            intent.putExtra(ConstantUtil.ENTITY_ORDER, dataSlice)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


}