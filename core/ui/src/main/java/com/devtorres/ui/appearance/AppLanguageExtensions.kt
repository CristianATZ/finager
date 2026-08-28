package com.devtorres.ui.appearance

import com.devtorres.domain.AppLanguage

fun AppLanguage.exonym(): String = when(this) {
    AppLanguage.ES -> "Español"
    AppLanguage.EN -> "Inglés"
    AppLanguage.FR -> "Francés"
}