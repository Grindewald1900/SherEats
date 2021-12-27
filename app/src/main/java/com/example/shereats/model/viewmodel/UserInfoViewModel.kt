package com.example.shereats.model.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.shereats.model.entity.Badge
import com.example.shereats.utils.LoginStatusUtil
import com.example.shereats.utils.firebase.StorageUtil
import com.example.shereats.view.custom.RoundCornerImageView
import com.example.shereats.view.custom.RoundImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Yee on 2021-12-26.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class UserInfoViewModel: BaseViewModel() {
    private var imageUrl: MutableLiveData<Uri> = MutableLiveData()
    private var badges: MutableLiveData<List<Badge>> = MutableLiveData()
    private lateinit var call: Call<List<Badge>>

    fun getBadges(): LiveData<List<Badge>>{
        return badges
    }

    fun setBadges() {
        if(!LoginStatusUtil.isLogin()) return
        val userId = LoginStatusUtil.getUser().user_id
        call = request.getBadges(userId)

        call.enqueue(object: Callback<List<Badge>>{
            override fun onResponse(call: Call<List<Badge>>, response: Response<List<Badge>>) {
                if (response.isSuccessful){
                    badges.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<Badge>>, t: Throwable) {
                t.stackTrace
            }

        })
    }

    fun getProfileImage(): LiveData<Uri>{
        return imageUrl
    }

    /**
     * Get image for the user profile picture(if login)
     */
    fun setProfileImage(name: String, view: RoundCornerImageView, context: Context){
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
            imageUrl.postValue(it)
        }
    }


}