package com.example.beardie.currencyholder.data.local.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.example.beardie.currencyholder.data.model.Period
import java.util.*

@Entity(tableName = "transaction")
data class Transaction(
        val cost: Double,
        val date: Date,
        @ForeignKey(entity = Balance::class, parentColumns = ["id"], childColumns = ["balanceId"], deferred = true)
        val balanceId: Long,
        @ForeignKey(entity = Category::class, parentColumns = ["id"], childColumns = ["categoryId"], deferred = true)
        val categoryId: Long,
        val period: Period
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}