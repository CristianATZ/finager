package com.devtorres.domain

import java.util.Locale

enum class AppLanguage(
    val code: String
) {
    SYSTEM("S"),
    ES("ES"),
    EN("EN"),
    FR("FR");

    companion object {
        fun AppLanguage.resolveLocale(): Locale = when (this) {
            ES -> Locale("es", "ES")
            EN -> Locale.ENGLISH
            FR -> Locale.FRENCH
            else -> Locale.getDefault()
        }
    }
}