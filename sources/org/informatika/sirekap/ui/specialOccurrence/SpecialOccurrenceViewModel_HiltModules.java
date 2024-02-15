package org.informatika.sirekap.ui.specialOccurrence;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;
import dagger.multibindings.StringKey;

/* loaded from: classes4.dex */
public final class SpecialOccurrenceViewModel_HiltModules {
    private SpecialOccurrenceViewModel_HiltModules() {
    }

    @Module
    /* loaded from: classes4.dex */
    public static abstract class BindsModule {
        @Binds
        @IntoMap
        @StringKey("org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel")
        public abstract ViewModel binds(SpecialOccurrenceViewModel vm);

        private BindsModule() {
        }
    }

    @Module
    /* loaded from: classes4.dex */
    public static final class KeyModule {
        @Provides
        @IntoSet
        public static String provide() {
            return "org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel";
        }

        private KeyModule() {
        }
    }
}
