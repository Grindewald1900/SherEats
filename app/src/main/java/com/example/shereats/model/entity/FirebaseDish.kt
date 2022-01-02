package com.example.shereats.model.entity

import java.io.Serializable

data class FirebaseDish(
    val restaurantId: String? = null,
    val restaurantName: String? = null,
    val itemId: Long? = null,
    val itemName: String? = null,
    val itemGenre: String? = null,
    val itemPrice: Double? = null,
    val itemPic: String? = null,
    val itemDiscount: Double? = null,
    val itemTaste: Double? = null,
    val itemEnvironment: Double? = null,
    val itemService: Double? = null,
): Serializable
