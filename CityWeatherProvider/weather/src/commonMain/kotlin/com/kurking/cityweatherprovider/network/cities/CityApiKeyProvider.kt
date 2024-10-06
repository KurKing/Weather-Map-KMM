package com.kurking.cityweatherprovider.network.cities

interface CityApiKeyProvider {

    fun getApiKey(): String
}