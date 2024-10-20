package com.kurking.cityweatherprovider.repository.weather

import com.kurking.cityweatherprovider.cache.Database

internal class MockWeatherRepository(
    private val database: Database
): WeatherRepository {

    override suspend fun getWeatherForLocation(latitude: Double, longitude: Double): List<WeatherItem> {

        val list = mutableListOf<WeatherItem>()

        for (index in 0..4) {

            list.add(
                WeatherItem(
                    temperature = index * 5,
                    windSpeed = 5.0,
                    humidity = 50,
                    iconId = 23,
                    date = 0
                )
            )
        }

        return list;
    }
}