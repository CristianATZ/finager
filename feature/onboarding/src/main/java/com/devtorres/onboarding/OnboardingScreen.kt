package com.devtorres.onboarding

import androidx.activity.compose.BackHandler
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
import com.devtorres.common.states.SavingState
import com.devtorres.navigation.decorator.rememberAppNavigator
import com.devtorres.onboarding.bar.bottom.BottomBar
import com.devtorres.onboarding.bar.top.TopBar
import com.devtorres.onboarding.navigation.OnboardingDestination
import com.devtorres.onboarding.saving.SavingScreen
import com.devtorres.onboarding.state.OnboardingEffect
import com.devtorres.onboarding.state.OnboardingEvent
import com.devtorres.onboarding.state.OnboardingState
import com.devtorres.onboarding.steps.biometrics.BiometricsScreen
import com.devtorres.onboarding.steps.currency.CurrencyScreen
import com.devtorres.onboarding.steps.intro.IntroScreen
import com.devtorres.onboarding.steps.language.LanguageScreen
import com.devtorres.onboarding.steps.summary.SummaryScreen
import com.devtorres.onboarding.steps.theme.ThemeScreen
import com.devtorres.onboarding.steps.username.UsernameScreen
import com.devtorres.ui.UiCommon

@Composable
fun OnBoardingScreen(
    onNavigateToHome: () -> Unit
) {
    val vm: OnboardingVM = hiltViewModel()
    val uiState by vm.uiState.collectAsStateWithLifecycle()

    val backStack = rememberNavBackStack(OnboardingDestination.IntroRoute)
    val onboardingNavigator = rememberAppNavigator(backStack)
    val currentRoute by rememberUpdatedState(onboardingNavigator.currentRoute as OnboardingDestination)

    val lifecycleOwner = rememberLifecycleOwner()
    LaunchedEffect(vm.uiEffect, lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            vm.uiEffect.collect { effect ->
                when(effect) {
                    OnboardingEffect.NavigateForward -> currentRoute.createRoute()?.let { onboardingNavigator.navigateTo(it) }
                    OnboardingEffect.NavigateBackward -> onboardingNavigator.goBack()
                    OnboardingEffect.NavigateToHome -> onNavigateToHome()
                }
            }
        }
    }

    UiCommon.RunIfNotPreview {
        BackHandler(enabled = onboardingNavigator.backstack.size > 1) {
            vm.onEvent(OnboardingEvent.OnBackClicked)
        }
    }

    OnboardingContent(
        uiState = uiState.uiState,
        savingState = uiState.savingState,
        currentRoute = currentRoute,
        onBack = { vm.onEvent(OnboardingEvent.OnBackClicked) },
        onNext = { vm.onEvent(OnboardingEvent.OnNextClicked) },
        onFinish = { vm.onEvent(OnboardingEvent.OnFinish) },
        onNavigateToHome = { vm.onEvent(OnboardingEvent.OnNavigateToHome) }
    ) { innerPadding ->
        NavDisplay(
            backStack = onboardingNavigator.backstack,
            onBack = onboardingNavigator::goBack,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            popTransitionSpec = { fadeIn() togetherWith fadeOut() },
            predictivePopTransitionSpec = { fadeIn() togetherWith fadeOut() },
            entryProvider = entryProvider {
                entry<OnboardingDestination.IntroRoute> {
                    IntroScreen()
                }
                entry<OnboardingDestination.UsernameRoute> {
                    UsernameScreen(
                        modifier = Modifier.padding(innerPadding),
                        username = uiState.uiState.username,
                        onUsernameChange = {
                            vm.onEvent(OnboardingEvent.OnUsernameChanged(it))
                        }
                    )
                }
                entry<OnboardingDestination.CurrencyRoute> {
                    CurrencyScreen(
                        modifier = Modifier.padding(innerPadding),
                        currency = uiState.uiState.currency,
                        onCurrencyChange = {
                            vm.onEvent(OnboardingEvent.OnCurrencySelected(it))
                        }
                    )
                }
                entry<OnboardingDestination.LanguageRoute> {
                    LanguageScreen(
                        modifier = Modifier.padding(innerPadding),
                        language = uiState.uiState.language,
                        onLanguageChange = {
                            vm.onEvent(OnboardingEvent.OnLanguageSelected(it))
                        }
                    )
                }
                entry<OnboardingDestination.ThemeRoute> {
                    ThemeScreen(
                        modifier = Modifier.padding(innerPadding),
                        theme = uiState.uiState.theme,
                        onThemeChange = {
                            vm.onEvent(OnboardingEvent.OnThemeSelected(it))
                        }
                    )
                }
                entry<OnboardingDestination.BiometricsRoute> {
                    BiometricsScreen(
                        modifier = Modifier.padding(innerPadding),
                        biometrics = uiState.uiState.biometrics,
                        onBiometricsChange = {
                            vm.onEvent(OnboardingEvent.OnBiometricsEnabled(it))
                        }
                    )
                }
                entry<OnboardingDestination.SummaryRoute> {
                    SummaryScreen(
                        modifier = Modifier.padding(innerPadding),
                        onboardingState = uiState.uiState
                    )
                }
            }
        )
    }
}

@Composable
internal fun OnboardingContent(
    uiState: OnboardingState,
    savingState: SavingState,
    currentRoute: OnboardingDestination,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
    onNavigateToHome: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopBar(
                    currentStep = currentRoute,
                    totalSteps = OnboardingDestination.totalSteps()
                )
            },
            bottomBar = {
                BottomBar(
                    step = currentRoute,
                    onboardingState = uiState,
                    onBack = onBack,
                    onNext = onNext,
                    onFinish = onFinish
                )
            },
            modifier = Modifier.imePadding()
        ) { innerPadding ->
            content.invoke(innerPadding)
        }

        if(savingState !is SavingState.Idle) {
            SavingScreen(
                username = uiState.username,
                savingState = savingState,
                onNavigateToHome = onNavigateToHome
            )
        }
    }
}