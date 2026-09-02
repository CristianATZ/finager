package com.devtorres.onboarding.state

import com.devtorres.domain.AppCurrency
import com.devtorres.domain.AppLanguage
import com.devtorres.domain.AppTheme

sealed interface OnboardingEvent {
    data object OnNextClicked : OnboardingEvent
    data object OnBackClicked : OnboardingEvent
    data object OnFinish : OnboardingEvent
    data object OnNavigateToHome : OnboardingEvent
    data class OnUsernameChanged(val value: String) : OnboardingEvent
    data class OnCurrencySelected(val value: AppCurrency) : OnboardingEvent
    data class OnLanguageSelected(val value: AppLanguage) : OnboardingEvent
    data class OnThemeSelected(val value: AppTheme) : OnboardingEvent
    data class OnBiometricsEnabled(val value: Boolean) : OnboardingEvent
}