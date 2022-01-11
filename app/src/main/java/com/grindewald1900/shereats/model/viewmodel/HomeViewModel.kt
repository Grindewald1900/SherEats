package com.grindewald1900.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grindewald1900.shereats.model.entity.FirebaseRestaurant
import com.grindewald1900.shereats.utils.ConstantUtil
import com.grindewald1900.shereats.utils.firebase.RealtimeUtil

class HomeViewModel : BaseViewModel() {
    private var state: MutableLiveData<Int> = MutableLiveData()
    private val firebaseRestaurant: MutableLiveData<List<FirebaseRestaurant>> = MutableLiveData()


    fun getFirebaseRestaurant(): LiveData<List<FirebaseRestaurant>> {
        return firebaseRestaurant
    }
    fun setAllFirebaseRestaurant() {
        val firebaseRestaurants: MutableList<FirebaseRestaurant> = mutableListOf()

        RealtimeUtil.restaurantReference.get().addOnSuccessListener { it ->
            if(it.childrenCount > 0){
                it.children.forEach {
                    firebaseRestaurants.add(it.getValue(FirebaseRestaurant::class.java)!!)
                }
                setState(ConstantUtil.STATE_SUCCESS)
                firebaseRestaurant.postValue(firebaseRestaurants)
            }
        }.addOnFailureListener {
            setState(ConstantUtil.STATE_FAIL)
            it.stackTrace
        }
    }


    fun getState(): LiveData<Int>{
        return state
    }

    fun setState(state: Int){
        this.state.postValue(state)
    }
}


