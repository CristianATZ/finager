package com.devtorres.finager

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.devtorres.common.VersionUtils
import com.devtorres.finager.presentation.AppContent
import com.devtorres.ui.theme.FinagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        VersionUtils.isSdkIntAtLeast(Build.VERSION_CODES.Q) { window.isNavigationBarContrastEnforced = false }

        installSplashScreen().apply {
            setOnExitAnimationListener { splashScreenView ->
                splashScreenView.view
                    .animate()
                    .alpha(0f)
                    .setDuration(400L)
                    .withEndAction { splashScreenView.remove() }
                    .start()
            }
        }

        setContent {
            FinagerTheme {
                AppContent()
            }
        }
    }
}