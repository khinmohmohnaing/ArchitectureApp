package com.example.architectureapp.utilities

import android.content.Context
import com.example.architectureapp.data.repository.WeatherRepository
import com.example.architectureapp.data.room.AppDatabase
import com.example.architectureapp.ui.WeatherVIewModelFactory
import com.example.dell.weatherappkotlin.api.Get_Retrofit
import com.example.dell.weatherappkotlin.api.mWeatherApiInterface

object InjectorUtils {
    fun provideViewModelFactory(context: Context):WeatherVIewModelFactory{
        var weatherRepository=WeatherRepository
            .getInstance(AppDatabase.getInstance(context).weatherDao)
        return WeatherVIewModelFactory(weatherRepository)
    }
}