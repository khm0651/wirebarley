package com.example.wirebarley.core.data.repository

import com.example.wirebarley.core.dataSourceRemote.apilayer.dataSource.ApilayerDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExchangeRateRepositoryImpl @Inject constructor(
    private val apilayerDataSource: ApilayerDataSource
): ExchangeRateRepository {
    override fun getExchangeRate(): Flow<Double> {
        return apilayerDataSource.getExchangeRate()
    }
}