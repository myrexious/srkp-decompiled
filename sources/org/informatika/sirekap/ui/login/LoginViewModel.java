package org.informatika.sirekap.ui.login;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.informatika.sirekap.BuildConfig;
import org.informatika.sirekap.model.ActiveProfile;
import org.informatika.sirekap.model.BackgroundProcess;
import org.informatika.sirekap.model.UserInformation;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.BackgroundProcessRepository;
import org.informatika.sirekap.repository.CertmanRepository;
import org.informatika.sirekap.repository.UserRepository;
import org.informatika.sirekap.support.security.AndroidKeystoreFacade;
import org.informatika.sirekap.support.worker.login.LoginTask;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import org.informatika.sirekap.usecase.AuthRequestUseCase;
import org.informatika.sirekap.usecase.GetLoggedInUserUseCase;

/* compiled from: LoginViewModel.kt */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001BC\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\b\u0001\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u0006\u0010(\u001a\u00020)J\u000e\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020,J\b\u0010-\u001a\u0004\u0018\u00010.J\u0006\u0010/\u001a\u00020#J\u0006\u00100\u001a\u00020)J!\u00101\u001a\u00020)2\u0006\u0010+\u001a\u00020,2\u0006\u00102\u001a\u000203H\u0086@ø\u0001\u0000¢\u0006\u0002\u00104J\u0006\u00105\u001a\u00020)R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\u001f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u0016¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0019R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'\u0082\u0002\u0004\n\u0002\b\u0019¨\u00066"}, d2 = {"Lorg/informatika/sirekap/ui/login/LoginViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lorg/informatika/sirekap/repository/AuthRepository;", "loginTask", "Lorg/informatika/sirekap/support/worker/login/LoginTask;", "backgroundProcessRepository", "Lorg/informatika/sirekap/repository/BackgroundProcessRepository;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "sharedPreferences", "Landroid/content/SharedPreferences;", "userRepository", "Lorg/informatika/sirekap/repository/UserRepository;", "certmanRepository", "Lorg/informatika/sirekap/repository/CertmanRepository;", "(Lorg/informatika/sirekap/repository/AuthRepository;Lorg/informatika/sirekap/support/worker/login/LoginTask;Lorg/informatika/sirekap/repository/BackgroundProcessRepository;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;Landroid/content/SharedPreferences;Lorg/informatika/sirekap/repository/UserRepository;Lorg/informatika/sirekap/repository/CertmanRepository;)V", "authRepositoryUseCase", "Lorg/informatika/sirekap/usecase/AuthRequestUseCase;", "getAuthRepositoryUseCase", "()Lorg/informatika/sirekap/usecase/AuthRequestUseCase;", "backgroundProcessLogin", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/BackgroundProcess;", "getBackgroundProcessLogin", "()Landroidx/lifecycle/LiveData;", "getCertmanRepository", "()Lorg/informatika/sirekap/repository/CertmanRepository;", "getEncryptedSharedPreferences", "()Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "getLoggedInUserUseCase", "Lorg/informatika/sirekap/usecase/GetLoggedInUserUseCase;", "getGetLoggedInUserUseCase", "()Lorg/informatika/sirekap/usecase/GetLoggedInUserUseCase;", "isLoading", "", "getSharedPreferences", "()Landroid/content/SharedPreferences;", "getUserRepository", "()Lorg/informatika/sirekap/repository/UserRepository;", "checkCertificateAndToken", "", "doLogin", "context", "Landroid/content/Context;", "getActiveProfile", "Lorg/informatika/sirekap/model/ActiveProfile;", "isExpired", "logout", "reportUserStatus", NotificationCompat.CATEGORY_STATUS, "", "(Landroid/content/Context;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setup", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class LoginViewModel extends ViewModel {
    private final AuthRepository authRepository;
    private final AuthRequestUseCase authRepositoryUseCase;
    private final LiveData<BackgroundProcess> backgroundProcessLogin;
    private final BackgroundProcessRepository backgroundProcessRepository;
    private final CertmanRepository certmanRepository;
    private final EncryptedSharedPreferences encryptedSharedPreferences;
    private final GetLoggedInUserUseCase getLoggedInUserUseCase;
    private final LiveData<Boolean> isLoading;
    private final LoginTask loginTask;
    private final SharedPreferences sharedPreferences;
    private final UserRepository userRepository;

    public final EncryptedSharedPreferences getEncryptedSharedPreferences() {
        return this.encryptedSharedPreferences;
    }

    public final SharedPreferences getSharedPreferences() {
        return this.sharedPreferences;
    }

    public final UserRepository getUserRepository() {
        return this.userRepository;
    }

    public final CertmanRepository getCertmanRepository() {
        return this.certmanRepository;
    }

    @Inject
    public LoginViewModel(AuthRepository authRepository, LoginTask loginTask, BackgroundProcessRepository backgroundProcessRepository, EncryptedSharedPreferences encryptedSharedPreferences, SharedPreferences sharedPreferences, UserRepository userRepository, CertmanRepository certmanRepository) {
        Intrinsics.checkNotNullParameter(authRepository, "authRepository");
        Intrinsics.checkNotNullParameter(loginTask, "loginTask");
        Intrinsics.checkNotNullParameter(backgroundProcessRepository, "backgroundProcessRepository");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
        Intrinsics.checkNotNullParameter(userRepository, "userRepository");
        Intrinsics.checkNotNullParameter(certmanRepository, "certmanRepository");
        this.authRepository = authRepository;
        this.loginTask = loginTask;
        this.backgroundProcessRepository = backgroundProcessRepository;
        this.encryptedSharedPreferences = encryptedSharedPreferences;
        this.sharedPreferences = sharedPreferences;
        this.userRepository = userRepository;
        this.certmanRepository = certmanRepository;
        this.getLoggedInUserUseCase = new GetLoggedInUserUseCase(authRepository, userRepository);
        this.authRepositoryUseCase = new AuthRequestUseCase(authRepository);
        LiveData<BackgroundProcess> byId = backgroundProcessRepository.getById("login");
        this.backgroundProcessLogin = byId;
        this.isLoading = Transformations.map(byId, new Function1<BackgroundProcess, Boolean>() { // from class: org.informatika.sirekap.ui.login.LoginViewModel$isLoading$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(BackgroundProcess backgroundProcess) {
                return Boolean.valueOf(backgroundProcess == null ? false : backgroundProcess.isLoading());
            }
        });
    }

    public final GetLoggedInUserUseCase getGetLoggedInUserUseCase() {
        return this.getLoggedInUserUseCase;
    }

    public final AuthRequestUseCase getAuthRepositoryUseCase() {
        return this.authRepositoryUseCase;
    }

    public final boolean isExpired() {
        Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(BuildConfig.EXPIRED_AT);
        Intrinsics.checkNotNullExpressionValue(parse, "SimpleDateFormat(\"yyyy-M…e(BuildConfig.EXPIRED_AT)");
        return parse.before(new Date());
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0099  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object reportUserStatus(android.content.Context r9, int r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof org.informatika.sirekap.ui.login.LoginViewModel$reportUserStatus$1
            if (r0 == 0) goto L14
            r0 = r11
            org.informatika.sirekap.ui.login.LoginViewModel$reportUserStatus$1 r0 = (org.informatika.sirekap.ui.login.LoginViewModel$reportUserStatus$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            org.informatika.sirekap.ui.login.LoginViewModel$reportUserStatus$1 r0 = new org.informatika.sirekap.ui.login.LoginViewModel$reportUserStatus$1
            r0.<init>(r8, r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L50
            if (r2 == r6) goto L43
            if (r2 == r5) goto L3f
            if (r2 == r4) goto L3b
            if (r2 != r3) goto L33
            kotlin.ResultKt.throwOnFailure(r11)
            goto L86
        L33:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L3b:
            kotlin.ResultKt.throwOnFailure(r11)
            goto L96
        L3f:
            kotlin.ResultKt.throwOnFailure(r11)
            goto La6
        L43:
            int r9 = r0.I$0
            java.lang.Object r10 = r0.L$0
            org.informatika.sirekap.ui.login.LoginViewModel r10 = (org.informatika.sirekap.ui.login.LoginViewModel) r10
            kotlin.ResultKt.throwOnFailure(r11)
            r7 = r10
            r10 = r9
            r9 = r7
            goto L6b
        L50:
            kotlin.ResultKt.throwOnFailure(r11)
            org.informatika.sirekap.usecase.AuthRequestUseCase r11 = r8.authRepositoryUseCase
            boolean r11 = r11.isLocalTokenExpired()
            if (r11 != 0) goto L6a
            org.informatika.sirekap.usecase.AuthRequestUseCase r11 = r8.authRepositoryUseCase
            r0.L$0 = r8
            r0.I$0 = r10
            r0.label = r6
            java.lang.Object r9 = r11.startRefreshToken(r9, r0)
            if (r9 != r1) goto L6a
            return r1
        L6a:
            r9 = r8
        L6b:
            r11 = 6
            r2 = 0
            if (r10 == r11) goto L99
            r11 = 7
            if (r10 == r11) goto L89
            r11 = 9
            if (r10 == r11) goto L79
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L79:
            org.informatika.sirekap.repository.CertmanRepository r9 = r9.certmanRepository
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r9 = r9.userUnobtainedCert(r0)
            if (r9 != r1) goto L86
            return r1
        L86:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L89:
            org.informatika.sirekap.repository.CertmanRepository r9 = r9.certmanRepository
            r0.L$0 = r2
            r0.label = r4
            java.lang.Object r9 = r9.successInsertKeystore(r0)
            if (r9 != r1) goto L96
            return r1
        L96:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L99:
            org.informatika.sirekap.repository.CertmanRepository r9 = r9.certmanRepository
            r0.L$0 = r2
            r0.label = r5
            java.lang.Object r9 = r9.reportFailed(r0)
            if (r9 != r1) goto La6
            return r1
        La6:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.login.LoginViewModel.reportUserStatus(android.content.Context, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void doLogin(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        LoginTask.DefaultImpls.login$default(this.loginTask, context, null, 2, null);
    }

    public final void setup() {
        this.backgroundProcessRepository.deleteById("login");
    }

    public final LiveData<BackgroundProcess> getBackgroundProcessLogin() {
        return this.backgroundProcessLogin;
    }

    public final LiveData<Boolean> isLoading() {
        return this.isLoading;
    }

    public final void checkCertificateAndToken() {
        ActiveProfile activeProfile = this.authRepository.getActiveProfile();
        UserInformation userInformation = this.authRepository.getUserInformation();
        Certificate certificate = AndroidKeystoreFacade.INSTANCE.getCertificate(AndroidKeystoreFacade.LOCAL_ALIAS_NAME);
        if (certificate == null) {
            throw new Exception("Device belum dilakukan inisialisasi");
        }
        X500Name x500Name = new X500Name(((X509Certificate) certificate).getSubjectDN().toString());
        if (x500Name.getRDNs(BCStyle.CN).length != 1 || x500Name.getRDNs(BCStyle.UID).length != 1 || x500Name.getRDNs(BCStyle.L).length != 1) {
            throw new Exception("Kunci digital tidak valid");
        }
        String obj = x500Name.getRDNs(BCStyle.UID)[0].getFirst().getValue().toString();
        String obj2 = x500Name.getRDNs(BCStyle.L)[0].getFirst().getValue().toString();
        if (!Intrinsics.areEqual(userInformation != null ? userInformation.getUserId() : null, obj)) {
            throw new Exception("Perangkat ini telah teregistrasi dengan akun lain");
        }
        if (!Intrinsics.areEqual(activeProfile != null ? activeProfile.getKode_wilayah() : null, obj2)) {
            throw new Exception("Perangkat Anda terdaftar pada Wilayah yang berbeda dengan Anda. Lakukan inisialisasi Perangkat dengan menghapus data aplikasi");
        }
    }

    public final void logout() {
        this.authRepository.logout();
        this.getLoggedInUserUseCase.refresh();
    }

    public final ActiveProfile getActiveProfile() {
        return this.authRepository.getActiveProfile();
    }
}
