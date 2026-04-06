package com.base.network.di

import android.content.Context
import com.base.domain.repository.TokenRepository
import com.base.network.api.AuthApi
import com.base.network.api.MovieApi
import com.base.network.authenticator.TokenAuthenticator
import com.base.network.interceptor.AuthInterceptor
import com.base.network.interceptor.CacheInterceptor
import com.base.network.interceptor.ConnectivityInterceptor
import com.base.network.interceptor.RetryInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
@Suppress("TooManyFunctions")
object NetworkModule {

    private const val CACHE_SIZE = 10 * 1024 * 1024L // 10 MB Cache
    private const val TIMEOUT_SECONDS = 30L

    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context): Cache {
        val cacheDir = File(context.cacheDir, "http-cache")
        return Cache(cacheDir, CACHE_SIZE)
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(tokenRepository: TokenRepository): AuthInterceptor = AuthInterceptor(tokenRepository)

    @Provides
    @Singleton
    fun provideConnectivityInterceptor(@ApplicationContext context: Context): ConnectivityInterceptor =
        ConnectivityInterceptor(context)

    @Provides
    @Singleton
    fun provideCacheInterceptor(): CacheInterceptor = CacheInterceptor()

    @Provides
    @Singleton
    fun provideRetryInterceptor(): RetryInterceptor = RetryInterceptor()

    @Provides
    @Singleton
    fun provideTokenAuthenticator(tokenRepository: TokenRepository): TokenAuthenticator {
        return TokenAuthenticator(tokenRepository)
    }
    
    @Provides
    @Singleton
    @Named("TmdbAuthInterceptor")
    fun provideTmdbAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                // Replace this line inside app/core implementation with proper buildconfig if needed. Assuming BuildConfig.API_KEY is available in app module
                // We'll use the API_KEY provided by dagger instead to avoid cross-module BuildConfig dependencies.
                .build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    @Suppress("LongParameterList")
    fun provideOkHttpClient(
        cache: Cache,
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        connectivityInterceptor: ConnectivityInterceptor,
        cacheInterceptor: CacheInterceptor,
        retryInterceptor: RetryInterceptor,
        tokenAuthenticator: TokenAuthenticator,
        @Named("ApiKey") apiKey: String,
        @Named("ExtraInterceptors") extraInterceptors: Set<@JvmSuppressWildcards Interceptor>
    ): OkHttpClient {
        val tmdbInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            
            val isV4Token = apiKey.length > 50
            
            val url = if (isV4Token) {
                originalRequest.url
            } else {
                originalRequest.url.newBuilder()
                    .addQueryParameter("api_key", apiKey)
                    .build()
            }

            val requestBuilder = originalRequest.newBuilder()
                .url(url)
                .addHeader("accept", "application/json")
                
            if (isV4Token) {
                requestBuilder.addHeader("Authorization", "Bearer $apiKey")
            }

            chain.proceed(requestBuilder.build())
        }
        
        val builder = OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(tmdbInterceptor)
            .addInterceptor(connectivityInterceptor)
            // .addInterceptor(authInterceptor) // Commented as we'll use TMDB Bearer
            .addInterceptor(retryInterceptor)
            .addNetworkInterceptor(cacheInterceptor)
            // .authenticator(tokenAuthenticator) 
            .addInterceptor(loggingInterceptor)

        extraInterceptors.forEach { builder.addInterceptor(it) }

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        @Named("BaseUrl") baseUrl: String,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    @Named("ExtraInterceptors")
    fun provideEmptyExtraInterceptors(): Set<@JvmSuppressWildcards Interceptor> {
        return emptySet()
    }
}
