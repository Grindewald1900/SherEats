package com.example.shereats.model.entity

import java.util.*


/**
 * Created by Yee on 2021-12-12.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 *
 * @param user_birthday: 1990-03-10
 * @param user_gender: 'M' or 'F'
 * @param user_icon: undefined
 */
data class User (
    val user_id: Int,
    val user_name: String,
    val user_password: String,
    val user_birthday: Date,
    val user_gender: Char,
    val user_tel: String,
    val user_mail: String,
    val user_icon: String,
)