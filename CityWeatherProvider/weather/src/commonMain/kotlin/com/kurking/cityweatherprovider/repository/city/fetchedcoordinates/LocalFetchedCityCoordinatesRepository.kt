package com.kurking.cityweatherprovider.repository.city.fetchedcoordinates

import com.kurking.cityweatherprovider.cache.Database

internal class LocalFetchedCityCoordinatesRepository(private val database: Database) : FetchedCityCoordinatesRepository {

    override suspend fun getFetchedCoordinates(): List<Coordinates> {

        return database.getAllFetchedCoordinates().map {
            Coordinates(
                latitude = it.latitude,
                longitude = it.longitude
            )
        }
    }

    override fun saveFetchedCoordinates(fetchedCityCoordinatesDTO: Coordinates) {

        database.insertFetchedCoordinates(
            latitude = fetchedCityCoordinatesDTO.latitude,
            longitude = fetchedCityCoordinatesDTO.longitude
        )
    }
}