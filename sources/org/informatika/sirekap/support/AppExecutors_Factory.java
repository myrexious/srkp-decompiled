package org.informatika.sirekap.support;

import dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class AppExecutors_Factory implements Factory<AppExecutors> {
    @Override // javax.inject.Provider
    public AppExecutors get() {
        return newInstance();
    }

    public static AppExecutors_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static AppExecutors newInstance() {
        return new AppExecutors();
    }

    /* loaded from: classes2.dex */
    private static final class InstanceHolder {
        private static final AppExecutors_Factory INSTANCE = new AppExecutors_Factory();

        private InstanceHolder() {
        }
    }
}
