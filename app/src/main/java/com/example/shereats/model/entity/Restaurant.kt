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
        val id: Int,
        val name: String,
        val address: String,
        val genre: String,
        val averagePrice: Float,
        val tel: String,
        val lat: Float,
        val long: Float,
        var isFavourite: Boolean,
)
