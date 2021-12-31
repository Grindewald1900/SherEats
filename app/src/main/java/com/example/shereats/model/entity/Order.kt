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
    var id: String,
    var price: Float,
    var time: String,
    var user_name: String,
    var items: MutableList<OrderItem>
)