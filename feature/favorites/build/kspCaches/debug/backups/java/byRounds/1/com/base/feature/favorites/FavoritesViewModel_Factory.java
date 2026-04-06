package com.base.feature.favorites;

import com.base.domain.usecase.AddFavoriteUseCase;
import com.base.domain.usecase.GetFavoritesUseCase;
import com.base.domain.usecase.RemoveFavoriteUseCase;
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
  private final Provider<GetFavoritesUseCase> getFavoritesUseCaseProvider;

  private final Provider<AddFavoriteUseCase> addFavoriteUseCaseProvider;

  private final Provider<RemoveFavoriteUseCase> removeFavoriteUseCaseProvider;

  public FavoritesViewModel_Factory(Provider<GetFavoritesUseCase> getFavoritesUseCaseProvider,
      Provider<AddFavoriteUseCase> addFavoriteUseCaseProvider,
      Provider<RemoveFavoriteUseCase> removeFavoriteUseCaseProvider) {
    this.getFavoritesUseCaseProvider = getFavoritesUseCaseProvider;
    this.addFavoriteUseCaseProvider = addFavoriteUseCaseProvider;
    this.removeFavoriteUseCaseProvider = removeFavoriteUseCaseProvider;
  }

  @Override
  public FavoritesViewModel get() {
    return newInstance(getFavoritesUseCaseProvider.get(), addFavoriteUseCaseProvider.get(), removeFavoriteUseCaseProvider.get());
  }

  public static FavoritesViewModel_Factory create(
      Provider<GetFavoritesUseCase> getFavoritesUseCaseProvider,
      Provider<AddFavoriteUseCase> addFavoriteUseCaseProvider,
      Provider<RemoveFavoriteUseCase> removeFavoriteUseCaseProvider) {
    return new FavoritesViewModel_Factory(getFavoritesUseCaseProvider, addFavoriteUseCaseProvider, removeFavoriteUseCaseProvider);
  }

  public static FavoritesViewModel newInstance(GetFavoritesUseCase getFavoritesUseCase,
      AddFavoriteUseCase addFavoriteUseCase, RemoveFavoriteUseCase removeFavoriteUseCase) {
    return new FavoritesViewModel(getFavoritesUseCase, addFavoriteUseCase, removeFavoriteUseCase);
  }
}
