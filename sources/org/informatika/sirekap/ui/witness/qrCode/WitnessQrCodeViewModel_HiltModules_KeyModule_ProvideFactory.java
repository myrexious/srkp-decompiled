package org.informatika.sirekap.ui.witness.qrCode;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeViewModel_HiltModules;

/* loaded from: classes4.dex */
public final class WitnessQrCodeViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
    @Override // javax.inject.Provider
    public String get() {
        return provide();
    }

    public static WitnessQrCodeViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static String provide() {
        return (String) Preconditions.checkNotNullFromProvides(WitnessQrCodeViewModel_HiltModules.KeyModule.provide());
    }

    /* loaded from: classes4.dex */
    private static final class InstanceHolder {
        private static final WitnessQrCodeViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new WitnessQrCodeViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }
}
