package com.devtorres.onboarding

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.devtorres.onboarding.bar.bottom.BottomBar
import com.devtorres.onboarding.bar.top.TopBar
import com.devtorres.onboarding.saving.SavingScreen
import com.devtorres.onboarding.steps.biometrics.BiometricsScreen
import com.devtorres.onboarding.steps.currency.CurrencyScreen
import com.devtorres.onboarding.steps.intro.IntroScreen
import com.devtorres.onboarding.steps.language.LanguageScreen
import com.devtorres.onboarding.steps.summary.SummaryScreen
import com.devtorres.onboarding.steps.theme.ThemeScreen
import com.devtorres.onboarding.steps.username.UsernameScreen

@Composable
fun OnBoardingScreen(
    onCloseApp: () -> Unit
) {
    var step by rememberSaveable {
        mutableStateOf(OnboardingStep.START)
    }

    var onboardingState by remember {
        mutableStateOf(OnboardingState())
    }

    var isSaving by rememberSaveable {
        mutableStateOf(false)
    }

    BackHandler {
        step.onBack()?.let {
            step = it
        } ?: run {
            onCloseApp()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopBar(
                    step = step
                )
            },
            bottomBar = {
                BottomBar(
                    step = step,
                    onboardingState = onboardingState,
                    onBack = {
                        step.onBack()?.let { step = it }
                    },
                    onNext = {
                        step.onNext()?.let { step = it }
                    },
                    onFinish = { isSaving = true },
                )
            },
            modifier = Modifier.imePadding()
        ) { innerPadding ->
            AnimatedContent(
                targetState = step,
                label = "step"
            ) { currentStep ->
                when (currentStep) {
                    OnboardingStep.START -> IntroScreen()
                    OnboardingStep.USERNAME -> UsernameScreen(
                        modifier = Modifier.padding(innerPadding),
                        username = onboardingState.username,
                        onUsernameChange = {
                            onboardingState = onboardingState.copy(username = it)
                        }
                    )
                    OnboardingStep.CURRENCY -> CurrencyScreen(
                        modifier = Modifier.padding(innerPadding),
                        currency = onboardingState.currency,
                        onCurrencyChange = {
                            onboardingState = onboardingState.copy(currency = it)
                        }
                    )
                    OnboardingStep.LANGUAGE -> LanguageScreen(
                        modifier = Modifier.padding(innerPadding),
                        language = onboardingState.language,
                        onLanguageChange = {
                            onboardingState = onboardingState.copy(language = it)
                        }
                    )
                    OnboardingStep.THEME -> ThemeScreen(
                        modifier = Modifier.padding(innerPadding),
                        theme = onboardingState.theme,
                        onThemeChange = {
                            onboardingState = onboardingState.copy(theme = it)
                        }
                    )
                    OnboardingStep.BIOMETRICS -> BiometricsScreen(
                        modifier = Modifier.padding(innerPadding),
                        biometrics = onboardingState.biometrics,
                        onBiometricsChange = {
                            onboardingState = onboardingState.copy(biometrics = it)
                        }
                    )
                    OnboardingStep.SUMMARY -> SummaryScreen(
                        modifier = Modifier.padding(innerPadding),
                        onboardingState = onboardingState
                    )
                }
            }
        }

        SavingScreen(
            visible = isSaving,
            username = onboardingState.username,
            onNavigateHome = {
                isSaving = false
                step = OnboardingStep.START
                onboardingState = OnboardingState()
            }
        )
    }
}