package com.example.beardie.currencyholder

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.DrawerMatchers
import android.support.test.espresso.contrib.NavigationViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.view.Gravity
import com.example.beardie.currencyholder.ui.finance.FinanceActivity
import com.example.beardie.currencyholder.util.getToolbarNavigationContentDescription
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class FinanceActivityTest {
    @Rule @JvmField
    var activityTestRule = ActivityTestRule(FinanceActivity::class.java)

    @Test
    fun clickOnAndroidHomeIcon_OpensAndClosesNavigation() {
        // Check that drawer is closed at startup
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout)).check(ViewAssertions.matches(DrawerMatchers.isClosed(Gravity.START)))

        clickOnHomeIconToOpenNavigationDrawer()
        checkDrawerIsOpen()
    }

    @Test fun clickOnNavView_StartsFragments() {
        clickOnHomeIconToOpenNavigationDrawer()

        // Navigate to settings
        Espresso.onView(ViewMatchers.withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.action_settings))

        // Check that the Settings is visible
        Espresso.onView(ViewMatchers.withId(R.id.fragment_settings)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Navigate to about
        Espresso.onView(ViewMatchers.withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.action_about))

        // Check that the about is visible
        Espresso.onView(ViewMatchers.withId(R.id.about_fragment)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test fun pressDeviceBack_CloseDrawer_Then_PressBack_Close_App() {
        clickOnHomeIconToOpenNavigationDrawer()
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
        checkDrawerIsNotOpen()
        Assert.assertEquals(activityTestRule.activity.isFinishing, false)
        Assert.assertEquals(activityTestRule.activity.isDestroyed, false)
    }

    private fun clickOnHomeIconToOpenNavigationDrawer() {
        Espresso.onView(ViewMatchers.withContentDescription(getToolbarNavigationContentDescription(
                activityTestRule.activity, R.id.toolbar))).perform(ViewActions.click())
    }

    private fun checkDrawerIsOpen() {
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout)).check(ViewAssertions.matches(DrawerMatchers.isOpen(Gravity.START)))
    }

    private fun checkDrawerIsNotOpen() {
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout)).check(ViewAssertions.matches(DrawerMatchers.isClosed(Gravity.START)))
    }
}