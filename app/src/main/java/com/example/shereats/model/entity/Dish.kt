package com.example.shereats.model.entity


/**
 * Created by Yee on 2021-10-26.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 *
 * @param id: item id
 * @param restaurantId: ID of the restaurant which provide this item
 * @param name: item name
 * @param price: item price
 * @param image: descriptive picture of the item(URI or Link)
 * @param rate: from 1 star to 5 star
 * @param promotion: e.g. 0.1 means 10% off
 */
data class Dish(
        val id: Int,
        val restaurantId: Int,
        val name: String,
        val price: Float,
        val image: String,
        val rate: Float,
        val promotion: Float,
)
