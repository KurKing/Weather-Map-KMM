package com.kurking.cityweatherprovider.cache

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.kurking.cityweatherprovider.city.DatabaseDriverFactory

class IOSDatabaseDriverFactory : DatabaseDriverFactory {

    override fun createDriver(): SqlDriver {

        return NativeSqliteDriver(AppDatabase.Schema, "weather.db")
    }
}