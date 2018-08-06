package com.example.beardie.currencyholder.data.local.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.example.beardie.currencyholder.data.model.TransactionType

@Entity(tableName = "category")
data class Category(
        @PrimaryKey(autoGenerate = true) val id: Long,
        val name: String,
        val transactionType: TransactionType,
        val color: Int
)