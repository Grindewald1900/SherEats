package com.example.shereats.model.viewmodel

import android.provider.ContactsContract
import androidx.lifecycle.MutableLiveData
import com.example.shereats.model.entity.IntResult
import com.example.shereats.utils.network.EndPointInterface
import com.example.shereats.utils.network.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Yee on 2021-12-15.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class RegisterViewModel: BaseViewModel() {
    private lateinit var call: Call<IntResult>
    private var isSuccess: MutableLiveData<IntResult> = MutableLiveData()

    fun getIsSuccess(): MutableLiveData<IntResult>{
        return isSuccess
    }

    fun register(id: String, name: String, pwd: String, email: String){
//        request = ServiceBuilder.ServiceBuilder.buildScalarService(EndPointInterface::class.java)
        call = request.register(id, name, pwd, email)
        call.enqueue(object: Callback<IntResult>{
            override fun onResponse(call: Call<IntResult>, response: Response<IntResult>) {
                isSuccess.postValue(response.body())
            }

            override fun onFailure(call: Call<IntResult>, t: Throwable) {
                t.stackTrace
            }

        })
    }
}