package com.example.beardie.currencyholder.data

import com.example.beardie.currencyholder.data.local.converter.CurrencyConverter
import com.example.beardie.currencyholder.data.local.converter.DateConverter
import com.example.beardie.currencyholder.data.local.converter.TransactionTypeConverter
import com.example.beardie.currencyholder.data.model.Currency
import com.example.beardie.currencyholder.data.model.TransactionType
import org.junit.Assert
import org.junit.Test
import java.util.*

class ConvertersTest {
    private val currencyConverter = CurrencyConverter()

    @Test
    fun currencyRubleConverterTest() {
        Assert.assertEquals(Currency.Ruble.ordinal, currencyConverter.currencyToInt(Currency.Ruble))
        Assert.assertEquals(Currency.Ruble, currencyConverter.intToCurrency(Currency.Ruble.ordinal))
    }

    @Test
    fun currencyDollarConverterTest() {
        Assert.assertEquals(Currency.Dollar.ordinal, currencyConverter.currencyToInt(Currency.Dollar))
        Assert.assertEquals(Currency.Dollar, currencyConverter.intToCurrency(Currency.Dollar.ordinal))
    }


    private val dateConverter = DateConverter()

    @Test
    fun dateConverterTest() {
        val date = Date()
        Assert.assertEquals(date.time, dateConverter.dateToTimestamp(date))
        Assert.assertEquals(date, dateConverter.fromTimestamp(date.time))
    }


    private val transactionTypeConverter = TransactionTypeConverter()

    @Test
    fun transactionTypeConverter() {
        Assert.assertEquals(TransactionType.INCOME.ordinal, transactionTypeConverter.transactionTypeToInt(TransactionType.INCOME))
        Assert.assertEquals(TransactionType.OUTGO, transactionTypeConverter.intToTransactionType(TransactionType.OUTGO.ordinal))
    }
}