package com.example.wirebarley.core.domain

import com.example.wirebarley.core.data.repository.UserRepository
import com.example.wirebarley.core.model.Country
import com.example.wirebarley.core.model.CountryInformation
import com.example.wirebarley.core.model.Currency
import com.example.wirebarley.core.test.repository.TestUserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class UpdateExchangeRateStatusUseCaseTest{

    private val userRepository: UserRepository = TestUserRepository()
    private val useCase = UpdateExchangeRateStatusUseCase(userRepository)

    @Test
    fun `selectedFromCountryInformation param만 넘길 시 해당 필드만 업데이트 되어야 한다`() = runTest{
        val userStream = userRepository.getUserStream()
        val beforeUpdateUserData = userStream.first()
        val selectedFromCountryInformation = CountryInformation(Country.PHL, Currency.PHP)
        useCase.invoke(selectedFromCountryInformation = selectedFromCountryInformation)
        val afterUpdateUserData = userStream.first()

        assertEquals(
            beforeUpdateUserData.exchangeRateStatus.remittance,
            afterUpdateUserData.exchangeRateStatus.remittance,
            0.0001
        )

        assertEquals(
            beforeUpdateUserData.exchangeRateStatus.selectedToCountryInformation,
            afterUpdateUserData.exchangeRateStatus.selectedToCountryInformation
        )

        assertEquals(
            afterUpdateUserData.exchangeRateStatus.selectedFromCountryInformation,
            selectedFromCountryInformation
        )
    }

    @Test
    fun `selectedToCountryInformation param만 넘길 시 해당 필드만 업데이트 되어야 한다`() = runTest{
        val userStream = userRepository.getUserStream()
        val beforeUpdateUserData = userStream.first()
        val selectedToCountryInformation = CountryInformation(Country.PHL, Currency.PHP)
        useCase.invoke(selectedToCountryInformation = selectedToCountryInformation)
        val afterUpdateUserData = userStream.first()

        assertEquals(
            beforeUpdateUserData.exchangeRateStatus.remittance,
            afterUpdateUserData.exchangeRateStatus.remittance,
            0.0001
        )

        assertEquals(
            beforeUpdateUserData.exchangeRateStatus.selectedFromCountryInformation,
            afterUpdateUserData.exchangeRateStatus.selectedFromCountryInformation
        )

        assertEquals(
            afterUpdateUserData.exchangeRateStatus.selectedToCountryInformation,
            selectedToCountryInformation
        )
    }

    @Test
    fun `remittance param만 넘길 시 해당 필드만 업데이트 되어야 한다`() = runTest{
        val userStream = userRepository.getUserStream()
        val beforeUpdateUserData = userStream.first()
        val remittance = 200.0
        useCase.invoke(remittance = remittance)
        val afterUpdateUserData = userStream.first()

        assertEquals(
            beforeUpdateUserData.exchangeRateStatus.selectedToCountryInformation,
            afterUpdateUserData.exchangeRateStatus.selectedToCountryInformation,
        )

        assertEquals(
            beforeUpdateUserData.exchangeRateStatus.selectedFromCountryInformation,
            afterUpdateUserData.exchangeRateStatus.selectedFromCountryInformation
        )

        assertEquals(
            afterUpdateUserData.exchangeRateStatus.remittance,
            remittance,
            0.0001
        )
    }

}