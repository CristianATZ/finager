package com.devtorres.onboarding

import androidx.activity.compose.BackHandler
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
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
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.devtorres.onboarding.bar.bottom.BottomBar
import com.devtorres.onboarding.bar.top.TopBar
import com.devtorres.onboarding.navigation.BiometricsRoute
import com.devtorres.onboarding.navigation.CurrencyRoute
import com.devtorres.onboarding.navigation.LanguageRoute
import com.devtorres.onboarding.navigation.IntroRoute
import com.devtorres.onboarding.navigation.OnboardingRoute
import com.devtorres.onboarding.navigation.SummaryRoute
import com.devtorres.onboarding.navigation.ThemeRoute
import com.devtorres.onboarding.navigation.UsernameRoute
import com.devtorres.onboarding.navigation.next
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
    val backStack = rememberNavBackStack(IntroRoute)
    val currentRoute = backStack.last() as OnboardingRoute

    var onboardingState by remember {
        mutableStateOf(OnboardingState())
    }

    var isSaving by rememberSaveable {
        mutableStateOf(false)
    }

    BackHandler {
        if (backStack.size > 1) {
            backStack.removeLastOrNull()
        } else {
            onCloseApp()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopBar(
                    step = currentRoute
                )
            },
            bottomBar = {
                BottomBar(
                    step = currentRoute,
                    onboardingState = onboardingState,
                    onBack = {
                        backStack.removeLastOrNull()
                    },
                    onNext = {
                        currentRoute.next()?.let { backStack.add(it) }
                    },
                    onFinish = { isSaving = true },
                )
            },
            modifier = Modifier.imePadding()
        ) { innerPadding ->
            NavDisplay(
                backStack = backStack,
                onBack = { backStack.removeLastOrNull() },
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                popTransitionSpec = { fadeIn() togetherWith fadeOut() },
                predictivePopTransitionSpec = { fadeIn() togetherWith fadeOut() },
                entryProvider = entryProvider {
                    entry<IntroRoute> {
                        IntroScreen()
                    }
                    entry<UsernameRoute> {
                        UsernameScreen(
                            modifier = Modifier.padding(innerPadding),
                            username = onboardingState.username,
                            onUsernameChange = {
                                onboardingState = onboardingState.copy(username = it)
                            }
                        )
                    }
                    entry<CurrencyRoute> {
                        CurrencyScreen(
                            modifier = Modifier.padding(innerPadding),
                            currency = onboardingState.currency,
                            onCurrencyChange = {
                                onboardingState = onboardingState.copy(currency = it)
                            }
                        )
                    }
                    entry<LanguageRoute> {
                        LanguageScreen(
                            modifier = Modifier.padding(innerPadding),
                            language = onboardingState.language,
                            onLanguageChange = {
                                onboardingState = onboardingState.copy(language = it)
                            }
                        )
                    }
                    entry<ThemeRoute> {
                        ThemeScreen(
                            modifier = Modifier.padding(innerPadding),
                            theme = onboardingState.theme,
                            onThemeChange = {
                                onboardingState = onboardingState.copy(theme = it)
                            }
                        )
                    }
                    entry<BiometricsRoute> {
                        BiometricsScreen(
                            modifier = Modifier.padding(innerPadding),
                            biometrics = onboardingState.biometrics,
                            onBiometricsChange = {
                                onboardingState = onboardingState.copy(biometrics = it)
                            }
                        )
                    }
                    entry<SummaryRoute> {
                        SummaryScreen(
                            modifier = Modifier.padding(innerPadding),
                            onboardingState = onboardingState
                        )
                    }
                }
            )
        }

        SavingScreen(
            visible = isSaving,
            username = onboardingState.username,
            onNavigateHome = {
                isSaving = false
                backStack.clear()
                backStack.add(IntroRoute)
                onboardingState = OnboardingState()
            }
        )
    }
}
