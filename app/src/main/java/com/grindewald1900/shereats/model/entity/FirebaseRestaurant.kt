package com.grindewald1900.shereats.model.entity

data class FirebaseRestaurant(
    val restaurantId: String? = null,
    val restaurantName: String? = null,
    val restaurantAddress: String? = null,
    val restaurantGenre: String? = null,
    val restaurantAverage: Double? = 0.0,
    val restaurantTel: String? = null,
    val restaurantLat: Double? = null,
    val restaurantLong: Double? = null,
    var restaurantIsfav: String? = null
    )
