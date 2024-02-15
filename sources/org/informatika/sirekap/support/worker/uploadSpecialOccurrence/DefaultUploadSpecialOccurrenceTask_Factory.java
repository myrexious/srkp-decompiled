package org.informatika.sirekap.support.worker.uploadSpecialOccurrence;

import dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class DefaultUploadSpecialOccurrenceTask_Factory implements Factory<DefaultUploadSpecialOccurrenceTask> {
    @Override // javax.inject.Provider
    public DefaultUploadSpecialOccurrenceTask get() {
        return newInstance();
    }

    public static DefaultUploadSpecialOccurrenceTask_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DefaultUploadSpecialOccurrenceTask newInstance() {
        return new DefaultUploadSpecialOccurrenceTask();
    }

    /* loaded from: classes2.dex */
    private static final class InstanceHolder {
        private static final DefaultUploadSpecialOccurrenceTask_Factory INSTANCE = new DefaultUploadSpecialOccurrenceTask_Factory();

        private InstanceHolder() {
        }
    }
}
