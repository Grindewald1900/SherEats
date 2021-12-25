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
        fun sortDishByPrice(dishes: MutableList<Dish>): List<Dish>{
            var result: MutableList<Dish> = mutableListOf()
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
    }
}