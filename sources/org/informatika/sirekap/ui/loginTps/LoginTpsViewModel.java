package org.informatika.sirekap.ui.loginTps;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.BackgroundProcess;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.BackgroundProcessRepository;
import org.informatika.sirekap.support.worker.login.LoginTask;
import org.informatika.sirekap.usecase.GetListTpsUseCase;

/* compiled from: LoginTpsViewModel.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001e"}, d2 = {"Lorg/informatika/sirekap/ui/loginTps/LoginTpsViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lorg/informatika/sirekap/repository/AuthRepository;", "loginTask", "Lorg/informatika/sirekap/support/worker/login/LoginTask;", "backgroundProcessRepository", "Lorg/informatika/sirekap/repository/BackgroundProcessRepository;", "(Lorg/informatika/sirekap/repository/AuthRepository;Lorg/informatika/sirekap/support/worker/login/LoginTask;Lorg/informatika/sirekap/repository/BackgroundProcessRepository;)V", "backgroundProcessLogin", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/BackgroundProcess;", "getBackgroundProcessLogin", "()Landroidx/lifecycle/LiveData;", "getListTPSUseCase", "Lorg/informatika/sirekap/usecase/GetListTpsUseCase;", "getGetListTPSUseCase", "()Lorg/informatika/sirekap/usecase/GetListTpsUseCase;", "isLoading", "", "loginTpsFormState", "Lorg/informatika/sirekap/ui/loginTps/LoginTpsFormState;", "getLoginTpsFormState", "()Lorg/informatika/sirekap/ui/loginTps/LoginTpsFormState;", "doLogin", "", "context", "Landroid/content/Context;", "kodeTps", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class LoginTpsViewModel extends ViewModel {
    private final AuthRepository authRepository;
    private final LiveData<BackgroundProcess> backgroundProcessLogin;
    private final GetListTpsUseCase getListTPSUseCase;
    private final LiveData<Boolean> isLoading;
    private final LoginTask loginTask;
    private final LoginTpsFormState loginTpsFormState;

    @Inject
    public LoginTpsViewModel(AuthRepository authRepository, LoginTask loginTask, BackgroundProcessRepository backgroundProcessRepository) {
        Intrinsics.checkNotNullParameter(authRepository, "authRepository");
        Intrinsics.checkNotNullParameter(loginTask, "loginTask");
        Intrinsics.checkNotNullParameter(backgroundProcessRepository, "backgroundProcessRepository");
        this.authRepository = authRepository;
        this.loginTask = loginTask;
        this.getListTPSUseCase = new GetListTpsUseCase(authRepository);
        this.loginTpsFormState = new LoginTpsFormState();
        LiveData<BackgroundProcess> byId = backgroundProcessRepository.getById("login");
        this.backgroundProcessLogin = byId;
        this.isLoading = Transformations.map(byId, new Function1<BackgroundProcess, Boolean>() { // from class: org.informatika.sirekap.ui.loginTps.LoginTpsViewModel$isLoading$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(BackgroundProcess backgroundProcess) {
                return Boolean.valueOf(backgroundProcess == null ? false : backgroundProcess.isLoading());
            }
        });
    }

    public final GetListTpsUseCase getGetListTPSUseCase() {
        return this.getListTPSUseCase;
    }

    public final LoginTpsFormState getLoginTpsFormState() {
        return this.loginTpsFormState;
    }

    public final void doLogin(Context context, String kodeTps) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        this.loginTask.login(context, kodeTps);
    }

    public final LiveData<BackgroundProcess> getBackgroundProcessLogin() {
        return this.backgroundProcessLogin;
    }

    public final LiveData<Boolean> isLoading() {
        return this.isLoading;
    }
}
