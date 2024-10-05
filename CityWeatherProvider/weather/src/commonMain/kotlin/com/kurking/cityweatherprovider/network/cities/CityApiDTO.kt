package com.kurking.cityweatherprovider.network.cities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityApiDTO(
    @SerialName("name")
    val name: String,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
)