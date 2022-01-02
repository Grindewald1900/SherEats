package com.example.shereats.model.entity

import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.LoginStatusUtil
import org.json.JSONArray
import kotlin.coroutines.Continuation
import kotlin.random.Random

object SingletonUtil {
    var MAP_FAVORITE_DISH: HashMap<Long, Boolean> = hashMapOf()
    var LIST_IS_FAVORITE_REST: MutableList<Boolean> = mutableListOf()
    var LIST_IS_FAVORITE_DISH: MutableList<Boolean> = mutableListOf()
    // <Int, OrderItem>: Int indicate the item id
    var CURRENT_ORDER: HashMap<Long, FirebaseOrderItem> = hashMapOf()


    /**
     * @param user: current user
     * @param dish: the item to be added in the cart
     * @param count: the number of item
     */
    fun addToCart(user: FirebaseUser, dish: FirebaseDish, count: Int, isAddOne: Boolean){
        var mCount = if (isAddOne){
            if(isContainItem(dish.itemId!!)){
                CURRENT_ORDER[dish.itemId]!!.itemAmount?.plus(1)
            }else{ 1 }
        }else{ count }
        val time = ConstantUtil.CURRENT_TIME
        val orderId = user.userId + time
        val id = orderId + dish.itemId
        val price = dish.itemPrice
        val rateOne = (Math.random()* 2 + 3).toLong()
        val rateTwo = (Math.random() * 2 + 3).toLong()
        val rateThree =(Math.random() * 2 + 3).toLong()
        val orderItem = FirebaseOrderItem(id, orderId, user.userId, dish.restaurantId, time, price, dish.itemId,
            mCount as Long?, rateOne, rateTwo, rateThree, dish.itemName, dish.restaurantName)

        if(mCount!! <= 0 && isContainItem(dish.itemId!!)){
            CURRENT_ORDER.remove(dish.itemId)
        }else{
            // If exist, change the count, if not, put a new <K, V> to HashMap
            CURRENT_ORDER[dish.itemId!!] = orderItem
        }
    }

    /**
     * Update the list
     */
    fun updateCart(orderItem: FirebaseOrderItem){
        CURRENT_ORDER[orderItem.itemId!!] = orderItem

        if(orderItem.itemAmount!! <= 0 && isContainItem(orderItem.itemId)){
            CURRENT_ORDER.remove(orderItem.itemId)
        }else{
            // If exist, change the count, if not, put a new <K, V> to HashMap
            CURRENT_ORDER[orderItem.itemId] = orderItem
        }
    }


    fun getPrice(): Double{
        var price = 0.0
        CURRENT_ORDER.forEach { (_, orderItem) ->
            val itemPrice = orderItem.orderPrice!! * orderItem.itemAmount!!
            price += itemPrice
        }
        return price
    }

    fun getCurrentCart(): List<FirebaseOrderItem>{
        val list: MutableList<FirebaseOrderItem> = mutableListOf()
        val orderHashMap = SingletonUtil.CURRENT_ORDER

        orderHashMap.forEach {(_, value) ->
            list.add(value)
        }
        return list
    }

    fun clearCurrentCart(){
        CURRENT_ORDER.clear()
        ConstantUtil.resetCurrentTime()
    }

    fun isCurrentCartEmpty(): Boolean{
        return CURRENT_ORDER.isEmpty()
    }

    fun getCurrentCartJson(): JSONArray{
        return JSONArray(getCurrentCart())
    }

    private fun isContainItem(id: Long): Boolean{
        return CURRENT_ORDER.containsKey(id)
    }
}