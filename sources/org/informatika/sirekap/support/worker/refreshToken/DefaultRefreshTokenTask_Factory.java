package org.informatika.sirekap.support.worker.refreshToken;

import dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class DefaultRefreshTokenTask_Factory implements Factory<DefaultRefreshTokenTask> {
    @Override // javax.inject.Provider
    public DefaultRefreshTokenTask get() {
        return newInstance();
    }

    public static DefaultRefreshTokenTask_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DefaultRefreshTokenTask newInstance() {
        return new DefaultRefreshTokenTask();
    }

    /* loaded from: classes2.dex */
    private static final class InstanceHolder {
        private static final DefaultRefreshTokenTask_Factory INSTANCE = new DefaultRefreshTokenTask_Factory();

        private InstanceHolder() {
        }
    }
}
