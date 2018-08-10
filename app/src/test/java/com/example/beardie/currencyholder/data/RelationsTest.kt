package com.example.beardie.currencyholder.data

import com.example.beardie.currencyholder.data.local.entity.Balance
import com.example.beardie.currencyholder.data.local.relation.BalanceWithTransactions
import org.junit.Assert.assertNull
import com.example.beardie.currencyholder.data.local.relation.TransactionWithCategory
import org.junit.Assert.assertTrue
import org.junit.Test


class RelationsTest {
    @Test
    fun test_default_transactionWithCategory() {
        val p = TransactionWithCategory()
        assertNull(p.transaction)
        assertTrue(p.categories.isEmpty())
    }

    @Test
    fun test_default_balanceWithTransactions() {
        val p = BalanceWithTransactions()
        assertNull(p.balance)
        assertTrue(p.transactions.isEmpty())
    }
}