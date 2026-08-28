package com.devtorres.ui.appearance

import com.devtorres.domain.AppCurrency

fun AppCurrency.displayName(): String = when(this) {
    AppCurrency.MXN -> "Peso mexicano"
    AppCurrency.USD -> "Dólar"
    AppCurrency.EUR -> "Euro"
}

fun AppCurrency.countryName(): String = when(this) {
    AppCurrency.MXN -> "México"
    AppCurrency.USD -> "Estados Unidos"
    AppCurrency.EUR -> "Europa"
}

fun AppCurrency.description(): String =
    "${this.displayName()} · ${this.countryName()}"