package com.base.data.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class FavoritesRepositoryImpl_Factory implements Factory<FavoritesRepositoryImpl> {
  @Override
  public FavoritesRepositoryImpl get() {
    return newInstance();
  }

  public static FavoritesRepositoryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FavoritesRepositoryImpl newInstance() {
    return new FavoritesRepositoryImpl();
  }

  private static final class InstanceHolder {
    private static final FavoritesRepositoryImpl_Factory INSTANCE = new FavoritesRepositoryImpl_Factory();
  }
}
