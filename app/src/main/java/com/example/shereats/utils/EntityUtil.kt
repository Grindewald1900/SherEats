package com.example.shereats.utils

import com.example.shereats.model.entity.Dish
import com.example.shereats.model.entity.Order
import com.example.shereats.model.entity.OrderItem


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
        fun sortDishByPrice(dishes: MutableList<Dish>, isAscending: Boolean): List<Dish>{
            val result: MutableList<Dish> = mutableListOf()
            var minPrice = Float.MAX_VALUE
            var minIndex= 0
            while (dishes.isNotEmpty()){
                dishes.forEach {
                    if (it.item_price < minPrice){
                        minPrice = it.item_price
                        minIndex = dishes.indexOf(it)
                    }
                }
                result.add(dishes[minIndex])
                dishes.removeAt(minIndex)
                minPrice = Float.MAX_VALUE
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
        fun sortDishByRate(dishes: MutableList<Dish>, isAscending: Boolean): List<Dish>{
            val result: MutableList<Dish> = mutableListOf()
            var minRate = Float.MAX_VALUE
            var minIndex= 0
            while (dishes.isNotEmpty()){
                dishes.forEach {
                    if (it.item_taste < minRate){
                        minRate = it.item_taste
                        minIndex = dishes.indexOf(it)
                    }
                }
                result.add(dishes[minIndex])
                dishes.removeAt(minIndex)
                minRate = Float.MAX_VALUE
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
        fun sortDishByPromo(dishes: MutableList<Dish>): List<Dish>{
            val result: MutableList<Dish> = mutableListOf()
            dishes.forEach {
                if (it.item_discount < 1){
                    result.add(it)
                }
            }
            dishes.forEach {
                if (it.item_discount == 1f){
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
        fun getOrderFromItem(items: List<OrderItem>): List<Order>{
            var isComplete = false
            val rawItems: MutableList<OrderItem> = items.toMutableList()
            val searchedId: MutableList<String> = mutableListOf()
            val orders: MutableList<Order> = mutableListOf()
            var tempId: String = items[0].order_id
            searchedId.add(tempId)

            // If every order_id is in searchedId list, break the whole loop
            while (!isComplete){
                val order = Order("", 0f, "", "", mutableListOf())
                rawItems.forEach {
                    if (it.order_id == tempId){
                        order.items.add(it)
                        order.price += it.order_price * it.item_amount
                    }
                }
                order.id = order.items[0].order_id
                order.time = order.items[0].upload_time
                order.user_name = LoginStatusUtil.getUser().user_name
                orders.add(order)
                    // Loop for a order_id not in the searchedId list
                for (i in rawItems.indices){
                    if (!searchedId.contains(rawItems[i].order_id)){
                        tempId = rawItems[i].order_id
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
    }
}