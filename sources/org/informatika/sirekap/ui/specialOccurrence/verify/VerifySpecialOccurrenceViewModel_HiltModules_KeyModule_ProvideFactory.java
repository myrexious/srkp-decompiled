package org.informatika.sirekap.ui.specialOccurrence.verify;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceViewModel_HiltModules;

/* loaded from: classes4.dex */
public final class VerifySpecialOccurrenceViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
    @Override // javax.inject.Provider
    public String get() {
        return provide();
    }

    public static VerifySpecialOccurrenceViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static String provide() {
        return (String) Preconditions.checkNotNullFromProvides(VerifySpecialOccurrenceViewModel_HiltModules.KeyModule.provide());
    }

    /* loaded from: classes4.dex */
    private static final class InstanceHolder {
        private static final VerifySpecialOccurrenceViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new VerifySpecialOccurrenceViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }
}
