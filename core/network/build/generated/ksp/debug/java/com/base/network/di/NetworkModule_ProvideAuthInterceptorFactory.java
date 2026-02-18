package com.base.network.di;

import com.base.domain.repository.TokenRepository;
import com.base.network.interceptor.AuthInterceptor;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
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
public final class NetworkModule_ProvideAuthInterceptorFactory implements Factory<AuthInterceptor> {
  private final Provider<TokenRepository> tokenRepositoryProvider;

  public NetworkModule_ProvideAuthInterceptorFactory(
      Provider<TokenRepository> tokenRepositoryProvider) {
    this.tokenRepositoryProvider = tokenRepositoryProvider;
  }

  @Override
  public AuthInterceptor get() {
    return provideAuthInterceptor(tokenRepositoryProvider.get());
  }

  public static NetworkModule_ProvideAuthInterceptorFactory create(
      Provider<TokenRepository> tokenRepositoryProvider) {
    return new NetworkModule_ProvideAuthInterceptorFactory(tokenRepositoryProvider);
  }

  public static AuthInterceptor provideAuthInterceptor(TokenRepository tokenRepository) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideAuthInterceptor(tokenRepository));
  }
}
