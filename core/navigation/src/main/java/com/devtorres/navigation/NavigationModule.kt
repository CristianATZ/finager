package com.devtorres.navigation

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class NavigationModule {

    @Binds
    abstract fun bindAppNavigator(impl: AppNavigatorImpl) : AppNavigator
}