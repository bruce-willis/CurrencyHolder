package com.example.beardie.currencyholder.data.local.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.beardie.currencyholder.data.model.Currency

/*
* Declaring the column info allows for the renaming of variables without implementing a
* database migration, as the column name would not change.
*/
@Entity(tableName = "balance")
data class Balance(
        @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Long,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "balance") var balance: Double,
        @ColumnInfo(name = "currency") val currency: Currency
)