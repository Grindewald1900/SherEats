package com.example.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shereats.model.entity.Order

class OrderViewModel : ViewModel() {
    private var orders: MutableLiveData<List<Order>> = MutableLiveData()

    fun getOrders(): LiveData<List<Order>> {
        return orders
    }

    fun setOrders(){
        val list: MutableList<Order> = mutableListOf()
        for (i in 1 .. 10){
            list.add(Order(i, "Yee", i, "2021-11-19", 19f, "911 000 0001", "aaa", 3, 5, 4))
        }
        orders.postValue(list)
    }
}