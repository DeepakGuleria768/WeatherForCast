package com.example.weather_forcast.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_forcast.Model.API.Constants
import com.example.weather_forcast.Model.API.NetworkResponseClass
import com.example.weather_forcast.Model.API.RetrofitInstance
import com.example.weather_forcast.Model.API.WeatherModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel(){

    // let create the live data so we can expose it to the ui
    private val _WeatherResult = MutableLiveData<NetworkResponseClass<WeatherModel>>()
     val weatherResult : LiveData<NetworkResponseClass<WeatherModel>> = _WeatherResult
    // Get instance of weatherAPI
    private val weatherApi = RetrofitInstance.weatherApi
    // When i press the search button for specific city then i get data
    fun getData(city:String){

        _WeatherResult.value = NetworkResponseClass.loading
        //viewModelScope.launch() is your go-to tool in a ViewModel for starting any task that might take a long time, ensuring your app stays smooth and responsive for the user.
        viewModelScope.launch {
            // wrap this whole is try catch
            delay(2000)
            try{ val response = weatherApi.getWeather(Constants.apiKey, city = city)
                if(response.isSuccessful){
                    response.body()?.let{
                        _WeatherResult.value = NetworkResponseClass.Success(it)
                    }
                }else{
                    _WeatherResult.value = NetworkResponseClass.Error("Failed To Load Data")
                }
            }catch (E: Exception){
                _WeatherResult.value = NetworkResponseClass.Error("Failed To Load Data")
            }

        }
    }
}