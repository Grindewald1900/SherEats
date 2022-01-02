package com.example.shereats.utils

import com.example.shereats.model.entity.FirebaseUser
import java.util.*


/**
 * Created by Yee on 2021-12-12.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class LoginStatusUtil {
    companion object{
        var mUser: FirebaseUser? = null

        fun isLogin(): Boolean{
            return mUser != null
        }

        fun setUser(id: String, name: String, pwd: String, email: String){
            mUser = FirebaseUser(id, name, pwd, "F", "+18190002920", email)
        }

        fun getUser(): FirebaseUser{
            return if (null != mUser){
                mUser!!
            }else{
                FirebaseUser("000","Click to login","", "F", "000", "")
            }
        }

        fun getUserId(): String{
            return getUser().userId!!
        }

        fun getUserName(): String{
            return getUser().userId!!
        }
    }
}