package com.base.network.authenticator;

import com.base.domain.repository.TokenRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class TokenAuthenticator_Factory implements Factory<TokenAuthenticator> {
  private final Provider<TokenRepository> tokenRepositoryProvider;

  public TokenAuthenticator_Factory(Provider<TokenRepository> tokenRepositoryProvider) {
    this.tokenRepositoryProvider = tokenRepositoryProvider;
  }

  @Override
  public TokenAuthenticator get() {
    return newInstance(tokenRepositoryProvider.get());
  }

  public static TokenAuthenticator_Factory create(
      Provider<TokenRepository> tokenRepositoryProvider) {
    return new TokenAuthenticator_Factory(tokenRepositoryProvider);
  }

  public static TokenAuthenticator newInstance(TokenRepository tokenRepository) {
    return new TokenAuthenticator(tokenRepository);
  }
}
