package com.kurking.cityweatherprovider.repository.city

import com.kurking.cityweatherprovider.cache.Database
import com.kurking.cityweatherprovider.network.cities.CityApiClient
import com.kurking.cityweatherprovider.network.cities.CityApiException
import com.kurking.cityweatherprovider.repository.city.fetchedcoordinates.Coordinates
import com.kurking.cityweatherprovider.repository.city.fetchedcoordinates.FetchedCityCoordinatesRepository
import com.kurking.cityweatherprovider.repository.city.fetchedcoordinates.LocalFetchedCityCoordinatesRepository

internal class CityLocalRemoteRepository(
    private val database: Database,
    private val cityApi: CityApiClient,
): CityRepository {

    private val fetchedCityCoordinatesRepository: FetchedCityCoordinatesRepository = LocalFetchedCityCoordinatesRepository(database)

    override suspend fun getCityNearbyCoordinates(coordinates: Coordinates): List<City> {

        if (isRemoteFetchNeeded(coordinates)) {

            val remoteCities = try {
                cityApi.getCities(latitude = coordinates.latitude, longitude = coordinates.longitude)
            } catch (e: CityApiException) { emptyList() }

            if (remoteCities.isEmpty()) { return emptyList(); }

            remoteCities.forEach {
                database.insertCity(
                    City(
                        name = it.name,
                        coordinates = Coordinates(
                            latitude = it.latitude,
                            longitude = it.longitude
                        )
                    )
                )
            }
            fetchedCityCoordinatesRepository.saveFetchedCoordinates(coordinates)

            return remoteCities.map {
                City(
                    name = it.name,
                    coordinates = Coordinates(
                        latitude = it.latitude,
                        longitude = it.longitude
                    )
                )
            }
        }

        return database.getCitiesNearby(coordinates.latitude, coordinates.longitude, 100.0).map {

            City(
                name = it.name,
                coordinates = Coordinates(
                    latitude = it.coordinates.latitude,
                    longitude = it.coordinates.longitude
                )
            )
        }
    }

    private suspend fun isRemoteFetchNeeded(coordinates: Coordinates): Boolean {

        val fetchedCoordinates = fetchedCityCoordinatesRepository.getFetchedCoordinates()

        if (fetchedCoordinates.isEmpty()) {
            return true
        }

        return fetchedCoordinates.none { it.distanceTo(coordinates) < 100000 }
    }
}