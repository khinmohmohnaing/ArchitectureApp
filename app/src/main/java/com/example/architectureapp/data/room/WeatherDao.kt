package com.example.architectureapp.data.room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weatherList: ArrayList<Weather>)

    @Query("SELECT * FROM weather")
    fun getAllWeatherInfo(): LiveData<List<Weather>>
}
