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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.devtorres.common.states.SavingState
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
    val progress = retain { Animatable(0f) }
    var hasPlayedEntryAnimation by retain { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (!hasPlayedEntryAnimation) {
            hasPlayedEntryAnimation = true
            progress.snapTo(0f)
            progress.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
            )
        } else {
            progress.snapTo(1f)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val maxRadius = hypot(size.width, size.height) / 2f
            drawCircle(
                color = containerColor,
                radius = progress.value * maxRadius,
                center = center
            )
        }

        if(progress.value > 0f) {
            AnimatedVisibility(
                visible = progress.value >= 1f,
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
