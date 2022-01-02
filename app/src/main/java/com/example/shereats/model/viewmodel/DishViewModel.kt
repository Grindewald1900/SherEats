package com.example.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.shereats.model.entity.FirebaseDish
import com.example.shereats.model.entity.FirebaseRestaurant
import com.example.shereats.model.entity.SingletonUtil
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.EntityUtil
import com.example.shereats.utils.firebase.RealtimeUtil
import com.example.shereats.utils.network.EndPointInterface
import com.example.shereats.utils.network.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DishViewModel : BaseViewModel() {
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
                dishes.postValue(firebaseDish)
            }
        }.addOnFailureListener {
            it.stackTrace
        }
    }
}