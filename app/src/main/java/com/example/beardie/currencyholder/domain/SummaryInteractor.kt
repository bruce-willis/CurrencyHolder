package com.example.beardie.currencyholder.domain

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
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

    fun getPieChartValues(balanceId: Long): LiveData<PieDataSet> {
        val liveData = MutableLiveData<PieDataSet>()
        val entries = ArrayList<PieEntry>()
        val color = ArrayList<Int>()
        var sum = 0f

        val transactions = transactionRepository.getTransactionsForBalance(balanceId).value

        val categories = categoryRepository.getAll().value

        categories?.forEach { c ->
            transactions?.filter { t -> t.balanceId == c.id }?.forEach { sum += Math.abs(it.cost.toFloat()) }
            if (sum > 0)
                entries.add(PieEntry(sum, c.name))
            sum = 0f
        }

//        categoryRepository.getAll().value?.filter { c ->
//            if (spref.getOnlyOutcomes()) {
//                c.transactionType == TransactionType.OUTGO
//            } else {
//                true
//            }}!!.forEach { category -> transactions.value?.filter { el ->
//                (el.balance == balance) and (el.categories == categories) }?.forEach { t ->
//                sum += Math.abs(t.cost.toFloat()) }
//            if (sum > 0)
//                entries.add(PieEntry(sum, category.name))
//            sum = 0f
//        }

        liveData.value = PieDataSet(entries, "")
        liveData.value!!.colors = color
        return liveData
    }

    fun getShowLegend(): Boolean {
        return spref.getShowlegend()
    }
}