package org.informatika.sirekap.ui.specialOccurrence.verify;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;
import dagger.multibindings.StringKey;

/* loaded from: classes4.dex */
public final class VerifySpecialOccurrenceViewModel_HiltModules {
    private VerifySpecialOccurrenceViewModel_HiltModules() {
    }

    @Module
    /* loaded from: classes4.dex */
    public static abstract class BindsModule {
        @Binds
        @IntoMap
        @StringKey("org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceViewModel")
        public abstract ViewModel binds(VerifySpecialOccurrenceViewModel vm);

        private BindsModule() {
        }
    }

    @Module
    /* loaded from: classes4.dex */
    public static final class KeyModule {
        @Provides
        @IntoSet
        public static String provide() {
            return "org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceViewModel";
        }

        private KeyModule() {
        }
    }
}
