package com.devtorres.navigation

import androidx.compose.runtime.snapshots.Snapshot
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.devtorres.navigation.key.Route

class AppNavigator(
    private val mutableBackStack: NavBackStack<NavKey>
) {
    val backstack: List<NavKey> = mutableBackStack

    val currentRoute: NavKey?
        get() = backstack.lastOrNull()

    fun navigateTo(route: Route) {
        mutableBackStack.add(route)
    }

    fun newRootScreen(route: Route) {
        Snapshot.withMutableSnapshot {
            mutableBackStack.clear()
            mutableBackStack.add(route)
        }
    }

    fun goBack() {
        if(mutableBackStack.size > 1)
            mutableBackStack.removeLastOrNull()
    }
}