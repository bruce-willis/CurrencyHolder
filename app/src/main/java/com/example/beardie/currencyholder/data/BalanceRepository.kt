package com.example.beardie.currencyholder.data

import com.example.beardie.currencyholder.data.model.Balance
import com.example.beardie.currencyholder.data.model.FinanceCurrency
import javax.inject.Inject

class BalanceRepository @Inject constructor() {

    fun getBalances() : List<Balance> {
        return HardcodeValues.balances
    }

    fun getBalance(financeCurrency: FinanceCurrency?): Balance? {
        return HardcodeValues.balances.find { b -> b.currency == financeCurrency }
    }
}