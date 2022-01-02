package com.example.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shereats.model.entity.Order
import com.example.shereats.model.entity.OrderItem
import com.example.shereats.utils.EntityUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderViewModel : BaseViewModel() {
    private var orders: MutableLiveData<List<Order>> = MutableLiveData()
    private lateinit var call: Call<List<OrderItem>>

    fun getOrders(): LiveData<List<Order>> {
        return orders
    }

    fun setOrders(id: String){
        call = request.getOrderByUserId(id, 3)

        call.enqueue(object: Callback<List<OrderItem>>{
            override fun onResponse(
                call: Call<List<OrderItem>>,
                response: Response<List<OrderItem>>
            ) {
                if (response.isSuccessful && response.body()!!.isNotEmpty()){
                    val order = EntityUtil.getOrderFromItem(response.body()!!)
                    orders.postValue(order)
                }
            }
            override fun onFailure(call: Call<List<OrderItem>>, t: Throwable) {
                t.stackTrace
            }

        })
    }
}