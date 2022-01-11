package com.grindewald1900.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grindewald1900.shereats.model.entity.FirebaseOrderItem
import com.grindewald1900.shereats.model.entity.Order
import com.grindewald1900.shereats.utils.LoginStatusUtil
import com.grindewald1900.shereats.utils.firebase.RealtimeUtil
import retrofit2.Call

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