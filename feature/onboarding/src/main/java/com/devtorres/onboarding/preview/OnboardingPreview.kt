package com.devtorres.onboarding.preview

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.devtorres.common.states.SavingState
import com.devtorres.domain.AppCurrency
import com.devtorres.domain.AppLanguage
import com.devtorres.domain.AppTheme
import com.devtorres.onboarding.OnboardingContent
import com.devtorres.onboarding.navigation.OnboardingDestination
import com.devtorres.onboarding.state.OnboardingState
import com.devtorres.onboarding.steps.biometrics.BiometricsScreen
import com.devtorres.onboarding.steps.currency.CurrencyScreen
import com.devtorres.onboarding.steps.intro.IntroScreen
import com.devtorres.onboarding.steps.language.LanguageScreen
import com.devtorres.onboarding.steps.summary.SummaryScreen
import com.devtorres.onboarding.steps.theme.ThemeScreen
import com.devtorres.onboarding.steps.username.UsernameScreen
import com.devtorres.ui.theme.FinagerTheme

private data class OnboardingDestinationState(
    val currentRoute: OnboardingDestination,
    val state: OnboardingState = OnboardingState()
)

private class OnboardingDestinationWindowProvider : PreviewParameterProvider<OnboardingDestinationState> {
    override val values: Sequence<OnboardingDestinationState>
        get() = sequenceOf(
            OnboardingDestinationState(currentRoute = OnboardingDestination.IntroRoute),
            OnboardingDestinationState(
                currentRoute = OnboardingDestination.UsernameRoute,
                state = OnboardingState(username = "C")
            ),
            OnboardingDestinationState(
                currentRoute = OnboardingDestination.UsernameRoute,
                state = OnboardingState(username = "Cristian")
            ),
            OnboardingDestinationState(
                currentRoute = OnboardingDestination.CurrencyRoute,
                state = OnboardingState(currency = null)
            ),
            OnboardingDestinationState(
                currentRoute = OnboardingDestination.CurrencyRoute,
                state = OnboardingState(currency = AppCurrency.EUR)
            ),
            OnboardingDestinationState(
                currentRoute = OnboardingDestination.LanguageRoute,
                state = OnboardingState(language = null)
            ),
            OnboardingDestinationState(
                currentRoute = OnboardingDestination.LanguageRoute,
                state = OnboardingState(language = AppLanguage.ES)
            ),
            OnboardingDestinationState(
                currentRoute = OnboardingDestination.ThemeRoute,
                state = OnboardingState(theme = null)
            ),
            OnboardingDestinationState(
                currentRoute = OnboardingDestination.ThemeRoute,
                state = OnboardingState(theme = AppTheme.DARK)
            ),
            OnboardingDestinationState(
                currentRoute = OnboardingDestination.BiometricsRoute,
                state = OnboardingState(biometrics = false)
            ),
            OnboardingDestinationState(
                currentRoute = OnboardingDestination.BiometricsRoute,
                state = OnboardingState(biometrics = true)
            ),
            OnboardingDestinationState(
                currentRoute = OnboardingDestination.SummaryRoute,
                state = OnboardingState(
                    username = "Cristian",
                    currency = AppCurrency.EUR,
                    language = AppLanguage.ES,
                    theme = AppTheme.DARK,
                    biometrics = true
                )
            )
        )
}

@Preview
@Composable
private fun OnboardingPreview(
    @PreviewParameter(OnboardingDestinationWindowProvider::class)
    previewState: OnboardingDestinationState
) {
    FinagerTheme {
        OnboardingContent(
            uiState = previewState.state,
            savingState = SavingState.Idle,
            currentRoute = previewState.currentRoute,
            onBack = {},
            onNext = {},
            onFinish = {},
            onNavigateToHome = {}
        ) { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            when (previewState.currentRoute) {
                OnboardingDestination.IntroRoute -> IntroScreen()
                OnboardingDestination.UsernameRoute -> UsernameScreen(
                    modifier = modifier,
                    username = previewState.state.username
                )
                OnboardingDestination.CurrencyRoute -> CurrencyScreen(
                    modifier = modifier,
                    currency = previewState.state.currency
                )
                OnboardingDestination.LanguageRoute -> LanguageScreen(
                    modifier = modifier,
                    language = previewState.state.language
                )
                OnboardingDestination.ThemeRoute -> ThemeScreen(
                    modifier = modifier,
                    theme = previewState.state.theme
                )
                OnboardingDestination.BiometricsRoute -> BiometricsScreen(
                    modifier = modifier,
                    biometrics = previewState.state.biometrics
                )
                OnboardingDestination.SummaryRoute -> SummaryScreen(
                    modifier = modifier,
                    onboardingState = previewState.state
                )
            }
        }
    }
}