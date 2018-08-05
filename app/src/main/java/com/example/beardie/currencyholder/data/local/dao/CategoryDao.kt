package com.example.beardie.currencyholder.data.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.beardie.currencyholder.data.local.entity.Category

@Dao
interface CategoryDao : BaseDao<Category> {
    @Query("SELECT * FROM category")
    fun getAllCategories(): LiveData<List<Category>>
}