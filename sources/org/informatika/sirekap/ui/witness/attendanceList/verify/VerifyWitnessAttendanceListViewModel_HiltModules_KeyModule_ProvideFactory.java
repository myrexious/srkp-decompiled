package org.informatika.sirekap.ui.witness.attendanceList.verify;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListViewModel_HiltModules;

/* loaded from: classes4.dex */
public final class VerifyWitnessAttendanceListViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
    @Override // javax.inject.Provider
    public String get() {
        return provide();
    }

    public static VerifyWitnessAttendanceListViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static String provide() {
        return (String) Preconditions.checkNotNullFromProvides(VerifyWitnessAttendanceListViewModel_HiltModules.KeyModule.provide());
    }

    /* loaded from: classes4.dex */
    private static final class InstanceHolder {
        private static final VerifyWitnessAttendanceListViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new VerifyWitnessAttendanceListViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }
}
