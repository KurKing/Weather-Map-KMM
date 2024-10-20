package com.kurking.cityweatherprovider.repository.weather

interface WeatherRepository {

    suspend fun getWeatherForLocation(latitude: Double, longitude: Double): List<WeatherItem>
}