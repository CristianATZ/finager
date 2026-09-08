package com.devtorres.finager.presentation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.devtorres.finager.navigation.AppDestination
import com.devtorres.home.HomeScreen
import com.devtorres.navigation.decorator.rememberAppNavigator
import com.devtorres.navigation.decorator.rememberAppNavigatorNavEntryDecorator
import com.devtorres.onboarding.OnBoardingScreen
import com.devtorres.splash.CustomSplashScreen

@Composable
fun AppContent(
    modifier: Modifier = Modifier
) {
    val backstack = rememberNavBackStack(AppDestination.CustomSplash)
    val appNavigator = rememberAppNavigator(backstack)

    NavDisplay(
        modifier = modifier,
        backStack = appNavigator.backstack,
        onBack = appNavigator::goBack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
            rememberAppNavigatorNavEntryDecorator(appNavigator)
        ),
        transitionSpec = {
            fadeIn(animationSpec = tween(durationMillis = 700)) togetherWith
                fadeOut(animationSpec = tween(durationMillis = 700))
        },
        popTransitionSpec = {
            fadeIn(animationSpec = tween(durationMillis = 700)) togetherWith
                fadeOut(animationSpec = tween(durationMillis = 700))
        },
        entryProvider = entryProvider {
            entry<AppDestination.CustomSplash> {
                CustomSplashScreen(
                    onNavigateToOnboarding = {
                        appNavigator.newRootScreen(AppDestination.OnboardingFlow)
                    },
                    onNavigateToHome = {
                        appNavigator.newRootScreen(AppDestination.MainFlow)
                    }
                )
            }

            entry<AppDestination.OnboardingFlow> {
                OnBoardingScreen(
                    onNavigateToHome = {
                        appNavigator.newRootScreen(AppDestination.MainFlow)
                    }
                )
            }

            entry<AppDestination.MainFlow> {
                HomeScreen()
            }
        }
    )
}