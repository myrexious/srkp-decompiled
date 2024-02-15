package org.informatika.sirekap.ui.verify.tabulation;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.FormC1Repository;

/* loaded from: classes4.dex */
public final class VerifyTabulationViewModel_Factory implements Factory<VerifyTabulationViewModel> {
    private final Provider<Context> contextProvider;
    private final Provider<DefaultElectionRepository> electionRepositoryProvider;
    private final Provider<FormC1Repository> formC1RepositoryProvider;

    public VerifyTabulationViewModel_Factory(Provider<Context> contextProvider, Provider<FormC1Repository> formC1RepositoryProvider, Provider<DefaultElectionRepository> electionRepositoryProvider) {
        this.contextProvider = contextProvider;
        this.formC1RepositoryProvider = formC1RepositoryProvider;
        this.electionRepositoryProvider = electionRepositoryProvider;
    }

    @Override // javax.inject.Provider
    public VerifyTabulationViewModel get() {
        return newInstance(this.contextProvider.get(), this.formC1RepositoryProvider.get(), this.electionRepositoryProvider.get());
    }

    public static VerifyTabulationViewModel_Factory create(Provider<Context> contextProvider, Provider<FormC1Repository> formC1RepositoryProvider, Provider<DefaultElectionRepository> electionRepositoryProvider) {
        return new VerifyTabulationViewModel_Factory(contextProvider, formC1RepositoryProvider, electionRepositoryProvider);
    }

    public static VerifyTabulationViewModel newInstance(Context context, FormC1Repository formC1Repository, DefaultElectionRepository electionRepository) {
        return new VerifyTabulationViewModel(context, formC1Repository, electionRepository);
    }
}
