package com.example.architectureapp.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.arch.persistence.room.migration.Migration
import android.content.Context

@Database(entities = [(Weather::class)], version =3)
 abstract class AppDatabase : RoomDatabase() {
     abstract val weatherDao:WeatherDao

    companion object {
        @Volatile private var instance:AppDatabase?=null

        fun getInstance(context: Context):AppDatabase{
            if (instance!=null)
                return instance!!
            synchronized(this){
                instance= Room.
                    databaseBuilder(context.applicationContext,AppDatabase::class.java,"weatherdb")
                    .build()
                return instance!!
            }
        }
    }

}
