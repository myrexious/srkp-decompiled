package org.informatika.sirekap.usecase;

import android.content.Context;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.TokenRequest;
import net.openid.appauth.TokenResponse;
import org.apache.commons.lang3.StringUtils;
import org.informatika.sirekap.model.ActiveProfile;

/* compiled from: AuthRequestUseCase.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.usecase.AuthRequestUseCase$processToken$1$1", f = "AuthRequestUseCase.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class AuthRequestUseCase$processToken$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ Function1<Exception, Unit> $onError;
    final /* synthetic */ Function1<ActiveProfile, Unit> $onSuccess;
    final /* synthetic */ AuthorizationResponse $resp;
    int label;
    final /* synthetic */ AuthRequestUseCase this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AuthRequestUseCase$processToken$1$1(Context context, AuthorizationResponse authorizationResponse, Function1<? super Exception, Unit> function1, AuthRequestUseCase authRequestUseCase, Function1<? super ActiveProfile, Unit> function12, Continuation<? super AuthRequestUseCase$processToken$1$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$resp = authorizationResponse;
        this.$onError = function1;
        this.this$0 = authRequestUseCase;
        this.$onSuccess = function12;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AuthRequestUseCase$processToken$1$1(this.$context, this.$resp, this.$onError, this.this$0, this.$onSuccess, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AuthRequestUseCase$processToken$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        AuthorizationService authorizationService = new AuthorizationService(this.$context);
        AuthorizationResponse authorizationResponse = this.$resp;
        TokenRequest createTokenExchangeRequest = authorizationResponse != null ? authorizationResponse.createTokenExchangeRequest() : null;
        Intrinsics.checkNotNull(createTokenExchangeRequest);
        final Function1<Exception, Unit> function1 = this.$onError;
        final AuthRequestUseCase authRequestUseCase = this.this$0;
        final Function1<ActiveProfile, Unit> function12 = this.$onSuccess;
        authorizationService.performTokenRequest(createTokenExchangeRequest, new AuthorizationService.TokenResponseCallback() { // from class: org.informatika.sirekap.usecase.AuthRequestUseCase$processToken$1$1$$ExternalSyntheticLambda0
            @Override // net.openid.appauth.AuthorizationService.TokenResponseCallback
            public final void onTokenRequestCompleted(TokenResponse tokenResponse, AuthorizationException authorizationException) {
                AuthRequestUseCase$processToken$1$1.invokeSuspend$lambda$0(Function1.this, authRequestUseCase, function12, tokenResponse, authorizationException);
            }
        });
        return Unit.INSTANCE;
    }

    public static final void invokeSuspend$lambda$0(Function1 function1, AuthRequestUseCase authRequestUseCase, Function1 function12, TokenResponse tokenResponse, AuthorizationException authorizationException) {
        FirebaseCrashlytics.getInstance().log(String.valueOf(tokenResponse != null ? tokenResponse.accessToken : null));
        if (authorizationException == null) {
            try {
                authRequestUseCase.saveNewToken(tokenResponse, false, function12, function1);
                authRequestUseCase.isLoading().setValue(false);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                FirebaseCrashlytics.getInstance().recordException(e);
                function1.invoke(new Exception("Access token is invalid: " + e + " '" + (tokenResponse != null ? tokenResponse.accessToken : null) + OperatorName.SHOW_TEXT_LINE));
                authRequestUseCase.isLoading().setValue(false);
                return;
            }
        }
        String message = authorizationException.getMessage();
        Throwable cause = authorizationException.getCause();
        Exception exc = new Exception("Error: " + message + StringUtils.SPACE + (cause != null ? cause.getMessage() : null));
        FirebaseCrashlytics.getInstance().recordException(exc);
        function1.invoke(exc);
        authRequestUseCase.isLoading().setValue(false);
    }
}
