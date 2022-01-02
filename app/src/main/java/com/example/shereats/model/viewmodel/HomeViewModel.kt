package com.example.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.shereats.model.entity.FirebaseRestaurant
import com.example.shereats.model.entity.FirebaseUser
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.LoginStatusUtil
import com.example.shereats.utils.firebase.RealtimeUtil
import com.example.shereats.utils.network.EndPointInterface
import com.example.shereats.utils.network.ServiceBuilder
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.EnumSet.range

class HomeViewModel : BaseViewModel() {
    private val backgroundState: MutableLiveData<Int> = MutableLiveData()
    private var state: MutableLiveData<Int> = MutableLiveData()

    private val firebaseRestaurant: MutableLiveData<List<FirebaseRestaurant>> = MutableLiveData()


//    fun getRestaurant(): LiveData<List<Restaurant>> {
//        return restaurants
//    }
//    fun setRestaurant(id: Int, daoType: Int) {
//        call = request.getRestaurants(id, daoType)
//        call.enqueue(object : Callback<List<Restaurant>> {
//            override fun onResponse(
//                call: Call<List<Restaurant>>, response: Response<List<Restaurant>>
//            ) {
//                if (response.isSuccessful) {
//                    backgroundState.postValue(ConstantUtil.BACKGROUND_STATE_NORMAL)
//                    restaurants.postValue(response.body())
//                }else{
//                    backgroundState.postValue(ConstantUtil.BACKGROUND_STATE_NETWORK_ERROR)
//                }
//            }
//            override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
//                t.stackTrace
//            }
//        })
//    }

    fun getFirebaseRestaurant(): LiveData<List<FirebaseRestaurant>> {
        return firebaseRestaurant
    }
    fun setAllFirebaseRestaurant() {
        val firebaseRestaurants: MutableList<FirebaseRestaurant> = mutableListOf()

        RealtimeUtil.restaurantReference.get().addOnSuccessListener { it ->
            if(it.childrenCount > 0){
                it.children.forEach {
                    firebaseRestaurants.add(it.getValue(FirebaseRestaurant::class.java)!!)
                }
                firebaseRestaurant.postValue(firebaseRestaurants)
            }
        }.addOnFailureListener {
            it.stackTrace
        }
    }

    fun getIsNetworkSuccess(): LiveData<Int>{
        return backgroundState
    }

    fun setIsNetworkSuccess(state: Int){
        backgroundState.postValue(state)
    }


    fun getState(): LiveData<Int>{
        return state
    }

    fun setState(state: Int){
        this.state.postValue(state)
    }
}


