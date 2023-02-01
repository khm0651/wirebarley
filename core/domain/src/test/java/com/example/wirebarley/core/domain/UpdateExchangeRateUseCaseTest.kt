package com.example.wirebarley.core.domain

import com.example.wirebarley.core.test.repository.TestExchangeRateRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class UpdateExchangeRateUseCaseTest{

    private val exchangeRateRepository = TestExchangeRateRepository()
    private val usecase: UpdateExchangeRateUseCase = UpdateExchangeRateUseCase(exchangeRateRepository)

    @Test
    fun `usdExchangeRate 값으로 업데이트 되어야 한다`() = runTest{
        usecase.invoke()
        assertEquals(
            exchangeRateRepository.getExchangeRateStream().first(),
            TestExchangeRateRepository.usdExchangeRateSample
        )
    }
}