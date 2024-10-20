package com.kurking.cityweatherprovider.repository.weather

data class WeatherItem(

    val temperature: Int,
    val windSpeed: Double,
    val humidity: Int,
    val iconId: Int,
    val date: Long
)