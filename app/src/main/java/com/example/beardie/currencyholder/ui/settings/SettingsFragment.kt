package com.example.beardie.currencyholder.ui.settings

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.beardie.currencyholder.R
import com.example.beardie.currencyholder.SharedConstants
import com.example.beardie.currencyholder.data.model.FinanceCurrency
import com.example.beardie.currencyholder.di.ViewModelFactory
import com.example.beardie.currencyholder.ui.finance.FinanceActivity
import com.example.beardie.currencyholder.viewmodel.SettingsViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.activity_finance.*
import kotlinx.android.synthetic.main.content_finance.*
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject

class SettingsFragment @Inject constructor() : DaggerFragment(), AdapterView.OnItemSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var shared : SharedPreferences

    private lateinit var settingViewModel : SettingsViewModel

    @SuppressLint("RestrictedApi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        settingViewModel = ViewModelProviders.of(this, viewModelFactory).get(SettingsViewModel::class.java)

        val baseActivity = activity as FinanceActivity
        baseActivity.toolbar.setTitle(R.string.settings_toolbar_title)
        baseActivity.toolbar.elevation = 4f
        baseActivity.toolbar.setWillNotDraw(false)
        if(baseActivity.dl_view.isDrawerOpen(Gravity.START))
            baseActivity.dl_view.closeDrawer(Gravity.START, true)
        baseActivity.dl_view.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        generateObservers()
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        s_currency_setting_list.onItemSelectedListener = this
    }

    private fun generateObservers() {
        val currencyNameList: Observer<List<FinanceCurrency>> = Observer { res ->
            if(res != null) {
                s_currency_setting_list.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, res.map { l -> l.shortTitle })
            }
        }

        settingViewModel.getCurrencyShortNameList().observe(this, currencyNameList)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        shared.edit().putInt(SharedConstants.DEFAULT_CURRENCY, p2).apply()
    }

}
