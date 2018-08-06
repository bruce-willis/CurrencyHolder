package com.example.beardie.currencyholder.data.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.beardie.currencyholder.data.local.entity.Transaction

@Dao
interface TransactionDao : BaseDao<Transaction> {
    @Query("SELECT * FROM `transaction`")
    fun getAllTransactions(): LiveData<List<Transaction>>

    @Query("SELECT * FROM `transaction` WHERE balanceId = :id")
    fun getTransactionsForBalance(id: Long) : LiveData<List<Transaction>>
}