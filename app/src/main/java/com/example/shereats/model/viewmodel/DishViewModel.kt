package com.example.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.shereats.model.entity.Dish
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.EntityUtil
import com.example.shereats.utils.network.EndPointInterface
import com.example.shereats.utils.network.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DishViewModel : BaseViewModel() {
    private var dishes: MutableLiveData<List<Dish>> = MutableLiveData()
    private lateinit var call: Call<List<Dish>>
    private var sort = ConstantUtil.SORT_BY_PRICE // By default, we provide the dishes with promotion at first

    fun getDishes(): LiveData<List<Dish>> {
        return dishes
    }

    fun setDishes(swipe: SwipeRefreshLayout){
        // The parameter for getDishes(): larger than 0 will return a certain dish with id, otherwise return all the dishes
        call = request.getDishes(0, 0,"")

        call.enqueue(object: Callback<List<Dish>>{
            override fun onResponse(call: Call<List<Dish>>, response: Response<List<Dish>>) {
                swipe.isRefreshing = false
                if(response.isSuccessful && response.body()!!.isNotEmpty()){
                    val data: List<Dish>
                    when(sort){
                        ConstantUtil.SORT_BY_ID ->{}
                        ConstantUtil.SORT_BY_PRICE ->{
                            data = EntityUtil.sortDishByPrice(response.body()!!.toMutableList(), true)
                            dishes.postValue(data)
                        }
                        ConstantUtil.SORT_BY_PROMO -> {
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Dish>>, t: Throwable) {
                t.stackTrace
            }

        })
    }

    fun resetDishes(data: List<Dish>){
        dishes.postValue(data)
    }
}