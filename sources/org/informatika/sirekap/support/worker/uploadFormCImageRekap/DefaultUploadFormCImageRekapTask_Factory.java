package org.informatika.sirekap.support.worker.uploadFormCImageRekap;

import dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class DefaultUploadFormCImageRekapTask_Factory implements Factory<DefaultUploadFormCImageRekapTask> {
    @Override // javax.inject.Provider
    public DefaultUploadFormCImageRekapTask get() {
        return newInstance();
    }

    public static DefaultUploadFormCImageRekapTask_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DefaultUploadFormCImageRekapTask newInstance() {
        return new DefaultUploadFormCImageRekapTask();
    }

    /* loaded from: classes2.dex */
    private static final class InstanceHolder {
        private static final DefaultUploadFormCImageRekapTask_Factory INSTANCE = new DefaultUploadFormCImageRekapTask_Factory();

        private InstanceHolder() {
        }
    }
}
