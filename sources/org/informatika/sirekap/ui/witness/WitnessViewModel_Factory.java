package org.informatika.sirekap.ui.witness;

import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.DefaultWitnessRepository;

/* loaded from: classes4.dex */
public final class WitnessViewModel_Factory implements Factory<WitnessViewModel> {
    private final Provider<DefaultElectionRepository> electionRepositoryProvider;
    private final Provider<DefaultWitnessRepository> witnessRepositoryProvider;

    public WitnessViewModel_Factory(Provider<DefaultWitnessRepository> witnessRepositoryProvider, Provider<DefaultElectionRepository> electionRepositoryProvider) {
        this.witnessRepositoryProvider = witnessRepositoryProvider;
        this.electionRepositoryProvider = electionRepositoryProvider;
    }

    @Override // javax.inject.Provider
    public WitnessViewModel get() {
        return newInstance(this.witnessRepositoryProvider.get(), this.electionRepositoryProvider.get());
    }

    public static WitnessViewModel_Factory create(Provider<DefaultWitnessRepository> witnessRepositoryProvider, Provider<DefaultElectionRepository> electionRepositoryProvider) {
        return new WitnessViewModel_Factory(witnessRepositoryProvider, electionRepositoryProvider);
    }

    public static WitnessViewModel newInstance(DefaultWitnessRepository witnessRepository, DefaultElectionRepository electionRepository) {
        return new WitnessViewModel(witnessRepository, electionRepository);
    }
}
