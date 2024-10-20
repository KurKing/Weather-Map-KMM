package com.kurking.cityweatherprovider.repository.city.fetchedcoordinates

import kotlin.math.*

data class Coordinates(
    val latitude: Double,
    val longitude: Double
) {

    internal fun distanceTo(other: Coordinates): Double {
        val earthRadius = 6371.0

        val dLat = toRadians(other.latitude - latitude)
        val dLng = toRadians(other.longitude - longitude)

        return 2 * earthRadius * asin(
            sqrt(
                sin(dLat / 2) * sin(dLat / 2) +
                        cos(toRadians(latitude)) * cos(toRadians(other.latitude)) *
                        sin(dLng / 2) * sin(dLng / 2)
            )
        )
    }

    private fun toRadians(deg: Double): Double = deg / 180.0 * PI
}