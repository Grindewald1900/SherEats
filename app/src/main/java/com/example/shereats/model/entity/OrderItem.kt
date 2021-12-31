package com.example.shereats.model.entity

import android.content.ClipData
import com.google.gson.annotations.SerializedName


/**
 * Created by Yee on 2021-10-28.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 *
 * @param id: order id(unique)
 * @param order_id: different item from the same order will share this id
 * @param user_id: id of user who place the order
 * @param item_environment: restaurant atmosphere, decor style.. from 1 to 5
 * @param item_taste: is the food delicious or not, from 1 to 5
 * @param item_service: how about service? from 1 to 5
*/
data class OrderItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("order_id")
    val order_id: String,
    @SerializedName("user_id")
    val user_id: String,
    @SerializedName("restaurant_id")
    val restaurant_id: Int,
    @SerializedName("upload_time")
    val upload_time: String,
    @SerializedName("order_price")
    val order_price: Float,
    @SerializedName("item_id")
    val item_id: Int,
    @SerializedName("item_amount")
    var item_amount: Int,
    @SerializedName("item_taste")
    val item_taste: Int,
    @SerializedName("item_environment")
    val item_environment: Int,
    @SerializedName("item_service")
    val item_service: Int,
        // Additional info, only used by cart views
    @SerializedName("item_name")
    val item_name: String,
    @SerializedName("restaurant_name")
    val restaurant_name: String
)
