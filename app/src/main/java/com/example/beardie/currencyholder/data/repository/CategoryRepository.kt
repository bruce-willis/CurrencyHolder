package com.example.beardie.currencyholder.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.beardie.currencyholder.data.local.dao.CategoryDao
import com.example.beardie.currencyholder.data.local.db.SeedDatabase
import com.example.beardie.currencyholder.data.model.TransactionType
import com.example.beardie.currencyholder.data.local.entity.Category
import com.example.beardie.currencyholder.utils.toInt
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val categoryDao: CategoryDao) {

    fun getAll() : LiveData<List<Category>> = categoryDao.getAllCategories()

    fun filterByType(typeId : Int) : LiveData<List<Category>> = categoryDao.getCategoriesByType(typeId)
}