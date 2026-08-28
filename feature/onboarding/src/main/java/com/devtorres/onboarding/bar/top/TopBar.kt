package com.devtorres.onboarding.bar.top

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devtorres.onboarding.OnboardingStep
import com.devtorres.ui.components.stepper.segmented.SegmentedStepper

@Composable
internal fun TopBar(
    modifier: Modifier = Modifier,
    step: OnboardingStep
) {
    AnimatedVisibility(
        visible = step.topBarVisible,
        enter = slideInVertically { -it } + fadeIn(),
        exit = slideOutVertically { -it } + fadeOut()
    ) {
        SegmentedStepper(
            steps = step.onboardingSize(),
            currentStep = step.step,
            showText = true,
            modifier = modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(16.dp)
        )
    }
}
