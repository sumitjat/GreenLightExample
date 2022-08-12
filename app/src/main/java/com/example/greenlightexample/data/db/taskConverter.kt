package com.example.greenlightexample.data.db

import androidx.room.TypeConverter
import com.example.greenlightexample.data.models.ResponseData
import com.google.gson.Gson

object TaskConverter {
    @TypeConverter
    fun ConvertSource(source: ResponseData?): String? {
        return Gson().toJson(source)
    }

    @TypeConverter
    fun ConvertSource(source: String?): ResponseData {
        return Gson().fromJson(source, ResponseData::class.java)
    }
}