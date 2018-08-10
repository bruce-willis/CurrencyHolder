package com.example.beardie.currencyholder.data.repository

import android.arch.lifecycle.LiveData
import com.example.beardie.currencyholder.data.local.dao.BalanceTransactionDao
import com.example.beardie.currencyholder.data.local.dao.TransactionDao
import com.example.beardie.currencyholder.data.local.entity.Transaction
import javax.inject.Inject

class TransactionRepository @Inject constructor(private val transactionDao: TransactionDao, private val balanceTransactionDao: BalanceTransactionDao) {


    fun add(transaction: Transaction) = transactionDao.insert(transaction)

    @android.arch.persistence.room.Transaction
    fun deleteTransaction(transaction: Transaction) {
        balanceTransactionDao.updateBalance(-transaction.cost, transaction.balanceId)
        transactionDao.delete(transaction)
    }


    fun getAll(): LiveData<List<Transaction>> = transactionDao.getAllTransactions()

    fun getTransactionsForBalance(id: Long): LiveData<List<Transaction>> = transactionDao.getTransactionsForBalance(id)
}