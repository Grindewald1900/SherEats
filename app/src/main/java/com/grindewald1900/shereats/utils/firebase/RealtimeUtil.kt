package com.grindewald1900.shereats.utils.firebase

import com.grindewald1900.shereats.model.entity.FirebaseBadge
import com.grindewald1900.shereats.model.entity.FirebaseDish
import com.grindewald1900.shereats.model.entity.FirebaseRestaurant
import com.grindewald1900.shereats.model.entity.FirebaseUser
import com.grindewald1900.shereats.utils.LoginStatusUtil
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RealtimeUtil {
    companion object{
        private val database = Firebase.database
        val userReference = database.getReference("users")
        val restaurantReference = database.getReference("restaurants")
        val dishReference = database.getReference("dishes")
        val badgeReference = database.getReference("badges")
        val orderItemReference = database.getReference("orderItems")
        val chatReference = database.getReference("chats")


        fun addUser(id: String, name: String, userPassword: String, userGender: String, userTel: String, userMail: String){
            val user = FirebaseUser(id, name, userPassword, userGender, userTel, userMail)
            userReference.child(name).setValue(user)
        }

        fun getUser(name: String){
            var user: FirebaseUser?
            userReference.child(name).get().addOnSuccessListener {
                if(null != it.getValue(FirebaseUser::class.java)){
                    user = it.getValue(FirebaseUser::class.java)
                    if(null != user){
                        LoginStatusUtil.setUser(user!!.userId!!, user!!.userName!!, user!!.userPassword!!, user!!.userMail!!)
                    }
                }
            }.addOnFailureListener {
                it.stackTrace
            }

//            return user
        }

        fun addRestaurant(restaurantId: String, restaurantName: String, restaurantAddress: String, restaurantGenre: String, restaurantAverage: Double, restaurantTel: String,
                          restaurantLat: Double, restaurantLong: Double, restaurantIsfav: String){
            val restaurant = FirebaseRestaurant(restaurantId, restaurantName, restaurantAddress, restaurantGenre, restaurantAverage, restaurantTel, restaurantLat, restaurantLong, restaurantIsfav)
            restaurantReference.child(restaurantId).setValue(restaurant)
        }

        fun addDish(restaurantId: String, restaurantName: String, itemId: Long, itemName: String, itemGenre: String, itemPrice: Double, itemDiscount: Double, itemTaste: Double){
            val dish = FirebaseDish(restaurantId, restaurantName, itemId, itemName, itemGenre, itemPrice, "", itemDiscount, itemTaste, 4.0, 4.0)
            dishReference.child(itemId.toString()).setValue(dish)
        }

        fun addBadge(userId: String,badgeId: Long, badgeType: Long, badgeSequence: Long, badgeContent: String, badgeDate: String, badgeRarity: Long){
            val badge = FirebaseBadge(userId, badgeId, badgeType, badgeSequence, badgeContent, badgeDate, badgeRarity)
            badgeReference.child(userId).child(badgeSequence.toString()).setValue(badge)
        }

        fun addOrderItem(){

        }
    }
}