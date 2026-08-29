package com.devtorres.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

typealias EntryProviderInstaller = EntryProviderScope<NavKey>.() -> Unit

@Serializable
sealed interface AppRoute : NavKey {

    @Serializable
    data object Onboarding : AppRoute

    @Serializable
    data object Home : AppRoute
}