package com.example.beardie.currencyholder.domain

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.beardie.currencyholder.data.local.relation.BalanceWithTransactions
import com.example.beardie.currencyholder.data.repository.CategoryRepository
import com.example.beardie.currencyholder.data.repository.SharedPrefRepository
import com.example.beardie.currencyholder.data.repository.TransactionRepository
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import javax.inject.Inject

class SummaryInteractor @Inject constructor(
        private val spref: SharedPrefRepository,
        private val transactionRepository: TransactionRepository,
        private val categoryRepository: CategoryRepository) {

    fun getPieChartValues(bwt: BalanceWithTransactions): LiveData<PieDataSet> {
        val liveData = MutableLiveData<PieDataSet>()
        val entries = ArrayList<PieEntry>()
        val map: HashMap<String, Float?> = HashMap()
        bwt.transactions.forEach { transaction ->
            if (map.contains(transaction.category()?.name)) {
                if (map[transaction.category()?.name] == null) {
                    map[transaction.category()!!.name] = 0f
                }
                map[transaction.category()!!.name] = map[transaction.category()!!.name]?.plus(Math.abs(transaction.transaction!!.cost.toFloat()))
            } else {
                map[transaction.category()!!.name] = Math.abs(transaction.transaction!!.cost.toFloat())
            }
        }
        map.forEach { v ->
            entries.add(PieEntry(v.value ?: 0f, v.key))
        }

        liveData.value = PieDataSet(entries, "")
        return liveData
    }

    fun getShowLegend(): Boolean {
        return spref.getShowlegend()
    }
}