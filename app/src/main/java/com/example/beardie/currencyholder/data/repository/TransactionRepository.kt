package com.example.beardie.currencyholder.data.repository

import android.arch.lifecycle.LiveData
import com.example.beardie.currencyholder.data.local.dao.BalanceTransactionDao
import com.example.beardie.currencyholder.data.local.dao.TransactionDao
import com.example.beardie.currencyholder.data.local.entity.Transaction
import com.example.beardie.currencyholder.utils.tryAll
import javax.inject.Inject

class TransactionRepository @Inject constructor(private val transactionDao: TransactionDao, private val balanceTransactionDao: BalanceTransactionDao) {


    fun add(transaction: Transaction) = transactionDao.insert(transaction)

    @android.arch.persistence.room.Transaction
    fun deleteTransaction(transaction: Transaction) {
        tryAll("delete transaction") {
            balanceTransactionDao.updateBalance(-transaction.cost, transaction.balanceId)
            transactionDao.delete(transaction)
        }
    }

    @android.arch.persistence.room.Transaction
    fun updateTransaction(oldTransaction: Transaction, newTransaction: Transaction) {
        tryAll("updating transaction") {
            // both transactions belong to the same balance -> just update balance and transaction
            if (oldTransaction.balanceId == newTransaction.balanceId) {
                balanceTransactionDao.updateBalance(newTransaction.cost - oldTransaction.cost, newTransaction.balanceId)
            } else {
                // we need to subtract old transaction cost from old balance
                balanceTransactionDao.updateBalance(-oldTransaction.cost, oldTransaction.balanceId)
                //  and then add new cost to new balance
                balanceTransactionDao.updateBalance(newTransaction.cost, newTransaction.balanceId)
            }
            // and finally update transaction
            newTransaction.id = oldTransaction.id
            transactionDao.update(newTransaction)
        }
    }

    fun getAll(): LiveData<List<Transaction>> = transactionDao.getAllTransactions()

    fun getTransactionsForBalance(id: Long): LiveData<List<Transaction>> = transactionDao.getTransactionsForBalance(id)
}