package com.example.wirebarley.core.data.repository

import com.example.wirebarley.core.model.CountryInformation
import com.example.wirebarley.core.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserStream(): Flow<UserData>
    fun updateExchangeRateStatus(
        selectedFromCountryInformation: CountryInformation? = null,
        selectedToCountryInformation: CountryInformation? = null,
        remittance: Double? = null
    )
}