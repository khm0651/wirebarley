package com.example.wirebarley.core.dataSourceRemote.apilayer.dataSource

import kotlinx.coroutines.flow.Flow

interface ApilayerDataSource {
    fun getExchangeRate(): Flow<Double>
}