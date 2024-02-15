package org.informatika.sirekap.ui.witness.qrCode;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel_HiltModules;

/* loaded from: classes4.dex */
public final class WitnessUrlListViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
    @Override // javax.inject.Provider
    public String get() {
        return provide();
    }

    public static WitnessUrlListViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static String provide() {
        return (String) Preconditions.checkNotNullFromProvides(WitnessUrlListViewModel_HiltModules.KeyModule.provide());
    }

    /* loaded from: classes4.dex */
    private static final class InstanceHolder {
        private static final WitnessUrlListViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new WitnessUrlListViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }
}
