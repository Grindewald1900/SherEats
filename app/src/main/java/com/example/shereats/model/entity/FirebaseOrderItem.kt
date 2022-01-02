package com.example.shereats.model.entity

import com.google.gson.annotations.SerializedName

data class FirebaseOrderItem(
    val id: String? = null,
    val orderId: String? = null,
    val userId: String? = null,
    val restaurantId: String? = null,
    val uploadTime: String? = null,
    val orderPrice: Double? = null,
    val itemId: Long? = null,
    var itemAmount: Long? = null,
    val itemTaste: Long? = null,
    val itemEnvironment: Long? = null,
    val itemService: Long? = null,
    // Additional info, only used by cart views
    val itemName: String? = null,
    val restaurantName: String? = null
)
