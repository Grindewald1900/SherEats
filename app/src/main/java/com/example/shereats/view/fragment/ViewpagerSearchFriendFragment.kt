package com.example.shereats.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.shereats.R
import com.example.shereats.model.entity.FirebaseChat
import com.example.shereats.model.entity.FirebaseUser
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.LoginStatusUtil
import com.example.shereats.utils.firebase.RealtimeUtil
import com.example.shereats.utils.firebase.StorageUtil
import com.example.shereats.view.custom.RoundCornerImageView
import com.example.shereats.view.custom.TransitionButton

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
            val chat = FirebaseChat(friend,"", ConstantUtil.CURRENT_TIME, false)
            RealtimeUtil.chatReference.child(name).child(friend.userName!!).setValue(chat)
        }
    }
}