package org.informatika.sirekap.support.worker.uploadFormCImage;

import dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class FakeUploadFormCImageTask_Factory implements Factory<FakeUploadFormCImageTask> {
    @Override // javax.inject.Provider
    public FakeUploadFormCImageTask get() {
        return newInstance();
    }

    public static FakeUploadFormCImageTask_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static FakeUploadFormCImageTask newInstance() {
        return new FakeUploadFormCImageTask();
    }

    /* loaded from: classes2.dex */
    private static final class InstanceHolder {
        private static final FakeUploadFormCImageTask_Factory INSTANCE = new FakeUploadFormCImageTask_Factory();

        private InstanceHolder() {
        }
    }
}
