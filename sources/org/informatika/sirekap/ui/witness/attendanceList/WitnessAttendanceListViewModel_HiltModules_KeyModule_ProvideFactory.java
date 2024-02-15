package org.informatika.sirekap.ui.witness.attendanceList;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListViewModel_HiltModules;

/* loaded from: classes4.dex */
public final class WitnessAttendanceListViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
    @Override // javax.inject.Provider
    public String get() {
        return provide();
    }

    public static WitnessAttendanceListViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static String provide() {
        return (String) Preconditions.checkNotNullFromProvides(WitnessAttendanceListViewModel_HiltModules.KeyModule.provide());
    }

    /* loaded from: classes4.dex */
    private static final class InstanceHolder {
        private static final WitnessAttendanceListViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new WitnessAttendanceListViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }
}
