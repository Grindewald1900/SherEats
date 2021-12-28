package com.example.shereats.utils.network

import com.example.shereats.model.entity.*
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

    /**
     * @param id: User id, if equal to 0, retrieve all items
     * @param count: the amount of rows to be retrieved
     */
    @POST(ConstantUtil.SERVLET_DISH)
    fun getDishes(@Query("id") id: Int, @Query("count") count: Int, @Query("keyword") keyword: String,
                  @Query("typeRestaurant") typeRestaurant: Boolean = false,
                  @Query("typeDish") typeDish: Boolean = false,
                  @Query("typeCuisine") typeCuisine: Boolean = false,
                  @Query("typeLocation") typeLocation: Boolean = false,
                  @Query("typeAll") typeAll: Boolean = true ): Call<List<Dish>>

    @POST(ConstantUtil.SERVLET_LOGIN)
    fun getUser(@Query("name") name: String, @Query("password") password: String): Call<List<User>>

    @POST(ConstantUtil.SERVLET_REGISTER)
    fun register(@Query("id") id: String, @Query("name") name: String, @Query("password") password: String, @Query("email") email: String): Call<IntResult>

    @POST(ConstantUtil.SERVLET_BADGE)
    fun getBadges(@Query("id") id: String): Call<List<Badge>>

}