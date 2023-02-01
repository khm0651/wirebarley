package com.example.wirebarley.core.common.extensions

import com.example.wirebarley.core.model.Country
import com.example.wirebarley.core.model.Currency
import java.text.DecimalFormat

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
