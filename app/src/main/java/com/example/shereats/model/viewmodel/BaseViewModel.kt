package com.example.shereats.model.viewmodel

import androidx.lifecycle.ViewModel
import com.example.shereats.utils.network.EndPointInterface
import com.example.shereats.utils.network.ServiceBuilder
import retrofit2.Call


/**
 * Created by Yee on 2021-12-12.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
abstract class BaseViewModel: ViewModel() {
    protected var request = ServiceBuilder.ServiceBuilder.buildService(EndPointInterface::class.java)
}