package org.informatika.sirekap.ui.security;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.informatika.sirekap.model.KeyState;
import org.informatika.sirekap.model.SecurityProperties;
import org.informatika.sirekap.support.security.AndroidKeystoreFacade;

/* compiled from: CertificateViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel$generateSymmetricKey$1", f = "CertificateViewModel.kt", i = {}, l = {178}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
final class CertificateViewModel$generateSymmetricKey$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    int label;
    final /* synthetic */ CertificateViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CertificateViewModel$generateSymmetricKey$1(CertificateViewModel certificateViewModel, Context context, Continuation<? super CertificateViewModel$generateSymmetricKey$1> continuation) {
        super(2, continuation);
        this.this$0 = certificateViewModel;
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CertificateViewModel$generateSymmetricKey$1(this.this$0, this.$context, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CertificateViewModel$generateSymmetricKey$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: CertificateViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel$generateSymmetricKey$1$1", f = "CertificateViewModel.kt", i = {2}, l = {179, 186, 190}, m = "invokeSuspend", n = {NotificationCompat.CATEGORY_ERROR}, s = {"L$0"})
    /* renamed from: org.informatika.sirekap.ui.security.CertificateViewModel$generateSymmetricKey$1$1 */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        Object L$0;
        int label;
        final /* synthetic */ CertificateViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(CertificateViewModel certificateViewModel, Context context, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = certificateViewModel;
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Exception exc;
            Object currentStatus;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
            } catch (Exception e) {
                FirebaseCrashlytics.getInstance().recordException(e);
                CertificateViewModel certificateViewModel = this.this$0;
                String name = e.getClass().getName();
                String message = e.getMessage();
                this.L$0 = e;
                this.label = 3;
                if (CertificateViewModel.setGenerationFailed$default(certificateViewModel, "Failed to generate symmetric key: " + name + ": " + message, SecurityProperties.SYMMETRIC_KEY_STATUS, null, this, 4, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                exc = e;
            }
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = this.this$0.checkCurrentStatus(SecurityProperties.SYMMETRIC_KEY_STATUS, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                } else if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    exc = (Exception) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    String name2 = exc.getClass().getName();
                    throw new Exception("Failed to generate symmetric key: " + name2 + ": " + exc.getMessage());
                }
            } else {
                ResultKt.throwOnFailure(obj);
            }
            if (obj == KeyState.GENERATED) {
                this.this$0.isLoading().postValue(Boxing.boxBoolean(false));
                return Unit.INSTANCE;
            }
            AndroidKeystoreFacade.INSTANCE.generateSymmetricKey(this.$context);
            this.label = 2;
            currentStatus = this.this$0.setCurrentStatus(KeyState.GENERATED, SecurityProperties.SYMMETRIC_KEY_STATUS, this);
            if (currentStatus == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1(this.this$0, this.$context, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
