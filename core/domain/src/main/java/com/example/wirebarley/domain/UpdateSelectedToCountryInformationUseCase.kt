package com.example.wirebarley.domain

import com.example.wirebarley.data.repository.UserRepository
import com.example.wirebarley.model.CountryInformation
import javax.inject.Inject

class UpdateSelectedToCountryInformationUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    operator fun invoke(country: CountryInformation) {
        userRepository.updateSelectedToCountryInformation(country)
    }
}