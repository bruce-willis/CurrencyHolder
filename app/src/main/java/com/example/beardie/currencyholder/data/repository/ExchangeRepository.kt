package com.example.beardie.currencyholder.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.beardie.currencyholder.data.api.ExchangeApiService
import com.example.beardie.currencyholder.data.model.Currency
import com.example.beardie.currencyholder.data.model.ExchangeCurrency
import okhttp3.ResponseBody
import retrofit2.Callback
import javax.inject.Inject


class ExchangeRepository @Inject constructor() {

    fun getAll(): LiveData<List<ExchangeCurrency>> {
        val exchangeCurrency = MutableLiveData<List<ExchangeCurrency>>()
        //exchangeCurrency.value = SeedDatabase.exchange
        return exchangeCurrency
    }

    fun getExchangeRate(from: Currency, to: Currency, callback: Callback<ResponseBody>) {
        ExchangeApiService.create()
                .getExchangeRate(from.code + "_" + to.code, "ultra")
                .enqueue(callback)
    }
}