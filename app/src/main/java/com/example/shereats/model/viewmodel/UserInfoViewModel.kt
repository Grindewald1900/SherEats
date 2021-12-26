package com.example.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shereats.model.entity.Badge
import com.example.shereats.utils.LoginStatusUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Yee on 2021-12-26.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class UserInfoViewModel: BaseViewModel() {
    private var badges: MutableLiveData<List<Badge>> = MutableLiveData()
    private lateinit var call: Call<List<Badge>>

    fun getBadges(): LiveData<List<Badge>>{
        return badges
    }

    fun setBadges() {
        if(!LoginStatusUtil.isLogin()) return
        val userId = LoginStatusUtil.getUser().user_id
        call = request.getBadges(userId)

        call.enqueue(object: Callback<List<Badge>>{
            override fun onResponse(call: Call<List<Badge>>, response: Response<List<Badge>>) {
                if (response.isSuccessful){
                    badges.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<Badge>>, t: Throwable) {
                t.stackTrace
            }

        })
    }

}