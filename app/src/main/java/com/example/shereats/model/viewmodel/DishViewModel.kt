package com.example.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shereats.model.entity.Dish
import com.example.shereats.utils.network.EndPointInterface
import com.example.shereats.utils.network.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DishViewModel : BaseViewModel() {
    private var dishes: MutableLiveData<List<Dish>> = MutableLiveData()
    private lateinit var call: Call<List<Dish>>

    fun getDishes(): LiveData<List<Dish>> {
        return dishes
    }

    fun setDishes(){
        // The parameter for getDishes(): larger than 0 will return a certain dish with id, otherwise return all the dishes
        call = request.getDishes(0, 0,"")

        call.enqueue(object: Callback<List<Dish>>{
            override fun onResponse(call: Call<List<Dish>>, response: Response<List<Dish>>) {
                if(response.isSuccessful){
                    dishes.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<Dish>>, t: Throwable) {
                t.stackTrace
            }

        })
    }
}