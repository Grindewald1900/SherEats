package com.example.shereats.model.entity

data class FirebaseMessage(
    val senderName: String? = null,
    val receiverName: String? = null,
    val time: String? = null,
    val type: String? = null,
    val contentText: String? = null,
    val contentImage: String? = null,
    val contentVideo: String? = null
)
