package com.example.beardie.currencyholder.data.local.converter

import android.arch.persistence.room.TypeConverter
import com.example.beardie.currencyholder.data.model.TransactionType
import com.example.beardie.currencyholder.utils.toEnum
import com.example.beardie.currencyholder.utils.toInt

class TransactionTypeConverter {
    @TypeConverter
    fun transactionTypeToInt(value: TransactionType) = value.toInt()

    @TypeConverter
    fun intToTransactionType(value: Int) = value.toEnum<TransactionType>()
}