package com.example.wirebarley.core.domain

import com.example.wirebarley.core.data.repository.ExchangeRateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateExchangeRateUseCase @Inject constructor(
    private val exchangeRateRepository: ExchangeRateRepository,
) {
    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        exchangeRateRepository.updateExchangeRate()
    }
}