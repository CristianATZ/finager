package com.devtorres.navigation

import androidx.compose.runtime.staticCompositionLocalOf

val LocalAppNavigator = staticCompositionLocalOf<AppNavigator> {
    error("No AppNavigator provided. Wrap the content with CompositionLocalProvider.")
}