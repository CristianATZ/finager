package com.devtorres.splash

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devtorres.splash.components.OrbitingCirclesBackground
import com.devtorres.splash.components.SplashContent
import com.devtorres.ui.UiCommon
import com.devtorres.ui.UiCommon.UpdateContentBarsColors
import com.devtorres.ui.theme.green
import com.devtorres.ui.theme.onGreen

internal val backgroundColor = green

internal val onBackgroundColor = onGreen

@Composable
fun CustomSplashScreen(
    modifier: Modifier = Modifier,
    onNavigateToOnboarding: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    UiCommon.RunIfNotPreview {
        BackHandler { }
    }

    UpdateContentBarsColors()

    OrbitingCirclesBackground(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = 16.dp,
        backgroundColor = backgroundColor,
        circleColor = onBackgroundColor
    ) {
        SplashContent(
            modifier = Modifier.align(Alignment.Center),
            onNavigateToHome = onNavigateToHome,
            onNavigateToOnboarding = onNavigateToOnboarding
        )

        Text(
            text = stringResource(R.string.splash_footer),
            style = MaterialTheme.typography.labelMedium.copy(
                letterSpacing = 2.sp
            ),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.W700,
            color = onBackgroundColor,
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.navigationBars)
                .align(Alignment.BottomCenter)
                .alpha(alpha = 0.5f)
        )
    }
}