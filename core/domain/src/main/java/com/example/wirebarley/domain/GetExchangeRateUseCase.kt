package com.example.wirebarley.domain

import com.example.wirebarley.data.repository.UserRepository
import com.example.wirebarley.model.ExchangeRate
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetExchangeRateUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    operator fun invoke():Flow<ExchangeRate> {
        val exchangeRate = flow {
            emit(1530.049999)
            delay(2000L)
            emit(1330.049999)
            delay(2000L)
            emit(1130.049999)
            delay(2000L)
            emit(1930.049999)
            delay(2000L)
            emit(1230.049999)
        }
        return combine(exchangeRate, userRepository.getUserStream()){ exchangeRate, user ->
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