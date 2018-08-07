package com.example.beardie.currencyholder.data.local.converter

import android.arch.persistence.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long): Date? {
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): Long? {
        return date.time
    }
}