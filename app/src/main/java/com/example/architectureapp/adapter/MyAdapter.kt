package com.example.architectureapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.architectureapp.base.BaseVH
import com.example.architectureapp.viewholder.weatherViewHolder
import com.example.dell.weatherappkotlin.model.root
import com.example.architectureapp.R
import com.example.architectureapp.data.room.Weather


class MyAdapter(context: Context): RecyclerView.Adapter<BaseVH>() {
     var infolist: List<Weather> = emptyList()
     var weatherlist: root? = null
    lateinit var onClickItem : BaseVH.onclick
    var times:Int = 0

    fun addList(infolist:List<Weather>){
        this.infolist=infolist
        times=2
        Log.i("size",infolist.size.toString())
    }
    fun addInfoList(weatherlist:root){
        this.weatherlist=weatherlist
        times=1
        Log.i("size",weatherlist.list.size.toString())
    }
    fun setOnClick(onClickItem :BaseVH.onclick){
        this.onClickItem=onClickItem
    }
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): BaseVH {
        return weatherViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_view, parent,
                false
            ))
    }

    override fun getItemCount(): Int {
        Log.i("Times",times.toString())
        if(times==1) return weatherlist?.list!!.size
        else return infolist.size

    }

    override fun onBindViewHolder(holder: BaseVH, position: Int) {
        if(times==1)holder.bindApiData(weatherlist!!.list.get(position),onClickItem,position)
        else  holder.bind(infolist.get(position), onClickItem, position)

    }
}