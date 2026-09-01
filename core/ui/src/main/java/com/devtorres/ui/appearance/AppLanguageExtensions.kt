package com.devtorres.ui.appearance

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.devtorres.domain.AppLanguage
import com.devtorres.ui.R

fun AppLanguage.exonym(): Int = when(this) {
    AppLanguage.SYSTEM -> R.string.language_system_exonym
    AppLanguage.ES -> R.string.language_es_exonym
    AppLanguage.EN -> R.string.language_en_exonym
    AppLanguage.FR -> R.string.language_fr_exonym
}

fun AppLanguage.endonym(): Any = when (this) {
    AppLanguage.ES -> "Español"
    AppLanguage.EN -> "English"
    AppLanguage.FR -> "Français"
    AppLanguage.SYSTEM -> R.string.language_system_endonym
}

@Composable
fun AppLanguage.endonymText(): String {
    val value = endonym()
    return value as? String ?: stringResource(value as Int)
}