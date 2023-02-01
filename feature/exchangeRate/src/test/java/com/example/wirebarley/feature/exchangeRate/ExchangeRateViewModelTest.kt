package com.example.wirebarley.feature.exchangeRate

import com.example.wirebarley.core.data.repository.ExchangeRateRepository
import com.example.wirebarley.core.data.repository.UserRepository
import com.example.wirebarley.core.domain.GetExchangeRateUseCase
import com.example.wirebarley.core.domain.UpdateExchangeRateStatusUseCase
import com.example.wirebarley.core.domain.UpdateExchangeRateUseCase
import com.example.wirebarley.core.model.Country
import com.example.wirebarley.core.model.CountryInformation
import com.example.wirebarley.core.model.Currency
import com.example.wirebarley.core.test.repository.TestExchangeRateRepository
import com.example.wirebarley.core.test.repository.TestUserRepository
import com.example.wirebarley.core.test.util.MainDispatcherRule
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertIs

class ExchangeRateViewModelTest{

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val userRepository: UserRepository = TestUserRepository()
    private val exchangeRateRepository: ExchangeRateRepository = TestExchangeRateRepository()
    private val getExchangeRateUseCase = GetExchangeRateUseCase(userRepository, exchangeRateRepository)
    private val updateExchangeRateUseCase = UpdateExchangeRateUseCase(exchangeRateRepository)
    private val updateExchangeRateStatusUseCase = UpdateExchangeRateStatusUseCase(userRepository)
    private lateinit var viewModel: ExchangeRateViewModel

    @Before
    fun setUp(){
        viewModel = ExchangeRateViewModel(
            getExchangeRateUseCase,
            updateExchangeRateStatusUseCase,
            updateExchangeRateUseCase
        )
    }

    @Test
    fun `exchangeRateUi의 초기 값은 ExchangeRateUiState_Loading 이여야 한다`() = runTest{
        assertIs<ExchangeRateUiState.Loading>(viewModel.exchangeRateUi.value)
    }

    @Test
    fun `exchangeRateUi가 성공적으로 값을 가져오면 ExchangeRateUiState_Success 이여야 한다`() = runTest{
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.exchangeRateUi.collect() }
        assertIs<ExchangeRateUiState.Success>(viewModel.exchangeRateUi.value)
        collectJob.cancel()
    }

    @Test
    fun `updateSelected호출시 exchangeRateUi의 필드 to가 업데이트 되어야 한다()`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.exchangeRateUi.collect() }

        val selected = CountryInformation(Country.JPN, Currency.JPY)
        viewModel.updateSelected(selected)

        val exchangeRateUi = viewModel.exchangeRateUi.value
        assertIs<ExchangeRateUiState.Success>(exchangeRateUi)

        assertEquals(selected,exchangeRateUi.exchangeRate.to)
        collectJob.cancel()
    }

    @Test
    fun `updateRemittance호출시 exchangeRateUi의 필드 remittance가 업데이트 되어야 한다()`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.exchangeRateUi.collect() }

        val remittance = "100"
        viewModel.updateRemittance(remittance)

        val exchangeRateUi = viewModel.exchangeRateUi.value
        assertIs<ExchangeRateUiState.Success>(exchangeRateUi)

        assertEquals(remittance.toDouble(),exchangeRateUi.exchangeRate.remittance,0.0001)
        collectJob.cancel()
    }
}