package com.devtorres.onboarding.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

internal sealed interface OnboardingRoute : NavKey {
    val stepIndex: Int
    val topBarVisible: Boolean
    val bottomBarVisible: Boolean
    val backButtonVisible: Boolean
}

@Serializable
internal data object IntroRoute : OnboardingRoute {
    override val stepIndex = -1
    override val topBarVisible = false
    override val bottomBarVisible = true
    override val backButtonVisible = false
}

@Serializable
internal data object UsernameRoute : OnboardingRoute {
    override val stepIndex = 0
    override val topBarVisible = true
    override val bottomBarVisible = true
    override val backButtonVisible = true
}

@Serializable
internal data object CurrencyRoute : OnboardingRoute {
    override val stepIndex = 1
    override val topBarVisible = true
    override val bottomBarVisible = true
    override val backButtonVisible = true
}

@Serializable
internal data object LanguageRoute : OnboardingRoute {
    override val stepIndex = 2
    override val topBarVisible = true
    override val bottomBarVisible = true
    override val backButtonVisible = true
}

@Serializable
internal data object ThemeRoute : OnboardingRoute {
    override val stepIndex = 3
    override val topBarVisible = true
    override val bottomBarVisible = true
    override val backButtonVisible = true
}

@Serializable
internal data object BiometricsRoute : OnboardingRoute {
    override val stepIndex = 4
    override val topBarVisible = true
    override val bottomBarVisible = true
    override val backButtonVisible = true
}

@Serializable
internal data object SummaryRoute : OnboardingRoute {
    override val stepIndex = 5
    override val topBarVisible = true
    override val bottomBarVisible = true
    override val backButtonVisible = true
}

internal const val ONBOARDING_STEP_COUNT = 6

internal fun OnboardingRoute?.next(): OnboardingRoute? = when (this) {
    IntroRoute -> UsernameRoute
    UsernameRoute -> CurrencyRoute
    CurrencyRoute -> LanguageRoute
    LanguageRoute -> ThemeRoute
    ThemeRoute -> BiometricsRoute
    BiometricsRoute -> SummaryRoute
    else -> null
}
