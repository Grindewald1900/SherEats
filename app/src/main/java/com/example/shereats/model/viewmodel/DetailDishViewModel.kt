package com.example.shereats.model.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.shereats.model.entity.FirebaseRestaurant
import com.example.shereats.utils.firebase.RealtimeUtil
import com.example.shereats.utils.firebase.StorageUtil
import com.example.shereats.view.custom.RoundImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailDishViewModel: BaseViewModel() {
    private var activityState: MutableLiveData<Int> = MutableLiveData()
    private var restaurants: MutableLiveData<List<FirebaseRestaurant>> = MutableLiveData()
    private val firebaseRestaurant: MutableLiveData<FirebaseRestaurant> = MutableLiveData()

    private lateinit var call: Call<List<FirebaseRestaurant>>

    fun getRestaurant(): LiveData<List<FirebaseRestaurant>>{
        return restaurants
    }

//    fun setRestaurant(id: Int){
//        call = request.getRestaurants(id, 2)
//        call.enqueue(object: Callback<List<Restaurant>>{
//            override fun onResponse(
//                call: Call<List<Restaurant>>,
//                response: Response<List<Restaurant>>
//            ) {
//                if(response.isSuccessful){
//                    restaurants.postValue(response.body())
//                }
//            }
//            override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
//                t.stackTrace
//            }
//        })
//    }
//
//    fun getFirebaseRestaurant(): LiveData<FirebaseRestaurant> {
//        return firebaseRestaurant
//    }

    fun setFirebaseRestaurant(id: String) {
        val firebaseRestaurants: MutableList<FirebaseRestaurant> = mutableListOf()

        RealtimeUtil.restaurantReference.child(id).get().addOnSuccessListener { it ->
            val restaurant = it.getValue(FirebaseRestaurant::class.java)
            if(null != restaurant){
                firebaseRestaurant.postValue(restaurant!!)
            }
            }
            .addOnFailureListener {
                it.stackTrace
            }
    }




    /**
     * Get image for the user profile picture(if login)
     */
    fun setProfileImage(name: String, view: RoundImageView, context: Context){
        val childPath = "user/$name.jpg"
        val pathReference = StorageUtil.reference.child(childPath)
        pathReference.downloadUrl.addOnSuccessListener {
            Glide.with(context)
                .asBitmap()
                .load(it.toString())
                .into(object: CustomTarget<Bitmap>(){
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        view.setImage(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                    }
                })
        }
    }
}
