package org.informatika.sirekap.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.Interceptor;
import org.informatika.sirekap.api.CertmanCertificateBucketAPIInterface;

/* loaded from: classes2.dex */
public final class ApiModule_ProvidesBucketCertificateApiInterfaceFactory implements Factory<CertmanCertificateBucketAPIInterface> {
    private final Provider<Interceptor> loggingInterceptorProvider;
    private final ApiModule module;

    public ApiModule_ProvidesBucketCertificateApiInterfaceFactory(ApiModule module, Provider<Interceptor> loggingInterceptorProvider) {
        this.module = module;
        this.loggingInterceptorProvider = loggingInterceptorProvider;
    }

    @Override // javax.inject.Provider
    public CertmanCertificateBucketAPIInterface get() {
        return providesBucketCertificateApiInterface(this.module, this.loggingInterceptorProvider.get());
    }

    public static ApiModule_ProvidesBucketCertificateApiInterfaceFactory create(ApiModule module, Provider<Interceptor> loggingInterceptorProvider) {
        return new ApiModule_ProvidesBucketCertificateApiInterfaceFactory(module, loggingInterceptorProvider);
    }

    public static CertmanCertificateBucketAPIInterface providesBucketCertificateApiInterface(ApiModule instance, Interceptor loggingInterceptor) {
        return (CertmanCertificateBucketAPIInterface) Preconditions.checkNotNullFromProvides(instance.providesBucketCertificateApiInterface(loggingInterceptor));
    }
}
