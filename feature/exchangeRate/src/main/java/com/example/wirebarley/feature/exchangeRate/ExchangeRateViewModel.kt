package com.example.wirebarley.feature.exchangeRate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wirebarley.core.domain.GetExchangeRateUseCase
import com.example.wirebarley.core.domain.UpdateExchangeRateStatusUseCase
import com.example.wirebarley.core.domain.UpdateExchangeRateUseCase
import com.example.wirebarley.core.model.Country
import com.example.wirebarley.core.model.CountryInformation
import com.example.wirebarley.core.model.Currency
import com.example.wirebarley.core.model.ExchangeRate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangeRateViewModel @Inject constructor(
    private val getExchangeRateUseCase: GetExchangeRateUseCase,
    private val updateExchangeRateStatusUseCase: UpdateExchangeRateStatusUseCase,
    private val updateExchangeRateUseCase: UpdateExchangeRateUseCase,
) : ViewModel() {

    init {
        updateExchangeRate()
    }

    val countryInformationLists = listOf(
        CountryInformation(name = Country.KOR, currency = Currency.KRW),
        CountryInformation(name = Country.JPN, currency = Currency.JPY),
        CountryInformation(name = Country.PHL, currency = Currency.PHP),
    )

    val exchangeRateUi: StateFlow<ExchangeRateUiState> = getExchangeRateUseCase().map {
        ExchangeRateUiState.Success(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = ExchangeRateUiState.Loading
    )

    fun updateSelected(countryInformation: CountryInformation){
        updateExchangeRateStatusUseCase(selectedToCountryInformation = countryInformation)
    }

    fun updateRemittance(remittance: String){
        updateExchangeRateStatusUseCase(remittance = remittance.toDouble())
    }

    fun updateExchangeRate() = viewModelScope.launch {
        updateExchangeRateUseCase()
    }
}

sealed interface ExchangeRateUiState{
    data class Success(val exchangeRate: ExchangeRate) : ExchangeRateUiState

    object Error : ExchangeRateUiState
    object Loading : ExchangeRateUiState
}

