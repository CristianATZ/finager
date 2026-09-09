package com.devtorres.ui

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

object UiCommon {
    fun Float.isAnimationCompleted(): Boolean = this == 1f

    @Composable
    fun UpdateContentBarsColors(
        isDark: Boolean = false
    ) {
        if(LocalInspectionMode.current) return

        val view = LocalView.current
        DisposableEffect(Unit) {
            val window = (view.context as Activity).window
            val insetsController = WindowCompat.getInsetsController(window, view)
            val originalStatusBarAppearance = insetsController.isAppearanceLightStatusBars
            val originalNavigationBarAppearance = insetsController.isAppearanceLightNavigationBars

            insetsController.isAppearanceLightStatusBars = isDark
            insetsController.isAppearanceLightNavigationBars = isDark

            onDispose {
                insetsController.isAppearanceLightStatusBars = originalStatusBarAppearance
                insetsController.isAppearanceLightNavigationBars = originalNavigationBarAppearance
            }
        }
    }

}