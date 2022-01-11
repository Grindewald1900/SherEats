package com.grindewald1900.shereats.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grindewald1900.shereats.R
import com.grindewald1900.shereats.model.entity.FirebaseChat
import com.grindewald1900.shereats.utils.ConstantUtil
import com.grindewald1900.shereats.utils.LoginStatusUtil
import com.grindewald1900.shereats.utils.TextUtil
import com.grindewald1900.shereats.utils.ToastUtil
import com.grindewald1900.shereats.utils.firebase.RealtimeUtil
import com.grindewald1900.shereats.utils.firebase.StorageUtil
import com.grindewald1900.shereats.view.activity.ChatActivity
import com.grindewald1900.shereats.view.custom.RoundCornerImageView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class FriendAdapter(val data: List<FirebaseChat>): RecyclerView.Adapter<FriendAdapter.FriendViewHolder>() {
    private lateinit var mContext: Context
    class FriendViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image: RoundCornerImageView = view.findViewById(R.id.iv_adapter_friend)
        val name: TextView = view.findViewById(R.id.tv_adapter_friend_name)
        val message: TextView = view.findViewById(R.id.tv_adapter_friend_message)
        val date: TextView = view.findViewById(R.id.tv_adapter_friend_date)
        val notice: ImageView = view.findViewById(R.id.iv_adapter_friend_notice)
        val background: ConstraintLayout = view.findViewById(R.id.cl_adapter_friend)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.view_adapter_friend, parent, false)
        return FriendViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val name = data[position].friend!!.userName!!
        initHolderView(holder, position)
        setProfileImage(name, holder.image)
        setChatChangeListener(name, holder, position)
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

    private fun setChatChangeListener(friend: String, holder: FriendViewHolder, position: Int){
        RealtimeUtil.chatReference.child(LoginStatusUtil.getUserName()).child(friend).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                initHolderView(holder, position)
            }

            override fun onCancelled(error: DatabaseError) {
                ToastUtil.showShortMessage(mContext.getString(R.string.hint_net_error), mContext)
            }
        })

    }

    private fun initHolderView(holder: FriendViewHolder, position: Int){
        val dataSlice = data[position]
        val friend = dataSlice.friend
        holder.name.text = friend!!.userName
        holder.message.text = TextUtil.getMaxLengthString(18, dataSlice.lastMessage.toString())
        holder.date.text = TextUtil.getDate(dataSlice.date!!)
        if(null != dataSlice.notice && dataSlice.notice){
            holder.notice.visibility = View.VISIBLE
        }else{
            holder.notice.visibility = View.INVISIBLE
        }
        holder.background.setOnClickListener {
            val intent = Intent(mContext, ChatActivity::class.java)
            intent.putExtra(ConstantUtil.ENTITY_CHAT, dataSlice)
            mContext.startActivity(intent)
        }
    }
}