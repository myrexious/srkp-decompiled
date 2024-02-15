package org.informatika.sirekap.repository;

import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.api.CertmanAPIInterface;
import org.informatika.sirekap.api.CertmanCertificateBucketAPIInterface;

/* loaded from: classes2.dex */
public final class DefaultCertmanRepository_Factory implements Factory<DefaultCertmanRepository> {
    private final Provider<AuthRepository> authRepositoryProvider;
    private final Provider<CertmanAPIInterface> certmanApiProvider;
    private final Provider<CertmanCertificateBucketAPIInterface> certmanBucketApiProvider;

    public DefaultCertmanRepository_Factory(Provider<CertmanAPIInterface> certmanApiProvider, Provider<CertmanCertificateBucketAPIInterface> certmanBucketApiProvider, Provider<AuthRepository> authRepositoryProvider) {
        this.certmanApiProvider = certmanApiProvider;
        this.certmanBucketApiProvider = certmanBucketApiProvider;
        this.authRepositoryProvider = authRepositoryProvider;
    }

    @Override // javax.inject.Provider
    public DefaultCertmanRepository get() {
        return newInstance(this.certmanApiProvider.get(), this.certmanBucketApiProvider.get(), this.authRepositoryProvider.get());
    }

    public static DefaultCertmanRepository_Factory create(Provider<CertmanAPIInterface> certmanApiProvider, Provider<CertmanCertificateBucketAPIInterface> certmanBucketApiProvider, Provider<AuthRepository> authRepositoryProvider) {
        return new DefaultCertmanRepository_Factory(certmanApiProvider, certmanBucketApiProvider, authRepositoryProvider);
    }

    public static DefaultCertmanRepository newInstance(CertmanAPIInterface certmanApi, CertmanCertificateBucketAPIInterface certmanBucketApi, AuthRepository authRepository) {
        return new DefaultCertmanRepository(certmanApi, certmanBucketApi, authRepository);
    }
}
