package com.example.greenlightexample.data

import com.example.greenlightexample.data.models.Data
import com.example.greenlightexample.data.models.ResponseData
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("v3/286f38b4-c6c1-4348-aabc-6d396dcbc4de")
    suspend fun  getData() : Response<Data>
}