package com.devtorres.splash

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.retain.retain
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.devtorres.ui.theme.green
import com.devtorres.ui.theme.onGreen
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

internal val backgroundColor = green

internal val onBackgroundColor = onGreen

internal fun Float.isAnimationCompleted(): Boolean = this == 1f

@Composable
fun CustomSplashScreen(
    modifier: Modifier = Modifier,
    onNavigateToOnboarding: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    val squirrelAlpha = retain { Animatable(0f) }
    val titleAlpha = retain { Animatable(0f) }
    val descAlpha = retain { Animatable(0f) }

    LaunchedEffect(Unit) {
        squirrelAlpha.snapTo(if(squirrelAlpha.value.isAnimationCompleted()) 1f else 0f)
        titleAlpha.snapTo(if(titleAlpha.value.isAnimationCompleted()) 1f else 0f)
        descAlpha.snapTo(if(descAlpha.value.isAnimationCompleted()) 1f else 0f)

        if(!squirrelAlpha.value.isAnimationCompleted()) {
            delay(400.milliseconds)
            squirrelAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
            )
        }

        if(!titleAlpha.value.isAnimationCompleted()) {
            delay(200.milliseconds)
            titleAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
            )
        }

        if(!descAlpha.value.isAnimationCompleted()) {
            delay(400.milliseconds)
            descAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
            )
        }

        delay(1000.milliseconds)
        onNavigateToOnboarding()
    }

    val view = LocalView.current

    DisposableEffect(Unit) {
        val window = (view.context as Activity).window
        val insetsController = WindowCompat.getInsetsController(window, view)
        val originalStatusBarAppearance = insetsController.isAppearanceLightStatusBars
        val originalNavigationBarAppearance = insetsController.isAppearanceLightNavigationBars

        insetsController.isAppearanceLightStatusBars = false
        insetsController.isAppearanceLightNavigationBars = false

        onDispose {
            insetsController.isAppearanceLightStatusBars = originalStatusBarAppearance
            insetsController.isAppearanceLightNavigationBars = originalNavigationBarAppearance
        }
    }

    BackHandler { }

    Box (
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-100).dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "meet finager.",
                color = onBackgroundColor,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.alpha(alpha = titleAlpha.value)
            )

            Column(
                modifier = Modifier.alpha(alpha = descAlpha.value),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "your personal",
                    color = onBackgroundColor,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "manager companion",
                    color = onBackgroundColor,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Image(
            painter = painterResource(R.drawable.squirrel_silhouette),
            contentDescription = null,
            colorFilter = ColorFilter.tint(onBackgroundColor),
            modifier = Modifier
                .size(412.dp)
                .align(Alignment.BottomStart)
                .offset(
                    x = (-100).dp,
                    y = (50).dp
                )
                .alpha(alpha = squirrelAlpha.value)
        )
    }
}