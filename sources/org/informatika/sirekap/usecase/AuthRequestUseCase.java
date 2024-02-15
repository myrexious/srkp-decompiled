package org.informatika.sirekap.usecase;

import android.content.Context;
import android.content.Intent;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.lifecycle.MutableLiveData;
import com.auth0.android.jwt.JWT;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.TokenResponse;
import org.apache.commons.lang3.StringUtils;
import org.informatika.sirekap.BuildConfig;
import org.informatika.sirekap.model.ActiveProfile;
import org.informatika.sirekap.model.UserInformation;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.support.OpenIdUtil;

/* compiled from: AuthRequestUseCase.kt */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\n\u001a\u00020\u0007J\u0082\u0001\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000e2!\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\f0\u00152%\u0010\u0018\u001a!\u0012\u0017\u0012\u00150\u0019j\u0002`\u001a¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\f0\u00152\u0006\u0010\u001c\u001a\u00020\u0007H\u0002Jh\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2!\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\f0\u00152%\u0010\u0018\u001a!\u0012\u0017\u0012\u00150\u0019j\u0002`\u001a¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\f0\u0015Jd\u0010$\u001a\u00020\f2\b\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010\u001c\u001a\u00020\u00072!\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\f0\u00152%\u0010\u0018\u001a!\u0012\u0017\u0012\u00150\u0019j\u0002`\u001a¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\f0\u0015H\u0002J\u001c\u0010'\u001a\u00020\f2\u0006\u0010\"\u001a\u00020#2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)J\u0019\u0010+\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020#H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010,JX\u0010+\u001a\u00020\f2\u0006\u0010\"\u001a\u00020#2!\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\f0\u00152%\u0010\u0018\u001a!\u0012\u0017\u0012\u00150\u0019j\u0002`\u001a¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\f0\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006-"}, d2 = {"Lorg/informatika/sirekap/usecase/AuthRequestUseCase;", "", "authRepository", "Lorg/informatika/sirekap/repository/AuthRepository;", "(Lorg/informatika/sirekap/repository/AuthRepository;)V", "isLoading", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "()Landroidx/lifecycle/MutableLiveData;", "isLocalTokenExpired", "proceedProcessToken", "", "userName", "", "activeProfile", "Lorg/informatika/sirekap/model/ActiveProfile;", "sub", "accessToken", "refreshToken", "onSuccess", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "onError", "Ljava/lang/Exception;", "Lkotlin/Exception;", "e", "isOnlySaveToken", "processToken", "result", "Landroidx/activity/result/ActivityResult;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "context", "Landroid/content/Context;", "saveNewToken", "resp", "Lnet/openid/appauth/TokenResponse;", "start", "authRequestLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "startRefreshToken", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class AuthRequestUseCase {
    private final AuthRepository authRepository;
    private final MutableLiveData<Boolean> isLoading;

    public AuthRequestUseCase(AuthRepository authRepository) {
        Intrinsics.checkNotNullParameter(authRepository, "authRepository");
        this.authRepository = authRepository;
        this.isLoading = new MutableLiveData<>(false);
    }

    public final MutableLiveData<Boolean> isLoading() {
        return this.isLoading;
    }

    public final boolean isLocalTokenExpired() {
        Boolean IS_CHECK_TOKEN_EXPIRED = BuildConfig.IS_CHECK_TOKEN_EXPIRED;
        Intrinsics.checkNotNullExpressionValue(IS_CHECK_TOKEN_EXPIRED, "IS_CHECK_TOKEN_EXPIRED");
        boolean z = false;
        if (IS_CHECK_TOKEN_EXPIRED.booleanValue()) {
            String accessToken = this.authRepository.getAccessToken();
            String str = accessToken;
            if (str == null || StringsKt.isBlank(str)) {
                z = true;
            }
            if (z) {
                return true;
            }
            return new Date(((long) (Math.floor(new Date().getTime() / 1000) * 1000)) + 300000).after(new JWT(accessToken).getExpiresAt());
        }
        return false;
    }

    public final void start(Context context, ActivityResultLauncher<Intent> authRequestLauncher) throws Exception {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(authRequestLauncher, "authRequestLauncher");
        this.isLoading.setValue(true);
        authRequestLauncher.launch(new AuthorizationService(context).getAuthorizationRequestIntent(new OpenIdUtil().getAuthRequestForKeySetup(null)));
    }

    public final void startRefreshToken(Context context, final Function1<? super ActiveProfile, Unit> onSuccess, final Function1<? super Exception, Unit> onError) throws Exception {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onError, "onError");
        String refreshToken = this.authRepository.getRefreshToken();
        String str = refreshToken;
        if (str == null || StringsKt.isBlank(str)) {
            onError.invoke(new Exception("Refresh token is invalid: '" + refreshToken + OperatorName.SHOW_TEXT_LINE));
            return;
        }
        this.isLoading.postValue(true);
        new AuthorizationService(context).performTokenRequest(new OpenIdUtil().getTokenRequest(refreshToken), new AuthorizationService.TokenResponseCallback() { // from class: org.informatika.sirekap.usecase.AuthRequestUseCase$$ExternalSyntheticLambda0
            @Override // net.openid.appauth.AuthorizationService.TokenResponseCallback
            public final void onTokenRequestCompleted(TokenResponse tokenResponse, AuthorizationException authorizationException) {
                AuthRequestUseCase.startRefreshToken$lambda$0(Function1.this, this, onSuccess, tokenResponse, authorizationException);
            }
        });
    }

    public static final void startRefreshToken$lambda$0(Function1 onError, AuthRequestUseCase this$0, Function1 onSuccess, TokenResponse tokenResponse, AuthorizationException authorizationException) {
        Intrinsics.checkNotNullParameter(onError, "$onError");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(onSuccess, "$onSuccess");
        if (authorizationException != null) {
            String message = authorizationException.getMessage();
            Throwable cause = authorizationException.getCause();
            Exception exc = new Exception("Error: " + message + StringUtils.SPACE + (cause != null ? cause.getMessage() : null));
            FirebaseCrashlytics.getInstance().recordException(exc);
            onError.invoke(exc);
            this$0.isLoading.setValue(false);
            return;
        }
        try {
            this$0.saveNewToken(tokenResponse, true, onSuccess, onError);
            this$0.isLoading.postValue(false);
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
            onError.invoke(new Exception("Access token is invalid: " + e + " '" + (tokenResponse != null ? tokenResponse.accessToken : null) + OperatorName.SHOW_TEXT_LINE));
            this$0.isLoading.postValue(false);
        }
    }

    public final Object startRefreshToken(Context context, Continuation<? super ActiveProfile> continuation) {
        SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(continuation));
        final SafeContinuation safeContinuation2 = safeContinuation;
        startRefreshToken(context, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.usecase.AuthRequestUseCase$startRefreshToken$3$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ActiveProfile activeProfile) {
                invoke2(activeProfile);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(ActiveProfile it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Continuation<ActiveProfile> continuation2 = safeContinuation2;
                Result.Companion companion = Result.Companion;
                continuation2.resumeWith(Result.m273constructorimpl(it));
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.usecase.AuthRequestUseCase$startRefreshToken$3$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Continuation<ActiveProfile> continuation2 = safeContinuation2;
                Result.Companion companion = Result.Companion;
                continuation2.resumeWith(Result.m273constructorimpl(ResultKt.createFailure(it)));
            }
        });
        Object orThrow = safeContinuation.getOrThrow();
        if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return orThrow;
    }

    public final void processToken(ActivityResult result, CoroutineScope coroutineScope, Context context, Function1<? super ActiveProfile, Unit> onSuccess, Function1<? super Exception, Unit> onError) {
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onError, "onError");
        if (result.getResultCode() == -1) {
            Intent data = result.getData();
            if (data != null) {
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AuthRequestUseCase$processToken$1$1(context, AuthorizationResponse.fromIntent(data), onError, this, onSuccess, null), 3, null);
                return;
            }
            return;
        }
        this.isLoading.setValue(false);
        onError.invoke(new Exception("Autentikasi dibatalkan oleh pengguna"));
    }

    public final void saveNewToken(TokenResponse tokenResponse, boolean z, Function1<? super ActiveProfile, Unit> function1, Function1<? super Exception, Unit> function12) {
        ActiveProfile activeProfile;
        String valueOf = String.valueOf(tokenResponse != null ? tokenResponse.refreshToken : null);
        JWT jwt = new JWT(String.valueOf(tokenResponse != null ? tokenResponse.accessToken : null));
        String str = tokenResponse != null ? tokenResponse.accessToken : null;
        if (str == null) {
            function12.invoke(new Exception("Access token is null"));
        } else {
            String asString = jwt.getClaim("sub").asString();
            if (asString == null) {
                function12.invoke(new Exception("'sub' is null"));
            } else {
                ActiveProfile activeProfile2 = (ActiveProfile) jwt.getClaim("active_profile").asObject(ActiveProfile.class);
                if (activeProfile2 != null) {
                    String lowerCase = activeProfile2.getNama_profil().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    activeProfile = ActiveProfile.copy$default(activeProfile2, lowerCase, null, null, null, null, null, 62, null);
                } else {
                    activeProfile = null;
                }
                if (activeProfile == null) {
                    function12.invoke(new Exception("Active profile is null"));
                } else if (StringsKt.isBlank(activeProfile.getNama_profil())) {
                    function12.invoke(new Exception("nama_profil invalid: '" + activeProfile.getNama_profil() + OperatorName.SHOW_TEXT_LINE));
                } else if (!activeProfile.isKodeWilayahValid()) {
                    function12.invoke(new Exception("kode_wilayah invalid: '" + activeProfile.getKode_wilayah() + OperatorName.SHOW_TEXT_LINE));
                } else if (!activeProfile.isRoleValid()) {
                    function12.invoke(new Exception("Aplikasi ini hanya dapat diakses oleh PPK dan KPPS: '" + activeProfile.getRole() + OperatorName.SHOW_TEXT_LINE));
                } else {
                    String jwtSub = this.authRepository.getJwtSub();
                    if (jwtSub != null) {
                        if (!Intrinsics.areEqual(jwtSub, asString)) {
                            function12.invoke(new Exception("Anda login menggunakan akun yang berbeda. Silakan logout terlebih dahulu jika Anda ingin mengganti akun."));
                            return;
                        }
                        String asString2 = jwt.getClaim("name").asString();
                        if (asString2 == null) {
                            function12.invoke(new Exception("Nama lengkap anda tidak terdaftar. Silahkan hubungi administrator"));
                            return;
                        } else {
                            proceedProcessToken(asString2, activeProfile, asString, str, valueOf, function1, function12, z);
                            return;
                        }
                    }
                    String asString3 = jwt.getClaim("name").asString();
                    if (asString3 == null) {
                        function12.invoke(new Exception("Nama lengkap anda tidak terdaftar. Silahkan hubungi administrator"));
                    } else {
                        proceedProcessToken(asString3, activeProfile, asString, str, valueOf, function1, function12, z);
                    }
                }
            }
        }
    }

    private final void proceedProcessToken(String str, ActiveProfile activeProfile, String str2, String str3, String str4, Function1<? super ActiveProfile, Unit> function1, Function1<? super Exception, Unit> function12, boolean z) {
        ActiveProfile activeProfile2 = this.authRepository.getActiveProfile();
        if (activeProfile2 != null) {
            if (!(Intrinsics.areEqual(activeProfile2.getNama_profil(), activeProfile.getNama_profil()) && Intrinsics.areEqual(activeProfile2.getKode_wilayah(), activeProfile.getKode_wilayah()) && Intrinsics.areEqual(activeProfile2.getRole(), activeProfile.getRole()))) {
                function12.invoke(new Exception("Anda memilih profil pemilihan yang berbeda. Silakan logout terlebih dahulu jika Anda ingin mengganti profil pemilihan."));
                return;
            }
            this.authRepository.saveAccessToken(str3);
            this.authRepository.saveRefreshToken(str4);
            if (!z) {
                this.authRepository.saveActiveProfile(activeProfile);
                this.authRepository.saveJwtSub(str2);
                this.authRepository.setUserInformation(new UserInformation(str2, str));
            }
            function1.invoke(activeProfile);
            return;
        }
        this.authRepository.saveAccessToken(str3);
        this.authRepository.saveRefreshToken(str4);
        if (!z) {
            this.authRepository.saveActiveProfile(activeProfile);
            this.authRepository.saveJwtSub(str2);
            this.authRepository.setUserInformation(new UserInformation(str2, str));
        }
        function1.invoke(activeProfile);
    }
}
