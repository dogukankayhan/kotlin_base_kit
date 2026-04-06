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

    @Provides
    @Named("ApiKey")
    fun provideApiKey(): String = BuildConfig.API_KEY

    @Provides
    @Named("ImageBaseUrl")
    fun provideImageBaseUrl(): String = BuildConfig.IMAGE_BASE_URL
}
