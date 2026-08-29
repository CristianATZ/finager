package com.devtorres.onboarding

import androidx.lifecycle.ViewModel
import com.devtorres.domain.AppCurrency
import com.devtorres.domain.AppLanguage
import com.devtorres.domain.AppTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OnboardingVM @Inject constructor(): ViewModel() {

    private val _onboardingState: MutableStateFlow<OnboardingState> = MutableStateFlow(OnboardingState())
    val onboardingState: StateFlow<OnboardingState> = _onboardingState.asStateFlow()

    fun updateUsername(newUsername: String) {
        _onboardingState.update { it.copy(username = newUsername) }
    }

    fun updateCurrency(newCurrency: AppCurrency) {
        _onboardingState.update { it.copy(currency = newCurrency) }
    }

    fun updateLanguage(newLanguage: AppLanguage) {
        _onboardingState.update { it.copy(language = newLanguage) }
    }

    fun updateTheme(newTheme: AppTheme) {
        _onboardingState.update { it.copy(theme = newTheme) }
    }

    fun updateBiometrics(enabled: Boolean) {
        _onboardingState.update { it.copy(biometrics = enabled) }
    }
}