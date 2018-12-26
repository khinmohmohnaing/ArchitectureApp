package com.example.architectureapp.data.repository

import android.arch.lifecycle.LiveData
import com.example.architectureapp.data.room.Weather
import com.example.architectureapp.data.room.WeatherDao
import com.example.dell.weatherappkotlin.api.mWeatherApiInterface
import com.example.dell.weatherappkotlin.model.root
import retrofit2.Call

class WeatherRepository private constructor(private val weatherDao: WeatherDao){
    fun insertWeatherInfo(weatherList: ArrayList<Weather>){
        weatherDao.insertWeather(weatherList)
    }
    fun getAllWeatherInfo():LiveData<List<Weather>>{
        return weatherDao.getAllWeatherInfo()
    }
    companion object {
        @Volatile private var instance:WeatherRepository?=null

        fun getInstance(weatherDao: WeatherDao)=
                instance?: synchronized(lock = this){
                    instance?:WeatherRepository(weatherDao).also { instance=it }
                }
    }
}