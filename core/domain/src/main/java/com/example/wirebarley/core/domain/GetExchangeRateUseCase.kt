package com.example.wirebarley.core.domain

import com.example.wirebarley.core.data.repository.ExchangeRateRepository
import com.example.wirebarley.core.data.repository.UserRepository
import com.example.wirebarley.core.model.ExchangeRate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetExchangeRateUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val exchangeRateRepository: ExchangeRateRepository,
) {

    operator fun invoke():Flow<ExchangeRate> {
        return combine(exchangeRateRepository.getExchangeRate(), userRepository.getUserStream()){ exchangeRate, user ->
            ExchangeRate(
                from = user.exchangeRateStatus.selectedFromCountryInformation,
                to = user.exchangeRateStatus.selectedToCountryInformation,
                exchangeRate = exchangeRate,
                requestTime = "2023-01-30 17:45",
                remittance = user.exchangeRateStatus.remittance,
                result = exchangeRate * user.exchangeRateStatus.remittance
            )
        }
    }
}