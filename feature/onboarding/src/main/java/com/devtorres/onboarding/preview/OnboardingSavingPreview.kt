package com.devtorres.onboarding.preview

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.devtorres.common.states.SavingState
import com.devtorres.onboarding.OnboardingContent
import com.devtorres.onboarding.navigation.OnboardingDestination
import com.devtorres.onboarding.state.OnboardingState
import com.devtorres.onboarding.steps.summary.SummaryScreen
import com.devtorres.ui.theme.FinagerTheme

private class SavingStateWindowProvider : PreviewParameterProvider<SavingState> {
    override val values: Sequence<SavingState>
        get() = sequenceOf(
            SavingState.Idle,
            SavingState.Loading,
            SavingState.Completed
        )
}

@Preview
@Composable
private fun OnboardingSavingPreview(
    @PreviewParameter(SavingStateWindowProvider::class)
    savingState: SavingState
) {
    FinagerTheme {
        OnboardingContent(
            uiState = OnboardingState(),
            savingState = savingState,
            currentRoute = OnboardingDestination.SummaryRoute,
            onBack = {},
            onNext = {},
            onFinish = {},
            onNavigateToHome = {}
        ) {
            SummaryScreen(
                modifier = Modifier.padding(it),
                onboardingState = OnboardingState(),
            )
        }
    }
}