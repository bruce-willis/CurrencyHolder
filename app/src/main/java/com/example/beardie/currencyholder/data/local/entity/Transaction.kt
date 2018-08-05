package com.example.beardie.currencyholder.data.local.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.example.beardie.currencyholder.data.model.FinanceCurrency
import java.util.*

@Entity(tableName = "transaction")
data class Transaction(
        @PrimaryKey(autoGenerate = true) val id: Long,
        val count: Double,
        @Ignore val balance: Balance,
        @Ignore val currency: FinanceCurrency,
        @Ignore val date : Date,
        @Ignore val category: Category
)