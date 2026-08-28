package com.devtorres.domain

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

enum class AppCurrency(
    val code: String,
    val symbol: String,
    val locale: Locale
) {
    MXN("MXN", "$", Locale("es", "MX")),
    USD("USD", "$", Locale.US),
    EUR("EUR", "€", Locale.GERMANY);

    fun formatCurrency(amount: Double): String {
        val format = NumberFormat.getCurrencyInstance(locale)
        format.currency = Currency.getInstance(code)

        return format.format(amount)
    }
}