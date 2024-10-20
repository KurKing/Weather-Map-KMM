package com.kurking.cityweatherprovider.cache

import com.kurking.cityweatherprovider.repository.city.City
import com.kurking.cityweatherprovider.repository.city.fetchedcoordinates.Coordinates

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {

    private val dataBase = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = dataBase.appDatabaseQueries

    internal fun getCitiesNearby(latitude: Double, longitude: Double, diff: Double): List<City> {
        return dbQuery.selectCitiesNearby(longitude - diff,
            longitude + diff,
            latitude - diff,
            latitude + diff).executeAsList().map {
            City(
                name = it.name,
                coordinates = Coordinates(
                    latitude = it.latitude,
                    longitude = it.longitude
                )
            )
        }
    }

    internal fun insertCity(city: City) {
        dbQuery.insertCity(
            name = city.name,
            latitude = city.coordinates.latitude,
            longitude = city.coordinates.longitude
        )
    }

    internal fun getAllFetchedCoordinates(): List<FetchedCoordinates> {
        return dbQuery.selectAllFetchedCoordinates().executeAsList()
    }

    internal fun insertFetchedCoordinates(latitude: Double, longitude: Double) {
        dbQuery.insertFetchedCoordinates(
            latitude = latitude,
            longitude = longitude
        )
    }
}