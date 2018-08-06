package com.example.beardie.currencyholder.domain

import com.example.beardie.currencyholder.data.local.entity.Balance
import com.example.beardie.currencyholder.data.local.entity.Category
import com.example.beardie.currencyholder.data.local.entity.Transaction
import com.example.beardie.currencyholder.data.model.Currency
import com.example.beardie.currencyholder.data.model.Period
import com.example.beardie.currencyholder.data.repository.BalanceRepository
import com.example.beardie.currencyholder.data.repository.ExchangeRepository
import com.example.beardie.currencyholder.data.repository.TransactionRepository
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.util.*
import java.util.concurrent.Executors
import javax.inject.Inject

class BalanceInteractor @Inject constructor(
        private val balanceRepository: BalanceRepository,
        private val transactionRepository: TransactionRepository,
        private val exchangeRepository: ExchangeRepository
) {

    fun addTransaction(difference: Double, balance: Balance, currency: Currency, date: Date, category: Category, period: Period = Period.None) {
        if (balance.currency != currency) {
            Executors.newSingleThreadScheduledExecutor().execute {
                exchangeRepository.getExchangeRate(currency, balance.currency, object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                        Timber.e(t)
                    }

                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        response.body()?.string()?.let {
                            val data = JSONObject(it)
                            val rate = data.getDouble("${currency.code}_${balance.currency.code}")
                            balanceRepository.insertOperationAndUpdateAmount(Transaction(balanceId = balance.id, categoryId = category.id, cost = difference * category.transactionType.effect() * rate, date = date, period = period), balance.id)
                        }
                    }
                })
            }
        } else {
            balanceRepository.insertOperationAndUpdateAmount(Transaction(balanceId = balance.id, categoryId = category.id, cost = difference * category.transactionType.effect(), date = date, period = period), balance.id)
        }
    }

    fun getSumTransaction(balanceId: Long): Double {
        return balanceRepository.getBalanceWithTransactions(balanceId).value?.transactions?.sumByDouble { it.transaction!!.cost * it.category()!!.transactionType.effect() }
                ?: 0.0
    }
}