package org.informatika.sirekap.support.worker.zipSpecialOccurrence;

import dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class DefaultZipSpecialOccurrenceTask_Factory implements Factory<DefaultZipSpecialOccurrenceTask> {
    @Override // javax.inject.Provider
    public DefaultZipSpecialOccurrenceTask get() {
        return newInstance();
    }

    public static DefaultZipSpecialOccurrenceTask_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DefaultZipSpecialOccurrenceTask newInstance() {
        return new DefaultZipSpecialOccurrenceTask();
    }

    /* loaded from: classes2.dex */
    private static final class InstanceHolder {
        private static final DefaultZipSpecialOccurrenceTask_Factory INSTANCE = new DefaultZipSpecialOccurrenceTask_Factory();

        private InstanceHolder() {
        }
    }
}
