package com.example.greenlightexample.data.models

sealed class LocationClass {

    data class ResponseZone(
        val zone: String,
        val territory: String
    ) : LocationClass()

    data class ResponseRegion(
        val region: String,
        val territory: String
    ) : LocationClass()

    data class ResponseArea(
        val area: String,
        val territory: String
    ) : LocationClass()
}
