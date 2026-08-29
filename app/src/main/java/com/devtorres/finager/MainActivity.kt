package com.devtorres.finager

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.devtorres.common.VersionUtils
import com.devtorres.onboarding.navigation.Onboarding
import com.devtorres.onboarding.navigation.onboardingEntryBuilder
import com.devtorres.ui.theme.FinagerTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@Serializable
data object Home : NavKey


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        VersionUtils.isSdkIntAtLeast(Build.VERSION_CODES.Q) { window.isNavigationBarContrastEnforced = false }

        setContent {
            val backStack = rememberNavBackStack(Onboarding)

            FinagerTheme {
                NavDisplay(
                    backStack = backStack,
                    entryProvider = entryProvider {
                        entry<Home> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Home")
                            }
                        }
                        onboardingEntryBuilder(
                            onNavigateToHome = {
                                backStack.clear()
                                backStack.add(Home)
                            }
                        )
                    }
                )
            }
        }
    }
}