package org.informatika.sirekap.ui.verify.tabulation;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationViewModel_HiltModules;

/* loaded from: classes4.dex */
public final class VerifyTabulationViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
    @Override // javax.inject.Provider
    public String get() {
        return provide();
    }

    public static VerifyTabulationViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static String provide() {
        return (String) Preconditions.checkNotNullFromProvides(VerifyTabulationViewModel_HiltModules.KeyModule.provide());
    }

    /* loaded from: classes4.dex */
    private static final class InstanceHolder {
        private static final VerifyTabulationViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new VerifyTabulationViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }
}
