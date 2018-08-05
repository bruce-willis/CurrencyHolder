package com.example.beardie.currencyholder.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.beardie.currencyholder.data.local.db.SeedDatabase
import com.example.beardie.currencyholder.data.model.FinanceCurrency
import javax.inject.Inject

class CurrencyRepository @Inject constructor() {

    fun getAll() : LiveData<List<FinanceCurrency>> {
        val currencyList = MutableLiveData<List<FinanceCurrency>>()
        currencyList.value = SeedDatabase.currency
        return currencyList
    }
}