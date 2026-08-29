package com.devtorres.finager

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.devtorres.common.VersionUtils
import com.devtorres.navigation.AppNavigator
import com.devtorres.navigation.AppRoute
import com.devtorres.navigation.EntryProviderInstaller
import com.devtorres.ui.theme.FinagerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var appNavigator: AppNavigator

    @Inject lateinit var installers: Set<@JvmSuppressWildcards EntryProviderInstaller>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        VersionUtils.isSdkIntAtLeast(Build.VERSION_CODES.Q) { window.isNavigationBarContrastEnforced = false }

        setContent {
            LaunchedEffect(true) {
                appNavigator.goTo(AppRoute.Onboarding)
            }

            LaunchedEffect(appNavigator.backStack.size) {
                Log.i("MyTag", appNavigator.backStack.size.toString())
            }

            FinagerTheme {
                if(appNavigator.backStack.isNotEmpty()) {
                    NavDisplay(
                        backStack = appNavigator.backStack,
                        entryDecorators = listOf(
                            rememberSaveableStateHolderNavEntryDecorator(),
                            rememberViewModelStoreNavEntryDecorator()
                        ),
                        entryProvider = entryProvider {
                            installers.forEach { it() }
                        }
                    )
                }
            }
        }
    }
}