package com.example.beardie.currencyholder.data.model

import java.util.concurrent.TimeUnit

private val SEC_IN_DAY: Long = TimeUnit.DAYS.toSeconds(1)

enum class Period(
        val title: String,
        val repeatInDays: Long
) {
    None("Not repeatable", 0),
    Day("Every day", 1),
    Week("Every week", 7),
    // TODO: fix for month with 31/29 days
    Month("Every month", 30)
}