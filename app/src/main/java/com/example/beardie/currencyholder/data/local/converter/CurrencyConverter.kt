package com.example.beardie.currencyholder.data.local.converter

import android.arch.persistence.room.TypeConverter
import com.example.beardie.currencyholder.data.model.Currency
import com.example.beardie.currencyholder.utils.toEnum
import com.example.beardie.currencyholder.utils.toInt

class CurrencyConverter {
    @TypeConverter
    fun currencyToInt(value: Currency) = value.toInt()

    @TypeConverter
    fun intToCurrency(value: Int) = value.toEnum<Currency>()
}