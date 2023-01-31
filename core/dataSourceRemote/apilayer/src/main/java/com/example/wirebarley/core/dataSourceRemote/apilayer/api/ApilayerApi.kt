package com.example.wirebarley.core.dataSourceRemote.apilayer.api

import com.example.wirebarley.core.dataSourceRemote.apilayer.model.ExchangeRateRes
import retrofit2.http.GET

interface ApilayerApi {

    @GET("/api/live")
    suspend fun getExchangeRate(): ExchangeRateRes
}