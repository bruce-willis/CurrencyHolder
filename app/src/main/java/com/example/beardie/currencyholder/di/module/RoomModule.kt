package com.example.beardie.currencyholder.di.module

import android.app.Application
import com.example.beardie.currencyholder.data.local.db.CurrencyDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application) = CurrencyDatabase.getInstance(app)

    @Provides
    @Singleton
    fun provideBalanceDao(db: CurrencyDatabase) = db.balanceDao()

    @Provides
    @Singleton
    fun provideCategoryDao(db: CurrencyDatabase) = db.categoryDao()

    @Provides
    @Singleton
    fun provideTransactionDao(db: CurrencyDatabase) = db.transactionDao()

    @Provides
    @Singleton
    fun provideBalanceTransactionDao(db: CurrencyDatabase) = db.balanceTransactionDao()
}