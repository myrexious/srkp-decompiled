package org.informatika.sirekap.ui.selectFormCImage;

import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.repository.DefaultElectionRepository;

/* loaded from: classes4.dex */
public final class SelectFormCImageViewModel_Factory implements Factory<SelectFormCImageViewModel> {
    private final Provider<DefaultElectionRepository> electionRepositoryProvider;

    public SelectFormCImageViewModel_Factory(Provider<DefaultElectionRepository> electionRepositoryProvider) {
        this.electionRepositoryProvider = electionRepositoryProvider;
    }

    @Override // javax.inject.Provider
    public SelectFormCImageViewModel get() {
        return newInstance(this.electionRepositoryProvider.get());
    }

    public static SelectFormCImageViewModel_Factory create(Provider<DefaultElectionRepository> electionRepositoryProvider) {
        return new SelectFormCImageViewModel_Factory(electionRepositoryProvider);
    }

    public static SelectFormCImageViewModel newInstance(DefaultElectionRepository electionRepository) {
        return new SelectFormCImageViewModel(electionRepository);
    }
}
