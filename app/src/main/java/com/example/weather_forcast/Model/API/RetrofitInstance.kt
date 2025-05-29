package com.example.weather_forcast.Model.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BaseUrl  = "https://api.weatherapi.com"
    // here by this method we will get the instance of the retrofit
    private fun getInstance() : Retrofit{
         return Retrofit.Builder()
             .baseUrl(BaseUrl)
        // we have Gson convertor
             .addConverterFactory(GsonConverterFactory.create())
             .build()

    }

    // we are integrate the interface with this retrofit
    val weatherApi : WeatherApi = getInstance().create(WeatherApi::class.java)
}