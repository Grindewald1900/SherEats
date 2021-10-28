package com.example.shereats.model.entity

import android.content.ClipData


/**
 * Created by Yee on 2021-10-28.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */

/**
 * @param item: dish detail
 */
data class OrderItem(
        val item: Dish,
        val count: Int,
)
