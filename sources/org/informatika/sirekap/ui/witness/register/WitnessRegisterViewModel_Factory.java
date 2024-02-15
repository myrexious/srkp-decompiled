package org.informatika.sirekap.ui.witness.register;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.repository.BackgroundProcessRepository;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.DefaultWitnessRepository;
import org.informatika.sirekap.support.worker.registerWitness.RegisterWitnessTask;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes4.dex */
public final class WitnessRegisterViewModel_Factory implements Factory<WitnessRegisterViewModel> {
    private final Provider<BackgroundProcessRepository> backgroundProcessRepositoryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DefaultElectionRepository> electionRepositoryProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;
    private final Provider<RegisterWitnessTask> registerWitnessTaskProvider;
    private final Provider<DefaultWitnessRepository> witnessRepositoryProvider;

    public WitnessRegisterViewModel_Factory(Provider<Context> contextProvider, Provider<DefaultElectionRepository> electionRepositoryProvider, Provider<DefaultWitnessRepository> witnessRepositoryProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider, Provider<BackgroundProcessRepository> backgroundProcessRepositoryProvider, Provider<RegisterWitnessTask> registerWitnessTaskProvider) {
        this.contextProvider = contextProvider;
        this.electionRepositoryProvider = electionRepositoryProvider;
        this.witnessRepositoryProvider = witnessRepositoryProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
        this.backgroundProcessRepositoryProvider = backgroundProcessRepositoryProvider;
        this.registerWitnessTaskProvider = registerWitnessTaskProvider;
    }

    @Override // javax.inject.Provider
    public WitnessRegisterViewModel get() {
        return newInstance(this.contextProvider.get(), this.electionRepositoryProvider.get(), this.witnessRepositoryProvider.get(), this.encryptedSharedPreferencesProvider.get(), this.backgroundProcessRepositoryProvider.get(), this.registerWitnessTaskProvider.get());
    }

    public static WitnessRegisterViewModel_Factory create(Provider<Context> contextProvider, Provider<DefaultElectionRepository> electionRepositoryProvider, Provider<DefaultWitnessRepository> witnessRepositoryProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider, Provider<BackgroundProcessRepository> backgroundProcessRepositoryProvider, Provider<RegisterWitnessTask> registerWitnessTaskProvider) {
        return new WitnessRegisterViewModel_Factory(contextProvider, electionRepositoryProvider, witnessRepositoryProvider, encryptedSharedPreferencesProvider, backgroundProcessRepositoryProvider, registerWitnessTaskProvider);
    }

    public static WitnessRegisterViewModel newInstance(Context context, DefaultElectionRepository electionRepository, DefaultWitnessRepository witnessRepository, EncryptedSharedPreferences encryptedSharedPreferences, BackgroundProcessRepository backgroundProcessRepository, RegisterWitnessTask registerWitnessTask) {
        return new WitnessRegisterViewModel(context, electionRepository, witnessRepository, encryptedSharedPreferences, backgroundProcessRepository, registerWitnessTask);
    }
}
