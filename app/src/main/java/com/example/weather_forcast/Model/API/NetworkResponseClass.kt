package com.example.weather_forcast.Model.API

//T Refer to weather model
sealed class NetworkResponseClass<out T> {
    // what if the request is success , loading , or failure

    data class Success<out T>(val data : T) : NetworkResponseClass<T>()
    data class Error(val message : String) : NetworkResponseClass<Nothing>()
    object loading : NetworkResponseClass<Nothing>()
}