package com.example.wirebarley.core.data.repository

import com.example.wirebarley.core.model.Currency
import com.example.wirebarley.core.model.ExchangeRateInformation
import kotlinx.coroutines.flow.Flow

interface ExchangeRateRepository {
    fun getExchangeRateStream(from: Currency, to: Currency): Flow<ExchangeRateInformation>
    suspend fun updateExchangeRate()
}