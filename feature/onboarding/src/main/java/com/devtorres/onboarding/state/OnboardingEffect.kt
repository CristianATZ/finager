package com.devtorres.onboarding.state

sealed interface OnboardingEffect {
    data object NavigateToHome : OnboardingEffect
    data object NavigateForward: OnboardingEffect
    data object NavigateBackward : OnboardingEffect
}