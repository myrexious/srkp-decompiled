package org.informatika.sirekap.ui.witness.register;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;
import dagger.multibindings.StringKey;

/* loaded from: classes4.dex */
public final class WitnessRegisterViewModel_HiltModules {
    private WitnessRegisterViewModel_HiltModules() {
    }

    @Module
    /* loaded from: classes4.dex */
    public static abstract class BindsModule {
        @Binds
        @IntoMap
        @StringKey("org.informatika.sirekap.ui.witness.register.WitnessRegisterViewModel")
        public abstract ViewModel binds(WitnessRegisterViewModel vm);

        private BindsModule() {
        }
    }

    @Module
    /* loaded from: classes4.dex */
    public static final class KeyModule {
        @Provides
        @IntoSet
        public static String provide() {
            return "org.informatika.sirekap.ui.witness.register.WitnessRegisterViewModel";
        }

        private KeyModule() {
        }
    }
}
