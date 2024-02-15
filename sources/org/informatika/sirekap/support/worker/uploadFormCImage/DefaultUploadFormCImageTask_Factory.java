package org.informatika.sirekap.support.worker.uploadFormCImage;

import dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class DefaultUploadFormCImageTask_Factory implements Factory<DefaultUploadFormCImageTask> {
    @Override // javax.inject.Provider
    public DefaultUploadFormCImageTask get() {
        return newInstance();
    }

    public static DefaultUploadFormCImageTask_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DefaultUploadFormCImageTask newInstance() {
        return new DefaultUploadFormCImageTask();
    }

    /* loaded from: classes2.dex */
    private static final class InstanceHolder {
        private static final DefaultUploadFormCImageTask_Factory INSTANCE = new DefaultUploadFormCImageTask_Factory();

        private InstanceHolder() {
        }
    }
}
