package com.example.shereats.utils

import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder


/**
 * Created by Yee on 2021-12-03.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class HttpUtil {

    /**
     * GetDish: get restaurant list from server
     * @return: JSONArray in a string (dish info)
     */
    companion object{
        fun getRestaurantByPost(id: Int?, daoType: Int?): String? {
            val address: String = ConstantUtil.SERVER_URL + ConstantUtil.SERVLET_RESTAURANT
            var result: String? = ""
            try {
                val url = URL(address)
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = ConstantUtil.HTTP_POST

                //Set up timeout
                conn.readTimeout = 5000
                conn.connectTimeout = 5000
                conn.useCaches = false
                val data = "&id=" + URLEncoder.encode(id.toString(), "UTF-8") +
                        "&dao_type=" + URLEncoder.encode(daoType.toString(), "UTF-8")
                val out = conn.outputStream
                out.write(data.toByteArray())
                out.flush()
                out.close()
                conn.connect()
                if (conn.responseCode == 200) {
                    val `is` = conn.inputStream
                    val message = ByteArrayOutputStream()
                    var len = 0
                    val buffer = ByteArray(1024)
                    while (`is`.read(buffer).also { len = it } != -1) {
                        message.write(buffer, 0, len)
                    }
                    `is`.close()
                    message.close()
                    result = String(message.toByteArray())
                    Log.d("getRestaurantByPost", result)
                    return result
                }
            } catch (e: IOException) {
                e.printStackTrace()
                Log.d("getRestaurantByPost - e", e.message!!)
            }
            return result
        }
    }

}