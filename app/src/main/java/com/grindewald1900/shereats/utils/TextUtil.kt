package com.grindewald1900.shereats.utils

import com.grindewald1900.shereats.model.entity.FirebaseOrderItem
import com.grindewald1900.shereats.model.entity.Order
import java.text.SimpleDateFormat
import java.util.Calendar.*


/**
 * Created by Yee on 2021-12-11.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class TextUtil {
    companion object{

        /**
         * return a item price with .2f and $ symbol
         * 20.59$
         */
        fun getItemPrice(price: Double): String{
            return String.format("%.2f", price) + "$"
        }

        fun getItemPriceEach(price: Double): String{
            return String.format("%.2f", price) + "$ each"
        }

        /**
         * return a promotion tag
         * 20% off
         */
        fun getPromotion(promo: Double): String{
            return (100 - promo*100).toInt().toString() + "% \nOff"
        }

        /**
         * return the content of order like
         * burger * 2, coffee * 3
         */
        fun getOrderContent(items: List<FirebaseOrderItem>): String{
            var content: String = ""
            items.forEach {
                content += "${it.itemName} * ${it.itemAmount}, "
            }
            return content.removeRange(content.length - 1, content.length)
        }

        fun getOrderInfo(order: Order): String {
            val time = order.time!!.toLong() * 1000
            val calendar = getInstance()
            calendar.timeInMillis = time
            return "Released at ${calendar.time} "
        }

        fun getDate(t: String): String{
            val time = t.toLong() * 1000
            val calendar = getInstance()
            val format = SimpleDateFormat("hh:mm aaa MMMM.dd yyyy")
            calendar.timeInMillis = time
            return format.format(calendar.time)
        }

        fun getMaxLengthString(length: Int, string: String): String{
            val result: String = if(string.length < length){
                string
            }else{
                string.removeRange(length, string.length).plus("...")
            }
            return result
        }

        fun isLetterOrNumeric(string: String): Boolean{
            return string.matches("^[a-zA-Z0-9]*$".toRegex())
        }

        fun isNumber(string: String): Boolean{
            return string.matches("^[0-9]*$".toRegex())
        }

    }
}