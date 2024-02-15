package org.informatika.sirekap.ui.aprilTagConflict;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.informatika.sirekap.ui.aprilTagConflict.AprilTagConflictViewModel_HiltModules;

/* loaded from: classes4.dex */
public final class AprilTagConflictViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
    @Override // javax.inject.Provider
    public String get() {
        return provide();
    }

    public static AprilTagConflictViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static String provide() {
        return (String) Preconditions.checkNotNullFromProvides(AprilTagConflictViewModel_HiltModules.KeyModule.provide());
    }

    /* loaded from: classes4.dex */
    private static final class InstanceHolder {
        private static final AprilTagConflictViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new AprilTagConflictViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }
}
