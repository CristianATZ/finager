package com.devtorres.navigation

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey

interface AppNavigator {
    val backStack: SnapshotStateList<NavKey>
    fun goTo(key: NavKey, inclusive: Boolean = false)
    fun goBack(key: NavKey? = null)
}