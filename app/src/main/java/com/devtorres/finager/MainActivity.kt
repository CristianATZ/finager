package com.devtorres.finager

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.devtorres.onboarding.OnBoardingScreen
import com.devtorres.ui.theme.FinagerTheme

inline fun isVersionAbove(q: Int, q1: () -> Unit) = if(Build.VERSION.SDK_INT >= q) q1() else {}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        isVersionAbove(Build.VERSION_CODES.Q) { window.isNavigationBarContrastEnforced = false }

        setContent {
            FinagerTheme {
                OnBoardingScreen(
                    onCloseApp = {
                        this@MainActivity.finish()
                    }
                )
            }
        }
    }
}