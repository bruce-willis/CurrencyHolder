package com.example.beardie.currencyholder.db

import com.example.beardie.currencyholder.data.local.db.SeedDatabase
import com.example.beardie.currencyholder.util.LiveDataTestUtil.getValue
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert
import org.junit.Test

class DaoTest : DbTest() {

    @Test
    fun categoryDaoSingleTest() {
        val category = SeedDatabase.category.first()
        db.categoryDao().insert(category)

        val loaded = getValue(db.categoryDao().getAllCategories()).first()

        Assert.assertThat(loaded, notNullValue())
        Assert.assertThat(loaded.name, `is`(category.name))
    }

    @Test
    fun categoryDaoMultipleTest() {
        db.categoryDao().insertAll(*SeedDatabase.category)

        val loaded = getValue(db.categoryDao().getAllCategories())

        Assert.assertThat(loaded, notNullValue())
        Assert.assertArrayEquals(SeedDatabase.category, loaded.toTypedArray())
    }

    @Test
    fun balanceDaoTest() {
        db.balanceDao().insertAll(*SeedDatabase.balances)

        val loaded = getValue(db.balanceDao().getAllBalances())

        Assert.assertThat(loaded, notNullValue())
        Assert.assertArrayEquals(SeedDatabase.balances, loaded.toTypedArray())
    }

    @Test
    fun transactionDaoTest() {
        db.transactionDao().insertAll(*SeedDatabase.transactions)

        val loaded = getValue(db.transactionDao().getAllTransactions())

        Assert.assertThat(loaded, notNullValue())
        Assert.assertArrayEquals(SeedDatabase.transactions, loaded.toTypedArray())
    }

}