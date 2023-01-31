package com.example.wirebarley.data.repository

import com.example.wirebarley.model.CountryInformation
import com.example.wirebarley.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserStream(): Flow<UserData>
    fun updateExchangeRateStatus(
        selectedFromCountryInformation: CountryInformation? = null,
        selectedToCountryInformation: CountryInformation? = null,
        remittance: Double? = null
    )
}