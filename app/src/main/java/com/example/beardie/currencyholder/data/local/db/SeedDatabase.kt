package com.example.beardie.currencyholder.data.local.db

import android.graphics.Color
import com.example.beardie.currencyholder.data.model.TransactionType
import com.example.beardie.currencyholder.data.local.entity.Balance
import com.example.beardie.currencyholder.data.model.Currency
import com.example.beardie.currencyholder.data.local.entity.Transaction
import com.example.beardie.currencyholder.data.local.entity.Category
import java.util.Date

object SeedDatabase {

//    val currency = listOf(
//            FinanceCurrency(1, "₽", "Rubles", "RUB", ""),
//            FinanceCurrency(2, "$", "Dollars", "USD", "")
//    )

    var category = arrayOf(
            Category(1, "Зарплата", TransactionType.INCOME, Color.GREEN),
            Category(2, "Стипендия", TransactionType.INCOME, Color.GREEN),
            Category(3, "Коммунальные платежи", TransactionType.OUTGO, Color.RED),
            Category(4, "Одежда", TransactionType.OUTGO, Color.RED),
            Category(5, "Питание", TransactionType.OUTGO, Color.RED),
            Category(6, "Развлечения", TransactionType.OUTGO, Color.RED),
            Category(7, "Другое", TransactionType.OUTGO, Color.RED)
    )

//    val exchange = listOf(
//            ExchangeCurrency(1, FinanceCurrency.Ruble, FinanceCurrency.Dollar, 0.016),
//            ExchangeCurrency(2, FinanceCurrency.Dollar, FinanceCurrency.Ruble, 63.49)
//    )

    var balances = arrayOf(
            Balance(1, "Наличные", -5000.0, Currency.Dollar),
            Balance(2, "Карта", 100.0, Currency.Ruble)

    )

    var transactions = arrayOf(
            Transaction(1, 1000.0,Date(),1, 0),
            //Transaction(2,  50000.0, Date(), 0, 2),
            Transaction(3, 99.0,Date(),1, 0),
            Transaction(4, 100.0,Date(),1, 0),
            Transaction(5, -90.0,Date(),1, 3),
            Transaction(6, -99.0,Date(),1, 4),
            Transaction(7, -100.0,Date(),1, 4),
            Transaction(8, -90.0,Date(),1, 5),
            Transaction(9, 99.0,Date(),1, 0),
            Transaction(10, -133.0,Date(),1, 5)
    )
}