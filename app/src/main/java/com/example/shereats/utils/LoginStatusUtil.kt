package com.example.shereats.utils

import com.example.shereats.model.entity.User
import java.util.*


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

        fun setUser(id: String, name: String, pwd: String, email: String){
            mUser = User(id, name, pwd, Date(), 'F', "000", email,"")
        }

        fun getUser(): User{
            return if (null != mUser){
                mUser!!
            }else{
                User("000","Click to login","", Date(), 'F', "000", "", "")
            }
        }
    }
}