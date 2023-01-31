package com.example.wirebarley.feature.exchangeRate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wirebarley.core.domain.GetExchangeRateUseCase
import com.example.wirebarley.core.domain.UpdateExchangeRateStatusUseCase
import com.example.wirebarley.core.model.Country
import com.example.wirebarley.core.model.CountryInformation
import com.example.wirebarley.core.model.Currency
import com.example.wirebarley.core.model.ExchangeRate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class ExchangeRateViewModel @Inject constructor(
    private val getExchangeRateUseCase: GetExchangeRateUseCase,
    private val updateExchangeRateStatusUseCase: UpdateExchangeRateStatusUseCase,
) : ViewModel() {

    val countryInformationLists = listOf(
        CountryInformation(name = Country.KOR, currency = Currency.KWR),
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
}

fun Double.toCurrencyFromToDecimalFormat(
    from: Currency = Currency.USD,
    to: Currency
): String {
    return if(this == 0.0) "0.00" else {
        val decimalFormat = DecimalFormat("#,###.00")
        "${decimalFormat.format(this)} ${to}/${from}"
    }
}

fun Double.toSecondDecimalPlaceFormat(): String {
    return if(this == 0.0) "0.00" else DecimalFormat("#,###.00").format(this)
}

fun Country.withCurrencyKrFormat(currency: Currency) = "${this.kr} (${currency})"

sealed interface ExchangeRateUiState{
    data class Success(val exchangeRate: ExchangeRate) : ExchangeRateUiState

    object Error : ExchangeRateUiState
    object Loading : ExchangeRateUiState
}

