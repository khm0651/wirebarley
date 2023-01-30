package com.example.wirebarley.domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetExchangeRateUseCase @Inject constructor(

) {

    operator fun invoke(from: Currency, to: Currency):Flow<Double> {
        return flow {
            emit(1530.04999)
            delay(2000L)
            emit(1130.04999)
            delay(2000L)
            emit(1230.04999)
            delay(2000L)
            emit(930.04999)
        }
    }
}

enum class Currency{
    USD,
    KWR,
    JPY,
    PHP
}