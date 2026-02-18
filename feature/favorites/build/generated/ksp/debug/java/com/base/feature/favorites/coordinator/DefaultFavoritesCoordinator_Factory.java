package com.base.feature.favorites.coordinator;

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
public final class DefaultFavoritesCoordinator_Factory implements Factory<DefaultFavoritesCoordinator> {
  private final Provider<Navigator> navigatorProvider;

  public DefaultFavoritesCoordinator_Factory(Provider<Navigator> navigatorProvider) {
    this.navigatorProvider = navigatorProvider;
  }

  @Override
  public DefaultFavoritesCoordinator get() {
    return newInstance(navigatorProvider.get());
  }

  public static DefaultFavoritesCoordinator_Factory create(Provider<Navigator> navigatorProvider) {
    return new DefaultFavoritesCoordinator_Factory(navigatorProvider);
  }

  public static DefaultFavoritesCoordinator newInstance(Navigator navigator) {
    return new DefaultFavoritesCoordinator(navigator);
  }
}
