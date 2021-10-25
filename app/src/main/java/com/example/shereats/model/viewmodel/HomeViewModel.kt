package com.example.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shereats.model.entity.Restaurant
import java.util.EnumSet.range

class HomeViewModel : ViewModel() {
    private val restaurants: MutableLiveData<List<Restaurant>> = MutableLiveData()

    fun getRestaurant(): LiveData<List<Restaurant>> {
        return restaurants
    }
    fun setRestaurant() {
        var list: MutableList<Restaurant> = mutableListOf()
        for (i in 1..10){
            list.add(Restaurant(i, "Rest", "Oxford", "A", 12f, "819 000 0088", 12.1f, 13.1f, false))
        }
        restaurants.postValue(list)
    }
}