package com.base.feature.favorites.di

import com.base.feature.favorites.coordinator.DefaultFavoritesCoordinator
import com.base.feature.favorites.coordinator.FavoritesCoordinator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FavoritesFeatureModule {

    @Binds
    @Singleton
    abstract fun bindFavoritesCoordinator(
        defaultFavoritesCoordinator: DefaultFavoritesCoordinator
    ): FavoritesCoordinator
}
