package org.informatika.sirekap.ui.witness.qrCode;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.repository.BackgroundProcessRepository;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.DefaultWitnessRepository;
import org.informatika.sirekap.support.worker.registerWitness.RegisterWitnessTask;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes4.dex */
public final class WitnessQrCodeViewModel_Factory implements Factory<WitnessQrCodeViewModel> {
    private final Provider<BackgroundProcessRepository> backgroundProcessRepositoryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DefaultElectionRepository> electionRepositoryProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;
    private final Provider<RegisterWitnessTask> registerWitnessTaskProvider;
    private final Provider<DefaultWitnessRepository> witnessRepositoryProvider;

    public WitnessQrCodeViewModel_Factory(Provider<Context> contextProvider, Provider<DefaultWitnessRepository> witnessRepositoryProvider, Provider<DefaultElectionRepository> electionRepositoryProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider, Provider<RegisterWitnessTask> registerWitnessTaskProvider, Provider<BackgroundProcessRepository> backgroundProcessRepositoryProvider) {
        this.contextProvider = contextProvider;
        this.witnessRepositoryProvider = witnessRepositoryProvider;
        this.electionRepositoryProvider = electionRepositoryProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
        this.registerWitnessTaskProvider = registerWitnessTaskProvider;
        this.backgroundProcessRepositoryProvider = backgroundProcessRepositoryProvider;
    }

    @Override // javax.inject.Provider
    public WitnessQrCodeViewModel get() {
        return newInstance(this.contextProvider.get(), this.witnessRepositoryProvider.get(), this.electionRepositoryProvider.get(), this.encryptedSharedPreferencesProvider.get(), this.registerWitnessTaskProvider.get(), this.backgroundProcessRepositoryProvider.get());
    }

    public static WitnessQrCodeViewModel_Factory create(Provider<Context> contextProvider, Provider<DefaultWitnessRepository> witnessRepositoryProvider, Provider<DefaultElectionRepository> electionRepositoryProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider, Provider<RegisterWitnessTask> registerWitnessTaskProvider, Provider<BackgroundProcessRepository> backgroundProcessRepositoryProvider) {
        return new WitnessQrCodeViewModel_Factory(contextProvider, witnessRepositoryProvider, electionRepositoryProvider, encryptedSharedPreferencesProvider, registerWitnessTaskProvider, backgroundProcessRepositoryProvider);
    }

    public static WitnessQrCodeViewModel newInstance(Context context, DefaultWitnessRepository witnessRepository, DefaultElectionRepository electionRepository, EncryptedSharedPreferences encryptedSharedPreferences, RegisterWitnessTask registerWitnessTask, BackgroundProcessRepository backgroundProcessRepository) {
        return new WitnessQrCodeViewModel(context, witnessRepository, electionRepository, encryptedSharedPreferences, registerWitnessTask, backgroundProcessRepository);
    }
}
