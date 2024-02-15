package org.informatika.sirekap.ui.settings;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.informatika.sirekap.ui.settings.SettingsViewModel_HiltModules;

/* loaded from: classes4.dex */
public final class SettingsViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
    @Override // javax.inject.Provider
    public String get() {
        return provide();
    }

    public static SettingsViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static String provide() {
        return (String) Preconditions.checkNotNullFromProvides(SettingsViewModel_HiltModules.KeyModule.provide());
    }

    /* loaded from: classes4.dex */
    private static final class InstanceHolder {
        private static final SettingsViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new SettingsViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }
}