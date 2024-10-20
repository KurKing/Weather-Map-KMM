package com.kurking.cityweatherprovider.repository.weather

class MockWeatherRepository: WeatherRepository {

    override suspend fun getWeatherForLocation(latitude: Double, longitude: Double): WeatherItem {

        return WeatherItem(
            temperature = 20,
            windSpeed = 5.0,
            humidity = 50,
            iconId = 1,
            date = 0
        )
    }
}