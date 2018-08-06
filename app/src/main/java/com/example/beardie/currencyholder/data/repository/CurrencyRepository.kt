package com.example.beardie.currencyholder.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.beardie.currencyholder.data.local.db.SeedDatabase
import com.example.beardie.currencyholder.data.model.Currency
import javax.inject.Inject

class CurrencyRepository @Inject constructor() {

    fun getAll() : LiveData<List<Currency>> {
        val currencyList = MutableLiveData<List<Currency>>()
        currencyList.value = enumValues<Currency>().toList()
        return currencyList
    }
}