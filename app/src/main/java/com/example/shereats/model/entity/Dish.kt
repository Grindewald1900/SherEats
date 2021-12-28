package com.example.shereats.model.entity

import java.io.Serializable


/**
 * Created by Yee on 2021-10-26.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 *
 * @param item_id: item id
 * @param restaurant_id: ID of the restaurant which provide this item
 * @param item_name: item name
 * @param item_price: item price
 * @param item_pic: descriptive picture of the item(URI or Link)
 * @param item_taste: from 1 star to 5 star(This is the only rate filled in our database)
 * @param item_discount: e.g. 0.1 means 10% off
 */
data class Dish(
        val restaurant_id: Int,
        val restaurant_name: String,
        val item_id: Int,
        val item_name: String,
        val item_genre: String,
        val item_price: Float,
        val item_pic: String,
        val item_discount: Float,
        val item_taste: Float,
        val item_environment: Float,
        val item_service: Float,
): Serializable
