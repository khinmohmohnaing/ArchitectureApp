@file:Suppress("DEPRECATION")

package com.example.dell.weatherappkotlin.api

import android.arch.lifecycle.LiveData
import com.example.dell.weatherappkotlin.model.root
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface mWeatherApiInterface {
    @Headers("x-api-key: 51dbe8eca0f8d327405da57e6eed0eb9")
    @GET("data/2.5/forecast")
    abstract fun getweatherinfo(
        @Query("lat") lat: Double?,
        @Query("lon") lon: Double?
    ): Call<root>

}