package com.base.config;

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
public final class AppConfigImpl_Factory implements Factory<AppConfigImpl> {
  @Override
  public AppConfigImpl get() {
    return newInstance();
  }

  public static AppConfigImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AppConfigImpl newInstance() {
    return new AppConfigImpl();
  }

  private static final class InstanceHolder {
    private static final AppConfigImpl_Factory INSTANCE = new AppConfigImpl_Factory();
  }
}
