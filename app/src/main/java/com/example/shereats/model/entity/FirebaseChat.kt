package com.example.shereats.model.entity

import java.io.Serializable

/**
 * @param friend: the user to chat with
 * @param isNotice: the red dot
 */
data class FirebaseChat(
    val friend: FirebaseUser? = null,
    val lastMessage: String? = null,
    val date: String? = null,
    val notice: Boolean? = null,
    val messageList: MutableList<FirebaseMessage>? = null
): Serializable
