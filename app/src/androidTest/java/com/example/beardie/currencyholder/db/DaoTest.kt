package com.example.beardie.currencyholder.db

import com.example.beardie.currencyholder.data.local.db.SeedValues
import com.example.beardie.currencyholder.util.LiveDataTestUtil.getValue
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert
import org.junit.Test

class DaoTest : DbTest() {

    @Test
    fun categoryDaoSingleTest() {
        val category = SeedValues.category.first()
        db.categoryDao().insert(category)

        val loaded = getValue(db.categoryDao().getAllCategories()).first()

        Assert.assertThat(loaded, notNullValue())
        Assert.assertThat(loaded.name, `is`(category.name))
    }

    @Test
    fun categoryDaoMultipleTest() {
        db.categoryDao().insertAll(*SeedValues.category)

        val loaded = getValue(db.categoryDao().getAllCategories())

        Assert.assertThat(loaded, notNullValue())
        Assert.assertArrayEquals(SeedValues.category, loaded.toTypedArray())
    }

    @Test
    fun balanceDaoTest() {
        db.balanceDao().insertAll(*SeedValues.balances)

        val loaded = getValue(db.balanceDao().getAllBalances())

        Assert.assertThat(loaded, notNullValue())
        Assert.assertArrayEquals(SeedValues.balances, loaded.toTypedArray())
    }

    @Test
    fun transactionDaoTest() {
        db.transactionDao().insertAll(*SeedValues.transactions)

        val loaded = getValue(db.transactionDao().getAllTransactions())

        Assert.assertThat(loaded, notNullValue())
        Assert.assertArrayEquals(SeedValues.transactions, loaded.toTypedArray())
    }

}