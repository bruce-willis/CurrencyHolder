package com.example.beardie.currencyholder.utils

import timber.log.Timber

inline fun tryAll(message: String, action: () -> Unit) {
    tryAll(message, message, message, action)
}

inline fun tryAll(startMessage: String, successMessage : String, errorMessage: String, action: () -> Unit) {
    try {
        Timber.d("Starting $startMessage")
        action()
        Timber.i("Successfully $successMessage")
    } catch (t: Throwable) {
        Timber.e(t, "Error at $errorMessage")
    }
}