package com.example.shereats.utils


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

        /**
         * return a promotion tag
         * 20% off
         */
        fun getPromotion(promo: Float): String{
            return (100 - promo*100).toInt().toString() + "% \nOff"
        }

    }
}