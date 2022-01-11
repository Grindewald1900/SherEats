package com.grindewald1900.shereats.utils

import com.grindewald1900.shereats.model.entity.*


/**
 * Created by Yee on 2021-12-24.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 *
 * Some utils for entity conversion, like change the order
 */
class EntityUtil {
    companion object{

        /**
         * Reset the dishes order by price
         */
        fun sortDishByPrice(dishes: MutableList<FirebaseDish>, isAscending: Boolean): List<FirebaseDish>{
            val result: MutableList<FirebaseDish> = mutableListOf()
            var minPrice = Double.MAX_VALUE
            var minIndex= 0
            while (dishes.isNotEmpty()){
                dishes.forEach {
                    if (it.itemPrice!! < minPrice){
                        minPrice = it.itemPrice
                        minIndex = dishes.indexOf(it)
                    }
                }
                result.add(dishes[minIndex])
                dishes.removeAt(minIndex)
                minPrice = Double.MAX_VALUE
            }
            return if (isAscending){
                result
            }else{
                result.reversed()
            }
        }

        /**
         * Reset the dishes order by price
         */
        fun sortDishByRate(dishes: MutableList<FirebaseDish>, isAscending: Boolean): List<FirebaseDish>{
            val result: MutableList<FirebaseDish> = mutableListOf()
            var minRate = Double.MAX_VALUE
            var minIndex= 0
            while (dishes.isNotEmpty()){
                dishes.forEach {
                    if (it.itemTaste!! < minRate){
                        minRate = it.itemTaste
                        minIndex = dishes.indexOf(it)
                    }
                }
                result.add(dishes[minIndex])
                dishes.removeAt(minIndex)
                minRate = Double.MAX_VALUE
            }
            return if (isAscending){
                result
            }else{
                result.reversed()
            }
        }

        /**
         * Reset the dishes order by promotion, we'll set promotions with higher priority
         */
        fun sortDishByPromo(dishes: MutableList<FirebaseDish>): List<FirebaseDish>{
            val result: MutableList<FirebaseDish> = mutableListOf()
            dishes.forEach {
                if (it.itemDiscount!! < 1){
                    result.add(it)
                }
            }
            dishes.forEach {
                if (it.itemDiscount == 1.0){
                    result.add(it)
                }
            }
            return result
        }


        /**
         * Retrieve order item from server like
         * 001-100-1, 001-100-2, 001-100-3, 001-200-1, 001-200-2
         * Convert raw data to orders, one order may contain more than one items
         * [001-100{001-100-1, 001-100-2, 001-100-3}]
         * [001-200{001-200-1, 001-200-2}]
         */
        fun getOrderFromItem(items: List<FirebaseOrderItem>): List<Order>{
            var isComplete = false
            val rawItems: MutableList<FirebaseOrderItem> = items.toMutableList()
            val searchedId: MutableList<String> = mutableListOf()
            val orders: MutableList<Order> = mutableListOf()
            var tempId: String = items[0].orderId!!
            searchedId.add(tempId)

            // If every order_id is in searchedId list, break the whole loop
            while (!isComplete){
                val order = Order("", 0.0, "", "", mutableListOf())
                rawItems.forEach {
                    if (it.orderId == tempId){
//                        order.items!!.add(it)
                        order.price = order.price?.plus(it.orderPrice!!.toFloat() * it.itemAmount!!)
                    }
                }
                order.id = order.items?.get(0)?.orderId!!
                order.time = order.items?.get(0)!!.uploadTime!!
                order.userName = LoginStatusUtil.getUser().userName!!
                orders.add(order)
                    // Loop for a order_id not in the searchedId list
                for (i in rawItems.indices){
                    if (!searchedId.contains(rawItems[i].orderId)){
                        tempId = rawItems[i].orderId!!
                        searchedId.add(tempId)
                        break
                    }
                    if(i == rawItems.size - 1){
                        isComplete = true
                    }
                }
            }
            return orders
        }

        fun getDishFromOrderItem(orderItem: FirebaseOrderItem): FirebaseDish {
            return FirebaseDish(
                orderItem.restaurantId,
                orderItem.restaurantName,
                orderItem.itemId,
                orderItem.itemName,
                "",
                orderItem.orderPrice,
                "",
                1.0,
                4.0,
                4.0,
                4.0
            )
        }

        fun searchDishByRestaurant(dishes: List<FirebaseDish>, keyword: String): List<FirebaseDish>{
            val result: MutableList<FirebaseDish> = mutableListOf()
            dishes.forEach {
                if (it.restaurantName!!.contains(keyword)){
                    result.add(it)
                }
            }
            return result
        }

        fun searchDishByDish(dishes: List<FirebaseDish>, keyword: String): List<FirebaseDish>{
            val result: MutableList<FirebaseDish> = mutableListOf()
            dishes.forEach {
                if (it.itemName!!.contains(keyword)){
                    result.add(it)
                }
            }
            return result
        }
        fun searchDishByCuisine(dishes: List<FirebaseDish>, keyword: String): List<FirebaseDish>{
            val result: MutableList<FirebaseDish> = mutableListOf()
            dishes.forEach {
                if (it.itemGenre!!.contains(keyword)){
                    result.add(it)
                }
            }
            return result
        }

        fun getUniqueDish(dishes: List<FirebaseDish>): MutableList<FirebaseDish>{
            val result: MutableList<FirebaseDish> = mutableListOf()
            val searchedId: MutableList<Long> = mutableListOf()
            dishes.forEach {
                if (searchedId.contains(it.itemId)){
                    return@forEach
                }else{
                    result.add(it)
                    searchedId.add(it.itemId!!)
                }
            }
            return result
        }

    }
}