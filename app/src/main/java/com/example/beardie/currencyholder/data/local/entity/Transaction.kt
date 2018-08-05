package com.example.beardie.currencyholder.data.local.entity

import android.arch.persistence.room.*
import com.example.beardie.currencyholder.data.local.converter.DateConverter
import com.example.beardie.currencyholder.data.model.Currency
import java.util.*

@Entity(tableName = "transaction")
data class Transaction(
        @PrimaryKey(autoGenerate = true) val id: Long,
        val cost: Double,
        val date : Date,
        @ForeignKey(entity = Balance::class, parentColumns = ["id"], childColumns = ["balanceId"])
        val balanceId: Long,
        @ForeignKey(entity = Category::class, parentColumns = ["id"], childColumns = ["categoryId"])
        val categoryId: Long
)