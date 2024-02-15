package org.informatika.sirekap.ui.specialOccurrence;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.BackgroundProcessRepository;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.SpecialOccurrenceRepository;
import org.informatika.sirekap.support.security.pdf.PdfLtv;
import org.informatika.sirekap.support.worker.uploadSpecialOccurrence.UploadSpecialOccurrenceTask;
import org.informatika.sirekap.support.worker.zipSpecialOccurrence.ZipSpecialOccurrenceTask;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes4.dex */
public final class SpecialOccurrenceViewModel_Factory implements Factory<SpecialOccurrenceViewModel> {
    private final Provider<AuthRepository> authRepositoryProvider;
    private final Provider<BackgroundProcessRepository> backgroundProcessRepositoryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DefaultElectionRepository> electionRepositoryProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;
    private final Provider<PdfLtv.Factory> pdfLtvFactoryProvider;
    private final Provider<SpecialOccurrenceRepository> specialOccurrenceRepositoryProvider;
    private final Provider<UploadSpecialOccurrenceTask> uploadTaskProvider;
    private final Provider<ZipSpecialOccurrenceTask> zipSpecialOccurrenceTaskProvider;

    public SpecialOccurrenceViewModel_Factory(Provider<Context> contextProvider, Provider<DefaultElectionRepository> electionRepositoryProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider, Provider<UploadSpecialOccurrenceTask> uploadTaskProvider, Provider<SpecialOccurrenceRepository> specialOccurrenceRepositoryProvider, Provider<AuthRepository> authRepositoryProvider, Provider<PdfLtv.Factory> pdfLtvFactoryProvider, Provider<ZipSpecialOccurrenceTask> zipSpecialOccurrenceTaskProvider, Provider<BackgroundProcessRepository> backgroundProcessRepositoryProvider) {
        this.contextProvider = contextProvider;
        this.electionRepositoryProvider = electionRepositoryProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
        this.uploadTaskProvider = uploadTaskProvider;
        this.specialOccurrenceRepositoryProvider = specialOccurrenceRepositoryProvider;
        this.authRepositoryProvider = authRepositoryProvider;
        this.pdfLtvFactoryProvider = pdfLtvFactoryProvider;
        this.zipSpecialOccurrenceTaskProvider = zipSpecialOccurrenceTaskProvider;
        this.backgroundProcessRepositoryProvider = backgroundProcessRepositoryProvider;
    }

    @Override // javax.inject.Provider
    public SpecialOccurrenceViewModel get() {
        return newInstance(this.contextProvider.get(), this.electionRepositoryProvider.get(), this.encryptedSharedPreferencesProvider.get(), this.uploadTaskProvider.get(), this.specialOccurrenceRepositoryProvider.get(), this.authRepositoryProvider.get(), this.pdfLtvFactoryProvider.get(), this.zipSpecialOccurrenceTaskProvider.get(), this.backgroundProcessRepositoryProvider.get());
    }

    public static SpecialOccurrenceViewModel_Factory create(Provider<Context> contextProvider, Provider<DefaultElectionRepository> electionRepositoryProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider, Provider<UploadSpecialOccurrenceTask> uploadTaskProvider, Provider<SpecialOccurrenceRepository> specialOccurrenceRepositoryProvider, Provider<AuthRepository> authRepositoryProvider, Provider<PdfLtv.Factory> pdfLtvFactoryProvider, Provider<ZipSpecialOccurrenceTask> zipSpecialOccurrenceTaskProvider, Provider<BackgroundProcessRepository> backgroundProcessRepositoryProvider) {
        return new SpecialOccurrenceViewModel_Factory(contextProvider, electionRepositoryProvider, encryptedSharedPreferencesProvider, uploadTaskProvider, specialOccurrenceRepositoryProvider, authRepositoryProvider, pdfLtvFactoryProvider, zipSpecialOccurrenceTaskProvider, backgroundProcessRepositoryProvider);
    }

    public static SpecialOccurrenceViewModel newInstance(Context context, DefaultElectionRepository electionRepository, EncryptedSharedPreferences encryptedSharedPreferences, UploadSpecialOccurrenceTask uploadTask, SpecialOccurrenceRepository specialOccurrenceRepository, AuthRepository authRepository, PdfLtv.Factory pdfLtvFactory, ZipSpecialOccurrenceTask zipSpecialOccurrenceTask, BackgroundProcessRepository backgroundProcessRepository) {
        return new SpecialOccurrenceViewModel(context, electionRepository, encryptedSharedPreferences, uploadTask, specialOccurrenceRepository, authRepository, pdfLtvFactory, zipSpecialOccurrenceTask, backgroundProcessRepository);
    }
}
