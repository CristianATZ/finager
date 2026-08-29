package com.devtorres.onboarding.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.devtorres.navigation.AppNavigator
import com.devtorres.navigation.AppRoute
import com.devtorres.onboarding.OnBoardingScreen

internal fun EntryProviderScope<NavKey>.onboardingEntryBuilder(appNavigator: AppNavigator) {
    entry<AppRoute.Onboarding> {
        OnBoardingScreen(
            onNavigateToHome = { appNavigator.goTo(AppRoute.Home, true)  }
        )
    }
}