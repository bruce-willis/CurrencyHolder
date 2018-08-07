package com.example.beardie.currencyholder.workers

import androidx.work.Worker
import com.example.beardie.currencyholder.data.local.db.CurrencyDatabase
import com.example.beardie.currencyholder.data.local.db.SeedValues
import timber.log.Timber

// seed database with populated data
class SeedDatabaseWorker : Worker() {
    override fun doWork(): Result {
        Timber.d("Start populating database")
        return try {
            val database = CurrencyDatabase.getInstance(applicationContext)
            database.runInTransaction {
                database.run {
                    // spread operator
                    // https://kotlinlang.org/docs/reference/functions.html#variable-number-of-arguments-varargs
                    balanceDao().insertAll(*SeedValues.balances)
                    categoryDao().insertAll(*SeedValues.category)
                    transactionDao().insertAll(*SeedValues.transactions)
                }
            }
            Timber.i("Successfully polate database")
            Worker.Result.SUCCESS
        } catch (ex: Exception) {
            Timber.e(ex, "Unable to populate database")
            Worker.Result.FAILURE
        }
    }
}