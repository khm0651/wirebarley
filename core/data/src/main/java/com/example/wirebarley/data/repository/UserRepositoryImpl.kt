package com.example.wirebarley.data.repository

import com.example.wirebarley.core.dataSourceLocal.inMemory.user.InMemoryUserDataSource
import com.example.wirebarley.model.CountryInformation
import com.example.wirebarley.model.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDataSource: InMemoryUserDataSource
): UserRepository{

    override fun getUserStream(): Flow<UserData> = userDataSource.getUserStream()

    override fun updateExchangeRateStatus(
        selectedFromCountryInformation: CountryInformation?,
        selectedToCountryInformation: CountryInformation?,
        remittance: Double?
    ) {
        userDataSource.updateExchangeRateStatus(
            selectedFromCountryInformation = selectedFromCountryInformation,
            selectedToCountryInformation = selectedToCountryInformation,
            remittance = remittance
        )
    }
}