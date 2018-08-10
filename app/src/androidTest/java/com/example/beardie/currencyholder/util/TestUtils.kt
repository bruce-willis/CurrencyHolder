package com.example.beardie.currencyholder.util

import android.app.Activity
import android.support.v7.widget.Toolbar

/**
 * Returns the content description for the navigation button view in the toolbar.
 */
fun getToolbarNavigationContentDescription(activity: Activity, toolbarId: Int) =
        activity.findViewById<Toolbar>(toolbarId).navigationContentDescription as String