package com.grindewald1900.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grindewald1900.shereats.model.entity.*
import com.grindewald1900.shereats.utils.LoginStatusUtil
import com.grindewald1900.shereats.utils.firebase.RealtimeUtil

class CartViewModel : BaseViewModel() {
    private var orderItems: MutableLiveData<List<FirebaseOrderItem>> = MutableLiveData()
    private var totalPrice: MutableLiveData<Double> = MutableLiveData()
    private var isSuccess: MutableLiveData<Boolean> = MutableLiveData()


    fun getOrderItems(): LiveData<List<FirebaseOrderItem>>{
        return orderItems
    }

    fun setOrderItems(){
        orderItems.postValue(SingletonUtil.getCurrentCart())
    }

    fun getTotalPrice(): LiveData<Double>{
        return totalPrice
    }

    fun setTotalPrice(){
        val price = SingletonUtil.getPrice()
        totalPrice.postValue(price)
    }

    fun getIsUploadSuccess(): LiveData<Boolean>{
        return isSuccess
    }

    fun setIsUploadSuccess(success: Boolean){
        isSuccess.postValue(success)
    }
    fun uploadOrderItems() {
        val data = SingletonUtil.getCurrentCart()
        val orderId: String = data[0].orderId!!
        val id = LoginStatusUtil.getUserId()
        RealtimeUtil.orderItemReference.child(id).child(orderId).setValue(data).addOnSuccessListener {
            setIsUploadSuccess(true)
        }.addOnFailureListener {
            setIsUploadSuccess(false)
        }
    }

}