package com.base.feature.auth.di

import com.base.feature.auth.coordinator.AuthCoordinator
import com.base.feature.auth.coordinator.DefaultAuthCoordinator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthFeatureModule {

    @Binds
    @Singleton
    abstract fun bindAuthCoordinator(
        defaultAuthCoordinator: DefaultAuthCoordinator
    ): AuthCoordinator
}
