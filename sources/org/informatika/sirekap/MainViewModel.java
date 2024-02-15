package org.informatika.sirekap;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.model.ActiveProfile;
import org.informatika.sirekap.model.User;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.UserRepository;
import org.informatika.sirekap.support.ElectionUtil;
import org.informatika.sirekap.support.worker.refreshToken.RefreshTokenTask;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import org.informatika.sirekap.usecase.AuthRequestUseCase;
import org.informatika.sirekap.usecase.GetLoggedInUserUseCase;

/* compiled from: MainViewModel.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B1\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u001a\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(2\b\u0010)\u001a\u0004\u0018\u00010(J\u0006\u0010*\u001a\u00020&J\u0006\u0010+\u001a\u00020\u001bJ\u0006\u0010,\u001a\u00020&J\u000e\u0010-\u001a\u00020&2\u0006\u0010.\u001a\u00020/R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001f\u0010\u0019\u001a\u0010\u0012\f\u0012\n \u001c*\u0004\u0018\u00010\u001b0\u001b0\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001d\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\"0 ¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lorg/informatika/sirekap/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lorg/informatika/sirekap/repository/AuthRepository;", "userRepository", "Lorg/informatika/sirekap/repository/UserRepository;", "refreshTokenTask", "Lorg/informatika/sirekap/support/worker/refreshToken/RefreshTokenTask;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "appDatabase", "Lorg/informatika/sirekap/db/AppDatabase;", "(Lorg/informatika/sirekap/repository/AuthRepository;Lorg/informatika/sirekap/repository/UserRepository;Lorg/informatika/sirekap/support/worker/refreshToken/RefreshTokenTask;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;Lorg/informatika/sirekap/db/AppDatabase;)V", "getAppDatabase", "()Lorg/informatika/sirekap/db/AppDatabase;", "getAuthRepository$app_productionRelease", "()Lorg/informatika/sirekap/repository/AuthRepository;", "authRequestUseCase", "Lorg/informatika/sirekap/usecase/AuthRequestUseCase;", "getAuthRequestUseCase", "()Lorg/informatika/sirekap/usecase/AuthRequestUseCase;", "getLoggedInUserUseCase", "Lorg/informatika/sirekap/usecase/GetLoggedInUserUseCase;", "getGetLoggedInUserUseCase", "()Lorg/informatika/sirekap/usecase/GetLoggedInUserUseCase;", "localAuth", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "getLocalAuth", "()Landroidx/lifecycle/MutableLiveData;", "mappingCache", "", "", "Lorg/informatika/sirekap/model/WitnessWithShare;", "getMappingCache", "()Ljava/util/Map;", "confirmSelectedProfile", "", "selectedProfileEncrypted", "Lorg/informatika/sirekap/model/User;", "selectedProfile", "finishLogin", "isNeedDoLocalAuthentication", "logout", "startRefreshTokenTask", "context", "Landroid/content/Context;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MainViewModel extends ViewModel {
    private final AppDatabase appDatabase;
    private final AuthRepository authRepository;
    private final AuthRequestUseCase authRequestUseCase;
    private final EncryptedSharedPreferences encryptedSharedPreferences;
    private final GetLoggedInUserUseCase getLoggedInUserUseCase;
    private final MutableLiveData<Boolean> localAuth;
    private final Map<String, WitnessWithShare> mappingCache;
    private final RefreshTokenTask refreshTokenTask;

    public final AuthRepository getAuthRepository$app_productionRelease() {
        return this.authRepository;
    }

    public final AppDatabase getAppDatabase() {
        return this.appDatabase;
    }

    @Inject
    public MainViewModel(AuthRepository authRepository, UserRepository userRepository, RefreshTokenTask refreshTokenTask, EncryptedSharedPreferences encryptedSharedPreferences, AppDatabase appDatabase) {
        Intrinsics.checkNotNullParameter(authRepository, "authRepository");
        Intrinsics.checkNotNullParameter(userRepository, "userRepository");
        Intrinsics.checkNotNullParameter(refreshTokenTask, "refreshTokenTask");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Intrinsics.checkNotNullParameter(appDatabase, "appDatabase");
        this.authRepository = authRepository;
        this.refreshTokenTask = refreshTokenTask;
        this.encryptedSharedPreferences = encryptedSharedPreferences;
        this.appDatabase = appDatabase;
        this.authRequestUseCase = new AuthRequestUseCase(authRepository);
        this.localAuth = new MutableLiveData<>(false);
        this.getLoggedInUserUseCase = new GetLoggedInUserUseCase(authRepository, userRepository);
        this.mappingCache = new LinkedHashMap();
    }

    public final AuthRequestUseCase getAuthRequestUseCase() {
        return this.authRequestUseCase;
    }

    public final MutableLiveData<Boolean> getLocalAuth() {
        return this.localAuth;
    }

    public final GetLoggedInUserUseCase getGetLoggedInUserUseCase() {
        return this.getLoggedInUserUseCase;
    }

    public final void confirmSelectedProfile(User user, User user2) {
        if (user != null) {
            this.encryptedSharedPreferences.putStringEncrypted(SharedPreferencesModule.SP_KEY_USER_ID, user.getId());
        }
        if (user2 != null) {
            this.authRepository.saveActiveProfile(new ActiveProfile(user2.getNamaProfil(), null, ElectionUtil.extractKodeTpsReal(user2.getId()), user2.getRole(), user2.getProfilStart(), user2.getProfilEnd()));
        }
        this.getLoggedInUserUseCase.refresh();
    }

    public final void finishLogin() {
        this.getLoggedInUserUseCase.refresh();
    }

    public final void logout() {
        this.localAuth.postValue(false);
        this.authRepository.logout();
        this.getLoggedInUserUseCase.refresh();
    }

    public final boolean isNeedDoLocalAuthentication() {
        if (this.authRepository.isAuthenticated()) {
            Boolean value = this.localAuth.getValue();
            Intrinsics.checkNotNull(value);
            if (!value.booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public final void startRefreshTokenTask(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.refreshTokenTask.start(context);
    }

    public final Map<String, WitnessWithShare> getMappingCache() {
        return this.mappingCache;
    }
}
