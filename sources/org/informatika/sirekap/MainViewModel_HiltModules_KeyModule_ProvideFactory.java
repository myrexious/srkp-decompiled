package org.informatika.sirekap;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.informatika.sirekap.MainViewModel_HiltModules;

/* loaded from: classes2.dex */
public final class MainViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
    @Override // javax.inject.Provider
    public String get() {
        return provide();
    }

    public static MainViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static String provide() {
        return (String) Preconditions.checkNotNullFromProvides(MainViewModel_HiltModules.KeyModule.provide());
    }

    /* loaded from: classes2.dex */
    private static final class InstanceHolder {
        private static final MainViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new MainViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }
}
