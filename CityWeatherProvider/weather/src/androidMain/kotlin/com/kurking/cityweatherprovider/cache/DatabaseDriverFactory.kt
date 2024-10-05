package com.kurking.cityweatherprovider.cache

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.kurking.cityweatherprovider.city.DatabaseDriverFactory

class AndroidDatabaseDriverFactory(private val context: Context) : DatabaseDriverFactory {

    override fun createDriver(): SqlDriver {

        return AndroidSqliteDriver(AppDatabase.Schema, context, "weather.db")
    }
}