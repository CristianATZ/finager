package com.devtorres.onboarding

import com.devtorres.domain.AppCurrency
import com.devtorres.domain.AppLanguage
import com.devtorres.domain.AppTheme
import com.devtorres.ui.appearance.title

internal data class OnboardingState(
    val username: String = "",
    val currency: AppCurrency? = null,
    val language: AppLanguage? = null,
    val theme: AppTheme? = null,
    val biometrics: Boolean = false
) {
    fun isUsernameValid() : Boolean = when {
        this.username.isBlank() -> false
        this.username.length < 2 -> false
        else -> true
    }

    fun isCurrencyValid() : Boolean = this.currency != null

    fun isLanguageValid() : Boolean = this.language != null

    fun isThemeValid() : Boolean = this.theme != null

    fun getSummaryAsList() : Map<String, String> =
        mapOf(
            "Usuario" to this.username,
            "Moneda" to "${this.currency?.code} (${this.currency?.symbol})",
            "Idioma" to this.language?.endonym.toString(),
            "Tema" to this.theme?.title().toString(),
            "Biometricos" to if(this.biometrics) "Activado" else "Desactivado"
        )
}