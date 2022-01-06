package com.example.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shereats.model.entity.FirebaseChat
import com.example.shereats.model.entity.FirebaseUser
import com.example.shereats.model.entity.SingletonUtil
import com.example.shereats.utils.firebase.RealtimeUtil

class FriendViewModel : BaseViewModel() {
    private var chats: MutableLiveData<List<FirebaseChat>> = MutableLiveData()

    fun getChats(): LiveData<List<FirebaseChat>>{
        return chats
    }

    fun setChats(name: String){
        RealtimeUtil.chatReference.child(name).get().addOnSuccessListener { it ->
            val userChat: MutableList<FirebaseChat> = mutableListOf()
            val friends: MutableList<FirebaseUser> = mutableListOf()
            if (it.childrenCount > 0){
                it.children.forEach { chat  ->
                    val tempChat = chat.getValue(FirebaseChat::class.java)
                    userChat.add(tempChat!!)
                    friends.add(tempChat.friend!!)
                }
                chats.postValue(userChat)
                SingletonUtil.setFriendList(friends)
            }
        }
    }
}