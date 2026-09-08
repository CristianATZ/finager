package com.devtorres.navigation.decorator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntryDecorator
import androidx.navigation3.runtime.NavKey
import com.devtorres.navigation.AppNavigator
import com.devtorres.navigation.LocalAppNavigator

@Composable
fun rememberAppNavigator(
    backStack: NavBackStack<NavKey>
): AppNavigator {
    return remember(backStack) {
        AppNavigator(backStack)
    }
}

@Composable
fun <T : Any> rememberAppNavigatorNavEntryDecorator(
    appNavigator: AppNavigator
): AppNavigatorNavEntryDecorator<T> {
    return remember(appNavigator) {
        AppNavigatorNavEntryDecorator(appNavigator)
    }
}

class AppNavigatorNavEntryDecorator<T : Any> (
    appNavigator: AppNavigator
) : NavEntryDecorator<T>(
    onPop = {},
    decorate = { entry ->
        CompositionLocalProvider(LocalAppNavigator provides appNavigator) {
            entry.Content()
        }
    }
)