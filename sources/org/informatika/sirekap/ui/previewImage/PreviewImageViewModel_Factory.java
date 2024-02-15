package org.informatika.sirekap.ui.previewImage;

import dagger.internal.Factory;

/* loaded from: classes4.dex */
public final class PreviewImageViewModel_Factory implements Factory<PreviewImageViewModel> {
    @Override // javax.inject.Provider
    public PreviewImageViewModel get() {
        return newInstance();
    }

    public static PreviewImageViewModel_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static PreviewImageViewModel newInstance() {
        return new PreviewImageViewModel();
    }

    /* loaded from: classes4.dex */
    private static final class InstanceHolder {
        private static final PreviewImageViewModel_Factory INSTANCE = new PreviewImageViewModel_Factory();

        private InstanceHolder() {
        }
    }
}
