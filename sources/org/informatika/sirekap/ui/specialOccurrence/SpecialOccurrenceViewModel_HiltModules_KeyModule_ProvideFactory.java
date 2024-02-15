package org.informatika.sirekap.ui.specialOccurrence;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel_HiltModules;

/* loaded from: classes4.dex */
public final class SpecialOccurrenceViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
    @Override // javax.inject.Provider
    public String get() {
        return provide();
    }

    public static SpecialOccurrenceViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static String provide() {
        return (String) Preconditions.checkNotNullFromProvides(SpecialOccurrenceViewModel_HiltModules.KeyModule.provide());
    }

    /* loaded from: classes4.dex */
    private static final class InstanceHolder {
        private static final SpecialOccurrenceViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new SpecialOccurrenceViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }
}
