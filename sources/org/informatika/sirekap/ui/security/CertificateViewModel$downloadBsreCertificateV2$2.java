package org.informatika.sirekap.ui.security;

import android.content.Context;
import androidx.lifecycle.ViewModelKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
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
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* compiled from: CertificateViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel$downloadBsreCertificateV2$2", f = "CertificateViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class CertificateViewModel$downloadBsreCertificateV2$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    int label;
    final /* synthetic */ CertificateViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CertificateViewModel$downloadBsreCertificateV2$2(CertificateViewModel certificateViewModel, Context context, Continuation<? super CertificateViewModel$downloadBsreCertificateV2$2> continuation) {
        super(2, continuation);
        this.this$0 = certificateViewModel;
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CertificateViewModel$downloadBsreCertificateV2$2(this.this$0, this.$context, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CertificateViewModel$downloadBsreCertificateV2$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
        final Context context2 = this.$context;
        final CertificateViewModel certificateViewModel2 = this.this$0;
        final Context context3 = this.$context;
        authRequestUseCase.startRefreshToken(context, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.security.CertificateViewModel$downloadBsreCertificateV2$2.1
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
            @DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel$downloadBsreCertificateV2$2$1$1", f = "CertificateViewModel.kt", i = {}, l = {626}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: org.informatika.sirekap.ui.security.CertificateViewModel$downloadBsreCertificateV2$2$1$1 */
            /* loaded from: classes4.dex */
            public static final class C00311 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Context $context;
                int label;
                final /* synthetic */ CertificateViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00311(CertificateViewModel certificateViewModel, Context context, Continuation<? super C00311> continuation) {
                    super(2, continuation);
                    this.this$0 = certificateViewModel;
                    this.$context = context;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00311(this.this$0, this.$context, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C00311) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* compiled from: CertificateViewModel.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel$downloadBsreCertificateV2$2$1$1$1", f = "CertificateViewModel.kt", i = {}, l = {631, 628, 633, 636}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: org.informatika.sirekap.ui.security.CertificateViewModel$downloadBsreCertificateV2$2$1$1$1 */
                /* loaded from: classes4.dex */
                public static final class C00321 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ Context $context;
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    int label;
                    final /* synthetic */ CertificateViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C00321(CertificateViewModel certificateViewModel, Context context, Continuation<? super C00321> continuation) {
                        super(2, continuation);
                        this.this$0 = certificateViewModel;
                        this.$context = context;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new C00321(this.this$0, this.$context, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C00321) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Removed duplicated region for block: B:82:0x00a7 A[RETURN] */
                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
                        /*
                            Method dump skipped, instructions count: 238
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.security.CertificateViewModel$downloadBsreCertificateV2$2.AnonymousClass1.C00311.C00321.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        if (BuildersKt.withContext(Dispatchers.getIO(), new C00321(this.this$0, this.$context, null), this) == coroutine_suspended) {
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
                BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(certificateViewModel), null, null, new C00311(certificateViewModel, context2, null), 3, null);
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.security.CertificateViewModel$downloadBsreCertificateV2$2.2
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
            @DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel$downloadBsreCertificateV2$2$2$1", f = "CertificateViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: org.informatika.sirekap.ui.security.CertificateViewModel$downloadBsreCertificateV2$2$2$1 */
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
                    this.this$0.isLoading().postValue(Boxing.boxBoolean(false));
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
