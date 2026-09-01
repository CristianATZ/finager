package com.devtorres.finager

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var appNavigator: AppNavigator

    @Inject lateinit var installers: Set<@JvmSuppressWildcards EntryProviderInstaller>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        VersionUtils.isSdkIntAtLeast(Build.VERSION_CODES.Q) { window.isNavigationBarContrastEnforced = false }

        var keepSplashScreen = true
        val splashScreen = installSplashScreen()
        splashScreen.apply {
            setKeepOnScreenCondition { keepSplashScreen }
            setOnExitAnimationListener { splashScreenView ->
                splashScreenView.view
                    .animate()
                    .alpha(0f)
                    .setStartDelay(
                        (splashScreenView.iconAnimationStartMillis + splashScreenView.iconAnimationDurationMillis)
                            .coerceAtLeast(0L)
                    )
                    .setDuration(400L)
                    .withEndAction { splashScreenView.remove() }
                    .start()
            }
        }

        setContent {
            LaunchedEffect(true) {
                delay(1000)
                appNavigator.goTo(AppRoute.Onboarding)
                keepSplashScreen = false
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