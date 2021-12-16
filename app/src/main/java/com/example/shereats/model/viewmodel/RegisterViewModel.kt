package com.example.shereats.model.viewmodel

import android.provider.ContactsContract
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Yee on 2021-12-15.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class RegisterViewModel: BaseViewModel() {
    private lateinit var call: Call<String>
    private var isSuccess: MutableLiveData<String> = MutableLiveData()

    fun getIsSuccess(): MutableLiveData<String>{
        return isSuccess
    }

    fun register(id: String, name: String, pwd: String, email: String){
        call = request.register(id, name, pwd, email)
        call.enqueue(object: Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                isSuccess.postValue(response.body())
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                t.stackTrace
            }

        })
    }
}