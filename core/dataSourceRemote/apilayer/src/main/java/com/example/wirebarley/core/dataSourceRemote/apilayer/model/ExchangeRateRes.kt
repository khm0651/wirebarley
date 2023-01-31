package com.example.wirebarley.core.dataSourceRemote.apilayer.model

import kotlinx.serialization.Serializable

@Serializable
data class ExchangeRateRes(
    val quotes: Map<String, Double>,
    val source: String,
    val success: Boolean,
    val timestamp: Long
)