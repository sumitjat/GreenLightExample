package com.example.greenlightexample.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import com.example.greenlightexample.data.db.LocationDao
import com.example.greenlightexample.data.models.Data
import com.example.greenlightexample.data.models.ResponseData
import retrofit2.Response
import javax.inject.Inject

class ResponseRepository @Inject constructor(
    private val apiService: ApiService,
    private val locationDao: LocationDao
) {

     val responseData: LiveData<ResponseData> =
        Transformations.map(locationDao.getAllCharacter()) {
            it?.responseData
        }

    suspend fun fetchDetails() {
        val res = apiService.getData()
        res.body()?.let { locationDao.insertCharacters(it) }
    }
}