package org.informatika.sirekap.ui.confirmSaveFormCImage;

import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.repository.DefaultElectionRepository;

/* loaded from: classes4.dex */
public final class ConfirmSaveFormCImageViewModel_Factory implements Factory<ConfirmSaveFormCImageViewModel> {
    private final Provider<DefaultElectionRepository> electionRepositoryProvider;

    public ConfirmSaveFormCImageViewModel_Factory(Provider<DefaultElectionRepository> electionRepositoryProvider) {
        this.electionRepositoryProvider = electionRepositoryProvider;
    }

    @Override // javax.inject.Provider
    public ConfirmSaveFormCImageViewModel get() {
        return newInstance(this.electionRepositoryProvider.get());
    }

    public static ConfirmSaveFormCImageViewModel_Factory create(Provider<DefaultElectionRepository> electionRepositoryProvider) {
        return new ConfirmSaveFormCImageViewModel_Factory(electionRepositoryProvider);
    }

    public static ConfirmSaveFormCImageViewModel newInstance(DefaultElectionRepository electionRepository) {
        return new ConfirmSaveFormCImageViewModel(electionRepository);
    }
}
