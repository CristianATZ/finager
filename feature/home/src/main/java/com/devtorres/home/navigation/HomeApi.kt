package com.devtorres.home.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.devtorres.home.HomeScreen
import com.devtorres.navigation.AppNavigator
import com.devtorres.navigation.AppRoute

internal fun EntryProviderScope<NavKey>.homeEntryBuilder(appNavigator: AppNavigator) {
    entry<AppRoute.Home> {
        HomeScreen()
    }
}