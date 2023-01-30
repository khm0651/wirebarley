package com.example.wirebarley.data.repository

import com.example.wirebarley.model.CountryInformation
import com.example.wirebarley.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(

): UserRepository{
    private val user: MutableStateFlow<UserData> = MutableStateFlow(UserData.Empty)

    override fun getUserStream(): Flow<UserData> = user

    override fun updateRemittance(remittance: Double){
        val exchangeRateStatus = user.value.exchangeRateStatus.copy(
            remittance = remittance
        )
        user.value = user.value.copy(exchangeRateStatus = exchangeRateStatus)
    }

    override fun updateSelectedToCountryInformation(country: CountryInformation) {
        val exchangeRateStatus = user.value.exchangeRateStatus.copy(
            selectedToCountryInformation = country
        )
        user.value = user.value.copy(exchangeRateStatus = exchangeRateStatus)
    }
}