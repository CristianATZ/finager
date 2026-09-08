package com.devtorres.onboarding.hilt

import com.devtorres.onboarding.state.OnboardingState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object OnboardingModule {

    @Provides
    fun provideInitialOnboardingState(): OnboardingState = OnboardingState()
}