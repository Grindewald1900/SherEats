package com.example.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.shereats.model.entity.Restaurant
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.network.EndPointInterface
import com.example.shereats.utils.network.ServiceBuilder
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.EnumSet.range

class HomeViewModel : BaseViewModel() {
    private val backgroundState: MutableLiveData<Int> = MutableLiveData()
    private val restaurants: MutableLiveData<List<Restaurant>> = MutableLiveData()
    private lateinit var call: Call<List<Restaurant>>

    fun getRestaurant(): LiveData<List<Restaurant>> {
        return restaurants
    }
    fun setRestaurant(id: Int, daoType: Int) {
        call = request.getRestaurants(id, daoType)
        call.enqueue(object : Callback<List<Restaurant>> {
            override fun onResponse(
                call: Call<List<Restaurant>>, response: Response<List<Restaurant>>
            ) {
                if (response.isSuccessful) {
                    backgroundState.postValue(ConstantUtil.BACKGROUND_STATE_NORMAL)
                    restaurants.postValue(response.body())
                }else{
                    backgroundState.postValue(ConstantUtil.BACKGROUND_STATE_NETWORK_ERROR)
                }
            }
            override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                t.stackTrace
            }
        })
    }

    fun getIsNetworkSuccess(): LiveData<Int>{
        return backgroundState
    }

    fun setIsNetworkSuccess(state: Int){
        backgroundState.postValue(state)
    }
}


