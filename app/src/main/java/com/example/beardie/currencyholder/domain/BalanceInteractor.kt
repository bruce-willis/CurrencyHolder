package com.example.beardie.currencyholder.domain

import androidx.work.*
import com.example.beardie.currencyholder.data.local.entity.Balance
import com.example.beardie.currencyholder.data.local.entity.Category
import com.example.beardie.currencyholder.data.local.entity.Transaction
import com.example.beardie.currencyholder.data.model.Currency
import com.example.beardie.currencyholder.data.model.Period
import com.example.beardie.currencyholder.data.repository.BalanceRepository
import com.example.beardie.currencyholder.data.repository.ExchangeRepository
import com.example.beardie.currencyholder.data.repository.TransactionRepository
import com.example.beardie.currencyholder.worker.BALANCE_ID_TAG
import com.example.beardie.currencyholder.worker.CATEGORY_ID_TAG
import com.example.beardie.currencyholder.worker.COST_TAG
import com.example.beardie.currencyholder.worker.PeriodicWorker
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
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
                        Timber.e(t, "Unable to get current rate")
                    }

                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        response.body()?.string()?.let {
                            val data = JSONObject(it)
                            val rate = data.getDouble("${currency.code}_${balance.currency.code}")
                            val transaction = Transaction(balanceId = balance.id, categoryId = category.id, cost = difference * category.transactionType.effect() * rate, date = date)
                            if (period == Period.None) {
                                balanceRepository.insertOperationAndUpdateAmount(transaction)
                            } else {
                                addPeriodic(transaction, period)
                            }
                        }
                    }
                })
            }
        } else {
            val transaction = Transaction(balanceId = balance.id, categoryId = category.id, cost = difference * category.transactionType.effect(), date = date)
            if (period == Period.None) {
                balanceRepository.insertOperationAndUpdateAmount(transaction)
            } else {
                addPeriodic(transaction, period)
            }
        }
    }


    // create period transaction
    // see https://developer.android.com/topic/libraries/architecture/workmanager#recurring
    fun addPeriodic(transaction: Transaction, period: Period) {
        val data = mapOf(COST_TAG to transaction.cost, BALANCE_ID_TAG to transaction.balanceId, CATEGORY_ID_TAG to transaction.categoryId)
                .toWorkData()
        val request = PeriodicWorkRequestBuilder<PeriodicWorker>(
                period.repeatInDays, TimeUnit.DAYS, // repeated interval
                PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS, TimeUnit.MILLISECONDS) // minimum allowed flex period of every interval period
                .setInputData(data)
                .build()
        val tag = System.currentTimeMillis().toString()
        WorkManager.getInstance().enqueueUniquePeriodicWork(tag, ExistingPeriodicWorkPolicy.REPLACE, request)
    }

    fun getSumTransaction(balanceId: Long): Double {
        return balanceRepository.getBalanceWithTransactions(balanceId).value?.transactions?.sumByDouble { it.transaction!!.cost * it.category()!!.transactionType.effect() }
                ?: 0.0
    }
}