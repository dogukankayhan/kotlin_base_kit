package com.example.kotlinbasekit.di

import com.example.kotlinbasekit.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl(): String = BuildConfig.BASE_URL
}
