package com.example.wirebarley.model

data class CountryInformation(
    val name: Country,
    val currency: Currency,
)

enum class Country(val kr: String){
    USA(kr = "미국"),
    KOR(kr = "한국"),
    JPN(kr = "일본"),
    PHL(kr = "필리핀")
}

enum class Currency{
    USD,
    KWR,
    JPY,
    PHP
}