package com.example.dell.weatherappkotlin.api

import com.github.leonardoxh.livedatacalladapter.LiveDataCallAdapterFactory
import com.github.leonardoxh.livedatacalladapter.LiveDataResponseBodyConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetRetrofit {
    companion object {
        fun getRetrofit(): Retrofit {
            val client = OkHttpClient.Builder()
            val builder = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .addConverterFactory(LiveDataResponseBodyConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
            return builder.client(client.build()).build()
        }
    }
}