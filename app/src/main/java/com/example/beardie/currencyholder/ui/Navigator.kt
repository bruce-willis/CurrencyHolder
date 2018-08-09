package com.example.beardie.currencyholder.ui

import android.support.annotation.DimenRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import com.example.beardie.currencyholder.R

interface Navigator {

    fun initToolbar(@StringRes title: Int, @DimenRes elevation: Int = R.dimen.toolbar_elevation)
    fun navigateTo(fragmentInstance: () -> Fragment, addToBackStack : Boolean = false, transaction: String? = null)
    fun navigateBack()

}