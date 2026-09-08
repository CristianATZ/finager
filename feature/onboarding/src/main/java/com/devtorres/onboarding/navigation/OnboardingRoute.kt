package com.devtorres.onboarding.navigation

import com.devtorres.navigation.key.Route
import kotlinx.serialization.Serializable

internal interface TopBarVisible
internal interface BackButtonVisible

@Serializable
sealed class OnboardingDestination(
    val stepIndex: Int
) : Route {

    @Serializable
    data object IntroRoute : OnboardingDestination(stepIndex = -1)

    @Serializable
    data object UsernameRoute : OnboardingDestination(stepIndex = 1), TopBarVisible, BackButtonVisible

    @Serializable
    data object CurrencyRoute : OnboardingDestination(stepIndex = 2), TopBarVisible, BackButtonVisible

    @Serializable
    data object LanguageRoute : OnboardingDestination(stepIndex = 3), TopBarVisible, BackButtonVisible

    @Serializable
    data object ThemeRoute : OnboardingDestination(stepIndex = 4), TopBarVisible, BackButtonVisible

    @Serializable
    data object BiometricsRoute : OnboardingDestination(stepIndex = 5), TopBarVisible, BackButtonVisible

    @Serializable
    data object SummaryRoute : OnboardingDestination(stepIndex = 6), TopBarVisible, BackButtonVisible

    fun createRoute(): OnboardingDestination? = when(this) {
        IntroRoute -> UsernameRoute
        UsernameRoute -> CurrencyRoute
        CurrencyRoute -> LanguageRoute
        LanguageRoute -> ThemeRoute
        ThemeRoute -> BiometricsRoute
        BiometricsRoute -> SummaryRoute
        else -> null
    }

    companion object {
        private val steps: List<OnboardingDestination> = listOf(
            UsernameRoute, CurrencyRoute, LanguageRoute, ThemeRoute, BiometricsRoute, SummaryRoute
        )

        fun totalSteps(): Int = steps.size
    }
}