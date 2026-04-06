package com.base.network.di;

import com.base.network.authenticator.TokenAuthenticator;
import com.base.network.interceptor.AuthInterceptor;
import com.base.network.interceptor.CacheInterceptor;
import com.base.network.interceptor.ConnectivityInterceptor;
import com.base.network.interceptor.RetryInterceptor;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.Set;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("javax.inject.Named")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class NetworkModule_ProvideOkHttpClientFactory implements Factory<OkHttpClient> {
  private final Provider<Cache> cacheProvider;

  private final Provider<HttpLoggingInterceptor> loggingInterceptorProvider;

  private final Provider<AuthInterceptor> authInterceptorProvider;

  private final Provider<ConnectivityInterceptor> connectivityInterceptorProvider;

  private final Provider<CacheInterceptor> cacheInterceptorProvider;

  private final Provider<RetryInterceptor> retryInterceptorProvider;

  private final Provider<TokenAuthenticator> tokenAuthenticatorProvider;

  private final Provider<String> apiKeyProvider;

  private final Provider<Set<Interceptor>> extraInterceptorsProvider;

  public NetworkModule_ProvideOkHttpClientFactory(Provider<Cache> cacheProvider,
      Provider<HttpLoggingInterceptor> loggingInterceptorProvider,
      Provider<AuthInterceptor> authInterceptorProvider,
      Provider<ConnectivityInterceptor> connectivityInterceptorProvider,
      Provider<CacheInterceptor> cacheInterceptorProvider,
      Provider<RetryInterceptor> retryInterceptorProvider,
      Provider<TokenAuthenticator> tokenAuthenticatorProvider, Provider<String> apiKeyProvider,
      Provider<Set<Interceptor>> extraInterceptorsProvider) {
    this.cacheProvider = cacheProvider;
    this.loggingInterceptorProvider = loggingInterceptorProvider;
    this.authInterceptorProvider = authInterceptorProvider;
    this.connectivityInterceptorProvider = connectivityInterceptorProvider;
    this.cacheInterceptorProvider = cacheInterceptorProvider;
    this.retryInterceptorProvider = retryInterceptorProvider;
    this.tokenAuthenticatorProvider = tokenAuthenticatorProvider;
    this.apiKeyProvider = apiKeyProvider;
    this.extraInterceptorsProvider = extraInterceptorsProvider;
  }

  @Override
  public OkHttpClient get() {
    return provideOkHttpClient(cacheProvider.get(), loggingInterceptorProvider.get(), authInterceptorProvider.get(), connectivityInterceptorProvider.get(), cacheInterceptorProvider.get(), retryInterceptorProvider.get(), tokenAuthenticatorProvider.get(), apiKeyProvider.get(), extraInterceptorsProvider.get());
  }

  public static NetworkModule_ProvideOkHttpClientFactory create(Provider<Cache> cacheProvider,
      Provider<HttpLoggingInterceptor> loggingInterceptorProvider,
      Provider<AuthInterceptor> authInterceptorProvider,
      Provider<ConnectivityInterceptor> connectivityInterceptorProvider,
      Provider<CacheInterceptor> cacheInterceptorProvider,
      Provider<RetryInterceptor> retryInterceptorProvider,
      Provider<TokenAuthenticator> tokenAuthenticatorProvider, Provider<String> apiKeyProvider,
      Provider<Set<Interceptor>> extraInterceptorsProvider) {
    return new NetworkModule_ProvideOkHttpClientFactory(cacheProvider, loggingInterceptorProvider, authInterceptorProvider, connectivityInterceptorProvider, cacheInterceptorProvider, retryInterceptorProvider, tokenAuthenticatorProvider, apiKeyProvider, extraInterceptorsProvider);
  }

  public static OkHttpClient provideOkHttpClient(Cache cache,
      HttpLoggingInterceptor loggingInterceptor, AuthInterceptor authInterceptor,
      ConnectivityInterceptor connectivityInterceptor, CacheInterceptor cacheInterceptor,
      RetryInterceptor retryInterceptor, TokenAuthenticator tokenAuthenticator, String apiKey,
      Set<Interceptor> extraInterceptors) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOkHttpClient(cache, loggingInterceptor, authInterceptor, connectivityInterceptor, cacheInterceptor, retryInterceptor, tokenAuthenticator, apiKey, extraInterceptors));
  }
}
