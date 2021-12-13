package com.example.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shereats.model.entity.User
import com.example.shereats.utils.LoginStatusUtil
import com.example.shereats.utils.network.EndPointInterface
import com.example.shereats.utils.network.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Yee on 2021-12-12.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class LoginViewModel: BaseViewModel() {
    private lateinit var call: Call<List<User>>
    private var user: MutableLiveData<List<User>> = MutableLiveData()

    fun getUser(): LiveData<List<User>>{
        return user
    }

    /**
     * Retrieve data from server
     */
    fun setUser(name: String, password: String){
        call = request.getUser(name, password)

        call.enqueue(object: Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                user.postValue(response.body())
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                t.stackTrace
            }

        })

    }
}