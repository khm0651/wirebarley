package com.example.wirebarley.core.dataSourceRemote.apilayer.model

import kotlinx.serialization.Serializable

@Serializable
data class ExchangeRateRes(
    val privacy: String,
    val quotes: Quotes,
    val source: String,
    val success: Boolean,
    val terms: String,
    val timestamp: Int
)