package com.example.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shereats.model.entity.Dish
import com.example.shereats.model.entity.OrderItem

class CartViewModel : ViewModel() {
    private var orders: MutableLiveData<List<OrderItem>> = MutableLiveData()

    fun getOrders(): LiveData<List<OrderItem>>{
        return orders
    }

    fun setOrders(){
        val list: MutableList<OrderItem> = mutableListOf()
        for (i in 1 .. 10){
            list.add(OrderItem(Dish(i, "McDonald", i, "Burger", "Fast",15f, " ", 4f, 0.1f, 5f, 4f), i))
        }
        orders.postValue(list)
    }
}