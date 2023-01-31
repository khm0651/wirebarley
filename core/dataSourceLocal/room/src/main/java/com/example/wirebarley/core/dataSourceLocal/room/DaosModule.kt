package com.example.wirebarley.core.dataSourceLocal.room

import com.example.wirebarley.core.dataSourceLocal.room.dao.ExchangeRateDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesExchangeRateDao(
        database: WirebarleyDatabase
    ): ExchangeRateDao = database.exchangeRateDao()
}