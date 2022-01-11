package com.grindewald1900.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grindewald1900.shereats.model.entity.FirebaseDish
import com.grindewald1900.shereats.utils.ConstantUtil
import com.grindewald1900.shereats.utils.firebase.RealtimeUtil

class DishViewModel : BaseViewModel() {
    private var state: MutableLiveData<Int> = MutableLiveData()
    private var dishes: MutableLiveData<List<FirebaseDish>> = MutableLiveData()
    private var sort = ConstantUtil.SORT_BY_PRICE // By default, we provide the dishes with promotion at first

    fun resetDishes(data: List<FirebaseDish>){
        dishes.postValue(data)
    }

    fun getFirebaseDish(): LiveData<List<FirebaseDish>> {
        return dishes
    }
    fun setAllFirebaseDish() {
        val firebaseDish: MutableList<FirebaseDish> = mutableListOf()

        RealtimeUtil.dishReference.get().addOnSuccessListener { it ->
            if(it.childrenCount > 0){
                it.children.forEach {
                    firebaseDish.add(it.getValue(FirebaseDish::class.java)!!)
                }
                setState(ConstantUtil.STATE_SUCCESS)
                dishes.postValue(firebaseDish)
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