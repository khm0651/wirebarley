package com.example.wirebarley.core.data.repository

import com.example.wirebarley.core.dataSourceLocal.room.dataSource.RoomDataSource
import com.example.wirebarley.core.dataSourceLocal.room.model.ExchangeRateInformationEntity
import com.example.wirebarley.core.dataSourceLocal.room.model.toDomain
import com.example.wirebarley.core.dataSourceRemote.apilayer.dataSource.ApilayerDataSource
import com.example.wirebarley.core.model.Currency
import com.example.wirebarley.core.model.ExchangeRateInformation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExchangeRateRepositoryImpl @Inject constructor(
    private val apilayerDataSource: ApilayerDataSource,
    private val roomDataSource: RoomDataSource,
): ExchangeRateRepository {
    override fun getExchangeRateStream(from: Currency, to: Currency): Flow<ExchangeRateInformation> {
        return roomDataSource.getExchangeRate(from, to).map { it.toDomain() }
    }

    override suspend fun updateExchangeRate() {
        val exchangeRate = apilayerDataSource.getExchangeRate()
        val from = exchangeRate.source

        roomDataSource.upsertExchangeRate(
            exchangeRate.quotes.map {
                val to = it.key.removePrefix(from)
                ExchangeRateInformationEntity(
                    fromCurrency = from,
                    toCurrency = to,
                    exchangeRate = it.value,
                    createdAt = System.currentTimeMillis()
                )
            }
        )
    }
}