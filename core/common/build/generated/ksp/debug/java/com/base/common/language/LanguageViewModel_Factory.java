package com.base.common.language;

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
public final class LanguageViewModel_Factory implements Factory<LanguageViewModel> {
  private final Provider<LanguageManager> languageManagerProvider;

  public LanguageViewModel_Factory(Provider<LanguageManager> languageManagerProvider) {
    this.languageManagerProvider = languageManagerProvider;
  }

  @Override
  public LanguageViewModel get() {
    return newInstance(languageManagerProvider.get());
  }

  public static LanguageViewModel_Factory create(
      Provider<LanguageManager> languageManagerProvider) {
    return new LanguageViewModel_Factory(languageManagerProvider);
  }

  public static LanguageViewModel newInstance(LanguageManager languageManager) {
    return new LanguageViewModel(languageManager);
  }
}
