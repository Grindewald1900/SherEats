package com.example.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.shereats.model.entity.FirebaseDish
import com.example.shereats.model.entity.FirebaseRestaurant
import com.example.shereats.model.entity.SingletonUtil
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.EntityUtil
import com.example.shereats.utils.firebase.RealtimeUtil
import com.example.shereats.utils.network.EndPointInterface
import com.example.shereats.utils.network.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DishViewModel : BaseViewModel() {
    private var dishes: MutableLiveData<List<FirebaseDish>> = MutableLiveData()
    private lateinit var call: Call<List<FirebaseDish>>
    private var sort = ConstantUtil.SORT_BY_PRICE // By default, we provide the dishes with promotion at first

//    fun getDishes(): LiveData<List<FirebaseDish>> {
//        return dishes
//    }
//
//    fun setDishes(swipe: SwipeRefreshLayout){
//        // The parameter for getDishes(): larger than 0 will return a certain dish with id, otherwise return all the dishes
//        call = request.getDishes(0, 0,"")
//
//        call.enqueue(object: Callback<List<FirebaseDish>>{
//            override fun onResponse(call: Call<List<FirebaseDish>>, response: Response<List<FirebaseDish>>) {
//                swipe.isRefreshing = false
//                if(response.isSuccessful && response.body()!!.isNotEmpty()){
//                    val data: List<FirebaseDish>
//                    when(sort){
//                        ConstantUtil.SORT_BY_ID ->{}
//                        ConstantUtil.SORT_BY_PRICE ->{
//                            data = EntityUtil.sortDishByPrice(response.body()!!.toMutableList(), true)
//                            dishes.postValue(data)
//                        }
//                        ConstantUtil.SORT_BY_PROMO -> {
//                        }
//                    }
//                    response.body()!!.forEach {
//                        SingletonUtil.MAP_FAVORITE_DISH[it.item_id] = false
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<List<FirebaseDish>>, t: Throwable) {
//                t.stackTrace
//            }
//
//        })
//    }

    fun resetDishes(data: List<FirebaseDish>){
        dishes.postValue(data)
    }

    fun getFirebaseDish(): LiveData<List<FirebaseDish>> {
        return dishes
    }
    fun setAllFirebaseDish() {
        val firebaseDish: MutableList<FirebaseDish> = mutableListOf()

        RealtimeUtil.dishReference.get().addOnSuccessListener { it ->
            if(it.childrenCount > 0){
                it.children.forEach {
                    firebaseDish.add(it.getValue(FirebaseDish::class.java)!!)
                }
                dishes.postValue(firebaseDish)
            }
        }.addOnFailureListener {
            it.stackTrace
        }
    }
}