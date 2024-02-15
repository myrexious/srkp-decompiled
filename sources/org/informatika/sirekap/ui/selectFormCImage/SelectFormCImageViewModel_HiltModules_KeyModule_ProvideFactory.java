package org.informatika.sirekap.ui.selectFormCImage;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageViewModel_HiltModules;

/* loaded from: classes4.dex */
public final class SelectFormCImageViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
    @Override // javax.inject.Provider
    public String get() {
        return provide();
    }

    public static SelectFormCImageViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static String provide() {
        return (String) Preconditions.checkNotNullFromProvides(SelectFormCImageViewModel_HiltModules.KeyModule.provide());
    }

    /* loaded from: classes4.dex */
    private static final class InstanceHolder {
        private static final SelectFormCImageViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new SelectFormCImageViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }
}
