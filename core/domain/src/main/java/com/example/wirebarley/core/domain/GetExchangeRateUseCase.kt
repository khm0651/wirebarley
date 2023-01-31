package com.example.wirebarley.core.domain

import com.example.wirebarley.core.data.repository.ExchangeRateRepository
import com.example.wirebarley.core.data.repository.UserRepository
import com.example.wirebarley.core.model.ExchangeRate
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetExchangeRateUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val exchangeRateRepository: ExchangeRateRepository,
) {

    operator fun invoke():Flow<ExchangeRate> {
        return userRepository.getUserStream().map { userData ->
            exchangeRateRepository.getExchangeRateStream(
                from = userData.exchangeRateStatus.selectedFromCountryInformation.currency,
                to = userData.exchangeRateStatus.selectedToCountryInformation.currency,
            ).map { exchangeInformation ->
                ExchangeRate(
                    from = userData.exchangeRateStatus.selectedFromCountryInformation,
                    to = userData.exchangeRateStatus.selectedToCountryInformation,
                    exchangeRate = exchangeInformation.exchangeRate,
                    requestTime = exchangeInformation.createdAt,
                    remittance = userData.exchangeRateStatus.remittance,
                    result = exchangeInformation.exchangeRate * userData.exchangeRateStatus.remittance
                )
            }
        }.flatMapLatest { it }
    }
}