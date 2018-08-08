package com.example.beardie.currencyholder.data.local.relation

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation
import com.example.beardie.currencyholder.data.local.entity.Balance
import com.example.beardie.currencyholder.data.local.entity.Transaction


/**
 * This class captures the relationship between a [Balance] and a user's [Transaction], which is
 * used by Room to fetch the related entities.
 */
class BalanceWithTransactions {

    @Embedded
    var balance: Balance? = null

    @Relation(parentColumn = "id", entityColumn = "balanceId", entity = Transaction::class)
    var transactions: List<TransactionWithCategory> = arrayListOf()
}