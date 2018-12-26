package com.example.architectureapp.data.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "weather")
data class Weather
    (
    @PrimaryKey
    var id:Long,
    var description:String,
    var clouds:String,
    var wind:String,
    var date:String,
    var icon:String) {


}
