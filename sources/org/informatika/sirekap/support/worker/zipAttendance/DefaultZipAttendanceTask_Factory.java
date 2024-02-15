package org.informatika.sirekap.support.worker.zipAttendance;

import dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class DefaultZipAttendanceTask_Factory implements Factory<DefaultZipAttendanceTask> {
    @Override // javax.inject.Provider
    public DefaultZipAttendanceTask get() {
        return newInstance();
    }

    public static DefaultZipAttendanceTask_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DefaultZipAttendanceTask newInstance() {
        return new DefaultZipAttendanceTask();
    }

    /* loaded from: classes2.dex */
    private static final class InstanceHolder {
        private static final DefaultZipAttendanceTask_Factory INSTANCE = new DefaultZipAttendanceTask_Factory();

        private InstanceHolder() {
        }
    }
}
