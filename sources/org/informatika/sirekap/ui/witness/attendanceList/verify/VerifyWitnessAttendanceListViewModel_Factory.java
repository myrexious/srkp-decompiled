package org.informatika.sirekap.ui.witness.attendanceList.verify;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.DefaultWitnessRepository;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes4.dex */
public final class VerifyWitnessAttendanceListViewModel_Factory implements Factory<VerifyWitnessAttendanceListViewModel> {
    private final Provider<Context> contextProvider;
    private final Provider<DefaultElectionRepository> electionRepositoryProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;
    private final Provider<DefaultWitnessRepository> witnessRepositoryProvider;

    public VerifyWitnessAttendanceListViewModel_Factory(Provider<Context> contextProvider, Provider<DefaultElectionRepository> electionRepositoryProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider, Provider<DefaultWitnessRepository> witnessRepositoryProvider) {
        this.contextProvider = contextProvider;
        this.electionRepositoryProvider = electionRepositoryProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
        this.witnessRepositoryProvider = witnessRepositoryProvider;
    }

    @Override // javax.inject.Provider
    public VerifyWitnessAttendanceListViewModel get() {
        return newInstance(this.contextProvider.get(), this.electionRepositoryProvider.get(), this.encryptedSharedPreferencesProvider.get(), this.witnessRepositoryProvider.get());
    }

    public static VerifyWitnessAttendanceListViewModel_Factory create(Provider<Context> contextProvider, Provider<DefaultElectionRepository> electionRepositoryProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider, Provider<DefaultWitnessRepository> witnessRepositoryProvider) {
        return new VerifyWitnessAttendanceListViewModel_Factory(contextProvider, electionRepositoryProvider, encryptedSharedPreferencesProvider, witnessRepositoryProvider);
    }

    public static VerifyWitnessAttendanceListViewModel newInstance(Context context, DefaultElectionRepository electionRepository, EncryptedSharedPreferences encryptedSharedPreferences, DefaultWitnessRepository witnessRepository) {
        return new VerifyWitnessAttendanceListViewModel(context, electionRepository, encryptedSharedPreferences, witnessRepository);
    }
}
