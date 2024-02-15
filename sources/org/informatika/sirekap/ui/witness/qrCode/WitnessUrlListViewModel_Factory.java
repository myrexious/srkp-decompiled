package org.informatika.sirekap.ui.witness.qrCode;

import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.DefaultWitnessRepository;

/* loaded from: classes4.dex */
public final class WitnessUrlListViewModel_Factory implements Factory<WitnessUrlListViewModel> {
    private final Provider<DefaultElectionRepository> electionRepositoryProvider;
    private final Provider<DefaultWitnessRepository> witnessRepositoryProvider;

    public WitnessUrlListViewModel_Factory(Provider<DefaultWitnessRepository> witnessRepositoryProvider, Provider<DefaultElectionRepository> electionRepositoryProvider) {
        this.witnessRepositoryProvider = witnessRepositoryProvider;
        this.electionRepositoryProvider = electionRepositoryProvider;
    }

    @Override // javax.inject.Provider
    public WitnessUrlListViewModel get() {
        return newInstance(this.witnessRepositoryProvider.get(), this.electionRepositoryProvider.get());
    }

    public static WitnessUrlListViewModel_Factory create(Provider<DefaultWitnessRepository> witnessRepositoryProvider, Provider<DefaultElectionRepository> electionRepositoryProvider) {
        return new WitnessUrlListViewModel_Factory(witnessRepositoryProvider, electionRepositoryProvider);
    }

    public static WitnessUrlListViewModel newInstance(DefaultWitnessRepository witnessRepository, DefaultElectionRepository electionRepository) {
        return new WitnessUrlListViewModel(witnessRepository, electionRepository);
    }
}
