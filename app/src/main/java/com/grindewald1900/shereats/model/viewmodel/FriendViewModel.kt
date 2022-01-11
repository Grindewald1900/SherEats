package com.grindewald1900.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grindewald1900.shereats.model.entity.FirebaseChat
import com.grindewald1900.shereats.model.entity.FirebaseUser
import com.grindewald1900.shereats.model.entity.SingletonUtil
import com.grindewald1900.shereats.utils.firebase.RealtimeUtil

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