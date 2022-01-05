package com.example.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shereats.model.entity.FirebaseDish
import com.example.shereats.model.entity.FirebaseUser
import com.example.shereats.model.entity.SingletonUtil
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.LoginStatusUtil
import com.example.shereats.utils.firebase.RealtimeUtil
import kotlin.math.min
import kotlin.random.Random

class SearchFriendViewModel: BaseViewModel() {
    private var recommendFriends: MutableLiveData<List<FirebaseUser>> = MutableLiveData()
    private var searchFriends: MutableLiveData<List<FirebaseUser>> = MutableLiveData()
    private var unFilteredFriends: MutableList<FirebaseUser> = mutableListOf()

    fun getRecFriend(): LiveData<List<FirebaseUser>> {
        return recommendFriends
    }

    fun getSearchFriend(): LiveData<List<FirebaseUser>> {
        return searchFriends
    }

    /**
     * Retrieve data from server
     */
    fun setFriends(){
        val friends: MutableList<FirebaseUser> = mutableListOf()
        RealtimeUtil.userReference.get().addOnSuccessListener { users ->
            if(users.childrenCount > 0){
                users.children.forEach { user ->
                    friends.add(user.getValue(FirebaseUser::class.java)!!)
                }
            }
            unFilteredFriends.clear()
            unFilteredFriends.addAll(friends)
            setRecommendFriends(10)
        }.addOnFailureListener {
            it.stackTrace
        }
    }

    private fun setRecommendFriends(count: Int){
        // Get a random user list with size of count
        val result: MutableList<FirebaseUser> = mutableListOf()
        val searchSize = min(count, unFilteredFriends.size)
        for (i in 0 until searchSize){
            if(!SingletonUtil.getFriendList().contains(unFilteredFriends[i].userName)){
                result.add(unFilteredFriends[i])
            }
        }

        recommendFriends.postValue(result)
    }

    fun setSearchFriends(keyword: String){
        val result: MutableList<FirebaseUser> = mutableListOf()
        unFilteredFriends.forEach {
            if (it.userName!!.contains(keyword)){
                result.add(it)
            }
        }
        searchFriends.postValue(result)
    }

}