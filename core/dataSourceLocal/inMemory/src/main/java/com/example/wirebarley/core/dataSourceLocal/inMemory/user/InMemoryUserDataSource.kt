package com.example.wirebarley.core.dataSourceLocal.inMemory.user

import com.example.wirebarley.model.CountryInformation
import com.example.wirebarley.model.UserData
import kotlinx.coroutines.flow.Flow

interface InMemoryUserDataSource {
    fun getUserStream(): Flow<UserData>
    fun updateExchangeRateStatus(
        selectedFromCountryInformation: CountryInformation? = null,
        selectedToCountryInformation: CountryInformation? = null,
        remittance: Double? = null
    )
}