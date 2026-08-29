package com.devtorres.onboarding

import com.devtorres.domain.AppCurrency
import com.devtorres.domain.AppLanguage
import com.devtorres.domain.AppTheme
import com.devtorres.ui.appearance.title
import com.devtorres.ui.R as ui
import com.devtorres.onboarding.R as onboarding

data class OnboardingState(
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

    fun getSummaryAsList() : List<Pair<Int, Any?>> =
        listOf(
            onboarding.string.onboarding_summary_username to this.username,
            onboarding.string.onboarding_summary_currency to "${this.currency?.code} (${this.currency?.symbol})",
            onboarding.string.onboarding_summary_language to this.language?.endonym.toString(),
            onboarding.string.onboarding_summary_theme to this.theme?.title(),
            onboarding.string.onboarding_summary_biometrics to if(this.biometrics) ui.string.common_enabled else ui.string.common_disabled
        )
}