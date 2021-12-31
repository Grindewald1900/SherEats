package com.example.shereats.utils

import android.content.Context
import android.widget.Toast


/**
 * Created by Yee on 2021-12-12.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class ToastUtil {
    companion object{
        /**
         * show message for a long time
         */
        fun showLongMessage(message: String, context: Context){
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }

        /**
         * show message for a short time
         */
        fun showShortMessage(message: String, context: Context){
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        fun showShortMessage(messageId: Int, context: Context){
            val message = context.getString(messageId)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}