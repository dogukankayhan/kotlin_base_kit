package com.base.navigation;

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
public final class AppNavigator_Factory implements Factory<AppNavigator> {
  @Override
  public AppNavigator get() {
    return newInstance();
  }

  public static AppNavigator_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AppNavigator newInstance() {
    return new AppNavigator();
  }

  private static final class InstanceHolder {
    private static final AppNavigator_Factory INSTANCE = new AppNavigator_Factory();
  }
}
