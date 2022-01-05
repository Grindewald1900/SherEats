package com.example.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shereats.model.entity.FirebaseChat
import com.example.shereats.model.entity.FirebaseUser
import com.example.shereats.model.entity.SingletonUtil
import com.example.shereats.utils.firebase.RealtimeUtil

class FriendViewModel : BaseViewModel() {
    private var friends: MutableLiveData<List<FirebaseUser>> = MutableLiveData()

    fun getFriends(): LiveData<List<FirebaseUser>>{
        return friends
    }

    fun setFriends(name: String){
        RealtimeUtil.chatReference.child(name).get().addOnSuccessListener { it ->
            val users: MutableList<FirebaseUser> = mutableListOf()
            if (it.childrenCount > 0){
                it.children.forEach { chat  ->
                    users.add(chat.getValue(FirebaseChat::class.java)?.friend!!)
                }
                friends.postValue(users)
                SingletonUtil.setFriendList(users)
            }
        }
    }
}