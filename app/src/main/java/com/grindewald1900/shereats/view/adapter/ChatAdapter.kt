package com.grindewald1900.shereats.view.adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grindewald1900.shereats.R
import com.grindewald1900.shereats.model.entity.FirebaseMessage
import com.grindewald1900.shereats.utils.ConstantUtil
import com.grindewald1900.shereats.utils.LoginStatusUtil
import com.grindewald1900.shereats.utils.firebase.StorageUtil

class ChatAdapter(val data: List<FirebaseMessage>, private val bitmapUser: Bitmap?, private val bitmapFriend: Bitmap?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var mContext: Context
    class ChatViewHolderLeft(view: View): RecyclerView.ViewHolder(view){
        val ivLeft: ImageView = view.findViewById(R.id.iv_adapter_chat_left)
        val textMessage: TextView = view.findViewById(R.id.tv_adapter_chat_left_message)
        val imageMessage: ImageView = view.findViewById(R.id.iv_adapter_chat_left_image)
        val emojiMessage: ImageView = view.findViewById(R.id.emoji_adapter_chat_left)
    }

    class ChatViewHolderRight(view: View): RecyclerView.ViewHolder(view){
        val ivRight: ImageView = view.findViewById(R.id.iv_adapter_chat_right)
        val textMessage: TextView = view.findViewById(R.id.tv_adapter_chat_right_message)
        val imageMessage: ImageView = view.findViewById(R.id.iv_adapter_chat_right_image)
        val emojiMessage: ImageView = view.findViewById(R.id.emoji_adapter_chat_right)
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
                when(dataSlice.type){
                    ConstantUtil.TYPE_TEXT ->{
                        holder.textMessage.visibility = View.VISIBLE
                        holder.imageMessage.visibility = View.GONE
                        holder.emojiMessage.visibility = View.GONE
                        holder.textMessage.text = dataSlice.contentText
                    }
                    ConstantUtil.TYPE_IMAGE ->{
                        holder.textMessage.visibility = View.GONE
                        holder.imageMessage.visibility = View.VISIBLE
                        holder.emojiMessage.visibility = View.GONE
                        setContentImage(dataSlice.contentImage!!, holder.imageMessage)
                    }
                    ConstantUtil.TYPE_EMOJI -> {
                        val resourceId = mContext.resources.getIdentifier(dataSlice.contentImage, "drawable", mContext.packageName)
                        holder.textMessage.visibility = View.GONE
                        holder.imageMessage.visibility = View.GONE
                        holder.emojiMessage.visibility = View.VISIBLE
                        setImage(resourceId, holder.emojiMessage)
                    }
                }
                if(null != bitmapFriend){
                    setImage(bitmapFriend, holder.ivLeft)
                }else{
                    setProfileImage(dataSlice.senderName!!, holder.ivLeft)
                }
            }
            ConstantUtil.CHAT_RIGHT -> {
                val holder = hold as ChatViewHolderRight
                when(dataSlice.type){
                    ConstantUtil.TYPE_TEXT ->{
                        holder.textMessage.visibility = View.VISIBLE
                        holder.imageMessage.visibility = View.GONE
                        holder.emojiMessage.visibility = View.GONE
                        holder.textMessage.text = dataSlice.contentText
                    }
                    ConstantUtil.TYPE_IMAGE ->{
                        holder.textMessage.visibility = View.GONE
                        holder.imageMessage.visibility = View.VISIBLE
                        holder.emojiMessage.visibility = View.GONE
                        setContentImage(dataSlice.contentImage!!, holder.imageMessage)
                    }
                    ConstantUtil.TYPE_EMOJI -> {
                        val resourceId = mContext.resources.getIdentifier(dataSlice.contentImage, "drawable", mContext.packageName)
                        holder.textMessage.visibility = View.GONE
                        holder.imageMessage.visibility = View.GONE
                        holder.emojiMessage.visibility = View.VISIBLE
                        setImage(resourceId, holder.emojiMessage)
                    }
                }
                if(null != bitmapUser) {
                    setImage(bitmapUser, holder.ivRight)
                }else{
                    setProfileImage(dataSlice.senderName!!, holder.ivRight)
                }
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

    private fun setContentImage(imageUrl: String, view: ImageView){
        Glide.with(mContext)
            .asBitmap()
            .placeholder(R.drawable.img_user_unknown)
            .load(imageUrl)
            .into(view)
    }

    private fun setImage(bitmap: Bitmap, view: ImageView){
        Glide.with(mContext)
            .asBitmap()
            .placeholder(R.drawable.img_user_unknown)
            .load(bitmap)
            .into(view)
    }

    private fun setImage(resource: Int, view: ImageView){
        Glide.with(mContext)
            .asBitmap()
            .placeholder(R.drawable.img_user_unknown)
            .load(resource)
            .into(view)
    }
}