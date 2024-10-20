package com.kurking.cityweatherprovider.repository.city.fetchedcoordinates

internal interface FetchedCityCoordinatesRepository {

    suspend fun getFetchedCoordinates(): List<Coordinates>

    fun saveFetchedCoordinates(fetchedCityCoordinatesDTO: Coordinates)
}