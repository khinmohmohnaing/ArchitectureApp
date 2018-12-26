package com.example.architectureapp.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.architectureapp.data.repository.WeatherRepository

class WeatherVIewModelFactory(private var weatherRepository:
                              WeatherRepository):ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherViewModel(weatherRepository) as T
    }

}