package com.example.greenlightexample.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.greenlightexample.data.models.Data
import com.example.greenlightexample.data.models.ResponseData

@Dao
interface LocationDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertCharacters(responseData: Data)

    @Query("SELECT * FROM Data")
     fun getAllCharacter(): LiveData<Data?>

}
