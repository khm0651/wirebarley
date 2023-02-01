package com.example.wirebarley.core.test.repository

import com.example.wirebarley.core.data.repository.UserRepository
import com.example.wirebarley.core.model.CountryInformation
import com.example.wirebarley.core.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class TestUserRepository: UserRepository {
    private val user: MutableStateFlow<UserData> = MutableStateFlow(UserData.Empty)

    override fun getUserStream(): Flow<UserData> = user

    override fun updateExchangeRateStatus(
        selectedFromCountryInformation: CountryInformation?,
        selectedToCountryInformation: CountryInformation?,
        remittance: Double?
    ) {
        val exchangeRateStatus = user.value.exchangeRateStatus.copy(
            selectedFromCountryInformation = selectedFromCountryInformation ?: user.value.exchangeRateStatus.selectedFromCountryInformation,
            selectedToCountryInformation = selectedToCountryInformation ?: user.value.exchangeRateStatus.selectedToCountryInformation,
            remittance = remittance ?: user.value.exchangeRateStatus.remittance
        )

        user.value = user.value.copy(exchangeRateStatus = exchangeRateStatus)
    }
}