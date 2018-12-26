package com.example.architectureapp.viewholder

import android.view.View
import com.bumptech.glide.Glide
import com.example.architectureapp.base.BaseVH
import com.example.architectureapp.data.room.Weather
import com.example.dell.weatherappkotlin.model.InfoList
import kotlinx.android.synthetic.main.card_view.view.*

class weatherViewHolder(itemView: View): BaseVH(itemView) {
    override fun bindApiData(itemlist: InfoList, onclicklistener: onclick, position: Int) {
        itemView.weathername.text=itemlist.weather[0].description.toString()
        itemView.cloudss.text=itemlist.clouds.all.toString()
        itemView.winds.text=itemlist.wind.speed.toString()
        itemView.dates.text=itemlist.dt_txt.toString()
        Glide.with(itemView.context)
            .load("http://api.openweathermap.org/img/w/" +
                    itemlist.weather[0].icon.toString()).into(itemView.img)
        itemView.setOnClickListener{itemView->onclicklistener.onItemClick(position)}
        itemView.detailbtn.setOnClickListener{itemView->onclicklistener.onItemClick(position)}
    }

    override fun bind(weather: Weather, onclicklistener: onclick, position: Int) {
            itemView.weathername.text=weather.description
            itemView.cloudss.text=weather.clouds
            itemView.winds.text=weather.wind
            itemView.dates.text=weather.date
            Glide.with(itemView.context)
                .load("http://api.openweathermap.org/img/w/" +
                        weather.icon).into(itemView.img)
            itemView.setOnClickListener{itemView->onclicklistener.onItemClick(position)}
            itemView.detailbtn.setOnClickListener{itemView->onclicklistener.onItemClick(position)}


    }

}