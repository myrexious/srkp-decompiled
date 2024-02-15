package org.informatika.sirekap.ui.verify.administrationHal2Ppwp;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.FormC1Repository;

/* loaded from: classes4.dex */
public final class VerifyAdministrationHal2PpwpViewModel_Factory implements Factory<VerifyAdministrationHal2PpwpViewModel> {
    private final Provider<Context> contextProvider;
    private final Provider<DefaultElectionRepository> electionRepositoryProvider;
    private final Provider<FormC1Repository> formC1RepositoryProvider;

    public VerifyAdministrationHal2PpwpViewModel_Factory(Provider<Context> contextProvider, Provider<FormC1Repository> formC1RepositoryProvider, Provider<DefaultElectionRepository> electionRepositoryProvider) {
        this.contextProvider = contextProvider;
        this.formC1RepositoryProvider = formC1RepositoryProvider;
        this.electionRepositoryProvider = electionRepositoryProvider;
    }

    @Override // javax.inject.Provider
    public VerifyAdministrationHal2PpwpViewModel get() {
        return newInstance(this.contextProvider.get(), this.formC1RepositoryProvider.get(), this.electionRepositoryProvider.get());
    }

    public static VerifyAdministrationHal2PpwpViewModel_Factory create(Provider<Context> contextProvider, Provider<FormC1Repository> formC1RepositoryProvider, Provider<DefaultElectionRepository> electionRepositoryProvider) {
        return new VerifyAdministrationHal2PpwpViewModel_Factory(contextProvider, formC1RepositoryProvider, electionRepositoryProvider);
    }

    public static VerifyAdministrationHal2PpwpViewModel newInstance(Context context, FormC1Repository formC1Repository, DefaultElectionRepository electionRepository) {
        return new VerifyAdministrationHal2PpwpViewModel(context, formC1Repository, electionRepository);
    }
}
