package org.informatika.sirekap.ui.witness.register;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.informatika.sirekap.ui.witness.register.WitnessRegisterViewModel_HiltModules;

/* loaded from: classes4.dex */
public final class WitnessRegisterViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
    @Override // javax.inject.Provider
    public String get() {
        return provide();
    }

    public static WitnessRegisterViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static String provide() {
        return (String) Preconditions.checkNotNullFromProvides(WitnessRegisterViewModel_HiltModules.KeyModule.provide());
    }

    /* loaded from: classes4.dex */
    private static final class InstanceHolder {
        private static final WitnessRegisterViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new WitnessRegisterViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }
}
