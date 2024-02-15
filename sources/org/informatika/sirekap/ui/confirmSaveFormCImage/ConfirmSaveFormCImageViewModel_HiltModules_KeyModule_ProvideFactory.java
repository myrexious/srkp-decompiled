package org.informatika.sirekap.ui.confirmSaveFormCImage;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageViewModel_HiltModules;

/* loaded from: classes4.dex */
public final class ConfirmSaveFormCImageViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
    @Override // javax.inject.Provider
    public String get() {
        return provide();
    }

    public static ConfirmSaveFormCImageViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static String provide() {
        return (String) Preconditions.checkNotNullFromProvides(ConfirmSaveFormCImageViewModel_HiltModules.KeyModule.provide());
    }

    /* loaded from: classes4.dex */
    private static final class InstanceHolder {
        private static final ConfirmSaveFormCImageViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new ConfirmSaveFormCImageViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }
}
