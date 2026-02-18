package com.base.deeplink;

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
public final class DeepLinkManager_Factory implements Factory<DeepLinkManager> {
  @Override
  public DeepLinkManager get() {
    return newInstance();
  }

  public static DeepLinkManager_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static DeepLinkManager newInstance() {
    return new DeepLinkManager();
  }

  private static final class InstanceHolder {
    private static final DeepLinkManager_Factory INSTANCE = new DeepLinkManager_Factory();
  }
}
