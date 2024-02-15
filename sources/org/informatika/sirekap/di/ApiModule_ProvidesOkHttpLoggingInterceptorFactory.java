package org.informatika.sirekap.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import okhttp3.Interceptor;

/* loaded from: classes2.dex */
public final class ApiModule_ProvidesOkHttpLoggingInterceptorFactory implements Factory<Interceptor> {
    private final ApiModule module;

    public ApiModule_ProvidesOkHttpLoggingInterceptorFactory(ApiModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public Interceptor get() {
        return providesOkHttpLoggingInterceptor(this.module);
    }

    public static ApiModule_ProvidesOkHttpLoggingInterceptorFactory create(ApiModule module) {
        return new ApiModule_ProvidesOkHttpLoggingInterceptorFactory(module);
    }

    public static Interceptor providesOkHttpLoggingInterceptor(ApiModule instance) {
        return (Interceptor) Preconditions.checkNotNullFromProvides(instance.providesOkHttpLoggingInterceptor());
    }
}
