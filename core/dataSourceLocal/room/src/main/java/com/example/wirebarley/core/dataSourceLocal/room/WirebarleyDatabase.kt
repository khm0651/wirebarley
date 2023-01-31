package com.example.wirebarley.core.dataSourceLocal.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.wirebarley.core.dataSourceLocal.room.converters.CurrencyConverter
import com.example.wirebarley.core.dataSourceLocal.room.dao.ExchangeRateDao
import com.example.wirebarley.core.dataSourceLocal.room.model.ExchangeRateInformationEntity

@Database(
    entities = [
        ExchangeRateInformationEntity::class
    ],
    version = 1,
    autoMigrations = [],
    exportSchema = true,
)
@TypeConverters(
    CurrencyConverter::class
)
abstract class WirebarleyDatabase: RoomDatabase() {
    abstract fun exchangeRateDao(): ExchangeRateDao
}