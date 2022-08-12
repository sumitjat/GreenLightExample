package com.example.greenlightexample.data.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class ResponseData(

    @SerializedName("sales_zone")
    val responseZone: List<LocationClass.ResponseZone>,
    @SerializedName("sales_region")
    val responseRegion: List<LocationClass.ResponseRegion>,
    @SerializedName("sales_area")
    val responseArea: List<LocationClass.ResponseArea>,
)
