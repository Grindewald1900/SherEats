package com.example.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.shereats.model.entity.Restaurant
import com.example.shereats.utils.network.EndPointInterface
import com.example.shereats.utils.network.ServiceBuilder
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.EnumSet.range

class HomeViewModel : BaseViewModel() {
    private val restaurants: MutableLiveData<List<Restaurant>> = MutableLiveData()
    private lateinit var call: Call<List<Restaurant>>

    fun getRestaurant(): LiveData<List<Restaurant>> {
        return restaurants
    }
    fun setRestaurant(id: Int, daoType: Int, swipe: SwipeRefreshLayout) {
        call = request.getRestaurants(id, daoType)

        call.enqueue(object : Callback<List<Restaurant>> {
            override fun onResponse(
                call: Call<List<Restaurant>>, response: Response<List<Restaurant>>
            ) {
                swipe.isRefreshing = false
                if (response.isSuccessful) {
                    restaurants.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                swipe.isRefreshing = false
                t.stackTrace
            }
        })

    }
}


