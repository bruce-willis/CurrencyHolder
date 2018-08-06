package com.example.beardie.currencyholder.data.model


enum class Currency(
        val title: String,
        val symbol: String,
        val code: String
) {
    Ruble("Rubles", "\u20BD", "RUB"),
    Dollar("Dollars", "\$", "USD")
}