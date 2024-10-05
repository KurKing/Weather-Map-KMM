package com.kurking.cityweatherprovider

import com.kurking.cityweatherprovider.network.cities.CityApiClient
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class CityApiTest {

    private lateinit var cityApi: CityApiClient

    @BeforeTest
    fun setUp() {
        cityApi = CityApiClient()
    }

    @Test
    fun testGetCities() {

        runBlocking {
            withTimeout(5000) {

                val latitude = 50.477501
                val longitude = 30.686766

                val cities = cityApi.getCities(latitude = latitude, longitude = longitude)
                println(cities)

                assertTrue(cities.isNotEmpty(), "Check if cities are returned")

                assertTrue (cities.any { it.name == "Kyiv" },
                    "Check if Kyiv is in the list")

                assertTrue (cities.any { it.name == "Brovary" },
                    "Check if Brovary is in the list")

                foreach@ for (city in cities) {

                    assertTrue(city.latitude == 50.4333, "Check latitude")
                    assertTrue(city.longitude == 30.5167, "Check longitude")
                    break@foreach
                }
            }
        }
    }
}