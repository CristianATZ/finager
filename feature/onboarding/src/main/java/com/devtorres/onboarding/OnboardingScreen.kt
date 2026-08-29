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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.devtorres.onboarding.bar.bottom.BottomBar
import com.devtorres.onboarding.bar.top.TopBar
import com.devtorres.onboarding.navigation.BiometricsRoute
import com.devtorres.onboarding.navigation.CurrencyRoute
import com.devtorres.onboarding.navigation.IntroRoute
import com.devtorres.onboarding.navigation.LanguageRoute
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
internal fun OnBoardingScreen(
    onNavigateToHome: () -> Unit
) {
    val vm: OnboardingVM = hiltViewModel()

    val backStack = rememberNavBackStack(IntroRoute)
    val currentRoute = backStack.lastOrNull() as? OnboardingRoute

    val onboardingState by vm.onboardingState.collectAsStateWithLifecycle()

    var isSaving by rememberSaveable {
        mutableStateOf(false)
    }

    BackHandler {
        backStack.removeLastOrNull()
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
                            onUsernameChange = vm::updateUsername
                        )
                    }
                    entry<CurrencyRoute> {
                        CurrencyScreen(
                            modifier = Modifier.padding(innerPadding),
                            currency = onboardingState.currency,
                            onCurrencyChange = vm::updateCurrency
                        )
                    }
                    entry<LanguageRoute> {
                        LanguageScreen(
                            modifier = Modifier.padding(innerPadding),
                            language = onboardingState.language,
                            onLanguageChange = vm::updateLanguage
                        )
                    }
                    entry<ThemeRoute> {
                        ThemeScreen(
                            modifier = Modifier.padding(innerPadding),
                            theme = onboardingState.theme,
                            onThemeChange = vm::updateTheme
                        )
                    }
                    entry<BiometricsRoute> {
                        BiometricsScreen(
                            modifier = Modifier.padding(innerPadding),
                            biometrics = onboardingState.biometrics,
                            onBiometricsChange = vm::updateBiometrics
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
            onNavigateToHome = onNavigateToHome
            /*onNavigateHome = {
                isSaving = false
                backStack.clear()
                backStack.add(IntroRoute)
                onboardingState = OnboardingState()
            }*/
        )
    }
}
