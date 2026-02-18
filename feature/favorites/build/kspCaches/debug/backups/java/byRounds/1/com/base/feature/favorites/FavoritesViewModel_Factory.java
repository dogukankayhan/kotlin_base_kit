package com.base.feature.favorites;

import com.base.domain.repository.FavoritesRepository;
import com.base.feature.favorites.coordinator.FavoritesCoordinator;
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
public final class FavoritesViewModel_Factory implements Factory<FavoritesViewModel> {
  private final Provider<FavoritesRepository> favoritesRepositoryProvider;

  private final Provider<FavoritesCoordinator> coordinatorProvider;

  public FavoritesViewModel_Factory(Provider<FavoritesRepository> favoritesRepositoryProvider,
      Provider<FavoritesCoordinator> coordinatorProvider) {
    this.favoritesRepositoryProvider = favoritesRepositoryProvider;
    this.coordinatorProvider = coordinatorProvider;
  }

  @Override
  public FavoritesViewModel get() {
    return newInstance(favoritesRepositoryProvider.get(), coordinatorProvider.get());
  }

  public static FavoritesViewModel_Factory create(
      Provider<FavoritesRepository> favoritesRepositoryProvider,
      Provider<FavoritesCoordinator> coordinatorProvider) {
    return new FavoritesViewModel_Factory(favoritesRepositoryProvider, coordinatorProvider);
  }

  public static FavoritesViewModel newInstance(FavoritesRepository favoritesRepository,
      FavoritesCoordinator coordinator) {
    return new FavoritesViewModel(favoritesRepository, coordinator);
  }
}
