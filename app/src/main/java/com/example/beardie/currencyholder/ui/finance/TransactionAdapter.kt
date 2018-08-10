package com.example.beardie.currencyholder.ui.finance

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.beardie.currencyholder.R
import com.example.beardie.currencyholder.data.local.relation.TransactionWithCategory
import java.text.SimpleDateFormat

class TransactionAdapter(
        private val context: Context
) : RecyclerView.Adapter<TransactionViewHolder>() {

    var transactions: List<TransactionWithCategory> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TransactionViewHolder {
        return TransactionViewHolder(LayoutInflater.from(context).inflate(R.layout.transaction_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: TransactionViewHolder, pos: Int) {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")

        val transaction = transactions[pos].transaction
        transaction?.let {
            if (transaction.cost >= 0) {
                holder.amount.setTextColor(ContextCompat.getColor(context, R.color.colorIncome))
                holder.amount.text = "+${String.format("%.2f", transaction.cost)}"
            } else {
                holder.amount.text = String.format("%.2f", transaction.cost)
                holder.amount.setTextColor(ContextCompat.getColor(context, R.color.colorOutGo))
            }
            holder.date.text = dateFormat.format(transaction.date)
        }

        val category = transactions[pos].category()
        category?.let {
            holder.group.text = it.name
        }
    }

}