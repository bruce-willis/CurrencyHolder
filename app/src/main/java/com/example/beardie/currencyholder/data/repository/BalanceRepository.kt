package com.example.beardie.currencyholder.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.beardie.currencyholder.data.local.db.SeedDatabase
import com.example.beardie.currencyholder.data.local.entity.Balance
import javax.inject.Inject

class BalanceRepository @Inject constructor() {

    fun getAll() : LiveData<List<Balance>> {
        val balances = MutableLiveData<List<Balance>>()
        balances.value = SeedDatabase.balances
        return balances
    }

    fun findById(id : String): LiveData<Balance> {
        val balance = MutableLiveData<Balance>()
        balance.value = SeedDatabase.balances.find { b -> b.id == id }
        return balance
    }

    fun setBalance(balance: Balance, amount : Double) {
    }
}