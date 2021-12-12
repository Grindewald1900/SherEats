package com.example.shereats.utils.network

import com.example.shereats.model.entity.Restaurant
import com.example.shereats.utils.ConstantUtil
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


/**
 * Created by Yee on 2021-12-10.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
interface EndPointInterface {
    @POST(ConstantUtil.SERVLET_RESTAURANT)
    fun getRestaurants(@Query("id") id: Int, @Query("daoType") daoType: Int): Call<List<Restaurant>>
//    fun getRestaurants(@Query("id") id: Int, @Query("daoType") daoType: Int): Call<List<Restaurant>>

}