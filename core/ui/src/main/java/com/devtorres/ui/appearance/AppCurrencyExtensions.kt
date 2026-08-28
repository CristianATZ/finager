package com.devtorres.ui.appearance

import com.devtorres.domain.AppCurrency
import com.devtorres.ui.R

fun AppCurrency.displayName(): Int = when(this) {
    AppCurrency.MXN -> R.string.currency_mxn_name
    AppCurrency.USD -> R.string.currency_usd_name
    AppCurrency.EUR -> R.string.currency_eur_name
}

fun AppCurrency.countryName(): Int = when(this) {
    AppCurrency.MXN -> R.string.currency_mxn_country
    AppCurrency.USD -> R.string.currency_usd_country
    AppCurrency.EUR -> R.string.currency_eur_country
}