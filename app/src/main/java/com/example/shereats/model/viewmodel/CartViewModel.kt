package com.example.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shereats.model.entity.*
import com.example.shereats.utils.LoginStatusUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartViewModel : BaseViewModel() {
    private var orderItems: MutableLiveData<List<FirebaseOrderItem>> = MutableLiveData()
    private var totalPrice: MutableLiveData<Double> = MutableLiveData()
    private lateinit var call: Call<IntResult>
    private var isSuccess: MutableLiveData<IntResult> = MutableLiveData()


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

    fun getIsUploadSuccess(): LiveData<IntResult>{
        return isSuccess
    }

//    fun uploadOrderItems(){
//        val data = SingletonUtil.getCurrentCart()
//        call = request.addOrder(data, 1)
//
//        call.enqueue(object: Callback<IntResult>{
//            override fun onResponse(call: Call<IntResult>, response: Response<IntResult>) {
//                if(response.isSuccessful){
//                    isSuccess.postValue(response.body())
//                }
//            }
//
//            override fun onFailure(call: Call<IntResult>, t: Throwable) {
//                t.stackTrace
//            }
//
//        })
//    }
}