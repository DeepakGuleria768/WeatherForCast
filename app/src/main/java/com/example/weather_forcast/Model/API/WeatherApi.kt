package com.example.weather_forcast.Model.API

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    //  here we have to create the API

    @GET("/v1/current.json")
    // we create the suspended function because it is a asynchronous call
    suspend fun getWeather(
        @Query("key") apikey : String, // apiKey --> this name can be anything that you like but ("Key") should be same as in the call in api website
        @Query("q") city : String
    ) : Response<WeatherModel>
}