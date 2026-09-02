package com.devtorres.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devtorres.domain.AppCurrency
import com.devtorres.domain.AppLanguage
import com.devtorres.domain.AppTheme
import com.devtorres.onboarding.state.OnboardingEffect
import com.devtorres.onboarding.state.OnboardingEvent
import com.devtorres.common.states.SavingState
import com.devtorres.onboarding.state.OnboardingState
import com.devtorres.onboarding.state.OnboardingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingVM @Inject constructor(
    initialState: OnboardingState
): ViewModel() {

    private val _uiState: MutableStateFlow<OnboardingUiState> = MutableStateFlow(OnboardingUiState(uiState = initialState))
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()

    private val _uiEffect: MutableSharedFlow<OnboardingEffect> = MutableSharedFlow(extraBufferCapacity = 1)
    val uiEffect: SharedFlow<OnboardingEffect> = _uiEffect.asSharedFlow()

    fun onEvent(event: OnboardingEvent) {
        when(event) {
            is OnboardingEvent.OnUsernameChanged -> updateUsername(event.value)
            is OnboardingEvent.OnCurrencySelected -> updateCurrency(event.value)
            is OnboardingEvent.OnLanguageSelected -> updateLanguage(event.value)
            is OnboardingEvent.OnThemeSelected -> updateTheme(event.value)
            is OnboardingEvent.OnBiometricsEnabled -> updateBiometrics(event.value)
            OnboardingEvent.OnBackClicked -> emitEffect(OnboardingEffect.NavigateBackward)
            OnboardingEvent.OnNextClicked -> emitEffect(OnboardingEffect.NavigateForward)
            OnboardingEvent.OnFinish -> showSavingOverlay()
            OnboardingEvent.OnNavigateToHome -> emitEffect(OnboardingEffect.NavigateToHome)
        }
    }

    private fun emitEffect(effect: OnboardingEffect) {
        viewModelScope.launch { _uiEffect.emit(effect) }
    }

    private fun showSavingOverlay() {
        viewModelScope.launch {
            _uiState.update { it.copy(savingState = SavingState.Loading) }

            delay(1_000L)

            _uiState.update { it.copy(savingState = SavingState.Completed) }
        }
    }

    private fun updateUsername(newUsername: String) {
        _uiState.update { it.copy(uiState = it.uiState.copy(username = newUsername)) }
    }

    private fun updateCurrency(newCurrency: AppCurrency) {
        _uiState.update { it.copy(uiState = it.uiState.copy(currency = newCurrency)) }
    }

    private fun updateLanguage(newLanguage: AppLanguage) {
        _uiState.update { it.copy(uiState = it.uiState.copy(language = newLanguage)) }
    }

    private fun updateTheme(newTheme: AppTheme) {
        _uiState.update { it.copy(uiState = it.uiState.copy(theme = newTheme)) }
    }

    private fun updateBiometrics(enabled: Boolean) {
        _uiState.update { it.copy(uiState = it.uiState.copy(biometrics = enabled)) }
    }
}