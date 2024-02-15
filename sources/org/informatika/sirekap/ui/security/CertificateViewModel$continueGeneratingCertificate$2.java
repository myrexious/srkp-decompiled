package org.informatika.sirekap.ui.security;

import android.content.Context;
import androidx.lifecycle.ViewModelKt;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
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
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.informatika.sirekap.model.ActiveProfile;
import org.informatika.sirekap.model.KeyState;
import org.informatika.sirekap.model.SecurityProperties;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* compiled from: CertificateViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel$continueGeneratingCertificate$2", f = "CertificateViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class CertificateViewModel$continueGeneratingCertificate$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ String $csr;
    final /* synthetic */ String $deviceId;
    final /* synthetic */ String $firebaseId;
    final /* synthetic */ String $gsfId;
    int label;
    final /* synthetic */ CertificateViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CertificateViewModel$continueGeneratingCertificate$2(CertificateViewModel certificateViewModel, Context context, String str, String str2, String str3, String str4, Continuation<? super CertificateViewModel$continueGeneratingCertificate$2> continuation) {
        super(2, continuation);
        this.this$0 = certificateViewModel;
        this.$context = context;
        this.$firebaseId = str;
        this.$csr = str2;
        this.$deviceId = str3;
        this.$gsfId = str4;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CertificateViewModel$continueGeneratingCertificate$2(this.this$0, this.$context, this.$firebaseId, this.$csr, this.$deviceId, this.$gsfId, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CertificateViewModel$continueGeneratingCertificate$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        AuthRequestUseCase authRequestUseCase = this.this$0.getAuthRequestUseCase();
        Context context = this.$context;
        final CertificateViewModel certificateViewModel = this.this$0;
        final String str = this.$firebaseId;
        final String str2 = this.$csr;
        final String str3 = this.$deviceId;
        final String str4 = this.$gsfId;
        final Context context2 = this.$context;
        final CertificateViewModel certificateViewModel2 = this.this$0;
        final Context context3 = this.$context;
        authRequestUseCase.startRefreshToken(context, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.security.CertificateViewModel$continueGeneratingCertificate$2.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ActiveProfile activeProfile) {
                invoke2(activeProfile);
                return Unit.INSTANCE;
            }

            /* compiled from: CertificateViewModel.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel$continueGeneratingCertificate$2$1$1", f = "CertificateViewModel.kt", i = {}, l = {229}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: org.informatika.sirekap.ui.security.CertificateViewModel$continueGeneratingCertificate$2$1$1 */
            /* loaded from: classes4.dex */
            public static final class C00231 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Context $context;
                final /* synthetic */ String $csr;
                final /* synthetic */ String $deviceId;
                final /* synthetic */ String $firebaseId;
                final /* synthetic */ String $gsfId;
                int label;
                final /* synthetic */ CertificateViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00231(CertificateViewModel certificateViewModel, String str, String str2, String str3, String str4, Context context, Continuation<? super C00231> continuation) {
                    super(2, continuation);
                    this.this$0 = certificateViewModel;
                    this.$firebaseId = str;
                    this.$csr = str2;
                    this.$deviceId = str3;
                    this.$gsfId = str4;
                    this.$context = context;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00231(this.this$0, this.$firebaseId, this.$csr, this.$deviceId, this.$gsfId, this.$context, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C00231) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* compiled from: CertificateViewModel.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel$continueGeneratingCertificate$2$1$1$1", f = "CertificateViewModel.kt", i = {}, l = {231, 240}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: org.informatika.sirekap.ui.security.CertificateViewModel$continueGeneratingCertificate$2$1$1$1 */
                /* loaded from: classes4.dex */
                public static final class C00241 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ Context $context;
                    final /* synthetic */ String $csr;
                    final /* synthetic */ String $deviceId;
                    final /* synthetic */ String $firebaseId;
                    final /* synthetic */ String $gsfId;
                    int label;
                    final /* synthetic */ CertificateViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C00241(CertificateViewModel certificateViewModel, String str, String str2, String str3, String str4, Context context, Continuation<? super C00241> continuation) {
                        super(2, continuation);
                        this.this$0 = certificateViewModel;
                        this.$firebaseId = str;
                        this.$csr = str2;
                        this.$deviceId = str3;
                        this.$gsfId = str4;
                        this.$context = context;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new C00241(this.this$0, this.$firebaseId, this.$csr, this.$deviceId, this.$gsfId, this.$context, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C00241) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object generationFailed;
                        Object doEnqueueCertificateRequest;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        try {
                        } catch (Exception e) {
                            FirebaseCrashlytics.getInstance().recordException(e);
                            CertificateViewModel certificateViewModel = this.this$0;
                            String name = e.getClass().getName();
                            String message = e.getMessage();
                            this.label = 2;
                            generationFailed = certificateViewModel.setGenerationFailed("Gagal mendapatkan kunci digital: " + name + ": " + message, SecurityProperties.LOCAL_CERTIFICATE_STATUS, KeyState.KEY_PAIR_GENERATED, this);
                            if (generationFailed == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.label = 1;
                            doEnqueueCertificateRequest = this.this$0.doEnqueueCertificateRequest(this.$firebaseId, this.$csr, this.$deviceId, this.$gsfId, this);
                            if (doEnqueueCertificateRequest == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else if (i != 1) {
                            if (i == 2) {
                                ResultKt.throwOnFailure(obj);
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        } else {
                            ResultKt.throwOnFailure(obj);
                        }
                        if (Intrinsics.areEqual(this.$firebaseId, "none")) {
                            this.this$0.getCertificateCheckerWorker().start(this.$context);
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
                        if (BuildersKt.withContext(Dispatchers.getIO(), new C00241(this.this$0, this.$firebaseId, this.$csr, this.$deviceId, this.$gsfId, this.$context, null), this) == coroutine_suspended) {
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

            /* renamed from: invoke */
            public final void invoke2(ActiveProfile it) {
                Intrinsics.checkNotNullParameter(it, "it");
                BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(certificateViewModel), null, null, new C00231(certificateViewModel, str, str2, str3, str4, context2, null), 3, null);
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.security.CertificateViewModel$continueGeneratingCertificate$2.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* compiled from: CertificateViewModel.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel$continueGeneratingCertificate$2$2$1", f = "CertificateViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: org.informatika.sirekap.ui.security.CertificateViewModel$continueGeneratingCertificate$2$2$1 */
            /* loaded from: classes4.dex */
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Context $context;
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
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    this.this$0.doLogout(this.$context);
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke */
            public final void invoke2(Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
                BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(certificateViewModel2), null, null, new AnonymousClass1(certificateViewModel2, context3, null), 3, null);
            }
        });
        return Unit.INSTANCE;
    }
}
