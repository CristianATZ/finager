package com.devtorres.onboarding.state

import com.devtorres.domain.AppCurrency
import com.devtorres.domain.AppLanguage
import com.devtorres.domain.AppTheme
import com.devtorres.onboarding.R
import com.devtorres.ui.appearance.endonym
import com.devtorres.ui.appearance.title

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

    fun toPairList() : List<Pair<Int, Any?>> =
        listOf(
            R.string.onboarding_summary_username to this.username,
            R.string.onboarding_summary_currency to "${this.currency?.code} (${this.currency?.symbol})",
            R.string.onboarding_summary_language to this.language?.endonym(),
            R.string.onboarding_summary_theme to this.theme?.title(),
            R.string.onboarding_summary_biometrics to if(this.biometrics) com.devtorres.ui.R.string.common_enabled else com.devtorres.ui.R.string.common_disabled
        )
}