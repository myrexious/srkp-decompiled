package org.informatika.sirekap.ui.sendImage;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.support.worker.uploadFormCImage.UploadFormCImageTask;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes4.dex */
public final class SendImageViewModel_Factory implements Factory<SendImageViewModel> {
    private final Provider<Context> contextProvider;
    private final Provider<DefaultElectionRepository> electionRepositoryProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;
    private final Provider<UploadFormCImageTask> uploadTaskProvider;

    public SendImageViewModel_Factory(Provider<Context> contextProvider, Provider<UploadFormCImageTask> uploadTaskProvider, Provider<DefaultElectionRepository> electionRepositoryProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        this.contextProvider = contextProvider;
        this.uploadTaskProvider = uploadTaskProvider;
        this.electionRepositoryProvider = electionRepositoryProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
    }

    @Override // javax.inject.Provider
    public SendImageViewModel get() {
        return newInstance(this.contextProvider.get(), this.uploadTaskProvider.get(), this.electionRepositoryProvider.get(), this.encryptedSharedPreferencesProvider.get());
    }

    public static SendImageViewModel_Factory create(Provider<Context> contextProvider, Provider<UploadFormCImageTask> uploadTaskProvider, Provider<DefaultElectionRepository> electionRepositoryProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        return new SendImageViewModel_Factory(contextProvider, uploadTaskProvider, electionRepositoryProvider, encryptedSharedPreferencesProvider);
    }

    public static SendImageViewModel newInstance(Context context, UploadFormCImageTask uploadTask, DefaultElectionRepository electionRepository, EncryptedSharedPreferences encryptedSharedPreferences) {
        return new SendImageViewModel(context, uploadTask, electionRepository, encryptedSharedPreferences);
    }
}
