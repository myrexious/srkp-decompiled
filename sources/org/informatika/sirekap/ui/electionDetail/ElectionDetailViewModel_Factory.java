package org.informatika.sirekap.ui.electionDetail;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.BackgroundProcessRepository;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.DefaultWitnessRepository;
import org.informatika.sirekap.repository.FormC1Repository;
import org.informatika.sirekap.repository.TpsTimeRepository;
import org.informatika.sirekap.support.security.pdf.PdfLtv;
import org.informatika.sirekap.support.worker.uploadFormCImageRekap.UploadFormCImageRekapTask;
import org.informatika.sirekap.support.worker.zipElection.ZipElectionTask;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes4.dex */
public final class ElectionDetailViewModel_Factory implements Factory<ElectionDetailViewModel> {
    private final Provider<AuthRepository> authRepositoryProvider;
    private final Provider<BackgroundProcessRepository> backgroundProcessRepositoryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DefaultElectionRepository> electionRepositoryProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;
    private final Provider<FormC1Repository> formC1RepositoryProvider;
    private final Provider<PdfLtv.Factory> pdfLtvFactoryProvider;
    private final Provider<TpsTimeRepository> tpsTimeRepositoryProvider;
    private final Provider<UploadFormCImageRekapTask> uploadFormC1CImageRekapTaskProvider;
    private final Provider<DefaultWitnessRepository> witnessRepositoryProvider;
    private final Provider<ZipElectionTask> zipElectionTaskProvider;

    public ElectionDetailViewModel_Factory(Provider<Context> contextProvider, Provider<DefaultElectionRepository> electionRepositoryProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider, Provider<UploadFormCImageRekapTask> uploadFormC1CImageRekapTaskProvider, Provider<FormC1Repository> formC1RepositoryProvider, Provider<AuthRepository> authRepositoryProvider, Provider<DefaultWitnessRepository> witnessRepositoryProvider, Provider<PdfLtv.Factory> pdfLtvFactoryProvider, Provider<TpsTimeRepository> tpsTimeRepositoryProvider, Provider<ZipElectionTask> zipElectionTaskProvider, Provider<BackgroundProcessRepository> backgroundProcessRepositoryProvider) {
        this.contextProvider = contextProvider;
        this.electionRepositoryProvider = electionRepositoryProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
        this.uploadFormC1CImageRekapTaskProvider = uploadFormC1CImageRekapTaskProvider;
        this.formC1RepositoryProvider = formC1RepositoryProvider;
        this.authRepositoryProvider = authRepositoryProvider;
        this.witnessRepositoryProvider = witnessRepositoryProvider;
        this.pdfLtvFactoryProvider = pdfLtvFactoryProvider;
        this.tpsTimeRepositoryProvider = tpsTimeRepositoryProvider;
        this.zipElectionTaskProvider = zipElectionTaskProvider;
        this.backgroundProcessRepositoryProvider = backgroundProcessRepositoryProvider;
    }

    @Override // javax.inject.Provider
    public ElectionDetailViewModel get() {
        return newInstance(this.contextProvider.get(), this.electionRepositoryProvider.get(), this.encryptedSharedPreferencesProvider.get(), this.uploadFormC1CImageRekapTaskProvider.get(), this.formC1RepositoryProvider.get(), this.authRepositoryProvider.get(), this.witnessRepositoryProvider.get(), this.pdfLtvFactoryProvider.get(), this.tpsTimeRepositoryProvider.get(), this.zipElectionTaskProvider.get(), this.backgroundProcessRepositoryProvider.get());
    }

    public static ElectionDetailViewModel_Factory create(Provider<Context> contextProvider, Provider<DefaultElectionRepository> electionRepositoryProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider, Provider<UploadFormCImageRekapTask> uploadFormC1CImageRekapTaskProvider, Provider<FormC1Repository> formC1RepositoryProvider, Provider<AuthRepository> authRepositoryProvider, Provider<DefaultWitnessRepository> witnessRepositoryProvider, Provider<PdfLtv.Factory> pdfLtvFactoryProvider, Provider<TpsTimeRepository> tpsTimeRepositoryProvider, Provider<ZipElectionTask> zipElectionTaskProvider, Provider<BackgroundProcessRepository> backgroundProcessRepositoryProvider) {
        return new ElectionDetailViewModel_Factory(contextProvider, electionRepositoryProvider, encryptedSharedPreferencesProvider, uploadFormC1CImageRekapTaskProvider, formC1RepositoryProvider, authRepositoryProvider, witnessRepositoryProvider, pdfLtvFactoryProvider, tpsTimeRepositoryProvider, zipElectionTaskProvider, backgroundProcessRepositoryProvider);
    }

    public static ElectionDetailViewModel newInstance(Context context, DefaultElectionRepository electionRepository, EncryptedSharedPreferences encryptedSharedPreferences, UploadFormCImageRekapTask uploadFormC1CImageRekapTask, FormC1Repository formC1Repository, AuthRepository authRepository, DefaultWitnessRepository witnessRepository, PdfLtv.Factory pdfLtvFactory, TpsTimeRepository tpsTimeRepository, ZipElectionTask zipElectionTask, BackgroundProcessRepository backgroundProcessRepository) {
        return new ElectionDetailViewModel(context, electionRepository, encryptedSharedPreferences, uploadFormC1CImageRekapTask, formC1Repository, authRepository, witnessRepository, pdfLtvFactory, tpsTimeRepository, zipElectionTask, backgroundProcessRepository);
    }
}
