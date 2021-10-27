package com.example.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shereats.model.entity.Dish

class DishViewModel : ViewModel() {
    private var dishes: MutableLiveData<List<Dish>> = MutableLiveData()

    fun getDishes(): LiveData<List<Dish>> {
        return dishes
    }

    fun setDishes(){
        val list: MutableList<Dish> = mutableListOf()
        for (i in 1 .. 10){
            list.add(Dish(i, i, "McDonald", 15f, " ", 4f, 0.1f))
        }
        dishes.postValue(list)
    }
}