package org.informatika.sirekap.ui.imageHistory;

import dagger.internal.Factory;

/* loaded from: classes4.dex */
public final class ImageHistoryViewModel_Factory implements Factory<ImageHistoryViewModel> {
    @Override // javax.inject.Provider
    public ImageHistoryViewModel get() {
        return newInstance();
    }

    public static ImageHistoryViewModel_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static ImageHistoryViewModel newInstance() {
        return new ImageHistoryViewModel();
    }

    /* loaded from: classes4.dex */
    private static final class InstanceHolder {
        private static final ImageHistoryViewModel_Factory INSTANCE = new ImageHistoryViewModel_Factory();

        private InstanceHolder() {
        }
    }
}
