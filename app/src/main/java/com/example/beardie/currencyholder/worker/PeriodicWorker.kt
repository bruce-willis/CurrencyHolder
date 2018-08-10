package com.example.beardie.currencyholder.worker

import androidx.work.Worker
import com.example.beardie.currencyholder.data.local.db.CurrencyDatabase
import com.example.beardie.currencyholder.data.local.entity.Transaction
import java.util.*

// tags for input data
// https://developer.android.com/topic/libraries/architecture/workmanager#params
const val COST_TAG = "cost"
const val BALANCE_ID_TAG = "balance_id"
const val CATEGORY_ID_TAG = "category_id"

class PeriodicWorker : Worker() {

    override fun doWork(): Result {
        val transaction = Transaction(
                cost = inputData.getDouble(COST_TAG, 0.0),
                date = Date(),
                balanceId = inputData.getLong(BALANCE_ID_TAG, 0),
                categoryId = inputData.getLong(CATEGORY_ID_TAG, 0))

        CurrencyDatabase.getInstance(applicationContext).balanceTransactionDao().insertOperationAndUpdateAmount(transaction)
        return Result.SUCCESS
    }
}