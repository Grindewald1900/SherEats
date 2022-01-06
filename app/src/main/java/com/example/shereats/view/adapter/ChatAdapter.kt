package com.example.shereats.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shereats.R
import com.example.shereats.model.entity.FirebaseChat
import com.example.shereats.model.entity.FirebaseMessage
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.LoginStatusUtil
import com.example.shereats.utils.firebase.StorageUtil

class ChatAdapter(val data: List<FirebaseMessage>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var mContext: Context
    class ChatViewHolderLeft(view: View): RecyclerView.ViewHolder(view){
        val ivLeft: ImageView = view.findViewById(R.id.iv_adapter_chat_left)
        val textMessage: TextView = view.findViewById(R.id.tv_adapter_chat_left_message)
    }

    class ChatViewHolderRight(view: View): RecyclerView.ViewHolder(view){
        val ivRight: ImageView = view.findViewById(R.id.iv_adapter_chat_right)
        val textMessage: TextView = view.findViewById(R.id.tv_adapter_chat_right_message)
    }

    class ChatViewHolderMiddle(view: View): RecyclerView.ViewHolder(view){
        val textTime: TextView = view.findViewById(R.id.tv_adapter_chat_middle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mContext = parent.context
        val view: View
        return when(viewType){
            ConstantUtil.CHAT_LEFT -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.view_adapter_chat_left, parent, false)
                ChatViewHolderLeft(view)
            }
            ConstantUtil.CHAT_RIGHT -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.view_adapter_chat_right, parent, false)
                ChatViewHolderRight(view)
            }
            else -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.view_adapter_chat_middle, parent, false)
                ChatViewHolderMiddle(view)
            }
        }
    }

    override fun onBindViewHolder(hold: RecyclerView.ViewHolder, position: Int) {
        val dataSlice = data[position]
        when(hold.itemViewType) {
            ConstantUtil.CHAT_LEFT -> {
                val holder = hold as ChatViewHolderLeft
                holder.textMessage.text = dataSlice.contentText
                setProfileImage(dataSlice.senderName!!, holder.ivLeft)
            }
            ConstantUtil.CHAT_RIGHT -> {
                val holder = hold as ChatViewHolderRight
                holder.textMessage.text = dataSlice.contentText
                setProfileImage(dataSlice.senderName!!, holder.ivRight)
            }
            else -> {}
        }
    }

    override fun getItemViewType(position: Int): Int {
        val msg = data[position]
        return if(msg.senderName.equals(LoginStatusUtil.getUserName())){
            ConstantUtil.CHAT_RIGHT
        }else{
            ConstantUtil.CHAT_LEFT
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun setProfileImage(name: String, view: ImageView){
        val childPath = "user/$name.jpg"
        val pathReference = StorageUtil.reference.child(childPath)
        pathReference.downloadUrl.addOnSuccessListener {
            Glide.with(mContext)
                .asBitmap()
                .placeholder(R.drawable.img_user_unknown)
                .load(it.toString())
                .into(view)
        }
    }
}