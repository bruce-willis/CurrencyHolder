package com.example.beardie.currencyholder.data.local.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.example.beardie.currencyholder.data.model.Period
import java.util.*

/*
* Declaring the column info allows for the renaming of variables without implementing a
* database migration, as the column name would not change.
*/
@Entity(tableName = "transaction")
data class Transaction(
        @ColumnInfo(name = "cost") val cost: Double,
        @ColumnInfo(name = "date") val date: Date,
        @ForeignKey(entity = Balance::class, parentColumns = ["id"], childColumns = ["balanceId"], deferred = true)
        @ColumnInfo(name = "balanceId") val balanceId: Long,
        @ForeignKey(entity = Category::class, parentColumns = ["id"], childColumns = ["categoryId"], deferred = true)
        @ColumnInfo(name = "categoryId") val categoryId: Long,
        @ColumnInfo(name = "period") val period: Period
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}