package com.example.shereats.utils

import com.example.shereats.model.entity.User


/**
 * Created by Yee on 2021-12-12.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class LoginStatusUtil {
    companion object{
        var mUser: User? = null

        fun isLogin(): Boolean{
            return mUser != null
        }
    }
}