package com.devtorres.common.states

sealed interface SavingState {
    data object Idle : SavingState
    data object Loading : SavingState
    data object Completed : SavingState
    data class Error(val message: String) : SavingState
}