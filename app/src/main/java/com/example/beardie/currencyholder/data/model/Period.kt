package com.example.beardie.currencyholder.data.model

import java.util.concurrent.TimeUnit

private val SEC_IN_DAY: Long = TimeUnit.DAYS.toSeconds(1)

enum class Period(
        val title: String,
        val duration: Long
) {
    None("Not repeatable", 0),
    Day("Every day", SEC_IN_DAY),
    Week("Every week", SEC_IN_DAY * 7),
    // TODO: fix for month with 31/29 days
    Month("Every month", SEC_IN_DAY * 30)
}