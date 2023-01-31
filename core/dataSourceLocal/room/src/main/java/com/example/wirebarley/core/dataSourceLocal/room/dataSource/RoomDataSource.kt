package com.example.wirebarley.core.dataSourceLocal.room.dataSource

import com.example.wirebarley.core.dataSourceLocal.room.model.ExchangeRateInformationEntity
import com.example.wirebarley.core.model.Currency
import kotlinx.coroutines.flow.Flow

interface RoomDataSource {

    fun getExchangeRate(from: Currency, to: Currency): Flow<ExchangeRateInformationEntity>
    fun upsertExchangeRate(entities: List<ExchangeRateInformationEntity>)
}