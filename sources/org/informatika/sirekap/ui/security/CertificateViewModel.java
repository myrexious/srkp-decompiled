package org.informatika.sirekap.ui.security;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.cert.Certificate;
import java.security.spec.ECGenParameterSpec;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import net.openid.appauth.AuthorizationRequest;
import org.apache.commons.text.lookup.StringLookupFactory;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.db.dao.SecurityDao;
import org.informatika.sirekap.model.KeyState;
import org.informatika.sirekap.model.SecurityProperties;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.CertmanRepository;
import org.informatika.sirekap.repository.UserRepository;
import org.informatika.sirekap.support.security.AndroidKeystoreFacade;
import org.informatika.sirekap.support.security.PKIFacade;
import org.informatika.sirekap.support.worker.security.CertificateCheckerTask;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import org.informatika.sirekap.usecase.AuthRequestUseCase;
import org.informatika.sirekap.usecase.GetLoggedInUserUseCase;

/* compiled from: CertificateViewModel.kt */
@Metadata(d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0007\u0018\u00002\u00020\u0001BC\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u001e\u00104\u001a\u0002052\u0006\u00106\u001a\u0002072\f\u00108\u001a\b\u0012\u0004\u0012\u00020:09H\u0002J\u0019\u0010;\u001a\u00020)2\u0006\u0010<\u001a\u00020\u001dH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010=J\u0010\u0010>\u001a\u0002052\b\b\u0002\u0010?\u001a\u00020\u001dJ\u0019\u0010@\u001a\u0002052\u0006\u00106\u001a\u000207H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010AJ!\u0010B\u001a\u0002052\u0006\u00106\u001a\u0002072\u0006\u0010C\u001a\u00020\u001dH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010DJ!\u0010E\u001a\u0002052\u0006\u00106\u001a\u0002072\u0006\u0010F\u001a\u00020GH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010HJ\u001f\u0010I\u001a\u0002052\f\u0010J\u001a\b\u0012\u0004\u0012\u00020:09H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010KJ\u001f\u0010L\u001a\u0002052\f\u0010J\u001a\b\u0012\u0004\u0012\u00020:09H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010KJ1\u0010M\u001a\u0002052\u0006\u0010N\u001a\u00020\u001d2\u0006\u0010O\u001a\u00020\u001d2\u0006\u0010P\u001a\u00020\u001d2\u0006\u0010Q\u001a\u00020\u001dH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010RJ\u001b\u0010S\u001a\u0004\u0018\u00010\u001d2\u0006\u00106\u001a\u000207H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010AJ?\u0010T\u001a\u0002052\u0006\u0010U\u001a\u00020V2\f\u0010W\u001a\b\u0012\u0004\u0012\u00020Y0X2!\u0010Z\u001a\u001d\u0012\u0013\u0012\u001107¢\u0006\f\b\\\u0012\b\b]\u0012\u0004\b\b(6\u0012\u0004\u0012\u0002050[J\u000e\u0010^\u001a\u0002052\u0006\u00106\u001a\u000207J!\u0010_\u001a\u00020#2\u0006\u00106\u001a\u0002072\u0006\u0010`\u001a\u00020\u001dH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010DJ\u0019\u0010a\u001a\u0002052\u0006\u00106\u001a\u000207H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010AJ\u0019\u0010b\u001a\u0002052\u0006\u00106\u001a\u000207H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010AJ\u0019\u0010c\u001a\u0002052\u0006\u00106\u001a\u000207H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010AJ\u000e\u0010d\u001a\u0002052\u0006\u0010e\u001a\u00020\u001dJ\u0019\u0010f\u001a\u0002052\u0006\u00106\u001a\u000207H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010AJ+\u0010g\u001a\b\u0012\u0004\u0012\u00020:0h2\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u00020:2\u0006\u0010i\u001a\u00020\u001dH\u0002¢\u0006\u0002\u0010jJ\b\u0010k\u001a\u00020lH\u0002J\u000e\u0010m\u001a\u0002052\u0006\u00106\u001a\u000207J\u0019\u0010n\u001a\u0002052\u0006\u00106\u001a\u000207H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010AJ\u0011\u0010o\u001a\u000205H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010pJ\u0011\u0010q\u001a\u000205H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010pJ\u0006\u0010r\u001a\u000205J!\u0010s\u001a\u0002052\u0006\u0010t\u001a\u00020)2\u0006\u0010<\u001a\u00020\u001dH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010uJ\u001b\u0010v\u001a\u0002052\b\u0010w\u001a\u0004\u0018\u00010\u001dH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010=J+\u0010x\u001a\u0002052\u0006\u0010y\u001a\u00020\u001d2\u0006\u0010<\u001a\u00020\u001d2\b\b\u0002\u0010t\u001a\u00020)H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010zR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0019\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\"\u001a\u0010\u0012\f\u0012\n $*\u0004\u0018\u00010#0#0\u001c¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001fR\u001f\u0010%\u001a\u0010\u0012\f\u0012\n $*\u0004\u0018\u00010#0#0\u001c¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001fR\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020#0\u001c¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001fR\u001f\u0010'\u001a\u0010\u0012\f\u0012\n $*\u0004\u0018\u00010#0#0\u001c¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001fR\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u001c¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001fR\u001f\u0010+\u001a\u0010\u0012\f\u0012\n $*\u0004\u0018\u00010,0,0\u001c¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001fR\u000e\u0010.\u001a\u00020/X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0017\u00102\u001a\b\u0012\u0004\u0012\u00020#0\u001c¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u001f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006{"}, d2 = {"Lorg/informatika/sirekap/ui/security/CertificateViewModel;", "Landroidx/lifecycle/ViewModel;", "certmanRepository", "Lorg/informatika/sirekap/repository/CertmanRepository;", "authRepository", "Lorg/informatika/sirekap/repository/AuthRepository;", "appDatabase", "Lorg/informatika/sirekap/db/AppDatabase;", "sharedPreferences", "Landroid/content/SharedPreferences;", "userRepository", "Lorg/informatika/sirekap/repository/UserRepository;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "certificateCheckerWorker", "Lorg/informatika/sirekap/support/worker/security/CertificateCheckerTask;", "(Lorg/informatika/sirekap/repository/CertmanRepository;Lorg/informatika/sirekap/repository/AuthRepository;Lorg/informatika/sirekap/db/AppDatabase;Landroid/content/SharedPreferences;Lorg/informatika/sirekap/repository/UserRepository;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;Lorg/informatika/sirekap/support/worker/security/CertificateCheckerTask;)V", "getAppDatabase", "()Lorg/informatika/sirekap/db/AppDatabase;", "authRequestUseCase", "Lorg/informatika/sirekap/usecase/AuthRequestUseCase;", "getAuthRequestUseCase", "()Lorg/informatika/sirekap/usecase/AuthRequestUseCase;", "getCertificateCheckerWorker", "()Lorg/informatika/sirekap/support/worker/security/CertificateCheckerTask;", "getEncryptedSharedPreferences", "()Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "errorMessage", "Landroidx/lifecycle/MutableLiveData;", "", "getErrorMessage", "()Landroidx/lifecycle/MutableLiveData;", "getLoggedInUserUseCase", "Lorg/informatika/sirekap/usecase/GetLoggedInUserUseCase;", "isCheckButtonEnabled", "", "kotlin.jvm.PlatformType", "isFailedAuthentication", "isLoading", "isSuccess", "keyState", "Lorg/informatika/sirekap/model/KeyState;", "getKeyState", "retryCounter", "", "getRetryCounter", "securityPropertiesDao", "Lorg/informatika/sirekap/db/dao/SecurityDao;", "getSharedPreferences", "()Landroid/content/SharedPreferences;", "shouldRelogin", "getShouldRelogin", "checkChain", "", "context", "Landroid/content/Context;", "certificate", "", "Ljava/security/cert/Certificate;", "checkCurrentStatus", StringLookupFactory.KEY_PROPERTIES, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearKeystore", "alias", "continueGeneratingCertificate", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doBsreCertificateDownload", "challengeToken", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doBsreCertificateDownloadV2", "payload", "Lorg/informatika/sirekap/api/response/BsreV2ChallengeResponsePayload;", "(Landroid/content/Context;Lorg/informatika/sirekap/api/response/BsreV2ChallengeResponsePayload;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doBsreRegistration", "chained", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doBsreRegistrationV2", "doEnqueueCertificateRequest", "firebaseId", "csr", "deviceId", "gsfId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doGetCertificateData", "doLocalAuthentication", AuthorizationRequest.ResponseMode.FRAGMENT, "Landroidx/fragment/app/Fragment;", "activityResultLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "successCallback", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "doLogout", "doSirekapCertificateDownload", "url", "downloadBsreCertificate", "downloadBsreCertificateV2", "ejbcaBypass", "failedAuthentication", "errString", "generateCertificate", "generateChain", "", "type", "(Landroid/content/Context;Ljava/security/cert/Certificate;Ljava/lang/String;)[Ljava/security/cert/Certificate;", "generateECPair", "Ljava/security/KeyPair;", "generateSymmetricKey", "getGeneratedCertificate", "laporEmergency", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "laporKeyless", "logout", "setCurrentStatus", NotificationCompat.CATEGORY_STATUS, "(Lorg/informatika/sirekap/model/KeyState;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setErrorMessages", "message", "setGenerationFailed", "error", "(Ljava/lang/String;Ljava/lang/String;Lorg/informatika/sirekap/model/KeyState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class CertificateViewModel extends ViewModel {
    private final AppDatabase appDatabase;
    private final AuthRepository authRepository;
    private final AuthRequestUseCase authRequestUseCase;
    private final CertificateCheckerTask certificateCheckerWorker;
    private final CertmanRepository certmanRepository;
    private final EncryptedSharedPreferences encryptedSharedPreferences;
    private final MutableLiveData<String> errorMessage;
    private final GetLoggedInUserUseCase getLoggedInUserUseCase;
    private final MutableLiveData<Boolean> isCheckButtonEnabled;
    private final MutableLiveData<Boolean> isFailedAuthentication;
    private final MutableLiveData<Boolean> isLoading;
    private final MutableLiveData<Boolean> isSuccess;
    private final MutableLiveData<KeyState> keyState;
    private final MutableLiveData<Integer> retryCounter;
    private final SecurityDao securityPropertiesDao;
    private final SharedPreferences sharedPreferences;
    private final MutableLiveData<Boolean> shouldRelogin;

    public final AppDatabase getAppDatabase() {
        return this.appDatabase;
    }

    public final SharedPreferences getSharedPreferences() {
        return this.sharedPreferences;
    }

    public final EncryptedSharedPreferences getEncryptedSharedPreferences() {
        return this.encryptedSharedPreferences;
    }

    public final CertificateCheckerTask getCertificateCheckerWorker() {
        return this.certificateCheckerWorker;
    }

    @Inject
    public CertificateViewModel(CertmanRepository certmanRepository, AuthRepository authRepository, AppDatabase appDatabase, SharedPreferences sharedPreferences, UserRepository userRepository, EncryptedSharedPreferences encryptedSharedPreferences, CertificateCheckerTask certificateCheckerWorker) {
        Intrinsics.checkNotNullParameter(certmanRepository, "certmanRepository");
        Intrinsics.checkNotNullParameter(authRepository, "authRepository");
        Intrinsics.checkNotNullParameter(appDatabase, "appDatabase");
        Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
        Intrinsics.checkNotNullParameter(userRepository, "userRepository");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Intrinsics.checkNotNullParameter(certificateCheckerWorker, "certificateCheckerWorker");
        this.certmanRepository = certmanRepository;
        this.authRepository = authRepository;
        this.appDatabase = appDatabase;
        this.sharedPreferences = sharedPreferences;
        this.encryptedSharedPreferences = encryptedSharedPreferences;
        this.certificateCheckerWorker = certificateCheckerWorker;
        this.securityPropertiesDao = appDatabase.securityDao();
        this.keyState = new MutableLiveData<>();
        this.errorMessage = new MutableLiveData<>(null);
        this.shouldRelogin = new MutableLiveData<>(false);
        this.authRequestUseCase = new AuthRequestUseCase(authRepository);
        this.getLoggedInUserUseCase = new GetLoggedInUserUseCase(authRepository, userRepository);
        this.isLoading = new MutableLiveData<>(false);
        this.isFailedAuthentication = new MutableLiveData<>(false);
        this.isSuccess = new MutableLiveData<>(false);
        this.isCheckButtonEnabled = new MutableLiveData<>(false);
        this.retryCounter = new MutableLiveData<>(0);
    }

    public final MutableLiveData<KeyState> getKeyState() {
        return this.keyState;
    }

    public final MutableLiveData<String> getErrorMessage() {
        return this.errorMessage;
    }

    public final MutableLiveData<Boolean> getShouldRelogin() {
        return this.shouldRelogin;
    }

    public final AuthRequestUseCase getAuthRequestUseCase() {
        return this.authRequestUseCase;
    }

    public final MutableLiveData<Boolean> isLoading() {
        return this.isLoading;
    }

    public final MutableLiveData<Boolean> isFailedAuthentication() {
        return this.isFailedAuthentication;
    }

    public final MutableLiveData<Boolean> isSuccess() {
        return this.isSuccess;
    }

    public final MutableLiveData<Boolean> isCheckButtonEnabled() {
        return this.isCheckButtonEnabled;
    }

    public final MutableLiveData<Integer> getRetryCounter() {
        return this.retryCounter;
    }

    public final void logout() {
        this.authRepository.logout();
        this.getLoggedInUserUseCase.refresh();
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0099  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object setErrorMessages(java.lang.String r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof org.informatika.sirekap.ui.security.CertificateViewModel$setErrorMessages$1
            if (r0 == 0) goto L14
            r0 = r10
            org.informatika.sirekap.ui.security.CertificateViewModel$setErrorMessages$1 r0 = (org.informatika.sirekap.ui.security.CertificateViewModel$setErrorMessages$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            org.informatika.sirekap.ui.security.CertificateViewModel$setErrorMessages$1 r0 = new org.informatika.sirekap.ui.security.CertificateViewModel$setErrorMessages$1
            r0.<init>(r8, r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            java.lang.String r7 = "certificate_generation_error"
            if (r2 == 0) goto L52
            if (r2 == r6) goto L4e
            if (r2 == r5) goto L42
            if (r2 == r4) goto L3e
            if (r2 != r3) goto L36
            kotlin.ResultKt.throwOnFailure(r10)
            goto La8
        L36:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L3e:
            kotlin.ResultKt.throwOnFailure(r10)
            goto L96
        L42:
            java.lang.Object r9 = r0.L$1
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r2 = r0.L$0
            org.informatika.sirekap.ui.security.CertificateViewModel r2 = (org.informatika.sirekap.ui.security.CertificateViewModel) r2
            kotlin.ResultKt.throwOnFailure(r10)
            goto L7a
        L4e:
            kotlin.ResultKt.throwOnFailure(r10)
            goto L67
        L52:
            kotlin.ResultKt.throwOnFailure(r10)
            androidx.lifecycle.MutableLiveData<java.lang.String> r10 = r8.errorMessage
            r10.postValue(r9)
            if (r9 != 0) goto L6a
            org.informatika.sirekap.db.dao.SecurityDao r9 = r8.securityPropertiesDao
            r0.label = r6
            java.lang.Object r9 = r9.deleteValue(r7, r0)
            if (r9 != r1) goto L67
            return r1
        L67:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L6a:
            org.informatika.sirekap.db.dao.SecurityDao r10 = r8.securityPropertiesDao
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r5
            java.lang.Object r10 = r10.getValue(r7, r0)
            if (r10 != r1) goto L79
            return r1
        L79:
            r2 = r8
        L7a:
            r5 = 0
            if (r10 != 0) goto L99
            org.informatika.sirekap.db.dao.SecurityDao r10 = r2.securityPropertiesDao
            org.informatika.sirekap.model.SecurityProperties[] r2 = new org.informatika.sirekap.model.SecurityProperties[r6]
            org.informatika.sirekap.model.SecurityProperties r3 = new org.informatika.sirekap.model.SecurityProperties
            r3.<init>(r7, r9)
            r9 = 0
            r2[r9] = r3
            r0.L$0 = r5
            r0.L$1 = r5
            r0.label = r4
            java.lang.Object r9 = r10.insertValue(r2, r0)
            if (r9 != r1) goto L96
            return r1
        L96:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L99:
            org.informatika.sirekap.db.dao.SecurityDao r10 = r2.securityPropertiesDao
            r0.L$0 = r5
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r9 = r10.updateValue(r7, r9, r0)
            if (r9 != r1) goto La8
            return r1
        La8:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.security.CertificateViewModel.setErrorMessages(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object checkCurrentStatus(java.lang.String r9, kotlin.coroutines.Continuation<? super org.informatika.sirekap.model.KeyState> r10) {
        /*
            Method dump skipped, instructions count: 230
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.security.CertificateViewModel.checkCurrentStatus(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ void clearKeystore$default(CertificateViewModel certificateViewModel, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = AndroidKeystoreFacade.LOCAL_ALIAS_NAME;
        }
        certificateViewModel.clearKeystore(str);
    }

    public final void clearKeystore(String alias) {
        Intrinsics.checkNotNullParameter(alias, "alias");
        AndroidKeystoreFacade.INSTANCE.deleteEntry(alias);
    }

    public final Object setCurrentStatus(KeyState keyState, String str, Continuation<? super Unit> continuation) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new CertificateViewModel$setCurrentStatus$2(this, str, keyState, null), 3, null);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object setGenerationFailed$default(CertificateViewModel certificateViewModel, String str, String str2, KeyState keyState, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            keyState = KeyState.GENERATION_FAILED;
        }
        return certificateViewModel.setGenerationFailed(str, str2, keyState, continuation);
    }

    public final Object setGenerationFailed(String str, String str2, KeyState keyState, Continuation<? super Unit> continuation) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new CertificateViewModel$setGenerationFailed$2(this, keyState, str2, str, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0098 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00d1 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object doEnqueueCertificateRequest(java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, kotlin.coroutines.Continuation<? super kotlin.Unit> r22) {
        /*
            Method dump skipped, instructions count: 213
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.security.CertificateViewModel.doEnqueueCertificateRequest(java.lang.String, java.lang.String, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void doLogout(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        new AlertDialog.Builder(context).setCancelable(false).setTitle("Sesi anda telah habis").setMessage("Silahkan lakukan login kembali").setPositiveButton("Logout", new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.security.CertificateViewModel$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: org.informatika.sirekap.ui.security.CertificateViewModel$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                CertificateViewModel.doLogout$lambda$1(CertificateViewModel.this, dialogInterface);
            }
        }).show();
    }

    public static final void doLogout$lambda$1(CertificateViewModel this$0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.logout();
        this$0.isLoading.postValue(false);
        this$0.shouldRelogin.postValue(true);
    }

    public final void generateSymmetricKey(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new CertificateViewModel$generateSymmetricKey$1(this, context, null), 3, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x00c5 A[Catch: Exception -> 0x0053, TryCatch #1 {Exception -> 0x0053, blocks: (B:78:0x004c, B:102:0x00bf, B:104:0x00c5, B:105:0x00ca), top: B:122:0x004c }] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x014e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object continueGeneratingCertificate(android.content.Context r22, kotlin.coroutines.Continuation<? super kotlin.Unit> r23) {
        /*
            Method dump skipped, instructions count: 338
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.security.CertificateViewModel.continueGeneratingCertificate(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x00a6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0111 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x013a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:158:0x00ad A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object doGetCertificateData(android.content.Context r14, kotlin.coroutines.Continuation<? super java.lang.String> r15) {
        /*
            Method dump skipped, instructions count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.security.CertificateViewModel.doGetCertificateData(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final Certificate[] generateChain(Context context, Certificate certificate, String str) {
        Certificate[] cACertificateChain = PKIFacade.INSTANCE.getCACertificateChain(context, str);
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.add(certificate);
        spreadBuilder.addSpread(cACertificateChain);
        return (Certificate[]) spreadBuilder.toArray(new Certificate[spreadBuilder.size()]);
    }

    private final void checkChain(Context context, List<? extends Certificate> list) {
        PKIFacade.INSTANCE.checkCertificateChainOnly(PKIFacade.INSTANCE.getTrustAnchor(context), list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:104:0x00a5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x00e9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x006c  */
    /* JADX WARN: Type inference failed for: r14v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object doSirekapCertificateDownload(android.content.Context r17, java.lang.String r18, kotlin.coroutines.Continuation<? super java.lang.Boolean> r19) {
        /*
            Method dump skipped, instructions count: 273
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.security.CertificateViewModel.doSirekapCertificateDownload(android.content.Context, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:1|(2:3|(8:5|6|7|(1:(1:(1:(3:12|13|14)(2:16|17))(2:18|19))(4:25|26|27|(2:29|30)(2:31|(1:33))))(4:34|35|36|(2:38|(1:40)(3:41|27|(0)(0)))(3:42|13|14))|20|(1:22)|23|24))|49|6|7|(0)(0)|20|(0)|23|24|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0053, code lost:
        r13 = e;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0073 A[Catch: Exception -> 0x0053, TryCatch #0 {Exception -> 0x0053, blocks: (B:71:0x0043, B:92:0x0083, B:94:0x008b, B:95:0x00b8, B:74:0x004f, B:85:0x006f, B:87:0x0073, B:89:0x0076), top: B:106:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0076 A[Catch: Exception -> 0x0053, TryCatch #0 {Exception -> 0x0053, blocks: (B:71:0x0043, B:92:0x0083, B:94:0x008b, B:95:0x00b8, B:74:0x004f, B:85:0x006f, B:87:0x0073, B:89:0x0076), top: B:106:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x008b A[Catch: Exception -> 0x0053, TryCatch #0 {Exception -> 0x0053, blocks: (B:71:0x0043, B:92:0x0083, B:94:0x008b, B:95:0x00b8, B:74:0x004f, B:85:0x006f, B:87:0x0073, B:89:0x0076), top: B:106:0x0026 }] */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getGeneratedCertificate(android.content.Context r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            Method dump skipped, instructions count: 282
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.security.CertificateViewModel.getGeneratedCertificate(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void doLocalAuthentication(Fragment fragment, ActivityResultLauncher<Intent> activityResultLauncher, Function1<? super Context, Unit> successCallback) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(activityResultLauncher, "activityResultLauncher");
        Intrinsics.checkNotNullParameter(successCallback, "successCallback");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new CertificateViewModel$doLocalAuthentication$1(fragment, activityResultLauncher, this, successCallback, null), 3, null);
    }

    public final void failedAuthentication(String errString) {
        Intrinsics.checkNotNullParameter(errString, "errString");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new CertificateViewModel$failedAuthentication$1(this, errString, null), 3, null);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:1|(2:3|(5:5|6|(1:(1:(1:(3:11|12|13)(2:15|16))(6:17|18|19|20|12|13))(1:27))(2:44|(1:46)(1:47))|28|(3:36|37|(1:39)(4:40|20|12|13))(2:34|35)))|48|6|(0)(0)|28|(1:30)|36|37|(0)(0)|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x00bb, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x00bc, code lost:
        r3 = r8;
     */
    /* JADX WARN: Removed duplicated region for block: B:61:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x009e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x00ff A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object generateCertificate(android.content.Context r18, kotlin.coroutines.Continuation<? super kotlin.Unit> r19) {
        /*
            Method dump skipped, instructions count: 259
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.security.CertificateViewModel.generateCertificate(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final KeyPair generateECPair() {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        keyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
        KeyPair generateKeyPair = keyPairGenerator.generateKeyPair();
        Intrinsics.checkNotNullExpressionValue(generateKeyPair, "keyGen.generateKeyPair()");
        return generateKeyPair;
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x010f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01c9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object doBsreCertificateDownload(android.content.Context r19, java.lang.String r20, kotlin.coroutines.Continuation<? super kotlin.Unit> r21) {
        /*
            Method dump skipped, instructions count: 465
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.security.CertificateViewModel.doBsreCertificateDownload(android.content.Context, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00a2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0126  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object doBsreCertificateDownloadV2(android.content.Context r17, org.informatika.sirekap.api.response.BsreV2ChallengeResponsePayload r18, kotlin.coroutines.Continuation<? super kotlin.Unit> r19) {
        /*
            Method dump skipped, instructions count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.security.CertificateViewModel.doBsreCertificateDownloadV2(android.content.Context, org.informatika.sirekap.api.response.BsreV2ChallengeResponsePayload, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0094 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x00b4 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object doBsreRegistration(java.util.List<? extends java.security.cert.Certificate> r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof org.informatika.sirekap.ui.security.CertificateViewModel$doBsreRegistration$1
            if (r0 == 0) goto L14
            r0 = r11
            org.informatika.sirekap.ui.security.CertificateViewModel$doBsreRegistration$1 r0 = (org.informatika.sirekap.ui.security.CertificateViewModel$doBsreRegistration$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            org.informatika.sirekap.ui.security.CertificateViewModel$doBsreRegistration$1 r0 = new org.informatika.sirekap.ui.security.CertificateViewModel$doBsreRegistration$1
            r0.<init>(r9, r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            if (r2 == 0) goto L57
            if (r2 == r5) goto L44
            if (r2 == r4) goto L3a
            if (r2 != r3) goto L32
            kotlin.ResultKt.throwOnFailure(r11)
            goto Lb5
        L32:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L3a:
            java.lang.Object r10 = r0.L$0
            org.informatika.sirekap.ui.security.CertificateViewModel r10 = (org.informatika.sirekap.ui.security.CertificateViewModel) r10
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Exception -> L42
            goto La2
        L42:
            r11 = move-exception
            goto L99
        L44:
            java.lang.Object r10 = r0.L$2
            org.informatika.sirekap.repository.CertmanRepository r10 = (org.informatika.sirekap.repository.CertmanRepository) r10
            java.lang.Object r2 = r0.L$1
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r5 = r0.L$0
            org.informatika.sirekap.ui.security.CertificateViewModel r5 = (org.informatika.sirekap.ui.security.CertificateViewModel) r5
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Exception -> L54
            goto L74
        L54:
            r11 = move-exception
            r10 = r5
            goto L99
        L57:
            kotlin.ResultKt.throwOnFailure(r11)
            org.informatika.sirekap.repository.CertmanRepository r11 = r9.certmanRepository     // Catch: java.lang.Exception -> L97
            org.informatika.sirekap.db.dao.SecurityDao r2 = r9.securityPropertiesDao     // Catch: java.lang.Exception -> L97
            java.lang.String r7 = "certificate_request_id"
            r0.L$0 = r9     // Catch: java.lang.Exception -> L97
            r0.L$1 = r10     // Catch: java.lang.Exception -> L97
            r0.L$2 = r11     // Catch: java.lang.Exception -> L97
            r0.label = r5     // Catch: java.lang.Exception -> L97
            java.lang.Object r2 = r2.getValue(r7, r0)     // Catch: java.lang.Exception -> L97
            if (r2 != r1) goto L6f
            return r1
        L6f:
            r5 = r9
            r8 = r2
            r2 = r10
            r10 = r11
            r11 = r8
        L74:
            org.informatika.sirekap.model.SecurityProperties r11 = (org.informatika.sirekap.model.SecurityProperties) r11     // Catch: java.lang.Exception -> L54
            if (r11 == 0) goto L7e
            java.lang.String r11 = r11.getValue()     // Catch: java.lang.Exception -> L54
            if (r11 != 0) goto L80
        L7e:
            java.lang.String r11 = ""
        L80:
            java.lang.Iterable r2 = (java.lang.Iterable) r2     // Catch: java.lang.Exception -> L54
            java.util.List r2 = kotlin.collections.CollectionsKt.toList(r2)     // Catch: java.lang.Exception -> L54
            r0.L$0 = r5     // Catch: java.lang.Exception -> L54
            r0.L$1 = r6     // Catch: java.lang.Exception -> L54
            r0.L$2 = r6     // Catch: java.lang.Exception -> L54
            r0.label = r4     // Catch: java.lang.Exception -> L54
            java.lang.Object r10 = r10.registerBsre(r11, r2, r0)     // Catch: java.lang.Exception -> L54
            if (r10 != r1) goto L95
            return r1
        L95:
            r10 = r5
            goto La2
        L97:
            r11 = move-exception
            r10 = r9
        L99:
            com.google.firebase.crashlytics.FirebaseCrashlytics r2 = com.google.firebase.crashlytics.FirebaseCrashlytics.getInstance()
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            r2.recordException(r11)
        La2:
            org.informatika.sirekap.model.KeyState r11 = org.informatika.sirekap.model.KeyState.DOWNLOADED
            r0.L$0 = r6
            r0.L$1 = r6
            r0.L$2 = r6
            r0.label = r3
            java.lang.String r2 = "bsre_certificate_status"
            java.lang.Object r10 = r10.setCurrentStatus(r11, r2, r0)
            if (r10 != r1) goto Lb5
            return r1
        Lb5:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.security.CertificateViewModel.doBsreRegistration(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x008f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x009f A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object doBsreRegistrationV2(java.util.List<? extends java.security.cert.Certificate> r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof org.informatika.sirekap.ui.security.CertificateViewModel$doBsreRegistrationV2$1
            if (r0 == 0) goto L14
            r0 = r10
            org.informatika.sirekap.ui.security.CertificateViewModel$doBsreRegistrationV2$1 r0 = (org.informatika.sirekap.ui.security.CertificateViewModel$doBsreRegistrationV2$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            org.informatika.sirekap.ui.security.CertificateViewModel$doBsreRegistrationV2$1 r0 = new org.informatika.sirekap.ui.security.CertificateViewModel$doBsreRegistrationV2$1
            r0.<init>(r8, r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            if (r2 == 0) goto L52
            if (r2 == r5) goto L42
            if (r2 == r4) goto L3a
            if (r2 != r3) goto L32
            kotlin.ResultKt.throwOnFailure(r10)
            goto La0
        L32:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L3a:
            java.lang.Object r9 = r0.L$0
            org.informatika.sirekap.ui.security.CertificateViewModel r9 = (org.informatika.sirekap.ui.security.CertificateViewModel) r9
            kotlin.ResultKt.throwOnFailure(r10)
            goto L91
        L42:
            java.lang.Object r9 = r0.L$2
            org.informatika.sirekap.repository.CertmanRepository r9 = (org.informatika.sirekap.repository.CertmanRepository) r9
            java.lang.Object r2 = r0.L$1
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r5 = r0.L$0
            org.informatika.sirekap.ui.security.CertificateViewModel r5 = (org.informatika.sirekap.ui.security.CertificateViewModel) r5
            kotlin.ResultKt.throwOnFailure(r10)
            goto L6f
        L52:
            kotlin.ResultKt.throwOnFailure(r10)
            org.informatika.sirekap.repository.CertmanRepository r10 = r8.certmanRepository
            org.informatika.sirekap.db.dao.SecurityDao r2 = r8.securityPropertiesDao
            r0.L$0 = r8
            r0.L$1 = r9
            r0.L$2 = r10
            r0.label = r5
            java.lang.String r5 = "certificate_request_id"
            java.lang.Object r2 = r2.getValue(r5, r0)
            if (r2 != r1) goto L6a
            return r1
        L6a:
            r5 = r8
            r7 = r2
            r2 = r9
            r9 = r10
            r10 = r7
        L6f:
            org.informatika.sirekap.model.SecurityProperties r10 = (org.informatika.sirekap.model.SecurityProperties) r10
            if (r10 == 0) goto L79
            java.lang.String r10 = r10.getValue()
            if (r10 != 0) goto L7b
        L79:
            java.lang.String r10 = ""
        L7b:
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.List r2 = kotlin.collections.CollectionsKt.toList(r2)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r6
            r0.label = r4
            java.lang.Object r9 = r9.registerBsreV2(r10, r2, r0)
            if (r9 != r1) goto L90
            return r1
        L90:
            r9 = r5
        L91:
            org.informatika.sirekap.model.KeyState r10 = org.informatika.sirekap.model.KeyState.DOWNLOADED
            r0.L$0 = r6
            r0.label = r3
            java.lang.String r2 = "bsre_certificate_status"
            java.lang.Object r9 = r9.setCurrentStatus(r10, r2, r0)
            if (r9 != r1) goto La0
            return r1
        La0:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.security.CertificateViewModel.doBsreRegistrationV2(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object ejbcaBypass(Context context, Continuation<? super Unit> continuation) {
        Object currentStatus = setCurrentStatus(KeyState.DOWNLOADED, SecurityProperties.LOCAL_CERTIFICATE_STATUS, continuation);
        return currentStatus == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? currentStatus : Unit.INSTANCE;
    }

    public final Object laporEmergency(Continuation<? super Unit> continuation) {
        Object reportFailed = this.certmanRepository.reportFailed(continuation);
        return reportFailed == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? reportFailed : Unit.INSTANCE;
    }

    public final Object laporKeyless(Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:1|(2:3|(6:5|6|7|(1:(1:(1:(1:(1:(4:14|15|16|17)(2:19|20))(4:21|22|23|24))(8:26|27|28|29|30|(1:32)|23|24))(6:46|47|48|(1:56)|52|(1:54)(5:55|30|(0)|23|24)))(3:57|58|59))(4:76|77|78|(1:80)(1:81))|60|(2:66|(4:68|(1:70)|71|(1:73)(6:74|48|(1:50)|56|52|(0)(0)))(3:75|16|17))(2:64|65)))|88|6|7|(0)(0)|60|(1:62)|66|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0054, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0055, code lost:
        r6 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x0095, code lost:
        r0 = e;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x00c9 A[Catch: Exception -> 0x0095, TryCatch #1 {Exception -> 0x0095, blocks: (B:122:0x007f, B:149:0x00f4, B:151:0x00f8, B:154:0x0100, B:125:0x008c, B:134:0x00ab, B:136:0x00b1, B:138:0x00b5, B:140:0x00c1, B:142:0x00c9, B:145:0x00db, B:163:0x0126), top: B:182:0x002e }] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0112 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0122 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0126 A[Catch: Exception -> 0x0095, TRY_ENTER, TRY_LEAVE, TryCatch #1 {Exception -> 0x0095, blocks: (B:122:0x007f, B:149:0x00f4, B:151:0x00f8, B:154:0x0100, B:125:0x008c, B:134:0x00ab, B:136:0x00b1, B:138:0x00b5, B:140:0x00c1, B:142:0x00c9, B:145:0x00db, B:163:0x0126), top: B:182:0x002e }] */
    /* JADX WARN: Removed duplicated region for block: B:175:0x01a1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:176:0x01a2  */
    /* JADX WARN: Type inference failed for: r4v0, types: [int] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v28 */
    /* JADX WARN: Type inference failed for: r4v39 */
    /* JADX WARN: Type inference failed for: r4v40 */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v2, types: [org.informatika.sirekap.ui.security.CertificateViewModel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object downloadBsreCertificateV2(android.content.Context r24, kotlin.coroutines.Continuation<? super kotlin.Unit> r25) {
        /*
            Method dump skipped, instructions count: 431
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.security.CertificateViewModel.downloadBsreCertificateV2(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:1|(2:3|(6:5|6|7|(1:(1:(1:(1:(1:(4:14|15|16|17)(2:19|20))(4:21|22|23|24))(8:26|27|28|29|30|(1:32)|23|24))(6:46|47|48|(1:56)|52|(1:54)(5:55|30|(0)|23|24)))(3:57|58|59))(4:76|77|78|(1:80)(1:81))|60|(2:66|(4:68|(1:70)|71|(1:73)(6:74|48|(1:50)|56|52|(0)(0)))(3:75|16|17))(2:64|65)))|88|6|7|(0)(0)|60|(1:62)|66|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0054, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0055, code lost:
        r6 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x0095, code lost:
        r0 = e;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x00c9 A[Catch: Exception -> 0x0095, TryCatch #1 {Exception -> 0x0095, blocks: (B:122:0x007f, B:149:0x00f4, B:151:0x00f8, B:154:0x0100, B:125:0x008c, B:134:0x00ab, B:136:0x00b1, B:138:0x00b5, B:140:0x00c1, B:142:0x00c9, B:145:0x00db, B:163:0x0126), top: B:182:0x002e }] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0112 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0122 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0126 A[Catch: Exception -> 0x0095, TRY_ENTER, TRY_LEAVE, TryCatch #1 {Exception -> 0x0095, blocks: (B:122:0x007f, B:149:0x00f4, B:151:0x00f8, B:154:0x0100, B:125:0x008c, B:134:0x00ab, B:136:0x00b1, B:138:0x00b5, B:140:0x00c1, B:142:0x00c9, B:145:0x00db, B:163:0x0126), top: B:182:0x002e }] */
    /* JADX WARN: Removed duplicated region for block: B:175:0x01a1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:176:0x01a2  */
    /* JADX WARN: Type inference failed for: r4v0, types: [int] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v28 */
    /* JADX WARN: Type inference failed for: r4v39 */
    /* JADX WARN: Type inference failed for: r4v40 */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v2, types: [org.informatika.sirekap.ui.security.CertificateViewModel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object downloadBsreCertificate(android.content.Context r24, kotlin.coroutines.Continuation<? super kotlin.Unit> r25) {
        /*
            Method dump skipped, instructions count: 431
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.security.CertificateViewModel.downloadBsreCertificate(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
