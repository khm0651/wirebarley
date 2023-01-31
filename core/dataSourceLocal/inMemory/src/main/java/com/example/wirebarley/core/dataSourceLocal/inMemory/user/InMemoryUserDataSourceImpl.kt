package com.example.wirebarley.core.dataSourceLocal.inMemory.user

import com.example.wirebarley.core.model.CountryInformation
import com.example.wirebarley.core.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryUserDataSourceImpl @Inject constructor(): InMemoryUserDataSource {
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