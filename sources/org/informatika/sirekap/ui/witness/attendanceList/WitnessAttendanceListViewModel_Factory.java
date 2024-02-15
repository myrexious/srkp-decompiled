package org.informatika.sirekap.ui.witness.attendanceList;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.BackgroundProcessRepository;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.DefaultWitnessRepository;
import org.informatika.sirekap.support.security.pdf.PdfLtv;
import org.informatika.sirekap.support.worker.uploadAttendancePdf.UploadAttendancePdfTask;
import org.informatika.sirekap.support.worker.zipAttendance.ZipAttendanceTask;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes4.dex */
public final class WitnessAttendanceListViewModel_Factory implements Factory<WitnessAttendanceListViewModel> {
    private final Provider<AuthRepository> authRepositoryProvider;
    private final Provider<BackgroundProcessRepository> backgroundProcessRepositoryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DefaultElectionRepository> electionRepositoryProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;
    private final Provider<PdfLtv.Factory> pdfLtvFactoryProvider;
    private final Provider<UploadAttendancePdfTask> uploadAttendancePdfTaskProvider;
    private final Provider<DefaultWitnessRepository> witnessRepositoryProvider;
    private final Provider<ZipAttendanceTask> zipAttendanceTaskProvider;

    public WitnessAttendanceListViewModel_Factory(Provider<Context> contextProvider, Provider<DefaultElectionRepository> electionRepositoryProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider, Provider<DefaultWitnessRepository> witnessRepositoryProvider, Provider<UploadAttendancePdfTask> uploadAttendancePdfTaskProvider, Provider<PdfLtv.Factory> pdfLtvFactoryProvider, Provider<AuthRepository> authRepositoryProvider, Provider<ZipAttendanceTask> zipAttendanceTaskProvider, Provider<BackgroundProcessRepository> backgroundProcessRepositoryProvider) {
        this.contextProvider = contextProvider;
        this.electionRepositoryProvider = electionRepositoryProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
        this.witnessRepositoryProvider = witnessRepositoryProvider;
        this.uploadAttendancePdfTaskProvider = uploadAttendancePdfTaskProvider;
        this.pdfLtvFactoryProvider = pdfLtvFactoryProvider;
        this.authRepositoryProvider = authRepositoryProvider;
        this.zipAttendanceTaskProvider = zipAttendanceTaskProvider;
        this.backgroundProcessRepositoryProvider = backgroundProcessRepositoryProvider;
    }

    @Override // javax.inject.Provider
    public WitnessAttendanceListViewModel get() {
        return newInstance(this.contextProvider.get(), this.electionRepositoryProvider.get(), this.encryptedSharedPreferencesProvider.get(), this.witnessRepositoryProvider.get(), this.uploadAttendancePdfTaskProvider.get(), this.pdfLtvFactoryProvider.get(), this.authRepositoryProvider.get(), this.zipAttendanceTaskProvider.get(), this.backgroundProcessRepositoryProvider.get());
    }

    public static WitnessAttendanceListViewModel_Factory create(Provider<Context> contextProvider, Provider<DefaultElectionRepository> electionRepositoryProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider, Provider<DefaultWitnessRepository> witnessRepositoryProvider, Provider<UploadAttendancePdfTask> uploadAttendancePdfTaskProvider, Provider<PdfLtv.Factory> pdfLtvFactoryProvider, Provider<AuthRepository> authRepositoryProvider, Provider<ZipAttendanceTask> zipAttendanceTaskProvider, Provider<BackgroundProcessRepository> backgroundProcessRepositoryProvider) {
        return new WitnessAttendanceListViewModel_Factory(contextProvider, electionRepositoryProvider, encryptedSharedPreferencesProvider, witnessRepositoryProvider, uploadAttendancePdfTaskProvider, pdfLtvFactoryProvider, authRepositoryProvider, zipAttendanceTaskProvider, backgroundProcessRepositoryProvider);
    }

    public static WitnessAttendanceListViewModel newInstance(Context context, DefaultElectionRepository electionRepository, EncryptedSharedPreferences encryptedSharedPreferences, DefaultWitnessRepository witnessRepository, UploadAttendancePdfTask uploadAttendancePdfTask, PdfLtv.Factory pdfLtvFactory, AuthRepository authRepository, ZipAttendanceTask zipAttendanceTask, BackgroundProcessRepository backgroundProcessRepository) {
        return new WitnessAttendanceListViewModel(context, electionRepository, encryptedSharedPreferences, witnessRepository, uploadAttendancePdfTask, pdfLtvFactory, authRepository, zipAttendanceTask, backgroundProcessRepository);
    }
}
