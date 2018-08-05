package com.example.beardie.currencyholder.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.beardie.currencyholder.data.local.db.SeedDatabase
import com.example.beardie.currencyholder.data.local.entity.Transaction
import javax.inject.Inject

class TransactionRepository @Inject constructor() {

    fun add(transaction: Transaction) {
        SeedDatabase.transactions.add(transaction)
    }

    fun getAll() : LiveData<List<Transaction>> {
        val transactions = MutableLiveData<List<Transaction>>()
        transactions.value = SeedDatabase.transactions
        return transactions
    }

    fun filterByBalanceId(id : String) : LiveData<List<Transaction>> {
        val transactions = MutableLiveData<List<Transaction>>()
        transactions.value = SeedDatabase.transactions.filter { t -> t.balance.id == id }
        return transactions
    }
}