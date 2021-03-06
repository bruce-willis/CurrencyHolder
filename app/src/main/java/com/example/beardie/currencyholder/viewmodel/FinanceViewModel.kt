package com.example.beardie.currencyholder.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.example.beardie.currencyholder.data.local.entity.Transaction
import com.example.beardie.currencyholder.data.repository.BalanceRepository
import com.example.beardie.currencyholder.data.repository.TransactionRepository
import com.example.beardie.currencyholder.domain.SummaryInteractor
import javax.inject.Inject

class FinanceViewModel @Inject constructor(
        context: Application,
        private val transactionRepository: TransactionRepository,
        private val balanceRepository: BalanceRepository,
        private val summaryInteractor: SummaryInteractor
) : AndroidViewModel(context) {

    var currentBalance = MutableLiveData<Long>()
        set(value) {
            currentBalance.value = value.value
        }

    val balanceWithTransactions = Transformations.switchMap(currentBalance) { id -> balanceRepository.getBalanceWithTransactions(id) }

    val balances = balanceRepository.getAll()

    val balancesList by lazy { balanceRepository.getAllList() }

    val summary = Transformations.switchMap(balanceWithTransactions) { btw -> summaryInteractor.getPieChartValues(btw) }

    fun getShowLegend(): Boolean {
        return summaryInteractor.getShowLegend()
    }

    fun deleteTransaction(transaction: Transaction) = transactionRepository.deleteTransaction(transaction)
}