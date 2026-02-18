package com.base.network.di;

import com.base.domain.repository.TokenRepository;
import com.base.network.authenticator.TokenAuthenticator;
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
public final class NetworkModule_ProvideTokenAuthenticatorFactory implements Factory<TokenAuthenticator> {
  private final Provider<TokenRepository> tokenRepositoryProvider;

  public NetworkModule_ProvideTokenAuthenticatorFactory(
      Provider<TokenRepository> tokenRepositoryProvider) {
    this.tokenRepositoryProvider = tokenRepositoryProvider;
  }

  @Override
  public TokenAuthenticator get() {
    return provideTokenAuthenticator(tokenRepositoryProvider.get());
  }

  public static NetworkModule_ProvideTokenAuthenticatorFactory create(
      Provider<TokenRepository> tokenRepositoryProvider) {
    return new NetworkModule_ProvideTokenAuthenticatorFactory(tokenRepositoryProvider);
  }

  public static TokenAuthenticator provideTokenAuthenticator(TokenRepository tokenRepository) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideTokenAuthenticator(tokenRepository));
  }
}
