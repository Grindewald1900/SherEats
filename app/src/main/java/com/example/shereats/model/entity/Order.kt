package com.example.shereats.model.entity

import java.io.Serializable


/**
 * Created by Yee on 2021-10-27.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 *
 * @param id: order id(unique)
 * @param items:
*/
data class Order(
    var id: String? = null,
    var price: Double? = null,
    var time: String? = null,
    var userName: String? = null,
    var items: List<FirebaseOrderItem>? = null
): Serializable