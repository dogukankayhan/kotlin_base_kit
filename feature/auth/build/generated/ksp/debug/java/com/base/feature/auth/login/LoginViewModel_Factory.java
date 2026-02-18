package com.base.feature.auth.login;

import com.base.auth.AuthManager;
import com.base.feature.auth.coordinator.AuthCoordinator;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class LoginViewModel_Factory implements Factory<LoginViewModel> {
  private final Provider<AuthManager> authManagerProvider;

  private final Provider<AuthCoordinator> coordinatorProvider;

  public LoginViewModel_Factory(Provider<AuthManager> authManagerProvider,
      Provider<AuthCoordinator> coordinatorProvider) {
    this.authManagerProvider = authManagerProvider;
    this.coordinatorProvider = coordinatorProvider;
  }

  @Override
  public LoginViewModel get() {
    return newInstance(authManagerProvider.get(), coordinatorProvider.get());
  }

  public static LoginViewModel_Factory create(Provider<AuthManager> authManagerProvider,
      Provider<AuthCoordinator> coordinatorProvider) {
    return new LoginViewModel_Factory(authManagerProvider, coordinatorProvider);
  }

  public static LoginViewModel newInstance(AuthManager authManager, AuthCoordinator coordinator) {
    return new LoginViewModel(authManager, coordinator);
  }
}
