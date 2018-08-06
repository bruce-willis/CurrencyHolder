package com.example.beardie.currencyholder.data.local.db

import android.graphics.Color
import com.example.beardie.currencyholder.data.local.entity.Balance
import com.example.beardie.currencyholder.data.local.entity.Category
import com.example.beardie.currencyholder.data.local.entity.Transaction
import com.example.beardie.currencyholder.data.model.Currency
import com.example.beardie.currencyholder.data.model.Period
import com.example.beardie.currencyholder.data.model.TransactionType
import java.util.*

object SeedDatabase {
    var category = arrayOf(
            Category(1, "Зарплата", TransactionType.INCOME, Color.GREEN),
            Category(2, "Стипендия", TransactionType.INCOME, Color.GREEN),
            Category(3, "Коммунальные платежи", TransactionType.OUTGO, Color.RED),
            Category(4, "Одежда", TransactionType.OUTGO, Color.RED),
            Category(5, "Питание", TransactionType.OUTGO, Color.RED),
            Category(6, "Развлечения", TransactionType.OUTGO, Color.RED),
            Category(7, "Другое", TransactionType.OUTGO, Color.RED)
    )

    var balances = arrayOf(
            Balance(1, "Наличные", 100.0, Currency.Dollar),
            Balance(2, "Карта", 3000.0, Currency.Ruble)

    )

    var transactions = arrayOf(
            Transaction(1000.0, Date(), 1, 1, Period.None),
            Transaction(-500.0, Date(), 1, 3, Period.None),
            Transaction(-400.0, Date(), 1, 4, Period.None),
            Transaction(-50.0, Date(), 1, 3, Period.None),
            Transaction(-20.0, Date(), 1, 3, Period.None),
            Transaction(-10.0, Date(), 1, 2, Period.None),
            Transaction(80.0, Date(), 1, 2, Period.None),

            Transaction(700.0, Date(), 2, 1, Period.None),
            Transaction(-100.0, Date(), 2, 6, Period.None),
            Transaction(-200.0, Date(), 2, 6, Period.None),
            Transaction(-300.0, Date(), 2, 5, Period.None),
            Transaction(-20.0, Date(), 2, 6, Period.None),
            Transaction(-10.0, Date(), 2, 1, Period.None),
            Transaction(2990.0, Date(), 2, 2, Period.None)
    )
}