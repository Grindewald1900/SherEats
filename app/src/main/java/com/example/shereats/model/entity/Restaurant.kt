package com.example.shereats.model.entity


/**
 * Created by Yee on 2021-10-25.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */

/**
 * @param id: store id
 * @param name: store name
 * @param genre: store genre
 * @param averagePrice: average price
 * @param tel: phone number
 * @param lat: latitude
 * @param long: longitude
 * @param isFavourite: if the restaurant in the fav list
 */

data class Restaurant(
        val restaurant_id: Int,
        val restaurant_name: String,
        val restaurant_address: String,
        val restaurant_genre: String,
        val restaurant_average: Float,
        val restaurant_tel: String,
        val restaurant_lat: Float,
        val restaurant_long: Float,
        var restaurant_isfav: String,
)
