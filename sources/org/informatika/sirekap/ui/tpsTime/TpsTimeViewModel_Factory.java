package org.informatika.sirekap.ui.tpsTime;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.repository.TpsTimeRepository;

/* loaded from: classes4.dex */
public final class TpsTimeViewModel_Factory implements Factory<TpsTimeViewModel> {
    private final Provider<Context> contextProvider;
    private final Provider<TpsTimeRepository> tpsTimeRepositoryProvider;

    public TpsTimeViewModel_Factory(Provider<Context> contextProvider, Provider<TpsTimeRepository> tpsTimeRepositoryProvider) {
        this.contextProvider = contextProvider;
        this.tpsTimeRepositoryProvider = tpsTimeRepositoryProvider;
    }

    @Override // javax.inject.Provider
    public TpsTimeViewModel get() {
        return newInstance(this.contextProvider.get(), this.tpsTimeRepositoryProvider.get());
    }

    public static TpsTimeViewModel_Factory create(Provider<Context> contextProvider, Provider<TpsTimeRepository> tpsTimeRepositoryProvider) {
        return new TpsTimeViewModel_Factory(contextProvider, tpsTimeRepositoryProvider);
    }

    public static TpsTimeViewModel newInstance(Context context, TpsTimeRepository tpsTimeRepository) {
        return new TpsTimeViewModel(context, tpsTimeRepository);
    }
}
