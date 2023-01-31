package com.example.wirebarley.core.dataSourceLocal.room.converters

import androidx.room.TypeConverter
import com.example.wirebarley.core.model.Currency

class CurrencyConverter {

    @TypeConverter
    fun toCurrency(value: String): Currency = Currency.values().first { currency ->
        currency.name == value
    }

    @TypeConverter
    fun fromCurrency(value: Currency): String = value.name
}