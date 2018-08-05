package com.example.beardie.currencyholder.data.model


data class ExchangeCurrency(
        val id: Long,
        val fromCurrency: Currency,
        val toCurrency: Currency,
        val coef: Double
)