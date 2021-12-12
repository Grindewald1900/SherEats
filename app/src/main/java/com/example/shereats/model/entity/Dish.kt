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
)
