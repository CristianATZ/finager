package com.devtorres.ui.appearance

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Brightness6
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.ui.graphics.Color
import com.devtorres.domain.AppTheme

fun AppTheme.icon() = when(this) {
    AppTheme.LIGHT -> Icons.Outlined.LightMode
    AppTheme.DARK -> Icons.Outlined.DarkMode
    AppTheme.SYSTEM -> Icons.Outlined.Brightness6
}

fun AppTheme.iconBackgroundColor() = when(this) {
    AppTheme.LIGHT -> Color(0xFFFFFFFF)
    AppTheme.DARK -> Color(0xFF1B1C15)
    AppTheme.SYSTEM -> Color(0xFF3B608F)
}

fun AppTheme.iconTint() = when(this) {
    AppTheme.LIGHT -> Color(0xFF1B1C15)
    AppTheme.DARK -> Color(0xFFFFFFFF)
    AppTheme.SYSTEM -> Color(0xFFFFFFFF)
}

fun AppTheme.title() = when(this) {
    AppTheme.LIGHT -> "Claro"
    AppTheme.DARK -> "Oscuro"
    AppTheme.SYSTEM -> "Sistema"
}

fun AppTheme.subtitle() = when(this) {
    AppTheme.LIGHT -> "Fondo luminoso"
    AppTheme.DARK -> "Fondo oscuro"
    AppTheme.SYSTEM -> "Se adapta al equipo"
}

fun AppTheme.subtitleColor() = when(this) {
    AppTheme.LIGHT -> Color(0xFFFFA726)
    AppTheme.DARK -> Color(0xFF7986CB)
    AppTheme.SYSTEM -> Color(0xFF9E9E9E)
}
