package com.base.data.repository;

import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
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
public final class TokenRepositoryImpl_Factory implements Factory<TokenRepositoryImpl> {
  private final Provider<DataStore<Preferences>> dataStoreProvider;

  public TokenRepositoryImpl_Factory(Provider<DataStore<Preferences>> dataStoreProvider) {
    this.dataStoreProvider = dataStoreProvider;
  }

  @Override
  public TokenRepositoryImpl get() {
    return newInstance(dataStoreProvider.get());
  }

  public static TokenRepositoryImpl_Factory create(
      Provider<DataStore<Preferences>> dataStoreProvider) {
    return new TokenRepositoryImpl_Factory(dataStoreProvider);
  }

  public static TokenRepositoryImpl newInstance(DataStore<Preferences> dataStore) {
    return new TokenRepositoryImpl(dataStore);
  }
}
