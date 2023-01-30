package com.example.wirebarley.exchangeRate

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class ExchangeRateViewModel @Inject constructor(

) : ViewModel() {

    val countryList = listOf(
        Country(name = "한국", currency = "KRW"),
        Country(name = "일본", currency = "JPY"),
        Country(name = "필리핀", currency = "PHP"),
    )

    private val _exchangeRateUiState: MutableStateFlow<ExchangeRateUiState> = MutableStateFlow(
        ExchangeRateUiState(
            from = Country(name = "미국", currency = "USD"),
            to = Country(name = "한국", currency = "KRW"),
            exchangeRate = 1530.04999,
            requestTime = "2023-01-30 17:45",
            remittance = 0.0,
            result = 0.0
        )
    )
    val exchangeRateUiState: StateFlow<ExchangeRateUiState>
        get() = _exchangeRateUiState

    fun updateSelected(country: Country){
        _exchangeRateUiState.value = exchangeRateUiState.value.copy(
            to = country
        )
    }

    fun updateRemittance(remittance: String){
        _exchangeRateUiState.value = exchangeRateUiState.value.copy(
            remittance = remittance.toDouble()
        )
    }
}

fun Double.toCurrencyFromToDecimalFormat(
    from: String = "USD",
    to: String
): String {
    return if(this == 0.0) "0.00" else {
        val decimalFormat = DecimalFormat("#,###.00")
        "${decimalFormat.format(this)} $to/$from"
    }
}

fun Double.toSecondDecimalPlaceFormat(): String {
    return if(this == 0.0) "0.00" else DecimalFormat("#,###.00").format(this)
}

fun String.withCurrencyFormat(currency: String) = "$this ($currency)"

data class ExchangeRateUiState(
    val from: Country,
    val to: Country,
    val exchangeRate: Double,
    val requestTime: String,
    val remittance: Double,
    val result: Double,
)

data class Country(
    val name: String,
    val currency: String,
)