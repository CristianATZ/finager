package com.devtorres.home.hilt

import com.devtorres.home.navigation.homeEntryBuilder
import com.devtorres.navigation.AppNavigator
import com.devtorres.navigation.EntryProviderInstaller
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
object HomeModule {

    @IntoSet
    @Provides
    fun provideEntryProviderInstaller(appNavigator: AppNavigator): EntryProviderInstaller = {
        homeEntryBuilder(appNavigator)
    }
}