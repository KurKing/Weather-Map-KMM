package com.kurking.cityweatherprovider.network.cities

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.coroutines.cancellation.CancellationException

class CityApiException(message: String) : Exception(message)

class CityApiClient {

    private val httpClient = HttpClient {

        install(ContentNegotiation) {

            json(Json {

                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    @Throws(CityApiException::class, CancellationException::class)
    suspend fun getCities(latitude: Double, longitude: Double): List<CityApiDTO> {

        val response = httpClient.get {
            url("https://world-geo-data.p.rapidapi.com/cities/nearby?format=json" +
                    "&language=en" +
                    "&latitude=$latitude" +
                    "&longitude=$longitude" +
                    "&radius=100" +
                    "&min_population=100000")
            headers {
                append("x-rapidapi-key", "f150990606msh77b3ff9464e5904p1c3c4ajsn0bee3bf118ef")
                append("x-rapidapi-host", "world-geo-data.p.rapidapi.com")
            }
        }

        if (response.status.value in 200..299) {
            return response.body<CitiesResponse>().cities
        }

        throw CityApiException("Failed to fetch cities")
    }

    @Serializable
    private class CitiesResponse (
        val cities: List<CityApiDTO>
    )
}