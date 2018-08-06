package com.example.beardie.currencyholder.data.repository

import android.arch.lifecycle.LiveData
import com.example.beardie.currencyholder.data.local.dao.BalanceDao
import com.example.beardie.currencyholder.data.local.dao.BalanceTransactionDao
import com.example.beardie.currencyholder.data.local.entity.Balance
import com.example.beardie.currencyholder.data.local.entity.Transaction
import javax.inject.Inject

class BalanceRepository @Inject constructor(private val balanceDao: BalanceDao, private val balanceTransactionDao: BalanceTransactionDao) {

    fun getAll(): LiveData<List<Balance>> = balanceDao.getAllBalances()

    fun getAllList() = balanceDao.getAllBalancesList()

    fun findById(id: Long): LiveData<Balance> = balanceDao.findById(id)

    fun getBalanceWithTransactions(id: Long) = balanceTransactionDao.getBalanceWithTransactions(id)

    fun insertOperationAndUpdateAmount(transaction: Transaction, balanceId: Long) =
            balanceTransactionDao.insertOperationAndUpdateAmount(transaction, balanceId)
}