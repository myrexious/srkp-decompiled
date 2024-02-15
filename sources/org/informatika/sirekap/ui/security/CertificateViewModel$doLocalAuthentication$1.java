package org.informatika.sirekap.ui.security;

import android.content.Context;
import android.content.Intent;
import androidx.activity.result.ActivityResultLauncher;
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.Fragment;
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
import org.informatika.sirekap.support.security.DeviceSecurityFacade;

/* compiled from: CertificateViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel$doLocalAuthentication$1", f = "CertificateViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class CertificateViewModel$doLocalAuthentication$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ActivityResultLauncher<Intent> $activityResultLauncher;
    final /* synthetic */ Fragment $fragment;
    final /* synthetic */ Function1<Context, Unit> $successCallback;
    int label;
    final /* synthetic */ CertificateViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public CertificateViewModel$doLocalAuthentication$1(Fragment fragment, ActivityResultLauncher<Intent> activityResultLauncher, CertificateViewModel certificateViewModel, Function1<? super Context, Unit> function1, Continuation<? super CertificateViewModel$doLocalAuthentication$1> continuation) {
        super(2, continuation);
        this.$fragment = fragment;
        this.$activityResultLauncher = activityResultLauncher;
        this.this$0 = certificateViewModel;
        this.$successCallback = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CertificateViewModel$doLocalAuthentication$1(this.$fragment, this.$activityResultLauncher, this.this$0, this.$successCallback, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CertificateViewModel$doLocalAuthentication$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        DeviceSecurityFacade deviceSecurityFacade = DeviceSecurityFacade.INSTANCE;
        Fragment fragment = this.$fragment;
        final CertificateViewModel certificateViewModel = this.this$0;
        final Function1<Context, Unit> function1 = this.$successCallback;
        final Fragment fragment2 = this.$fragment;
        deviceSecurityFacade.doLocalAuthentication(fragment, new BiometricPrompt.AuthenticationCallback() { // from class: org.informatika.sirekap.ui.security.CertificateViewModel$doLocalAuthentication$1.1
            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                super.onAuthenticationSucceeded(result);
                certificateViewModel.isFailedAuthentication().postValue(false);
                Function1<Context, Unit> function12 = function1;
                Context requireContext = fragment2.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "fragment.requireContext()");
                function12.invoke(requireContext);
            }

            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationError(int i, CharSequence errString) {
                Intrinsics.checkNotNullParameter(errString, "errString");
                super.onAuthenticationError(i, errString);
                certificateViewModel.failedAuthentication(String.valueOf(errString));
            }
        }, this.$activityResultLauncher);
        return Unit.INSTANCE;
    }
}
