package com.example.beardie.currencyholder.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.beardie.currencyholder.data.local.db.SeedDatabase
import com.example.beardie.currencyholder.data.enum.TypeCategoryEnum
import com.example.beardie.currencyholder.data.local.entity.Category
import javax.inject.Inject

class CategoryRepository @Inject constructor() {

    fun getAll() : LiveData<List<Category>> {
        val categories = MutableLiveData<List<Category>>()
        categories.value = SeedDatabase.category
        return categories
    }

    fun filterByType(value : Int) : LiveData<List<Category>> {
        val type = TypeCategoryEnum.findByNumber(value)
        val categories = MutableLiveData<List<Category>>()
        categories.value = SeedDatabase.category.filter { c -> c.type == type }
        return categories
    }

    fun add(category : Category) : Unit {
        SeedDatabase.category.add(category)
    }
}