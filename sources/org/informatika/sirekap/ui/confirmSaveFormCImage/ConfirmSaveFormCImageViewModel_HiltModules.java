package org.informatika.sirekap.ui.confirmSaveFormCImage;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;
import dagger.multibindings.StringKey;

/* loaded from: classes4.dex */
public final class ConfirmSaveFormCImageViewModel_HiltModules {
    private ConfirmSaveFormCImageViewModel_HiltModules() {
    }

    @Module
    /* loaded from: classes4.dex */
    public static abstract class BindsModule {
        @Binds
        @IntoMap
        @StringKey("org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageViewModel")
        public abstract ViewModel binds(ConfirmSaveFormCImageViewModel vm);

        private BindsModule() {
        }
    }

    @Module
    /* loaded from: classes4.dex */
    public static final class KeyModule {
        @Provides
        @IntoSet
        public static String provide() {
            return "org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageViewModel";
        }

        private KeyModule() {
        }
    }
}
