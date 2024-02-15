package org.informatika.sirekap.ui.autocapture;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes4.dex */
public final class AutoCaptureViewModel_Factory implements Factory<AutoCaptureViewModel> {
    private final Provider<Context> contextProvider;

    public AutoCaptureViewModel_Factory(Provider<Context> contextProvider) {
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public AutoCaptureViewModel get() {
        return newInstance(this.contextProvider.get());
    }

    public static AutoCaptureViewModel_Factory create(Provider<Context> contextProvider) {
        return new AutoCaptureViewModel_Factory(contextProvider);
    }

    public static AutoCaptureViewModel newInstance(Context context) {
        return new AutoCaptureViewModel(context);
    }
}
