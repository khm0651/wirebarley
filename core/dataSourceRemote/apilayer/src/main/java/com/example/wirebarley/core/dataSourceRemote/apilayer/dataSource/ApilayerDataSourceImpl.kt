package com.example.wirebarley.core.dataSourceRemote.apilayer.dataSource

import com.example.wirebarley.core.dataSourceRemote.apilayer.api.ApilayerApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApilayerDataSourceImpl @Inject constructor(
    private val apilayerApi: ApilayerApi
): ApilayerDataSource {
    override fun getExchangeRate(): Flow<Double> = flow {
        emit(apilayerApi.getExchangeRate().quotes.USDKRW)
    }
}