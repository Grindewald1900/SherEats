package com.example.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shereats.model.entity.FirebaseDish
import com.example.shereats.utils.EntityUtil
import com.example.shereats.utils.firebase.RealtimeUtil



/**
 * Created by Yee on 2021-12-17.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class SearchViewModel: BaseViewModel() {
    private var dishes: MutableLiveData<List<FirebaseDish>> = MutableLiveData()
    private var searchResult: MutableLiveData<List<FirebaseDish>> = MutableLiveData()
    private var allDish: MutableList<FirebaseDish> = mutableListOf()

    fun getDishes(): LiveData<List<FirebaseDish>>{
        return dishes
    }

    fun setAllDishes(){
        val firebaseDish: MutableList<FirebaseDish> = mutableListOf()

        RealtimeUtil.dishReference.get().addOnSuccessListener { it ->
            if(it.childrenCount > 0){
                it.children.forEach {
                    firebaseDish.add(it.getValue(FirebaseDish::class.java)!!)
                }
                allDish = firebaseDish
                dishes.postValue(firebaseDish)
            }
        }.addOnFailureListener {
            it.stackTrace
        }
    }


    fun getSearchResult(): MutableLiveData<List<FirebaseDish>>{
        return searchResult
    }


    /**
     * @param type: the type to search {RESTAURANT, DISH, CUISINE, LOCATION, ALL}
     * @param keyword: searching keyword
     */
    fun setSearchResult(keyword: String,  isRestaurant: Boolean, isDish: Boolean, isCuisine: Boolean, isLocation: Boolean){
        val dishList: List<FirebaseDish> = allDish
        var searchedList: MutableList<FirebaseDish> = mutableListOf()
        if(keyword.isEmpty()){
            setAllDishes()
            return
        }
        if(isRestaurant){
            searchedList.addAll(EntityUtil.searchDishByRestaurant(dishList, keyword))
        }
        if(isDish){
            searchedList.addAll(EntityUtil.searchDishByDish(dishList, keyword))
        }
        if(isCuisine){
            searchedList.addAll(EntityUtil.searchDishByCuisine(dishList, keyword))
        }
        searchedList = EntityUtil.getUniqueDish(searchedList)
        dishes.postValue(searchedList)
    }

    enum class SearchType{
        RESTAURANT, DISH, CUISINE, LOCATION, ALL
    }
}