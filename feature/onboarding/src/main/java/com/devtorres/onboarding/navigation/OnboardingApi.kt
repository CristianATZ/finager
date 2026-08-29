package com.devtorres.onboarding.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.devtorres.onboarding.OnBoardingScreen
import kotlinx.serialization.Serializable

@Serializable
data object Onboarding : NavKey

fun EntryProviderScope<NavKey>.onboardingEntryBuilder(
    onNavigateToHome: () -> Unit
) {
    entry<Onboarding> {
        OnBoardingScreen(
            onNavigateToHome = onNavigateToHome
        )
    }
}