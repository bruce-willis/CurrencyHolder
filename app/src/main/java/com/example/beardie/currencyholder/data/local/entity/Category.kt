package com.example.beardie.currencyholder.data.local.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.beardie.currencyholder.data.model.TransactionType

/*
* Declaring the column info allows for the renaming of variables without implementing a
* database migration, as the column name would not change.
*/
@Entity(tableName = "category")
data class Category(
        @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Long,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "transactionType") val transactionType: TransactionType,
        @ColumnInfo(name = "color") val color: Int
)