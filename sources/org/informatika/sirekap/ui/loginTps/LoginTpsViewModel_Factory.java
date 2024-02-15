package org.informatika.sirekap.ui.loginTps;

import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.BackgroundProcessRepository;
import org.informatika.sirekap.support.worker.login.LoginTask;

/* loaded from: classes4.dex */
public final class LoginTpsViewModel_Factory implements Factory<LoginTpsViewModel> {
    private final Provider<AuthRepository> authRepositoryProvider;
    private final Provider<BackgroundProcessRepository> backgroundProcessRepositoryProvider;
    private final Provider<LoginTask> loginTaskProvider;

    public LoginTpsViewModel_Factory(Provider<AuthRepository> authRepositoryProvider, Provider<LoginTask> loginTaskProvider, Provider<BackgroundProcessRepository> backgroundProcessRepositoryProvider) {
        this.authRepositoryProvider = authRepositoryProvider;
        this.loginTaskProvider = loginTaskProvider;
        this.backgroundProcessRepositoryProvider = backgroundProcessRepositoryProvider;
    }

    @Override // javax.inject.Provider
    public LoginTpsViewModel get() {
        return newInstance(this.authRepositoryProvider.get(), this.loginTaskProvider.get(), this.backgroundProcessRepositoryProvider.get());
    }

    public static LoginTpsViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider, Provider<LoginTask> loginTaskProvider, Provider<BackgroundProcessRepository> backgroundProcessRepositoryProvider) {
        return new LoginTpsViewModel_Factory(authRepositoryProvider, loginTaskProvider, backgroundProcessRepositoryProvider);
    }

    public static LoginTpsViewModel newInstance(AuthRepository authRepository, LoginTask loginTask, BackgroundProcessRepository backgroundProcessRepository) {
        return new LoginTpsViewModel(authRepository, loginTask, backgroundProcessRepository);
    }
}
