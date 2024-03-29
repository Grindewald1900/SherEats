package com.grindewald1900.shereats.model.entity

import java.io.Serializable

data class FirebaseUser(
    val userId: String? = null,
    val userName: String? = null,
    val userPassword: String? = null,
    val userGender: String? = null,
    val userTel: String? = null,
    val userMail: String? = null,
): Serializable