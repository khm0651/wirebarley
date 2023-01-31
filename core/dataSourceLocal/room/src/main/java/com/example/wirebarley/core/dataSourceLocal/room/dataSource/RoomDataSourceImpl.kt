package com.example.wirebarley.core.dataSourceLocal.room.dataSource

import com.example.wirebarley.core.dataSourceLocal.room.dao.ExchangeRateDao
import com.example.wirebarley.core.dataSourceLocal.room.model.ExchangeRateInformationEntity
import com.example.wirebarley.core.model.Currency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomDataSourceImpl @Inject constructor(
    private val exchangeRateDao: ExchangeRateDao
): RoomDataSource {
    override fun getExchangeRate(from: Currency, to: Currency): Flow<ExchangeRateInformationEntity> {
        return exchangeRateDao.getExchangeRate(from, to).map {
            it?: ExchangeRateInformationEntity(
                fromCurrency = from.name,
                toCurrency = to.name,
                exchangeRate = 0.0,
                createdAt = System.currentTimeMillis()
            )
        }
    }

    override fun upsertExchangeRate(entities: List<ExchangeRateInformationEntity>) {
        exchangeRateDao.upsertExchangeRate(entities)
    }
}