package org.informatika.sirekap.ui.verify.administration;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.FormC1Repository;

/* loaded from: classes4.dex */
public final class VerifyAdministrationViewModel_Factory implements Factory<VerifyAdministrationViewModel> {
    private final Provider<Context> contextProvider;
    private final Provider<DefaultElectionRepository> electionRepositoryProvider;
    private final Provider<FormC1Repository> formC1RepositoryProvider;

    public VerifyAdministrationViewModel_Factory(Provider<Context> contextProvider, Provider<FormC1Repository> formC1RepositoryProvider, Provider<DefaultElectionRepository> electionRepositoryProvider) {
        this.contextProvider = contextProvider;
        this.formC1RepositoryProvider = formC1RepositoryProvider;
        this.electionRepositoryProvider = electionRepositoryProvider;
    }

    @Override // javax.inject.Provider
    public VerifyAdministrationViewModel get() {
        return newInstance(this.contextProvider.get(), this.formC1RepositoryProvider.get(), this.electionRepositoryProvider.get());
    }

    public static VerifyAdministrationViewModel_Factory create(Provider<Context> contextProvider, Provider<FormC1Repository> formC1RepositoryProvider, Provider<DefaultElectionRepository> electionRepositoryProvider) {
        return new VerifyAdministrationViewModel_Factory(contextProvider, formC1RepositoryProvider, electionRepositoryProvider);
    }

    public static VerifyAdministrationViewModel newInstance(Context context, FormC1Repository formC1Repository, DefaultElectionRepository electionRepository) {
        return new VerifyAdministrationViewModel(context, formC1Repository, electionRepository);
    }
}
