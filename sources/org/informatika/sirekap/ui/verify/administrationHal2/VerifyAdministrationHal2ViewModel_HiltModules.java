package org.informatika.sirekap.ui.verify.administrationHal2;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;
import dagger.multibindings.StringKey;

/* loaded from: classes4.dex */
public final class VerifyAdministrationHal2ViewModel_HiltModules {
    private VerifyAdministrationHal2ViewModel_HiltModules() {
    }

    @Module
    /* loaded from: classes4.dex */
    public static abstract class BindsModule {
        @Binds
        @IntoMap
        @StringKey("org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2ViewModel")
        public abstract ViewModel binds(VerifyAdministrationHal2ViewModel vm);

        private BindsModule() {
        }
    }

    @Module
    /* loaded from: classes4.dex */
    public static final class KeyModule {
        @Provides
        @IntoSet
        public static String provide() {
            return "org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2ViewModel";
        }

        private KeyModule() {
        }
    }
}
