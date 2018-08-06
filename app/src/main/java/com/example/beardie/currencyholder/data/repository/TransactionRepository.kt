package com.example.beardie.currencyholder.data.repository

import android.arch.lifecycle.LiveData
import com.example.beardie.currencyholder.data.local.dao.TransactionDao
import com.example.beardie.currencyholder.data.local.entity.Transaction
import javax.inject.Inject

class TransactionRepository @Inject constructor(private val transactionDao: TransactionDao) {

    fun add(transaction: Transaction) = transactionDao.insert(transaction)


    fun getAll(): LiveData<List<Transaction>> = transactionDao.getAllTransactions()

    fun getTransactionsForBalance(id: Long): LiveData<List<Transaction>> = transactionDao.getTransactionsForBalance(id)
}