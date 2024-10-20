package com.kurking.cityweatherprovider.repository.city

import com.kurking.cityweatherprovider.repository.city.fetchedcoordinates.Coordinates

class City(
    val name: String,
    val coordinates: Coordinates
)

internal interface CityRepository {

    suspend fun getCityNearbyCoordinates(coordinates: Coordinates): List<City>
}