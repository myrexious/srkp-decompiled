package org.informatika.sirekap.support.worker.zipElection;

import dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class DefaultZipElectionTask_Factory implements Factory<DefaultZipElectionTask> {
    @Override // javax.inject.Provider
    public DefaultZipElectionTask get() {
        return newInstance();
    }

    public static DefaultZipElectionTask_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DefaultZipElectionTask newInstance() {
        return new DefaultZipElectionTask();
    }

    /* loaded from: classes2.dex */
    private static final class InstanceHolder {
        private static final DefaultZipElectionTask_Factory INSTANCE = new DefaultZipElectionTask_Factory();

        private InstanceHolder() {
        }
    }
}
