package com.grindewald1900.shereats.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grindewald1900.shereats.R
import com.grindewald1900.shereats.model.entity.FirebaseChat
import com.grindewald1900.shereats.model.entity.FirebaseUser
import com.grindewald1900.shereats.model.entity.SingletonUtil
import com.grindewald1900.shereats.utils.ConstantUtil
import com.grindewald1900.shereats.utils.LoginStatusUtil
import com.grindewald1900.shereats.utils.firebase.RealtimeUtil
import com.grindewald1900.shereats.utils.firebase.StorageUtil
import com.grindewald1900.shereats.view.custom.TransitionButton

class UserAdapter(val data: List<FirebaseUser>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private lateinit var mContext: Context
    class UserViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image: ImageView = view.findViewById(R.id.iv_adapter_user)
        val name: TextView = view.findViewById(R.id.tv_adapter_user)
        val btnAdd: TransitionButton = view.findViewById(R.id.btn_adapter_user_add)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.view_adapter_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val dataSlice = data[position]
        holder.name.text = dataSlice.userName
        holder.btnAdd.setOnClickListener {
            holder.btnAdd.startAnimation()
            addFriend(dataSlice)
        }
        if(SingletonUtil.getFriendList().contains(dataSlice.userName)){
            holder.btnAdd.visibility = View.GONE
        }else{
            holder.btnAdd.visibility = View.VISIBLE
        }
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

    private fun addFriend(friend: FirebaseUser){
        if(LoginStatusUtil.isLogin()){
            val name = LoginStatusUtil.getUserName()
            val chatUser = FirebaseChat(friend,"",ConstantUtil.getCurrentTime(), false)
            val chatFriend = FirebaseChat(LoginStatusUtil.getUser(),"",ConstantUtil.CURRENT_TIME, false)
            RealtimeUtil.chatReference.child(friend.userName!!).child(name).setValue(chatFriend)
            RealtimeUtil.chatReference.child(name).child(friend.userName).setValue(chatUser)
        }
    }
}