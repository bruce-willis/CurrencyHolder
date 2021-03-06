package com.example.beardie.currencyholder.ui.finance

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.DividerItemDecoration.VERTICAL
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.beardie.currencyholder.R
import com.example.beardie.currencyholder.data.local.db.SeedValues.PieChartColors
import com.example.beardie.currencyholder.data.local.entity.Balance
import com.example.beardie.currencyholder.data.local.relation.BalanceWithTransactions
import com.example.beardie.currencyholder.di.ViewModelFactory
import com.example.beardie.currencyholder.ui.Navigator
import com.example.beardie.currencyholder.viewmodel.FinanceViewModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_finance.*
import javax.inject.Inject


class FinanceFragment : DaggerFragment(),
        AdapterView.OnItemSelectedListener,
        AppBarLayout.OnOffsetChangedListener,
        View.OnCreateContextMenuListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var financeViewModel: FinanceViewModel

    private lateinit var transactionAdapter: TransactionAdapter

    private val changeBalances: Observer<List<Balance>> = Observer { balances ->
        if (balances != null && balances.isNotEmpty()) {
            s_balance_names.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, balances.map { it.name })
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val index = item.groupId
        val transaction = transactionAdapter.transactions[index].transaction!!


            return when (item.order) {
            0 -> {
                (activity as? Navigator)?.navigateTo({ AddTransactionFragment.newInstance(transaction) }, true)
                true
            }
            1 -> {
                financeViewModel.deleteTransaction(transaction)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }


    private val changeBalance: Observer<BalanceWithTransactions> = Observer { res ->
        if (res?.balance != null) {

            if (res.balance!!.balance < 0) {
                tv_balance.setTextColor(ContextCompat.getColor(context!!, R.color.colorOutGo))
            } else {
                tv_balance.setTextColor(ContextCompat.getColor(context!!, R.color.colorIncome))
            }
            tv_balance.text = String.format(getString(R.string.format_balance_text,
                    res.balance?.balance,
                    res.balance?.currency?.symbol))

            if (res.transactions.isNotEmpty()) {
                appbar.setExpanded(true, true)
                rv_transaction_list.adapter = transactionAdapter
                transactionAdapter.transactions = res.transactions
                transactionAdapter.notifyDataSetChanged()
                registerForContextMenu(rv_transaction_list)
                no_transactions_view.visibility = View.GONE
                rv_transaction_list.visibility = View.VISIBLE
            } else {
                no_transactions_view.visibility = View.VISIBLE
                rv_transaction_list.visibility = View.GONE
                appbar.setExpanded(false, true)
            }
        }
    }

    private val dataSet: Observer<PieDataSet> = Observer { res ->
        if (res != null) {
            val dataSet = financeViewModel.summary?.value

            dataSet?.apply {
                sliceSpace = 2f
                selectionShift = 8f
                colors = PieChartColors
                setDrawValues(false)
            }

            val data = PieData(dataSet).apply {
                setValueFormatter(PercentFormatter())
                setValueTextSize(12f)
                setValueTextColor(Color.BLACK)
            }

            chart.data = data
            chart.isHighlightPerTapEnabled = false
            chart.highlightValues(null)
            chart.invalidate()
        }
    }

    companion object {
        fun newInstance(): FinanceFragment {
            return FinanceFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_finance, container, false)
        subscribeUI()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as Navigator).initToolbar(R.string.finance_toolbar_title)

        appbar.addOnOffsetChangedListener(this)


        s_balance_names.onItemSelectedListener = this

        fab_add_transaction.setOnClickListener {
            if (activity != null) {
                (activity as Navigator).navigateTo({ AddTransactionFragment.newInstance() }, true)
            }
        }

        transactionAdapter = TransactionAdapter(context!!)
        rv_transaction_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, true)
        rv_transaction_list.addItemDecoration(DividerItemDecoration(context, VERTICAL))
        rv_transaction_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && fab_add_transaction.visibility == View.VISIBLE) {
                    fab_add_transaction.hide()
                } else if (dy < 0 && fab_add_transaction.visibility != View.VISIBLE) {
                    fab_add_transaction.show()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    fab_add_transaction.show()
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
        initChart()
    }

    private fun subscribeUI() {
        financeViewModel = ViewModelProviders.of(this, viewModelFactory).get(FinanceViewModel::class.java)
        financeViewModel.balances.observe(viewLifecycleOwner, changeBalances)
        financeViewModel.balanceWithTransactions.observe(viewLifecycleOwner, changeBalance)
        financeViewModel.summary.observe(viewLifecycleOwner, dataSet)
    }

    private fun initChart() {
        chart.legend.isEnabled = financeViewModel.getShowLegend()
        chart.legend.orientation = Legend.LegendOrientation.HORIZONTAL
        chart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        chart.legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        chart.description.isEnabled = false

        chart.isDrawHoleEnabled = true
        chart.holeRadius = 30f
        chart.transparentCircleRadius = 40f
        chart.setHoleColor(Color.WHITE)
        chart.setTransparentCircleColor(Color.WHITE)
        chart.setTransparentCircleAlpha(90)

        chart.setDrawEntryLabels(false)

        chart.setUsePercentValues(true)
        chart.setExtraOffsets(8f, 0f, 8f, 0f)
        chart.dragDecelerationFrictionCoef = 0.95f
        chart.rotationAngle = 0f
        chart.isRotationEnabled = false
        chart.isHighlightPerTapEnabled = false
        chart.animateY(1000, Easing.EasingOption.EaseInOutQuad)

        chart.setEntryLabelColor(Color.BLACK)
        chart.setEntryLabelTextSize(14f)

        //chart.setOnChartValueSelectedListener(this)
    }


    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        if (verticalOffset == 0) {
            (activity as AppCompatActivity).supportActionBar?.elevation = 0f
            rl_balance_view.elevation = 0f
        } else if (verticalOffset == -appBarLayout.totalScrollRange) {
            (activity as AppCompatActivity).supportActionBar?.elevation = 0f
            rl_balance_view.elevation = 0f
        } else {
            (activity as AppCompatActivity).supportActionBar?.elevation = resources.getDimension(R.dimen.default_app_elevation)
            rl_balance_view.elevation = resources.getDimension(R.dimen.default_app_elevation)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        if (pos >= 0) {
            financeViewModel.currentBalance.value = financeViewModel.balancesList[pos].id
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}
