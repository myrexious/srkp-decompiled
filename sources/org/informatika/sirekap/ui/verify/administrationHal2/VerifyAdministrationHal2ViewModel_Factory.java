package org.informatika.sirekap.ui.verify.administrationHal2;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.FormC1Repository;

/* loaded from: classes4.dex */
public final class VerifyAdministrationHal2ViewModel_Factory implements Factory<VerifyAdministrationHal2ViewModel> {
    private final Provider<Context> contextProvider;
    private final Provider<DefaultElectionRepository> electionRepositoryProvider;
    private final Provider<FormC1Repository> formC1RepositoryProvider;

    public VerifyAdministrationHal2ViewModel_Factory(Provider<Context> contextProvider, Provider<FormC1Repository> formC1RepositoryProvider, Provider<DefaultElectionRepository> electionRepositoryProvider) {
        this.contextProvider = contextProvider;
        this.formC1RepositoryProvider = formC1RepositoryProvider;
        this.electionRepositoryProvider = electionRepositoryProvider;
    }

    @Override // javax.inject.Provider
    public VerifyAdministrationHal2ViewModel get() {
        return newInstance(this.contextProvider.get(), this.formC1RepositoryProvider.get(), this.electionRepositoryProvider.get());
    }

    public static VerifyAdministrationHal2ViewModel_Factory create(Provider<Context> contextProvider, Provider<FormC1Repository> formC1RepositoryProvider, Provider<DefaultElectionRepository> electionRepositoryProvider) {
        return new VerifyAdministrationHal2ViewModel_Factory(contextProvider, formC1RepositoryProvider, electionRepositoryProvider);
    }

    public static VerifyAdministrationHal2ViewModel newInstance(Context context, FormC1Repository formC1Repository, DefaultElectionRepository electionRepository) {
        return new VerifyAdministrationHal2ViewModel(context, formC1Repository, electionRepository);
    }
}
