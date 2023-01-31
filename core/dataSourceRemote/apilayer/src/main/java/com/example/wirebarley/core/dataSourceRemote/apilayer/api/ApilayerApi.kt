package com.example.wirebarley.core.dataSourceRemote.apilayer.api

import com.example.wirebarley.core.dataSourceRemote.apilayer.model.ExchangeRateRes
import retrofit2.http.GET

interface ApilayerApi {

    @GET("/currency_data/live")
    suspend fun getExchangeRate(): ExchangeRateRes
}