package com.example.wirebarley.model

data class ExchangeRate(
    val from: CountryInformation,
    val to: CountryInformation,
    val exchangeRate: Double,
    val requestTime: String,
    val remittance: Double,
    val result: Double,
)
