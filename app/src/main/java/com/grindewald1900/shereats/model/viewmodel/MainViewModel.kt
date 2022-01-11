package com.grindewald1900.shereats.model.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.grindewald1900.shereats.utils.firebase.StorageUtil
import com.grindewald1900.shereats.view.custom.RoundImageView

/**
 * Created by Yee on 2021-12-26.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class MainViewModel: BaseViewModel(){
    private var imageUrl: MutableLiveData<Uri> = MutableLiveData()

    fun getProfileImage(): LiveData<Uri>{
        return imageUrl
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
            imageUrl.postValue(it)
        }
    }


}