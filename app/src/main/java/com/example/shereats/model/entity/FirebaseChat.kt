package com.example.shereats.model.entity

/**
 * @param friend: the user to chat with
 * @param isNotice: the red dot
 */
data class FirebaseChat(
    val friend: FirebaseUser? = null,
    val lastMessage: String? = null,
    val date: String? = null,
    val isNotice: Boolean? = null,
    val messageList: List<FirebaseMessage>? = null
)
