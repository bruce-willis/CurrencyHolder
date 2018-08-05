package com.example.beardie.currencyholder.data.model


data class FinanceCurrency(
        val id: Long,
        val symbol: String,
        val title: String,
        val shortTitle: String,
        val imageUri: String
)