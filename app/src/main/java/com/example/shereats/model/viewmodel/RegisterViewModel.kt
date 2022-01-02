package com.example.shereats.model.viewmodel

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shereats.model.entity.FirebaseUser
import com.example.shereats.model.entity.IntResult
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.LoginStatusUtil
import com.example.shereats.utils.firebase.RealtimeUtil
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
    private var state: MutableLiveData<Int> = MutableLiveData()

    fun getIsSuccess(): MutableLiveData<IntResult>{
        return isSuccess
    }

//    fun register(id: String, name: String, pwd: String, email: String){
////        request = ServiceBuilder.ServiceBuilder.buildScalarService(EndPointInterface::class.java)
//        call = request.register(id, name, pwd, email)
//        call.enqueue(object: Callback<IntResult>{
//            override fun onResponse(call: Call<IntResult>, response: Response<IntResult>) {
//                isSuccess.postValue(response.body())
//            }
//
//            override fun onFailure(call: Call<IntResult>, t: Throwable) {
//                t.stackTrace
//            }
//
//        })
//    }

    fun addFirebaseUser(id: String, name: String, pwd: String, email: String){
        val user = FirebaseUser(id, name, pwd, "M", "+18190010202", email )
        RealtimeUtil.userReference.child(name).setValue(user).addOnSuccessListener {
            setState(ConstantUtil.REGISTER_SUCCESS)
        }.addOnFailureListener {
            setState(ConstantUtil.STATE_FAIL)
        }
    }

    fun getState(): LiveData<Int> {
        return state
    }

    fun setState(state: Int){
        this.state.postValue(state)
    }
}