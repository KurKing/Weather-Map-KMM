package com.kurking.cityweatherprovider.cache

import com.kurking.cityweatherprovider.city.CityApiDTO
import com.kurking.cityweatherprovider.city.DatabaseDriverFactory

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {

    private val dataBase = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = dataBase.appDatabaseQueries

    internal fun getAllCities(): List<City> {
        return dbQuery.selectAllCities().executeAsList()
    }

    internal fun insertCity(city: CityApiDTO) {
        dbQuery.insertCity(
            name = city.name,
            latitude = city.latitude,
            longitude = city.longitude
        )
    }
}