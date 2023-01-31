package com.example.wirebarley.core.dataSourceRemote.apilayer.dataSource

import com.example.wirebarley.core.dataSourceRemote.apilayer.model.ExchangeRateRes

interface ApilayerDataSource {
    suspend fun getExchangeRate(): ExchangeRateRes
}