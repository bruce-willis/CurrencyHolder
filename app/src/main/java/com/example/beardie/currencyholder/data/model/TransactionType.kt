package com.example.beardie.currencyholder.data.model

import com.example.beardie.currencyholder.R

enum class TransactionType constructor(
        val stringRes: Int) {
    INCOME(R.string.income_title) {
        override fun effect() = 1.0
    },
    OUTGO(R.string.outcome_title) {
        override fun effect() = -1.0
    };

    abstract fun effect(): Double
}