package com.example.shereats.utils

import android.graphics.Color
import com.example.shereats.model.entity.OrderItem


/**
 * Created by Yee on 2021-12-03.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
object ConstantUtil {
    var DEBUG_MODE = true

    // URL
    const val DATABASE_USER: String = "Users"
    const val DATABASE_EVENT: String = "Events/"
    const val STORAGE_IMAGE: String = "Images/"

    // Classes
    const val CLASS_ITEM: String = "ITEM"
    const val CLASS_ORDER_ITEM = "ORDER_ITEM"

    // Ordinary String
    const val STRING_RESULT_ACTIVITY = "RESULT_ACTIVITY"
    const val STRING_CONTACT = "CONTACT"
    const val STRING_IMAGE = "IMAGE"
    const val STRING_CHAT_SYSTEM_INIT = "Welcome"

    // Connection - Local test
    /** Here we set ip 10.0.2.2 as localhost**/
    const val SERVER_URL: String = "http://192.168.0.245:8080/BU_war_exploded/"
    const val SERVER_URL_MOBILE: String = "http://10.0.2.2:8080/BU_war_exploded/"

    const val HTTP_GET = "GET"
    const val HTTP_POST = "POST"

    const val SERVLET_REGISTER: String = "Servlet.RegisterServlet.do"
    const val SERVLET_LOGIN: String = "Servlet.LoginServlet.do"
    const val SERVLET_ADD_ORDER: String = "Servlet.OrderServlet.do"
    const val SERVLET_DISH: String = "Servlet.DishServlet.do"
    const val SERVLET_RESTAURANT: String = "Servlet.RestaurantServlet.do"
    const val SERVLET_EVENT: String = "Servlet.EventServlet.do"
    const val SERVLET_BADGE: String = "Servlet.BadgeServlet.do"

    const val ENTITY_DISH = "DISH"
    const val ATTRIBUTE_POSITION = "POSITION"

    const val SERVER_RESULT = "result"
    const val SERVER_SUCCESS = "success"
    const val SERVER_FAIL = "fail"

    // Tags
    const val TAG_DIALOG_UPLOAD_IMAGE = "TAG_DIALOG_UPLOAD_IMAGE"

    // States
    const val STATE_NULL = -1
    const val STATE_SUCCESS = 1
    const val STATE_FAIL = 2
    const val STATE_CANCEL = 3

    // onActivityResult
    const val SELECT_IMAGE: Int = 101



    // User type
    const val REGISTER_DEFAULT: Int = 200
    const val REGISTER_SUCCESS: Int = 201
    const val REGISTER_DUPLICATE_ID: Int = 202
    const val REGISTER_DUPLICATE_NAME: Int = 203




    // Handler
    const val HANDLER_LOGIN: Int = 301
    const val HANDLER_REGISTER: Int = 302
    const val HANDLER_ORDER: Int = 303
    const val HANDLER_DISH: Int = 304
    const val HANDLER_STORE: Int = 305


    // ResultActivity
    const val RESULT_DEFAULT : Int = 500
    const val RESULT_CORRECT : Int = 501
    const val RESULT_INCORRECT : Int = 502
    const val RESULT_DIALOG_UPLOAD_IMAGE = 511

    // Image Mode
    const val MODE_EDGE: Int = 601
    const val MODE_POINT: Int = 602

    // Dish sort
    const val SORT_BY_PROMO = 701
    const val SORT_BY_ID = 702
    const val SORT_BY_PRICE = 703

    // Search Filter state
    const val FILTER_STATE_NONE = 800
    const val FILTER_STATE_UP = 801
    const val FILTER_STATE_DOWN = 802

    // Background state
    const val BACKGROUND_STATE_NORMAL = 900
    const val BACKGROUND_STATE_NETWORK_ERROR = 901

    // Activity state
    const val ACTIVITY_STATE_CREATE = 1000
    const val ACTIVITY_STATE_START = 1001
    const val ACTIVITY_STATE_RESUME = 1002
    const val ACTIVITY_STATE_PAUSE = 1003
    const val ACTIVITY_STATE_STOP = 1004
    const val ACTIVITY_STATE_COROUTINE = 1010
    const val ACTIVITY_STATE_UI_THREAD = 1011


    // Constant amount
    const val MAX_RECOMMEND_ITEM: Int = 20
    const val MAX_UPLOAD_IMAGE: Int = 5
    const val MAX_SEARCH_HISTORY: Int = 5
    const val MIN_TRANSFORM_SCALE = 0.8f
    const val MIN_TRANSFORM_ALPHA = 0.5f
    const val RESULT_ACTIVITY_TIME: Long = 3000
    const val ANIMATION_DELAY: Long = 1000

    val MAP_BADGE_COLOR: Map<Int, Int> = mapOf(
        1 to Color.parseColor("#6aa84f"),
        2 to Color.parseColor("#a0604a"),
        3 to Color.parseColor("#5bc0de"),
        4 to Color.parseColor("#7926A9"),
        5 to Color.parseColor("#d9534f"),)


}