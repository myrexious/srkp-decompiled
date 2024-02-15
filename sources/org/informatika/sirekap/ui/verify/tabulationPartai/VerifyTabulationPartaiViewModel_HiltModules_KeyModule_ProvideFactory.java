package org.informatika.sirekap.ui.verify.tabulationPartai;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel_HiltModules;

/* loaded from: classes4.dex */
public final class VerifyTabulationPartaiViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
    @Override // javax.inject.Provider
    public String get() {
        return provide();
    }

    public static VerifyTabulationPartaiViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static String provide() {
        return (String) Preconditions.checkNotNullFromProvides(VerifyTabulationPartaiViewModel_HiltModules.KeyModule.provide());
    }

    /* loaded from: classes4.dex */
    private static final class InstanceHolder {
        private static final VerifyTabulationPartaiViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new VerifyTabulationPartaiViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }
}