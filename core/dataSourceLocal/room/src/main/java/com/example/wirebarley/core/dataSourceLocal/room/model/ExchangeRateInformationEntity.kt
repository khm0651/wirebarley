package com.example.wirebarley.core.dataSourceLocal.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.wirebarley.core.common.extensions.toLocalDateTime
import com.example.wirebarley.core.model.Currency
import com.example.wirebarley.core.model.ExchangeRateInformation
import java.util.*

@Entity(
    tableName = "exchange_rates",
    primaryKeys = ["fromCurrency", "toCurrency"]
)
data class ExchangeRateInformationEntity(
    val fromCurrency: String,
    val toCurrency: String,
    val exchangeRate: Double,
    @ColumnInfo("created_at") val createdAt: Long
)

fun ExchangeRateInformationEntity.toDomain() = ExchangeRateInformation(
    from = Currency.values().first { it.name == fromCurrency },
    to = Currency.values().first { it.name == toCurrency },
    exchangeRate = exchangeRate,
    createdAt = createdAt.toLocalDateTime()
)
