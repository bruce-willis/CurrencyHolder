package com.example.beardie.currencyholder.data.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.beardie.currencyholder.data.local.entity.Balance

@Dao
interface BalanceDao : BaseDao<Balance> {
    @Query("SELECT * FROM balance")
    fun getAllBalances(): LiveData<List<Balance>>
}