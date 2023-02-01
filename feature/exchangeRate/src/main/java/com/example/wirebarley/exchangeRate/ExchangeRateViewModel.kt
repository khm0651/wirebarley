package com.example.wirebarley.exchangeRate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wirebarley.domain.GetExchangeRateUseCase
import com.example.wirebarley.domain.UpdateRemittanceUseCase
import com.example.wirebarley.domain.UpdateSelectedToCountryInformationUseCase
import com.example.wirebarley.model.Country
import com.example.wirebarley.model.CountryInformation
import com.example.wirebarley.model.Currency
import com.example.wirebarley.model.ExchangeRate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class ExchangeRateViewModel @Inject constructor(
    private val getExchangeRateUseCase: GetExchangeRateUseCase,
    private val updateSelectedToCountryInformationUseCase: UpdateSelectedToCountryInformationUseCase,
    private val updateRemittanceUseCase: UpdateRemittanceUseCase,
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
        updateSelectedToCountryInformationUseCase(countryInformation)
    }

    fun updateRemittance(remittance: String){
        updateRemittanceUseCase(remittance.toDouble())
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

