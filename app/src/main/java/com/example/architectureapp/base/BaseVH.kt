package com.example.architectureapp.base

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.architectureapp.data.room.Weather
import com.example.dell.weatherappkotlin.model.InfoList

abstract class BaseVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(itemlist: Weather, onclicklistener: BaseVH.onclick, position: Int)
    abstract fun bindApiData(itemlist:InfoList,onclicklistener: BaseVH.onclick,position: Int)

    interface onclick {
        fun onItemClick(position: Int)
    }
}