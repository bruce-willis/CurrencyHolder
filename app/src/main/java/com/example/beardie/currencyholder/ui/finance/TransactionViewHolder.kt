package com.example.beardie.currencyholder.ui.finance

import android.support.v7.widget.RecyclerView
import android.view.ContextMenu
import android.view.View
import com.example.beardie.currencyholder.R
import kotlinx.android.synthetic.main.transaction_list_item.view.*

class TransactionViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        menu?.apply {
            add(adapterPosition, v?.id!!, 0, R.string.edit_title)
            add(adapterPosition, v.id, 1, R.string.delete_title)
        }
    }

    val amount = view.tv_amount!!
    val date = view.tv_date!!
    val group = view.tv_group_name!!

    init {
        view.setOnCreateContextMenuListener(this)
    }
}