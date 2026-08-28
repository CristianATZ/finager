package com.devtorres.ui.appearance

import com.devtorres.domain.AppLanguage
import com.devtorres.ui.R

fun AppLanguage.exonym(): Int = when(this) {
    AppLanguage.ES -> R.string.language_es_exonym
    AppLanguage.EN -> R.string.language_en_exonym
    AppLanguage.FR -> R.string.language_fr_exonym
}