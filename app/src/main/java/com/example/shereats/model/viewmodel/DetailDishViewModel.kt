package com.example.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shereats.model.entity.Restaurant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailDishViewModel: BaseViewModel() {
    private var restaurants: MutableLiveData<List<Restaurant>> = MutableLiveData()
    private lateinit var call: Call<List<Restaurant>>

    fun getRestaurant(): LiveData<List<Restaurant>>{
        return restaurants
    }

    fun setRestaurant(id: Int){
        call = request.getRestaurants(id, 2)
        call.enqueue(object: Callback<List<Restaurant>>{
            override fun onResponse(
                call: Call<List<Restaurant>>,
                response: Response<List<Restaurant>>
            ) {
                if(response.isSuccessful){
                    restaurants.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                t.stackTrace
            }
        })
    }
}