package com.example.dell.weatherappkotlin.model


import com.example.architectureapp.data.model.Clouds
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class InfoList(var dt:Int, var weather:List<Weather>, var clouds: Clouds, var wind: Wind,
                    var rain: Rain, var sys:Sys, var dt_txt:String) {


    inner class Weather {

        @SerializedName("id")
        @Expose
        var id: Int? = null
        @SerializedName("main")
        @Expose
        var main: String? = null
        @SerializedName("description")
        @Expose
        var description: String? = null
        @SerializedName("icon")
        @Expose
        var icon: String? = null
    }

    inner class Wind {

        @SerializedName("speed")
        @Expose
        var speed: Double? = null
        @SerializedName("deg")
        @Expose
        var deg: Double? = null
    }

    inner class Rain {

        @SerializedName("3h")
        @Expose
        var _3h: Double? = null
    }

    inner class Sys {

        @SerializedName("pod")
        @Expose
        var pod: String? = null
    }

}
