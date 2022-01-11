package com.grindewald1900.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "To be completed"
    }
    val text: LiveData<String> = _text
}