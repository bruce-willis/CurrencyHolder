package com.example.beardie.currencyholder.ui.finance

import android.os.Bundle
import android.support.annotation.DimenRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import android.view.View
import com.example.beardie.currencyholder.R
import com.example.beardie.currencyholder.ui.Navigator
import com.example.beardie.currencyholder.ui.about.AboutFragment
import com.example.beardie.currencyholder.ui.settings.SettingsFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_finance.*
import kotlinx.android.synthetic.main.content_finance.*

class FinanceActivity : DaggerAppCompatActivity(),
        Navigator {


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finance)
        setSupportActionBar(toolbar)

        nav_view.setNavigationItemSelectedListener { onNavigationItemSelected(it) }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(if (supportFragmentManager.backStackEntryCount == 0)
                R.drawable.ic_menu_24dp
                else
                R.drawable.ic_arrow_back_24dp)
        }

        if (savedInstanceState == null) {
            nav_view.setCheckedItem(R.id.action_dashboard)
            navigateTo({ FinanceFragment.newInstance() }, false, null)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId != android.R.id.home) {
            return super.onOptionsItemSelected(item)
        }
        if (supportFragmentManager.backStackEntryCount == 0) {
            drawer_layout.openDrawer(GravityCompat.START, true)
        } else {
            navigateBack()
        }
        return true
    }


    override fun initToolbar(@StringRes title: Int, @DimenRes elevation: Int) {
        if (title == R.string.finance_toolbar_title) {
            supportActionBar?.setDisplayShowTitleEnabled(false)
            main_title.visibility = View.VISIBLE
        } else {
            main_title.visibility = View.GONE
            supportActionBar?.setDisplayShowTitleEnabled(true)
            supportActionBar?.setTitle(title)
        }
        toolbar.elevation = resources.getDimension(elevation)
    }

    override fun navigateTo(fragmentInstance: () -> Fragment, addToBackStack: Boolean, transaction: String?) {
        val builder = supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.fl_finance_frame, fragmentInstance())
        if (addToBackStack) {
            builder.addToBackStack(transaction)
            supportActionBar?.apply {
                setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp)
            }
        }
        builder.commit()
    }

    override fun navigateBack() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_24dp)
        }
        supportFragmentManager.popBackStack()
    }

    private fun onNavigationItemSelected(item: MenuItem): Boolean {
        item.isChecked = true
        when (item.itemId) {
            R.id.action_dashboard -> {
                navigateTo({ FinanceFragment.newInstance() }, false, null)
            }
            R.id.action_settings -> {
                navigateTo({ SettingsFragment.newInstance() }, false, null)
            }
            R.id.action_about -> {
                navigateTo({ AboutFragment.newInstance() }, false, null)
            }
        }
        drawer_layout.closeDrawers()
        return true
    }

    override fun onBackPressed() {
        when {
            drawer_layout != null && drawer_layout.isDrawerOpen(GravityCompat.START) -> drawer_layout.closeDrawer(GravityCompat.START, true)
            supportFragmentManager.backStackEntryCount > 0 -> navigateBack()
            else -> super.onBackPressed()
        }
    }

}
