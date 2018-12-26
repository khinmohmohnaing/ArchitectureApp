package com.example.architectureapp.ui

import android.arch.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.architectureapp.data.repository.WeatherRepository
import com.example.architectureapp.data.room.Weather
import com.example.dell.weatherappkotlin.model.root
import retrofit2.Call

class WeatherViewModel(private var weatherRepository:WeatherRepository):android.arch.lifecycle.ViewModel() {
    fun insertWeatherInfo(weatherList: ArrayList<Weather>){
        weatherRepository.insertWeatherInfo(weatherList)
    }
    fun getAllWeatherInfo():LiveData<List<Weather>>{
        return weatherRepository.getAllWeatherInfo()
    }

}