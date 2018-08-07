package com.example.beardie.currencyholder.data.local.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import android.os.Build
import com.example.beardie.currencyholder.BuildConfig
import com.example.beardie.currencyholder.data.local.converter.CurrencyConverter
import com.example.beardie.currencyholder.data.local.converter.DateConverter
import com.example.beardie.currencyholder.data.local.converter.PeriodConverter
import com.example.beardie.currencyholder.data.local.converter.TransactionTypeConverter
import com.example.beardie.currencyholder.data.local.dao.BalanceDao
import com.example.beardie.currencyholder.data.local.dao.BalanceTransactionDao
import com.example.beardie.currencyholder.data.local.dao.CategoryDao
import com.example.beardie.currencyholder.data.local.dao.TransactionDao
import com.example.beardie.currencyholder.data.local.entity.Balance
import com.example.beardie.currencyholder.data.local.entity.Category
import com.example.beardie.currencyholder.data.local.entity.Transaction
import java.util.concurrent.Executors

private const val DATABASE_NAME = "currency-db"

@Database(entities =
[Balance::class,
    Category::class,
    Transaction::class], version = 1,
        exportSchema = false)
@TypeConverters(value = [CurrencyConverter::class, TransactionTypeConverter::class, DateConverter::class, PeriodConverter::class])
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun balanceDao(): BalanceDao
    abstract fun categoryDao(): CategoryDao
    abstract fun transactionDao(): TransactionDao
    abstract fun balanceTransactionDao(): BalanceTransactionDao

    // how to build db
    // https://github.com/googlesamples/android-sunflower/blob/master/app/src/main/java/com/google/samples/apps/sunflower/data/AppDatabase.kt#L39
    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: CurrencyDatabase? = null


        fun getInstance(context: Context): CurrencyDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): CurrencyDatabase {
            return Room.databaseBuilder(context, CurrencyDatabase::class.java, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            if (BuildConfig.DEBUG)
                            // TODO: move to workManager
                            Executors.newSingleThreadScheduledExecutor().execute {
                                insertData(getInstance(context))
                            }
                        }
                    })
                    .build()
        }

        private fun insertData(database: CurrencyDatabase) {
            database.runInTransaction {
                // maybe better with apply/with/...?
                database.run {
                    // spread operator
                    // https://kotlinlang.org/docs/reference/functions.html#variable-number-of-arguments-varargs
                    balanceDao().insertAll(*SeedDatabase.balances)
                    categoryDao().insertAll(*SeedDatabase.category)
                    transactionDao().insertAll(*SeedDatabase.transactions)
                }
            }
        }
    }
}