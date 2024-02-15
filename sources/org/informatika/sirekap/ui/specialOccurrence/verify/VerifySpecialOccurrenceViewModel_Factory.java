package org.informatika.sirekap.ui.specialOccurrence.verify;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes4.dex */
public final class VerifySpecialOccurrenceViewModel_Factory implements Factory<VerifySpecialOccurrenceViewModel> {
    private final Provider<Context> contextProvider;
    private final Provider<DefaultElectionRepository> electionRepositoryProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;

    public VerifySpecialOccurrenceViewModel_Factory(Provider<Context> contextProvider, Provider<DefaultElectionRepository> electionRepositoryProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        this.contextProvider = contextProvider;
        this.electionRepositoryProvider = electionRepositoryProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
    }

    @Override // javax.inject.Provider
    public VerifySpecialOccurrenceViewModel get() {
        return newInstance(this.contextProvider.get(), this.electionRepositoryProvider.get(), this.encryptedSharedPreferencesProvider.get());
    }

    public static VerifySpecialOccurrenceViewModel_Factory create(Provider<Context> contextProvider, Provider<DefaultElectionRepository> electionRepositoryProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        return new VerifySpecialOccurrenceViewModel_Factory(contextProvider, electionRepositoryProvider, encryptedSharedPreferencesProvider);
    }

    public static VerifySpecialOccurrenceViewModel newInstance(Context context, DefaultElectionRepository electionRepository, EncryptedSharedPreferences encryptedSharedPreferences) {
        return new VerifySpecialOccurrenceViewModel(context, electionRepository, encryptedSharedPreferences);
    }
}
