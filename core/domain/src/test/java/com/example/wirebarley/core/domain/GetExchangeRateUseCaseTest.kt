package com.example.wirebarley.core.domain

import com.example.wirebarley.core.data.repository.ExchangeRateRepository
import com.example.wirebarley.core.data.repository.UserRepository
import com.example.wirebarley.core.model.Country
import com.example.wirebarley.core.model.CountryInformation
import com.example.wirebarley.core.model.Currency
import com.example.wirebarley.core.test.repository.TestExchangeRateRepository
import com.example.wirebarley.core.test.repository.TestUserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class GetExchangeRateUseCaseTest{

    private val userRepository: UserRepository = TestUserRepository()
    private val exchangeRateRepository: ExchangeRateRepository = TestExchangeRateRepository()
    private val usecase = GetExchangeRateUseCase(userRepository, exchangeRateRepository)

    /**
        USD_KRW = 1150.3899
        USD_JPY = 890.2938
        USD_PHP = 660.2938
     **/
    @Test
    fun `selectedToCountryInformation = KRW, remittance = 100_0 시 result = 115038_99이여야 한다 `() = runTest{
        val remittance = 100.0
        userRepository.updateExchangeRateStatus(
            selectedToCountryInformation = CountryInformation(name = Country.KOR, currency = Currency.KRW),
            remittance = remittance
        )

        exchangeRateRepository.updateExchangeRate()

        assertEquals(
            usecase.invoke().first().result,
            TestExchangeRateRepository.USD_KRW * remittance,
            0.0001
        )
    }

    @Test
    fun `selectedToCountryInformation = JPY, remittance = 100_0 시 result = 89029_38이여야 한다 `() = runTest{
        val remittance = 100.0
        userRepository.updateExchangeRateStatus(
            selectedToCountryInformation = CountryInformation(name = Country.JPN, currency = Currency.JPY),
            remittance = remittance
        )

        exchangeRateRepository.updateExchangeRate()

        assertEquals(
            usecase.invoke().first().result,
            TestExchangeRateRepository.USD_JPY * remittance,
            0.0001
        )
    }

    @Test
    fun `selectedToCountryInformation = PHP, remittance = 100_0 시 result = 66029_38이여야 한다 `() = runTest{
        val remittance = 100.0
        userRepository.updateExchangeRateStatus(
            selectedToCountryInformation = CountryInformation(name = Country.PHL, currency = Currency.PHP),
            remittance = remittance
        )

        exchangeRateRepository.updateExchangeRate()

        assertEquals(
            usecase.invoke().first().result,
            TestExchangeRateRepository.USD_PHP * remittance,
            0.0001
        )
    }
}