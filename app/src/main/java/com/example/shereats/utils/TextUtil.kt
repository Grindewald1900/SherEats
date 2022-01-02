package com.example.shereats.utils

import com.example.shereats.model.entity.Order
import com.example.shereats.model.entity.OrderItem
import java.time.Month
import java.util.*
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
        fun getItemPrice(price: Float): String{
            return String.format("%.2f", price) + "$"
        }

        fun getItemPriceEach(price: Float): String{
            return String.format("%.2f", price) + "$ each"
        }

        /**
         * return a promotion tag
         * 20% off
         */
        fun getPromotion(promo: Float): String{
            return (100 - promo*100).toInt().toString() + "% \nOff"
        }

        /**
         * return the content of order like
         * burger * 2, coffee * 3
         */
        fun getOrderContent(items: List<OrderItem>): String{
            var content: String = ""
            items.forEach {
                content += "${it.item_name} * ${it.item_amount}, "
            }
            return content.removeRange(content.length - 1, content.length)
        }

        fun getOrderInfo(order: Order): String {
            val time = order.time.toLong() * 1000
            val calendar = getInstance()
            calendar.timeInMillis = time
            return "Released at ${calendar.time} "
        }

    }
}