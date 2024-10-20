package com.kurking.cityweatherprovider

import com.kurking.cityweatherprovider.cache.Database
import com.kurking.cityweatherprovider.cache.IOSDatabaseDriverFactory
import com.kurking.cityweatherprovider.repository.city.City
import com.kurking.cityweatherprovider.repository.city.fetchedcoordinates.Coordinates
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DatabaseTest {

    private lateinit var database: Database

    @BeforeTest
    fun setUp() {
        database = Database(IOSDatabaseDriverFactory())
    }

//    @Test
    fun testInsertAndQuery() {

        val city = City(
            name = "Test City",
            coordinates = Coordinates(
                latitude = 0.0,
                longitude = 0.0
            )
        )

        database.insertCity(city)

        val queriedCity = database.getCitiesNearby(0.0, 0.0, 100.0).first()
        assertEquals(city.name, queriedCity.name, "Check if the city was inserted and queried correctly")
    }
}