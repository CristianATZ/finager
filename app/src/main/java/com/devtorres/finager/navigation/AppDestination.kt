package com.devtorres.finager.navigation

import com.devtorres.navigation.key.Route
import kotlinx.serialization.Serializable

sealed interface AppDestination : Route {

    @Serializable
    data object CustomSplash : AppDestination

    @Serializable
    data object OnboardingFlow : AppDestination

    @Serializable
    data object MainFlow : AppDestination
}