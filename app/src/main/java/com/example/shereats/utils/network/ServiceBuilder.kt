package com.example.shereats.utils.network

import com.example.shereats.utils.ConstantUtil
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Yee on 2021-12-10.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class ServiceBuilder {
    object ServiceBuilder{
        private val client = OkHttpClient.Builder().build()
        private val retrofit = Retrofit.Builder()
            .baseUrl(ConstantUtil.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        fun<T> buildService(service: Class<T>): T{
            return retrofit.create(service)
        }
    }
}