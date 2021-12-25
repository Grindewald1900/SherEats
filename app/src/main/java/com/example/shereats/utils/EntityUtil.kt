package com.example.shereats.utils

import com.example.shereats.model.entity.Dish


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
        fun sortDishByPrice(dishes: MutableList<Dish>): List<Dish>{
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
            return result
        }

        /**
         * Reset the dishes order by promotion, we'll set promotions with higher priority
         */
        fun sortDishByPromo(dishes: MutableList<Dish>): List<Dish>{
            val result: MutableList<Dish> = mutableListOf()
            dishes.forEach {
                if (it.item_discount < 1){
                    result.add(it)
                    dishes.remove(it)
                }
            }
            result.addAll(dishes)
            return result
        }
    }
}