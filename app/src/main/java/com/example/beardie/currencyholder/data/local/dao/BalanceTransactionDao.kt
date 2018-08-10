package com.example.beardie.currencyholder.data.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.beardie.currencyholder.data.local.entity.Balance
import com.example.beardie.currencyholder.data.local.entity.Category
import com.example.beardie.currencyholder.data.local.entity.Transaction
import com.example.beardie.currencyholder.data.local.relation.BalanceWithTransactions

@Dao
interface BalanceTransactionDao {
    /**
     * This query will tell Room to query both the [Balance] and [Transaction] and [Category] tables and handle
     * the object mapping.
     */
    @android.arch.persistence.room.Transaction
    @Query("SELECT * FROM balance WHERE id = :id")
    fun getBalanceWithTransactions(id: Long): LiveData<BalanceWithTransactions>

    // TODO: maybe use update?
    @Query("""UPDATE balance
            SET balance = balance + :difference
            WHERE id = :balanceId """)
    fun updateBalance(difference: Double, balanceId: Long)

    @Insert
    fun insertTransaction(transaction: Transaction)

    @android.arch.persistence.room.Transaction
    fun insertOperationAndUpdateAmount(transaction: Transaction) {
        insertTransaction(transaction)
        updateBalance(transaction.cost, transaction.balanceId)
    }
}