package com.base.data.manager;

import com.base.auth.AuthRepository;
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
public final class AuthManagerImpl_Factory implements Factory<AuthManagerImpl> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<TokenRepository> tokenRepositoryProvider;

  public AuthManagerImpl_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<TokenRepository> tokenRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.tokenRepositoryProvider = tokenRepositoryProvider;
  }

  @Override
  public AuthManagerImpl get() {
    return newInstance(authRepositoryProvider.get(), tokenRepositoryProvider.get());
  }

  public static AuthManagerImpl_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<TokenRepository> tokenRepositoryProvider) {
    return new AuthManagerImpl_Factory(authRepositoryProvider, tokenRepositoryProvider);
  }

  public static AuthManagerImpl newInstance(AuthRepository authRepository,
      TokenRepository tokenRepository) {
    return new AuthManagerImpl(authRepository, tokenRepository);
  }
}
