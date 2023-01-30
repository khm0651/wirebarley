package com.example.wirebarley.domain

import com.example.wirebarley.data.repository.UserRepository
import javax.inject.Inject

class UpdateRemittanceUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    operator fun invoke(remittance: Double) {
        userRepository.updateRemittance(remittance)
    }
}