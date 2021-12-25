package com.example.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shereats.model.entity.Dish
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Yee on 2021-12-17.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class SearchViewModel: BaseViewModel() {
    private var dishes: MutableLiveData<List<Dish>> = MutableLiveData()
    private var searchResult: MutableLiveData<List<Dish>> = MutableLiveData()
    private lateinit var call: Call<List<Dish>>

    fun getDishes(): LiveData<List<Dish>>{
        return dishes
    }

    fun getSearchResult(): MutableLiveData<List<Dish>>{
        return searchResult
    }

    fun setDishes(count: Int){
        call = request.getDishes(0, count, "")
        call.enqueue(object: Callback<List<Dish>>{
            override fun onResponse(call: Call<List<Dish>>, response: Response<List<Dish>>) {
                if (response.isSuccessful){
                    dishes.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<Dish>>, t: Throwable) {
                t.stackTrace
            }
        })
    }

    /**
     * @param count: the number of rows to be returned
     * @param keyword: searching keyword
     */
    fun setSearchResult(count: Int, keyword: String, isRestaurant: Boolean, isDish: Boolean, isCuisine: Boolean, isLocation: Boolean, isAll: Boolean){
        call = if(isAll){
            request.getDishes(0, 0, keyword)
        }else{
            request.getDishes(0, 0, keyword, typeRestaurant = isRestaurant, typeDish = isDish, typeCuisine = isCuisine, typeLocation = isLocation, typeAll = false)
        }
        call.enqueue(object: Callback<List<Dish>>{
            override fun onResponse(call: Call<List<Dish>>, response: Response<List<Dish>>) {
                if (response.isSuccessful){
                    searchResult.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<List<Dish>>, t: Throwable) {
                t.stackTrace
            }
        })
    }


}