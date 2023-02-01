package com.example.wirebarley.core.test.repository

import com.example.wirebarley.core.common.extensions.toLocalDateTime
import com.example.wirebarley.core.data.repository.ExchangeRateRepository
import com.example.wirebarley.core.model.Currency
import com.example.wirebarley.core.model.ExchangeRateInformation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import java.util.EnumMap

class TestExchangeRateRepository: ExchangeRateRepository {
    companion object{
        const val USD_KRW = 1150.3899
        const val USD_JPY = 890.2938
        const val USD_PHP = 660.2938

        val usdExchangeRateSample = EnumMap(
            hashMapOf(
                Currency.KRW to ExchangeRateInformation(Currency.USD, Currency.KRW, USD_KRW, System.currentTimeMillis().toLocalDateTime()),
                Currency.JPY to ExchangeRateInformation(Currency.USD, Currency.JPY, USD_JPY, System.currentTimeMillis().toLocalDateTime()),
                Currency.PHP to ExchangeRateInformation(Currency.USD, Currency.PHP, USD_PHP, System.currentTimeMillis().toLocalDateTime())
            )
        )
    }

    private val exchangeRate: MutableStateFlow<EnumMap<Currency,ExchangeRateInformation>> = MutableStateFlow(
        EnumMap(Currency::class.java)
    )

    fun getExchangeRateStream(): Flow<EnumMap<Currency,ExchangeRateInformation>> = exchangeRate

    override fun getExchangeRateStream(
        from: Currency,
        to: Currency
    ): Flow<ExchangeRateInformation> {
        return exchangeRate.map {
            it[to]?: throw IllegalArgumentException("Un Support Type")
        }
    }

    override suspend fun updateExchangeRate() {
        exchangeRate.value = usdExchangeRateSample
    }
}