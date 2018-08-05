package com.example.beardie.currencyholder.data.local.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.example.beardie.currencyholder.data.model.Currency

@Entity(tableName = "balance")
data class Balance(
        @PrimaryKey(autoGenerate = true) val id: Long,
        val name: String,
        var balance: Double,
        val currency: Currency
)