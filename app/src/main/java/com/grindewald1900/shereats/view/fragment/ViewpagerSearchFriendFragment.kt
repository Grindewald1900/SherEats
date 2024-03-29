package com.grindewald1900.shereats.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.grindewald1900.shereats.R
import com.grindewald1900.shereats.model.entity.FirebaseChat
import com.grindewald1900.shereats.model.entity.FirebaseUser
import com.grindewald1900.shereats.utils.ConstantUtil
import com.grindewald1900.shereats.utils.LoginStatusUtil
import com.grindewald1900.shereats.utils.firebase.RealtimeUtil
import com.grindewald1900.shereats.utils.firebase.StorageUtil
import com.grindewald1900.shereats.view.custom.RoundCornerImageView
import com.grindewald1900.shereats.view.custom.TransitionButton

class ViewpagerSearchFriendFragment(val user: FirebaseUser): Fragment() {
    private lateinit var tvTitle: TextView
    private lateinit var ivImage: RoundCornerImageView
    private lateinit var btnAdd: TransitionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.view_viewpager_search_friend, container, false)
        tvTitle = view.findViewById(R.id.tv_viewpager_title_friend)
        ivImage = view.findViewById(R.id.iv_viewpager_search_friend)
        btnAdd = view.findViewById(R.id.btn_viewpager_search_friend)
        tvTitle.text = user.userName
        btnAdd.setOnClickListener {
            addFriend(user)
        }
        setProfileImage(user.userName!!, ivImage)
        return view
    }

    private fun setProfileImage(name: String, view: ImageView){
        val childPath = "user/$name.jpg"
        val pathReference = StorageUtil.reference.child(childPath)
        pathReference.downloadUrl.addOnSuccessListener {
            Glide.with(this)
                .asBitmap()
                .placeholder(R.drawable.img_user_unknown)
                .load(it.toString())
                .into(view)
        }
    }

    private fun addFriend(friend: FirebaseUser){
        if(LoginStatusUtil.isLogin()){
            val name = LoginStatusUtil.getUserName()
            val chatUser = FirebaseChat(friend,"",ConstantUtil.CURRENT_TIME, false)
            val chatFriend = FirebaseChat(LoginStatusUtil.getUser(),"",ConstantUtil.getCurrentTime(), false)
            RealtimeUtil.chatReference.child(friend.userName!!).child(name).setValue(chatFriend)
            RealtimeUtil.chatReference.child(name).child(friend.userName).setValue(chatUser)
        }
    }
}