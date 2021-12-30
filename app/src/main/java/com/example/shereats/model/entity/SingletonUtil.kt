package com.example.shereats.model.entity

import com.example.shereats.utils.LoginStatusUtil
import kotlin.random.Random

object SingletonUtil {
    var MAP_FAVORITE_DISH: HashMap<Int, Boolean> = hashMapOf()

    var LIST_IS_FAVORITE_REST: MutableList<Boolean> = mutableListOf()
    var LIST_IS_FAVORITE_DISH: MutableList<Boolean> = mutableListOf()
    // <Int, OrderItem>: Int indicate the item id
    var CURRENT_ORDER: HashMap<Int, OrderItem> = hashMapOf()


    /**
     * @param user: current user
     * @param dish: the item to be added in the cart
     * @param count: the number of item
     */
    fun addToCart(user: User, dish: Dish, count: Int, isAddOne: Boolean){
        var mCount = if (isAddOne){
            if(isContainItem(dish.item_id)){
                CURRENT_ORDER[dish.item_id]!!.item_amount + 1
            }else{ 1 }
        }else{ count }

        val time = (System.currentTimeMillis()/1000).toString()
        val orderId = user.user_id + time
        val id = orderId + dish.item_id
        val price = dish.item_price
        val rateOne = (Math.random()* 2 + 3).toInt()
        val rateTwo = (Math.random() * 2 + 3).toInt()
        val rateThree =(Math.random() * 2 + 3).toInt()
        val orderItem = OrderItem(id, orderId, user.user_id, dish.restaurant_id, time, price, dish.item_id, mCount, rateOne, rateTwo, rateThree, dish.item_name, dish.restaurant_name)

        if(mCount <= 0 && isContainItem(dish.item_id)){
            CURRENT_ORDER.remove(dish.item_id)
        }else{
            // If exist, change the count, if not, put a new <K, V> to HashMap
            CURRENT_ORDER[dish.item_id] = orderItem
        }
    }

    /**
     * Update the list
     */
    fun updateCart(orderItem: OrderItem){
        CURRENT_ORDER[orderItem.item_id] = orderItem

        if(orderItem.item_amount <= 0 && isContainItem(orderItem.item_id)){
            CURRENT_ORDER.remove(orderItem.item_id)
        }else{
            // If exist, change the count, if not, put a new <K, V> to HashMap
            CURRENT_ORDER[orderItem.item_id] = orderItem
        }
    }


    fun getPrice(): Float{
        var price = 0f
        CURRENT_ORDER.forEach { (_, orderItem) ->
            val itemPrice = orderItem.order_price * orderItem.item_amount
            price += itemPrice
        }
        return price
    }

    private fun isContainItem(id: Int): Boolean{
        return CURRENT_ORDER.containsKey(id)
    }
}