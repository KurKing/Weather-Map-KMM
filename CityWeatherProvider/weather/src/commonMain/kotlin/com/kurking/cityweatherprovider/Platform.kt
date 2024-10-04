package com.kurking.cityweatherprovider

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform