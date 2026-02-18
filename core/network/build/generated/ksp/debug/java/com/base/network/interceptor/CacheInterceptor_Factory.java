package com.base.network.interceptor;

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
public final class CacheInterceptor_Factory implements Factory<CacheInterceptor> {
  @Override
  public CacheInterceptor get() {
    return newInstance();
  }

  public static CacheInterceptor_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CacheInterceptor newInstance() {
    return new CacheInterceptor();
  }

  private static final class InstanceHolder {
    private static final CacheInterceptor_Factory INSTANCE = new CacheInterceptor_Factory();
  }
}
