package com.example.shereats.model.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MHomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "To be completed"
    }
    val text: LiveData<String> = _text
}