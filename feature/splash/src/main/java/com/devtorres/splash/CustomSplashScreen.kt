package com.devtorres.splash

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.StartOffsetType
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.retain.retain
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.core.view.WindowCompat
import com.devtorres.ui.UiCommon.isAnimationCompleted
import com.devtorres.ui.components.cards.CustomOutlinedCard
import com.devtorres.ui.theme.green
import com.devtorres.ui.theme.onGreen
import com.devtorres.splash.R as SplashResource
import com.devtorres.ui.R as UiResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

internal val backgroundColor = green

internal val onBackgroundColor = onGreen

@Composable
fun CustomSplashScreen(
    modifier: Modifier = Modifier,
    onNavigateToOnboarding: () -> Unit,
    onNavigateToHome: () -> Unit
) {

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

    val logoEnter = retain { Animatable(0f) }
    val brandCopyEnter = retain { Animatable(0f) }

    LaunchedEffect(Unit) {
        if(!logoEnter.value.isAnimationCompleted()) {
            launch {
                logoEnter.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(
                        durationMillis = 900,
                        easing = CubicBezierEasing(0.22f, 1f, 0.36f, 1f)
                    )
                )
            }
        }
        if(!brandCopyEnter.value.isAnimationCompleted()) {
            launch {
                delay(250.milliseconds)
                brandCopyEnter.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(
                        durationMillis = 850,
                        easing = CubicBezierEasing(0.22f, 1f, 0.36f, 1f)
                    )
                )
            }
        }
        launch {
            delay(3000.milliseconds)
            onNavigateToOnboarding()
        }
    }

    val logoFloatTransition = rememberInfiniteTransition(label = "logoFloat")
    val logoFloat by logoFloatTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
            initialStartOffset = StartOffset(900, StartOffsetType.Delay)
        ),
        label = "logoFloatValue"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .drawBehind {
                drawRect(
                    color = backgroundColor
                )
            }
            .padding(16.dp)
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            CustomOutlinedCard(
                border = BorderStroke(
                    width = 4.dp,
                    color = onBackgroundColor
                ),
                colors = CardDefaults.outlinedCardColors(
                    containerColor = Color.Black
                ),
                shape = RoundedCornerShape(40.dp),
                modifier = Modifier
                    .size(128.dp)
                    .align(Alignment.CenterHorizontally)
                    .graphicsLayer {
                        val floatBounce = 1f + 0.03f * logoFloat

                        alpha = logoEnter.value
                        rotationZ = lerp(-8f, 0f, logoEnter.value)
                        scaleX = lerp(0.65f, 1f, logoEnter.value) * floatBounce
                        scaleY = scaleX
                        translationY = (-7.2).dp.toPx() * logoFloat
                    }
            ) {
                Image(
                    painter = painterResource(SplashResource.drawable.bird_silhoutte),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(onBackgroundColor),
                    modifier = Modifier.padding(24.dp)
                )
            }

            Spacer(Modifier.size(32.dp))

            Column(
                modifier = Modifier
                    .graphicsLayer {
                        alpha = brandCopyEnter.value
                        translationY = 16.dp.toPx() * (1f - brandCopyEnter.value)
                    }
            ) {
                Text(
                    text = stringResource(UiResource.string.app_name).uppercase(),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.W800,
                    color = onBackgroundColor,
                    letterSpacing = 2.sp,
                    modifier = Modifier
                        .alpha(0.5f)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(Modifier.size(8.dp))

                Text(
                    text = stringResource(SplashResource.string.splash_title),
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.W900,
                    letterSpacing = (-3.6).sp,
                    color = onBackgroundColor,
                )

                Spacer(Modifier.size(8.dp))

                Text(
                    text = stringResource(SplashResource.string.splash_desc),
                    style = MaterialTheme.typography.bodyLarge,
                    color = onBackgroundColor,
                    modifier = Modifier
                        .alpha(0.75f)
                        .align(Alignment.CenterHorizontally)
                )
            }

            Spacer(Modifier.size(64.dp))
        }

        Text(
            text = stringResource(SplashResource.string.splash_footer),
            style = MaterialTheme.typography.labelMedium.copy(
                letterSpacing = 2.sp
            ),
            fontWeight = FontWeight.W700,
            color = onBackgroundColor,
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.navigationBars)
                .align(Alignment.BottomCenter)
                .alpha(alpha = 0.5f)
        )
    }
}