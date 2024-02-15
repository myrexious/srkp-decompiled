package org.informatika.sirekap.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import okhttp3.Interceptor;

/* loaded from: classes2.dex */
public final class ApiModule_ProvidesOkHttpAuthInterceptorFactory implements Factory<Interceptor> {
    private final ApiModule module;

    public ApiModule_ProvidesOkHttpAuthInterceptorFactory(ApiModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public Interceptor get() {
        return providesOkHttpAuthInterceptor(this.module);
    }

    public static ApiModule_ProvidesOkHttpAuthInterceptorFactory create(ApiModule module) {
        return new ApiModule_ProvidesOkHttpAuthInterceptorFactory(module);
    }

    public static Interceptor providesOkHttpAuthInterceptor(ApiModule instance) {
        return (Interceptor) Preconditions.checkNotNullFromProvides(instance.providesOkHttpAuthInterceptor());
    }
}
