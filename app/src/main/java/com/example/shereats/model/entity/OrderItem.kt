package com.example.shereats.model.entity

import android.content.ClipData


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
    val id: String,
    val order_id: String,
    val user_id: String,
    val restaurant_id: Int,
    val upload_time: String,
    val order_price: Float,
    val item_id: Int,
    var item_amount: Int,
    val item_taste: Int,
    val item_environment: Int,
    val item_service: Int,
        // Additional info, only used by cart views
    val item_name: String,
    val restaurant_name: String
)
