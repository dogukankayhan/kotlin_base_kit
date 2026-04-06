package com.base.common.language;

import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
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
public final class LanguageManager_Factory implements Factory<LanguageManager> {
  private final Provider<DataStore<Preferences>> dataStoreProvider;

  public LanguageManager_Factory(Provider<DataStore<Preferences>> dataStoreProvider) {
    this.dataStoreProvider = dataStoreProvider;
  }

  @Override
  public LanguageManager get() {
    return newInstance(dataStoreProvider.get());
  }

  public static LanguageManager_Factory create(Provider<DataStore<Preferences>> dataStoreProvider) {
    return new LanguageManager_Factory(dataStoreProvider);
  }

  public static LanguageManager newInstance(DataStore<Preferences> dataStore) {
    return new LanguageManager(dataStore);
  }
}
