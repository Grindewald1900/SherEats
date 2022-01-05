package com.example.shereats.view.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.shereats.R
import com.example.shereats.model.entity.FirebaseUser
import com.example.shereats.utils.TextUtil
import com.example.shereats.utils.firebase.StorageUtil
import com.example.shereats.view.custom.RoundCornerImageView

class FriendAdapter(val data: List<FirebaseUser>): RecyclerView.Adapter<FriendAdapter.FriendViewHolder>() {
    private lateinit var mContext: Context
    class FriendViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image: RoundCornerImageView = view.findViewById(R.id.iv_adapter_friend)
        val name: TextView = view.findViewById(R.id.tv_adapter_friend_name)
        val message: TextView = view.findViewById(R.id.tv_adapter_friend_message)
        val date: TextView = view.findViewById(R.id.tv_adapter_friend_date)
        val notice: ImageView = view.findViewById(R.id.iv_adapter_friend_notice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.view_adapter_friend, parent, false)
        return FriendViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val dataSlice = data[position]
        holder.name.text = dataSlice.userName
        holder.message.text = TextUtil.getMaxLengthString(18, dataSlice.userId!!)
        setProfileImage(dataSlice.userName!!, holder.image)
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