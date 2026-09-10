package com.devtorres.common.states

import androidx.compose.runtime.Immutable

@Immutable
sealed interface SavingState {
    data object Idle : SavingState
    data object Loading : SavingState
    data object Completed : SavingState
    data class Error(val message: String) : SavingState
}