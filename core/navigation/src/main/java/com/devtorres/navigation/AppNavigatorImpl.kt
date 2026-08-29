package com.devtorres.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject


@ActivityRetainedScoped
class AppNavigatorImpl @Inject constructor() : AppNavigator {
    override val backStack: SnapshotStateList<NavKey> = mutableStateListOf()

    override fun goTo(key: NavKey, inclusive: Boolean) {
        if(inclusive) backStack.clear()
        backStack.add(key)
    }

    override fun goBack(key: NavKey?) {
        if(key != null) {
            val index = backStack.indexOf(key)
            require(index != -1) { "NavKey does not exists" }
            backStack.subList(index + 1, backStack.size).clear()
        } else {
            backStack.removeLastOrNull()
        }
    }
}