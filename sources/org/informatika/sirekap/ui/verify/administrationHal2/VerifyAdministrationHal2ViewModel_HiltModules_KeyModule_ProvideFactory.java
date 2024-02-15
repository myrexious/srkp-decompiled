package org.informatika.sirekap.ui.verify.administrationHal2;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2ViewModel_HiltModules;

/* loaded from: classes4.dex */
public final class VerifyAdministrationHal2ViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
    @Override // javax.inject.Provider
    public String get() {
        return provide();
    }

    public static VerifyAdministrationHal2ViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static String provide() {
        return (String) Preconditions.checkNotNullFromProvides(VerifyAdministrationHal2ViewModel_HiltModules.KeyModule.provide());
    }

    /* loaded from: classes4.dex */
    private static final class InstanceHolder {
        private static final VerifyAdministrationHal2ViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new VerifyAdministrationHal2ViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }
}