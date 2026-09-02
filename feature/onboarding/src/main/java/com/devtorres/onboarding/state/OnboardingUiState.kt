package com.devtorres.onboarding.state

import com.devtorres.common.states.SavingState

data class OnboardingUiState(
    val uiState: OnboardingState,
    val savingState: SavingState = SavingState.Idle
)