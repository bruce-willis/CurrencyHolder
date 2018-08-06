package com.example.beardie.currencyholder.domain

import com.example.beardie.currencyholder.data.repository.BalanceRepository
import com.example.beardie.currencyholder.data.repository.ExchangeRepository
import com.example.beardie.currencyholder.data.local.db.SeedDatabase
import com.example.beardie.currencyholder.data.repository.TransactionRepository
import com.example.beardie.currencyholder.data.model.Currency
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BalanceInteractorTest {

//    val balanceInteractor : BalanceInteractor = BalanceInteractor(
//            BalanceRepository(),
//            TransactionRepository(),
//            ExchangeRepository())

    @Test
    fun assertEqualSumTransaction() {

//        val currency = listOf(
//                Currency("1", "₽", "Rubles", "RUB", ""),
//                Currency("2", "$", "Dollars", "USD", "")
//        )
//
//        val c0 = SeedDatabase.transactions.sumByDouble { t ->
//            if (t.currency == currency[0])
//                t.cost
//            else
//                t.cost * SeedDatabase.exchange.find { exp -> exp.fromCurrency == t.currency }?.coef!!
//        }
//
//        val c1 = SeedDatabase.transactions.sumByDouble { t ->
//            if (t.currency == currency[1])
//                t.cost
//            else
//                t.cost * SeedDatabase.exchange.find { exp -> exp.fromCurrency == t.currency }?.coef!!
//        }
//
//        assert(balanceInteractor.getSumTransaction(currency[0])  == c0)
//        assert(balanceInteractor.getSumTransaction(currency[1])  == c1)
    }


}