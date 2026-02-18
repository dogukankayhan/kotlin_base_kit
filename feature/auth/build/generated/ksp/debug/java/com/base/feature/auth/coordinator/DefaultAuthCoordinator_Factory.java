package com.base.feature.auth.coordinator;

import com.base.navigation.Navigator;
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
public final class DefaultAuthCoordinator_Factory implements Factory<DefaultAuthCoordinator> {
  private final Provider<Navigator> navigatorProvider;

  public DefaultAuthCoordinator_Factory(Provider<Navigator> navigatorProvider) {
    this.navigatorProvider = navigatorProvider;
  }

  @Override
  public DefaultAuthCoordinator get() {
    return newInstance(navigatorProvider.get());
  }

  public static DefaultAuthCoordinator_Factory create(Provider<Navigator> navigatorProvider) {
    return new DefaultAuthCoordinator_Factory(navigatorProvider);
  }

  public static DefaultAuthCoordinator newInstance(Navigator navigator) {
    return new DefaultAuthCoordinator(navigator);
  }
}
