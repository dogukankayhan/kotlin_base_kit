package com.base.network.di;

import com.base.network.interceptor.CacheInterceptor;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class NetworkModule_ProvideCacheInterceptorFactory implements Factory<CacheInterceptor> {
  @Override
  public CacheInterceptor get() {
    return provideCacheInterceptor();
  }

  public static NetworkModule_ProvideCacheInterceptorFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CacheInterceptor provideCacheInterceptor() {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideCacheInterceptor());
  }

  private static final class InstanceHolder {
    private static final NetworkModule_ProvideCacheInterceptorFactory INSTANCE = new NetworkModule_ProvideCacheInterceptorFactory();
  }
}
