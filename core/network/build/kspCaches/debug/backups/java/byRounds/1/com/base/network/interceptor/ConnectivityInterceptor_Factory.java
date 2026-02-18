package com.base.network.interceptor;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class ConnectivityInterceptor_Factory implements Factory<ConnectivityInterceptor> {
  private final Provider<Context> contextProvider;

  public ConnectivityInterceptor_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ConnectivityInterceptor get() {
    return newInstance(contextProvider.get());
  }

  public static ConnectivityInterceptor_Factory create(Provider<Context> contextProvider) {
    return new ConnectivityInterceptor_Factory(contextProvider);
  }

  public static ConnectivityInterceptor newInstance(Context context) {
    return new ConnectivityInterceptor(context);
  }
}
