package com.base.network.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.Set;
import javax.annotation.processing.Generated;
import okhttp3.Interceptor;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("javax.inject.Named")
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
public final class NetworkModule_ProvideEmptyExtraInterceptorsFactory implements Factory<Set<Interceptor>> {
  @Override
  public Set<Interceptor> get() {
    return provideEmptyExtraInterceptors();
  }

  public static NetworkModule_ProvideEmptyExtraInterceptorsFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static Set<Interceptor> provideEmptyExtraInterceptors() {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideEmptyExtraInterceptors());
  }

  private static final class InstanceHolder {
    private static final NetworkModule_ProvideEmptyExtraInterceptorsFactory INSTANCE = new NetworkModule_ProvideEmptyExtraInterceptorsFactory();
  }
}
