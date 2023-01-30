package com.example.wirebarley.model

data class UserData (
    val exchangeRateStatus: ExchangeRateStatus = ExchangeRateStatus.Empty
){
    companion object{
        val Empty = UserData()
    }
}

data class ExchangeRateStatus(
    val selectedFromCountryInformation: CountryInformation = CountryInformation(name = Country.USA, currency = Currency.USD),
    val selectedToCountryInformation: CountryInformation = CountryInformation(name = Country.KOR, currency = Currency.KWR),
    val remittance: Double = 0.0
) {
    companion object{
        val Empty = ExchangeRateStatus()
    }
}