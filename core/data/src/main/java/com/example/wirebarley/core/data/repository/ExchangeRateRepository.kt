package com.example.wirebarley.core.data.repository

import kotlinx.coroutines.flow.Flow

interface ExchangeRateRepository {
    fun getExchangeRate(): Flow<Double>
}