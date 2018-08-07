package com.example.beardie.currencyholder.data.repository

import android.arch.lifecycle.LiveData
import com.example.beardie.currencyholder.data.local.dao.CategoryDao
import com.example.beardie.currencyholder.data.local.entity.Category
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val categoryDao: CategoryDao) {

    fun getAll() : LiveData<List<Category>> = categoryDao.getAllCategories()

    fun filterByType(typeId : Int) : LiveData<List<Category>> = categoryDao.getCategoriesByType(typeId)
}