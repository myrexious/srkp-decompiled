package org.informatika.sirekap.ui.verify.tabulationPartai;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;
import dagger.multibindings.StringKey;

/* loaded from: classes4.dex */
public final class VerifyTabulationPartaiViewModel_HiltModules {
    private VerifyTabulationPartaiViewModel_HiltModules() {
    }

    @Module
    /* loaded from: classes4.dex */
    public static abstract class BindsModule {
        @Binds
        @IntoMap
        @StringKey("org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel")
        public abstract ViewModel binds(VerifyTabulationPartaiViewModel vm);

        private BindsModule() {
        }
    }

    @Module
    /* loaded from: classes4.dex */
    public static final class KeyModule {
        @Provides
        @IntoSet
        public static String provide() {
            return "org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel";
        }

        private KeyModule() {
        }
    }
}
