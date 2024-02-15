package org.informatika.sirekap;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;
import dagger.multibindings.StringKey;

/* loaded from: classes2.dex */
public final class MainViewModel_HiltModules {
    private MainViewModel_HiltModules() {
    }

    @Module
    /* loaded from: classes2.dex */
    public static abstract class BindsModule {
        @Binds
        @IntoMap
        @StringKey("org.informatika.sirekap.MainViewModel")
        public abstract ViewModel binds(MainViewModel vm);

        private BindsModule() {
        }
    }

    @Module
    /* loaded from: classes2.dex */
    public static final class KeyModule {
        @Provides
        @IntoSet
        public static String provide() {
            return "org.informatika.sirekap.MainViewModel";
        }

        private KeyModule() {
        }
    }
}
