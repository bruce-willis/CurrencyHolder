package com.example.beardie.currencyholder.data.model


data class ExchangeCurrency(
        val id: Long,
        val fromCurrency: FinanceCurrency,
        val toCurrency: FinanceCurrency,
        val coef: Double
)