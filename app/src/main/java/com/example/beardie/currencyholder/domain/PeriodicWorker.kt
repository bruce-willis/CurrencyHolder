package com.example.beardie.currencyholder.domain

import androidx.work.Worker
import com.example.beardie.currencyholder.data.local.db.CurrencyDatabase
import com.example.beardie.currencyholder.data.local.entity.Transaction

class PeriodicWorker : Worker() {
    companion object {
        internal val TAG = "PeriodicWorker"
    }

    override fun doWork(): Result {
        CurrencyDatabase.getInstance(applicationContext).transactionDao().getRepeatedTransactions().forEach { t ->
            val currentDate = System.currentTimeMillis()
            while (t.period.duration + t.date.time < currentDate) {
                updateBalance(t)
            }
        }
        return Result.SUCCESS
    }

    private fun updateBalance(transaction: Transaction) {
        CurrencyDatabase.getInstance(applicationContext).balanceTransactionDao().insertOperationAndUpdateAmount(transaction, transaction.balanceId)
    }
}