package com.devtorres.onboarding.hilt

import com.devtorres.navigation.AppNavigator
import com.devtorres.navigation.EntryProviderInstaller
import com.devtorres.onboarding.navigation.onboardingEntryBuilder
import com.devtorres.onboarding.state.OnboardingState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
object OnboardingModule {

    @IntoSet
    @Provides
    fun provideEntryProviderInstaller(appNavigator: AppNavigator): EntryProviderInstaller = {
        onboardingEntryBuilder(appNavigator)
    }

    @Provides
    fun provideInitialOnboardingState(): OnboardingState = OnboardingState()
}