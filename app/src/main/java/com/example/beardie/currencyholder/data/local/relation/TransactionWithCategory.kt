package com.example.beardie.currencyholder.data.local.relation

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.Relation
import com.example.beardie.currencyholder.data.local.entity.Category
import com.example.beardie.currencyholder.data.local.entity.Transaction

class TransactionWithCategory {

    @Embedded
    var transaction: Transaction? = null

    @Relation(parentColumn = "categoryId", entityColumn = "id")
    var categories: Set<Category> = emptySet()

    @Ignore
    fun category() = this.categories.singleOrNull()
}