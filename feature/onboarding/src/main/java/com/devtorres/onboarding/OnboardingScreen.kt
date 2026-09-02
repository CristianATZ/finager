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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.rememberLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
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
import com.devtorres.onboarding.state.OnboardingEffect
import com.devtorres.onboarding.state.OnboardingEvent
import com.devtorres.common.states.SavingState
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
    val uiState by vm.uiState.collectAsStateWithLifecycle()

    val backStack = rememberNavBackStack(IntroRoute)
    val currentRoute by rememberUpdatedState(backStack.lastOrNull() as? OnboardingRoute)
    val lifecycleOwner = rememberLifecycleOwner()

    LaunchedEffect(vm.uiEffect, lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            vm.uiEffect.collect { effect ->
                when(effect) {
                    OnboardingEffect.NavigateForward -> currentRoute.next()?.let { backStack.add(it) }
                    OnboardingEffect.NavigateBackward -> backStack.removeLastOrNull()
                    OnboardingEffect.NavigateToHome -> onNavigateToHome()
                }
            }
        }
    }

    BackHandler {
        vm.onEvent(OnboardingEvent.OnBackClicked)
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
                    onboardingState = uiState.uiState,
                    onBack = {
                        vm.onEvent(OnboardingEvent.OnBackClicked)
                    },
                    onNext = {
                        vm.onEvent(OnboardingEvent.OnNextClicked)
                    },
                    onFinish = {
                        vm.onEvent(OnboardingEvent.OnFinish)
                    }
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
                            username = uiState.uiState.username,
                            onUsernameChange = {
                                vm.onEvent(OnboardingEvent.OnUsernameChanged(it))
                            }
                        )
                    }
                    entry<CurrencyRoute> {
                        CurrencyScreen(
                            modifier = Modifier.padding(innerPadding),
                            currency = uiState.uiState.currency,
                            onCurrencyChange = {
                                vm.onEvent(OnboardingEvent.OnCurrencySelected(it))
                            }
                        )
                    }
                    entry<LanguageRoute> {
                        LanguageScreen(
                            modifier = Modifier.padding(innerPadding),
                            language = uiState.uiState.language,
                            onLanguageChange = {
                                vm.onEvent(OnboardingEvent.OnLanguageSelected(it))
                            }
                        )
                    }
                    entry<ThemeRoute> {
                        ThemeScreen(
                            modifier = Modifier.padding(innerPadding),
                            theme = uiState.uiState.theme,
                            onThemeChange = {
                                vm.onEvent(OnboardingEvent.OnThemeSelected(it))
                            }
                        )
                    }
                    entry<BiometricsRoute> {
                        BiometricsScreen(
                            modifier = Modifier.padding(innerPadding),
                            biometrics = uiState.uiState.biometrics,
                            onBiometricsChange = {
                                vm.onEvent(OnboardingEvent.OnBiometricsEnabled(it))
                            }
                        )
                    }
                    entry<SummaryRoute> {
                        SummaryScreen(
                            modifier = Modifier.padding(innerPadding),
                            onboardingState = uiState.uiState
                        )
                    }
                }
            )
        }

        if(uiState.savingState !is SavingState.Idle) {
            SavingScreen(
                username = uiState.uiState.username,
                savingState = uiState.savingState,
                onNavigateToHome = {
                    vm.onEvent(OnboardingEvent.OnNavigateToHome)
                }
            )
        }
    }
}
