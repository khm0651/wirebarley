package com.example.wirebarley.core.dataSourceRemote.apilayer.dataSource

import com.example.wirebarley.core.dataSourceRemote.apilayer.api.ApilayerApi
import com.example.wirebarley.core.dataSourceRemote.apilayer.model.ExchangeRateRes
import javax.inject.Inject

class ApilayerDataSourceImpl @Inject constructor(
    private val apilayerApi: ApilayerApi
): ApilayerDataSource {
    override suspend fun getExchangeRate(): ExchangeRateRes = apilayerApi.getExchangeRate()
}