package com.example.greenlightexample.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.greenlightexample.data.db.TaskConverter
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity
data class Data(
    @PrimaryKey(autoGenerate = true,)
    val id : Int,
    @NotNull
    @SerializedName("ResponseStatus")
    val responseStatus: Int,
    @NotNull
    @SerializedName("ResponseData")
    val responseData: ResponseData
)
