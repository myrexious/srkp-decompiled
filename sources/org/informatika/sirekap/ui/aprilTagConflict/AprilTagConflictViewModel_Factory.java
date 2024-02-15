package org.informatika.sirekap.ui.aprilTagConflict;

import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.repository.DefaultElectionRepository;

/* loaded from: classes4.dex */
public final class AprilTagConflictViewModel_Factory implements Factory<AprilTagConflictViewModel> {
    private final Provider<DefaultElectionRepository> electionRepositoryProvider;

    public AprilTagConflictViewModel_Factory(Provider<DefaultElectionRepository> electionRepositoryProvider) {
        this.electionRepositoryProvider = electionRepositoryProvider;
    }

    @Override // javax.inject.Provider
    public AprilTagConflictViewModel get() {
        return newInstance(this.electionRepositoryProvider.get());
    }

    public static AprilTagConflictViewModel_Factory create(Provider<DefaultElectionRepository> electionRepositoryProvider) {
        return new AprilTagConflictViewModel_Factory(electionRepositoryProvider);
    }

    public static AprilTagConflictViewModel newInstance(DefaultElectionRepository electionRepository) {
        return new AprilTagConflictViewModel(electionRepository);
    }
}
