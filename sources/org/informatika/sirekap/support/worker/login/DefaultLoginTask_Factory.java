package org.informatika.sirekap.support.worker.login;

import dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class DefaultLoginTask_Factory implements Factory<DefaultLoginTask> {
    @Override // javax.inject.Provider
    public DefaultLoginTask get() {
        return newInstance();
    }

    public static DefaultLoginTask_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DefaultLoginTask newInstance() {
        return new DefaultLoginTask();
    }

    /* loaded from: classes2.dex */
    private static final class InstanceHolder {
        private static final DefaultLoginTask_Factory INSTANCE = new DefaultLoginTask_Factory();

        private InstanceHolder() {
        }
    }
}
