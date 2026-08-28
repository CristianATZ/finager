package com.devtorres.domain

import java.util.Locale

enum class AppLanguage(
    val code: String,
    val endonym: String,
    val locale: Locale
) {
    ES("ES", "Español", Locale("es", "ES")),
    EN("EN", "English", Locale.ENGLISH),
    FR("FR", "Français", Locale.FRENCH);
}