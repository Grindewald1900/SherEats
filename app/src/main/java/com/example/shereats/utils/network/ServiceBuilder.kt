package com.example.shereats.utils.network

import com.example.shereats.utils.ConstantUtil
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


/**
 * Created by Yee on 2021-12-10.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class ServiceBuilder {
    object ServiceBuilder{
        private val inspector = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private val client = OkHttpClient.Builder().addInterceptor(inspector).build()
        private val retrofit = Retrofit.Builder()
            .baseUrl(ConstantUtil.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        private val retrofitScalar = Retrofit.Builder()
            .baseUrl(ConstantUtil.SERVER_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(client)
            .build()

        fun<T> buildService(service: Class<T>): T{
            return retrofit.create(service)
        }

        fun<T> buildScalarService(service: Class<T>): T{
            return retrofitScalar.create(service)
        }
    }
}