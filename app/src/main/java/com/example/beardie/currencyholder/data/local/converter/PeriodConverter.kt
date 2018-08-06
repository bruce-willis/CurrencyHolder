package com.example.beardie.currencyholder.data.local.converter

import android.arch.persistence.room.TypeConverter
import com.example.beardie.currencyholder.data.model.Currency
import com.example.beardie.currencyholder.data.model.Period
import com.example.beardie.currencyholder.utils.toEnum
import com.example.beardie.currencyholder.utils.toInt
import java.util.*

class PeriodConverter {
    @TypeConverter
    fun periodToInt(value: Period) = value.toInt()

    @TypeConverter
    fun intToPeriod(value: Int) = value.toEnum<Period>()
}