package com.kurking.cityweatherprovider

import com.kurking.cityweatherprovider.cache.City
import com.kurking.cityweatherprovider.cache.Database
import com.kurking.cityweatherprovider.cache.IOSDatabaseDriverFactory
import com.kurking.cityweatherprovider.city.CityApiDTO
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DatabaseTest {

    private lateinit var database: Database

    @BeforeTest
    fun setUp() {
        database = Database(IOSDatabaseDriverFactory())
    }

    @Test
    fun testInsertAndQuery() {

        val cityDTO = CityApiDTO("TestCity", 0.0, 0.0)

        database.insertCity(cityDTO)

        val queriedCity = database.getAllCities().first()
        assertEquals(cityDTO.name, queriedCity.name, "Check if the city was inserted and queried correctly")
    }
}