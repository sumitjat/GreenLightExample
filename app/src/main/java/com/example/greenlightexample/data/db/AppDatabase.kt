package com.example.greenlightexample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.greenlightexample.data.models.Data
import com.example.greenlightexample.data.models.ResponseData

@Database(
    entities = [Data::class],
    version = 1,
)
@TypeConverters(TaskConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
}