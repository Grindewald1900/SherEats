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
    }
}