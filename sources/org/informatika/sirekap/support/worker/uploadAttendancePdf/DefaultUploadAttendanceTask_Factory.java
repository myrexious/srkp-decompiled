package org.informatika.sirekap.support.worker.uploadAttendancePdf;

import dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class DefaultUploadAttendanceTask_Factory implements Factory<DefaultUploadAttendanceTask> {
    @Override // javax.inject.Provider
    public DefaultUploadAttendanceTask get() {
        return newInstance();
    }

    public static DefaultUploadAttendanceTask_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DefaultUploadAttendanceTask newInstance() {
        return new DefaultUploadAttendanceTask();
    }

    /* loaded from: classes2.dex */
    private static final class InstanceHolder {
        private static final DefaultUploadAttendanceTask_Factory INSTANCE = new DefaultUploadAttendanceTask_Factory();

        private InstanceHolder() {
        }
    }
}
