package com.example.shereats.utils.firebase

import android.content.Context
import android.net.Uri
import com.example.shereats.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage


/**
 * Created by Yee on 2021-12-19.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class StorageUtil {
    companion object{
        private const val ONE_MEGABYTE: Long = 1024 * 1024
        private val storage = Firebase.storage
        val reference = storage.reference
        //your.package.here/drawable/image_name


    }
}