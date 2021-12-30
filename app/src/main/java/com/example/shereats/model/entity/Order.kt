package com.example.shereats.model.entity


/**
 * Created by Yee on 2021-10-27.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 *
 * @param id: order id(unique)
 * @param items:
*/
data class Order(
    val id: String,
    val price: Float,
    val time: String,
    val user_name: String,
    val items: List<OrderItem>
)