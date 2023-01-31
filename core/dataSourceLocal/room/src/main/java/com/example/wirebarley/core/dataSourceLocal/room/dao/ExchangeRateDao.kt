package com.example.wirebarley.core.dataSourceLocal.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.wirebarley.core.dataSourceLocal.room.model.ExchangeRateInformationEntity
import com.example.wirebarley.core.model.Currency
import kotlinx.coroutines.flow.Flow

@Dao
interface ExchangeRateDao {

    @Query(
        value = """
            SELECT * FROM exchange_rates
            WHERE fromCurrency = :fromCurrency And toCurrency = :toCurrency
        """
    )
    fun getExchangeRate(fromCurrency: Currency, toCurrency: Currency): Flow<ExchangeRateInformationEntity?>

    @Upsert
    fun upsertExchangeRate(entities: List<ExchangeRateInformationEntity>)
}