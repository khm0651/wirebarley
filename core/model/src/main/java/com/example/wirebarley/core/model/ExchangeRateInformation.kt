package com.example.wirebarley.core.model

data class ExchangeRateInformation(
    val from: Currency,
    val to: Currency,
    val exchangeRate: Double,
    val createdAt: String
)
