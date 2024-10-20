package com.kurking.cityweatherprovider.repository

import com.kurking.cityweatherprovider.cache.Database
import com.kurking.cityweatherprovider.cache.DatabaseDriverFactory
import com.kurking.cityweatherprovider.network.cities.CityApiClient
import com.kurking.cityweatherprovider.network.cities.CityApiKeyProvider
import com.kurking.cityweatherprovider.repository.city.City
import com.kurking.cityweatherprovider.repository.city.CityLocalRemoteRepository
import com.kurking.cityweatherprovider.repository.city.CityRepository
import com.kurking.cityweatherprovider.repository.city.fetchedcoordinates.Coordinates
import com.kurking.cityweatherprovider.repository.weather.MockWeatherRepository
import com.kurking.cityweatherprovider.repository.weather.WeatherItem
import com.kurking.cityweatherprovider.repository.weather.WeatherRepository

data class CityWeatherModel(
    val city: City,
    val weather: List<WeatherItem>
)

class CityWeatherRepository(
    databaseDriverFactory: DatabaseDriverFactory,
    cityApiKeyProvider: CityApiKeyProvider
) {

    private val cityRepository: CityRepository
    private val weatherRepository: WeatherRepository

    init {

        val database = Database(databaseDriverFactory)

        cityRepository = CityLocalRemoteRepository(database, CityApiClient(cityApiKeyProvider))
        weatherRepository = MockWeatherRepository(database)
    }

    suspend fun getCityWeatherNearby(latitude: Double, longitude: Double): List<CityWeatherModel> {

        val result = mutableListOf<CityWeatherModel>()

        val cities = cityRepository.getCityNearbyCoordinates(coordinates = Coordinates(latitude, longitude))

        cities.forEach {
            val weather = weatherRepository
                .getWeatherForLocation(it.coordinates.latitude, it.coordinates.longitude)

            result.add(CityWeatherModel(it, weather))
        }

        return result
    }
}