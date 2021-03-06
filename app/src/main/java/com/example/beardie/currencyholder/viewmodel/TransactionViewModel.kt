package com.example.beardie.currencyholder.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.example.beardie.currencyholder.data.local.entity.Balance
import com.example.beardie.currencyholder.data.local.entity.Category
import com.example.beardie.currencyholder.data.model.Currency
import com.example.beardie.currencyholder.data.model.Period
import com.example.beardie.currencyholder.data.repository.BalanceRepository
import com.example.beardie.currencyholder.data.repository.CategoryRepository
import com.example.beardie.currencyholder.data.repository.CurrencyRepository
import com.example.beardie.currencyholder.domain.BalanceInteractor
import java.util.*
import javax.inject.Inject

class TransactionViewModel @Inject constructor(
        context: Application,
        private val balanceInteractor: BalanceInteractor,
        private val balanceRepository: BalanceRepository,
        private val currencyRepository: CurrencyRepository,
        private val categoryRepository: CategoryRepository
) : AndroidViewModel(context) {

    var filter = MutableLiveData<Int>()// prefRepository.getDefaultCurrency()
        set(value) {
            filter.value = value.value
        }

    val currency by lazy { currencyRepository.getAll() }

    val balances by lazy { balanceRepository.getAll() }

    val categories = Transformations.switchMap(filter) { id ->
        categoryRepository.filterByType(id)
    } ?: categoryRepository.getAll()

    fun addTransaction(amount: Double, balance: Balance, currency: Currency, date: Date, category: Category, period: Period = Period.None) {
        balanceInteractor.addTransaction(amount, balance, currency, date, category, period)
    }
}