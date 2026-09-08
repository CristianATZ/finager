package com.devtorres.onboarding.saving

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.retain.retain
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.devtorres.common.states.SavingState
import com.devtorres.ui.UiCommon.isAnimationCompleted
import com.devtorres.ui.theme.green
import com.devtorres.ui.theme.onGreen
import kotlin.math.hypot

internal val containerColor = green
internal val contentColor = onGreen

@Composable
internal fun SavingScreen(
    username: String,
    savingState: SavingState,
    onNavigateToHome: () -> Unit
) {
    val screenEnter = retain { Animatable(0f) }

    LaunchedEffect(Unit) {
        if (!screenEnter.value.isAnimationCompleted()) {
            screenEnter.snapTo(0f)
            screenEnter.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
            )
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val maxRadius = hypot(size.width, size.height) / 2f
            drawCircle(
                color = containerColor,
                radius = screenEnter.value * maxRadius,
                center = center
            )
        }

        if(screenEnter.value > 0f) {
            AnimatedVisibility(
                visible = screenEnter.value >= 1f,
                enter = scaleIn(),
                exit = scaleOut(),
                modifier = Modifier.align(Alignment.Center)
            ) {
                SavingOverlayContent(
                    username = username,
                    containerColor = containerColor,
                    contentColor = contentColor,
                    homeButtonEnabled = savingState !is SavingState.Loading,
                    onNavigateHome = onNavigateToHome
                )
            }
        }
    }
}
