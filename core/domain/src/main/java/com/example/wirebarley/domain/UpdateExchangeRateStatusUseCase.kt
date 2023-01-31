package com.example.wirebarley.domain

import com.example.wirebarley.data.repository.UserRepository
import com.example.wirebarley.model.CountryInformation
import javax.inject.Inject

class UpdateExchangeRateStatusUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    operator fun invoke(
        selectedFromCountryInformation: CountryInformation? = null,
        selectedToCountryInformation: CountryInformation? = null,
        remittance: Double? = null
    ) {
        userRepository.updateExchangeRateStatus(
            selectedFromCountryInformation = selectedFromCountryInformation,
            selectedToCountryInformation = selectedToCountryInformation,
            remittance = remittance
        )
    }
}