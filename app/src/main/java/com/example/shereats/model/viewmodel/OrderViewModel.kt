package com.example.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shereats.model.entity.FirebaseOrderItem
import com.example.shereats.model.entity.Order
import com.example.shereats.utils.EntityUtil
import com.example.shereats.utils.LoginStatusUtil
import com.example.shereats.utils.firebase.RealtimeUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderViewModel : BaseViewModel() {
    private var firebaseOrders: MutableLiveData<List<Order>> = MutableLiveData()
    private lateinit var call: Call<List<FirebaseOrderItem>>

    fun getOrders(): LiveData<List<Order>> {
        return firebaseOrders
    }

    fun setOrders(){
        val orders: MutableList<Order> = mutableListOf()
        val id = LoginStatusUtil.getUserId()
        RealtimeUtil.orderItemReference.child(id).get().addOnSuccessListener { userOrders ->
            // One user may have more than one orders
            if (userOrders.childrenCount > 0){
                // One order may have more than one orderItems
                userOrders.children.forEach { userOrder ->
                    val order = Order()
                    val items: MutableList<FirebaseOrderItem> = mutableListOf()
                    var totalPrice = 0.0
                    if(userOrder.childrenCount > 0){
                        userOrder.children.forEach { userOrderItem ->
                            val  orderItem = userOrderItem.getValue(FirebaseOrderItem::class.java)
                            totalPrice += orderItem?.orderPrice!! * orderItem.itemAmount!!
                            items.add(orderItem)
                        }
                    }
                    order.id = items[0].orderId!!
                    order.time = items[0].uploadTime
                    order.userName = LoginStatusUtil.getUserName()
                    order.price = totalPrice
                    order.items = items
                    orders.add(order)
                }
            }
            firebaseOrders.postValue(orders)
        }.addOnFailureListener {}
    }
}