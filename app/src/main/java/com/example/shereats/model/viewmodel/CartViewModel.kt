package com.example.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shereats.model.entity.Dish
import com.example.shereats.model.entity.OrderItem
import com.example.shereats.model.entity.SingletonUtil
import com.example.shereats.utils.LoginStatusUtil

class CartViewModel : ViewModel() {
    private var orderItems: MutableLiveData<List<OrderItem>> = MutableLiveData()
    private var totalPrice: MutableLiveData<Float> = MutableLiveData()

    fun getOrderItems(): LiveData<List<OrderItem>>{
        return orderItems
    }

    fun setOrderItems(){
        val list: MutableList<OrderItem> = mutableListOf()
        val orderHashMap = SingletonUtil.CURRENT_ORDER

        orderHashMap.forEach {(_, value) ->
            list.add(value)
        }
        orderItems.postValue(list)
    }

    fun getTotalPrice(): LiveData<Float>{
        return totalPrice
    }

    fun setTotalPrice(){
        val price = SingletonUtil.getPrice()
        totalPrice.postValue(price)
    }
}