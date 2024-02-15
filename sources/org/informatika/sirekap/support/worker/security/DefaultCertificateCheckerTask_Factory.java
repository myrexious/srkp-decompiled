package org.informatika.sirekap.support.worker.security;

import dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class DefaultCertificateCheckerTask_Factory implements Factory<DefaultCertificateCheckerTask> {
    @Override // javax.inject.Provider
    public DefaultCertificateCheckerTask get() {
        return newInstance();
    }

    public static DefaultCertificateCheckerTask_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DefaultCertificateCheckerTask newInstance() {
        return new DefaultCertificateCheckerTask();
    }

    /* loaded from: classes2.dex */
    private static final class InstanceHolder {
        private static final DefaultCertificateCheckerTask_Factory INSTANCE = new DefaultCertificateCheckerTask_Factory();

        private InstanceHolder() {
        }
    }
}
