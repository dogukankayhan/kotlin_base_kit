package com.base.network.di;

import android.content.Context;
import com.base.network.interceptor.ConnectivityInterceptor;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class NetworkModule_ProvideConnectivityInterceptorFactory implements Factory<ConnectivityInterceptor> {
  private final Provider<Context> contextProvider;

  public NetworkModule_ProvideConnectivityInterceptorFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ConnectivityInterceptor get() {
    return provideConnectivityInterceptor(contextProvider.get());
  }

  public static NetworkModule_ProvideConnectivityInterceptorFactory create(
      Provider<Context> contextProvider) {
    return new NetworkModule_ProvideConnectivityInterceptorFactory(contextProvider);
  }

  public static ConnectivityInterceptor provideConnectivityInterceptor(Context context) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideConnectivityInterceptor(context));
  }
}
