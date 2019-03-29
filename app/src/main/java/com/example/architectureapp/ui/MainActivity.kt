package com.example.architectureapp.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.SharedPreferences
import android.icu.util.Calendar
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.architectureapp.R
import com.example.architectureapp.adapter.MyAdapter
import com.example.architectureapp.base.BaseVH
import com.example.architectureapp.data.room.Weather
import com.example.architectureapp.utilities.InjectorUtils
import com.example.architectureapp.utilities.ioThread
import com.example.dell.weatherappkotlin.api.GetRetrofit
import com.example.dell.weatherappkotlin.api.mWeatherApiInterface
import com.example.dell.weatherappkotlin.model.root
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity(),BaseVH.onclick {
    lateinit var myAdapter: MyAdapter
    private lateinit var viewModel: WeatherViewModel
    public lateinit var weather:Weather
    lateinit var api:mWeatherApiInterface
    var weatherList:ArrayList<Weather> = ArrayList()
    lateinit var sharedPreference:SharedPreferences
    lateinit var editor:SharedPreferences.Editor

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreference=getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        editor=sharedPreference.edit()
        myAdapter = MyAdapter(this)
        weatherRecycler.layoutManager = LinearLayoutManager(this)

        val factory=InjectorUtils.provideViewModelFactory(this)
        viewModel=ViewModelProviders.of(this,factory)
            .get(WeatherViewModel::class.java)

        var date=Calendar.getInstance().time
        val dateformat=SimpleDateFormat("dd-MMM-yyyy")
        var currentDate=dateformat.format(date)
        var oldDate=sharedPreference.getString("Date","null")
        if(!(oldDate.equals(currentDate))){
            Log.i("Date","next day ")
            getFromAPI()
        }else addWeatherInfoToAdapter()

    }


    private fun getFromAPI()  {
        api = GetRetrofit.getRetrofit().
            create(mWeatherApiInterface::class.java)
        var weatherInfo=api.getweatherinfo(16.871311,96.199379)
        weatherInfo.enqueue(object : Callback<root> {
            override fun onResponse(call: Call<root>, response: Response<root>) {
                if (response.isSuccessful()) {
                    progressBar.visibility= View.GONE
                    Log.i("MainActivity",response.body()!!.list.size.toString()+"api size")
                    myAdapter.addInfoList(response.body()!!)
                    saveWeather(response.body()!!)
                    weatherRecycler.adapter = myAdapter
                    myAdapter.notifyDataSetChanged()
                    myAdapter.setOnClick(this@MainActivity)

                } else
                    Log.e("MainActivity", "unsuccess")
            }
            override fun onFailure(call: Call<root>, t: Throwable) {
                Log.e("MainActivity", "fail")
            }
        })
    }

    private fun addWeatherInfoToAdapter() {
        progressBar.visibility=View.GONE
        val weatherInfoList=viewModel.getAllWeatherInfo()

        weatherInfoList.observe(this, Observer {
            Log.i("MainActivity",it!!.size.toString()+"add adapter size")
            myAdapter.addList(it!!)
        })
        weatherRecycler.adapter = myAdapter
        myAdapter.notifyDataSetChanged()
        myAdapter.setOnClick(this@MainActivity)
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this,position.toString(),Toast.LENGTH_LONG).show()
    }

    private fun saveWeather(infoList: root) {
        infoList.list.forEach {
            Log.i("MainActivity",infoList.list.indexOf(it).toString())
            weather=Weather(infoList.list.indexOf(it).toLong(),it.weather.get(0).description.toString(),
                it.clouds.all.toString(),it.wind.speed.toString(),it.dt_txt.toString(),
                it.weather.get(0).icon.toString())
            Log.i("MainActivity",weather.description)
            weatherList.add(weather)

        }
        ioThread {
            viewModel.insertWeatherInfo(weatherList)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onResume() {
        super.onResume()

        if(sharedPreference.getBoolean("firstRun",true)){
            Log.i("True","onresume")
            var date=Calendar.getInstance().time
            val dateformat=SimpleDateFormat("dd-MMM-yyyy")
            var oldDate=dateformat.format(date)
            getFromAPI()
            editor.putBoolean("firstRun",false)
            editor.putString("Date",oldDate)
            editor.commit()
        }
    }
}


