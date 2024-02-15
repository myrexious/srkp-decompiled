package org.informatika.sirekap.ui.security;

import android.content.Context;
import androidx.lifecycle.ViewModelKt;
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
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* compiled from: CertificateViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel$getGeneratedCertificate$2", f = "CertificateViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class CertificateViewModel$getGeneratedCertificate$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    int label;
    final /* synthetic */ CertificateViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CertificateViewModel$getGeneratedCertificate$2(CertificateViewModel certificateViewModel, Context context, Continuation<? super CertificateViewModel$getGeneratedCertificate$2> continuation) {
        super(2, continuation);
        this.this$0 = certificateViewModel;
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CertificateViewModel$getGeneratedCertificate$2(this.this$0, this.$context, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CertificateViewModel$getGeneratedCertificate$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
        authRequestUseCase.startRefreshToken(context, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.security.CertificateViewModel$getGeneratedCertificate$2.1
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
            @DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel$getGeneratedCertificate$2$1$1", f = "CertificateViewModel.kt", i = {}, l = {357}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: org.informatika.sirekap.ui.security.CertificateViewModel$getGeneratedCertificate$2$1$1 */
            /* loaded from: classes4.dex */
            public static final class C00331 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Context $context;
                int label;
                final /* synthetic */ CertificateViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00331(CertificateViewModel certificateViewModel, Context context, Continuation<? super C00331> continuation) {
                    super(2, continuation);
                    this.this$0 = certificateViewModel;
                    this.$context = context;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00331(this.this$0, this.$context, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C00331) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* compiled from: CertificateViewModel.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel$getGeneratedCertificate$2$1$1$1", f = "CertificateViewModel.kt", i = {}, l = {359, 360, 379}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: org.informatika.sirekap.ui.security.CertificateViewModel$getGeneratedCertificate$2$1$1$1 */
                /* loaded from: classes4.dex */
                public static final class C00341 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ Context $context;
                    int label;
                    final /* synthetic */ CertificateViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C00341(CertificateViewModel certificateViewModel, Context context, Continuation<? super C00341> continuation) {
                        super(2, continuation);
                        this.this$0 = certificateViewModel;
                        this.$context = context;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new C00341(this.this$0, this.$context, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C00341) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Removed duplicated region for block: B:64:0x005a A[Catch: Exception -> 0x0026, TRY_LEAVE, TryCatch #0 {Exception -> 0x0026, blocks: (B:46:0x001e, B:62:0x0052, B:64:0x005a, B:47:0x0022, B:55:0x003b, B:57:0x003f, B:59:0x0042, B:52:0x002b), top: B:71:0x0009 }] */
                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
                        /*
                            r11 = this;
                            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r1 = r11.label
                            r2 = 3
                            r3 = 2
                            r4 = 1
                            if (r1 == 0) goto L28
                            if (r1 == r4) goto L22
                            if (r1 == r3) goto L1e
                            if (r1 != r2) goto L16
                            kotlin.ResultKt.throwOnFailure(r12)
                            goto Ld1
                        L16:
                            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
                            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                            r12.<init>(r0)
                            throw r12
                        L1e:
                            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Exception -> L26
                            goto L52
                        L22:
                            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Exception -> L26
                            goto L3b
                        L26:
                            r12 = move-exception
                            goto L8f
                        L28:
                            kotlin.ResultKt.throwOnFailure(r12)
                            org.informatika.sirekap.ui.security.CertificateViewModel r12 = r11.this$0     // Catch: java.lang.Exception -> L26
                            android.content.Context r1 = r11.$context     // Catch: java.lang.Exception -> L26
                            r5 = r11
                            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5     // Catch: java.lang.Exception -> L26
                            r11.label = r4     // Catch: java.lang.Exception -> L26
                            java.lang.Object r12 = org.informatika.sirekap.ui.security.CertificateViewModel.access$doGetCertificateData(r12, r1, r5)     // Catch: java.lang.Exception -> L26
                            if (r12 != r0) goto L3b
                            return r0
                        L3b:
                            java.lang.String r12 = (java.lang.String) r12     // Catch: java.lang.Exception -> L26
                            if (r12 != 0) goto L42
                            kotlin.Unit r12 = kotlin.Unit.INSTANCE     // Catch: java.lang.Exception -> L26
                            return r12
                        L42:
                            org.informatika.sirekap.ui.security.CertificateViewModel r1 = r11.this$0     // Catch: java.lang.Exception -> L26
                            android.content.Context r5 = r11.$context     // Catch: java.lang.Exception -> L26
                            r6 = r11
                            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch: java.lang.Exception -> L26
                            r11.label = r3     // Catch: java.lang.Exception -> L26
                            java.lang.Object r12 = org.informatika.sirekap.ui.security.CertificateViewModel.access$doSirekapCertificateDownload(r1, r5, r12, r6)     // Catch: java.lang.Exception -> L26
                            if (r12 != r0) goto L52
                            return r0
                        L52:
                            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch: java.lang.Exception -> L26
                            boolean r12 = r12.booleanValue()     // Catch: java.lang.Exception -> L26
                            if (r12 == 0) goto Ld1
                            org.informatika.sirekap.ui.security.CertificateViewModel r12 = r11.this$0     // Catch: java.lang.Exception -> L26
                            android.content.SharedPreferences r12 = r12.getSharedPreferences()     // Catch: java.lang.Exception -> L26
                            android.content.SharedPreferences$Editor r12 = r12.edit()     // Catch: java.lang.Exception -> L26
                            java.lang.String r1 = "status_initialization25"
                            r3 = 7
                            android.content.SharedPreferences$Editor r12 = r12.putInt(r1, r3)     // Catch: java.lang.Exception -> L26
                            r12.apply()     // Catch: java.lang.Exception -> L26
                            org.informatika.sirekap.ui.security.CertificateViewModel r12 = r11.this$0     // Catch: java.lang.Exception -> L26
                            android.content.SharedPreferences r12 = r12.getSharedPreferences()     // Catch: java.lang.Exception -> L26
                            android.content.SharedPreferences$Editor r12 = r12.edit()     // Catch: java.lang.Exception -> L26
                            java.lang.String r1 = "app_initialization25"
                            android.content.SharedPreferences$Editor r12 = r12.putBoolean(r1, r4)     // Catch: java.lang.Exception -> L26
                            r12.apply()     // Catch: java.lang.Exception -> L26
                            org.informatika.sirekap.ui.security.CertificateViewModel r12 = r11.this$0     // Catch: java.lang.Exception -> L26
                            androidx.lifecycle.MutableLiveData r12 = r12.isSuccess()     // Catch: java.lang.Exception -> L26
                            java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)     // Catch: java.lang.Exception -> L26
                            r12.postValue(r1)     // Catch: java.lang.Exception -> L26
                            goto Ld1
                        L8f:
                            com.google.firebase.crashlytics.FirebaseCrashlytics r1 = com.google.firebase.crashlytics.FirebaseCrashlytics.getInstance()
                            r3 = r12
                            java.lang.Throwable r3 = (java.lang.Throwable) r3
                            r1.recordException(r3)
                            org.informatika.sirekap.ui.security.CertificateViewModel r4 = r11.this$0
                            java.lang.Class r1 = r12.getClass()
                            java.lang.String r1 = r1.getName()
                            java.lang.String r12 = r12.getMessage()
                            java.lang.StringBuilder r3 = new java.lang.StringBuilder
                            java.lang.String r5 = "Gagal mendapatkan kunci digital: "
                            r3.<init>(r5)
                            java.lang.StringBuilder r1 = r3.append(r1)
                            java.lang.String r3 = ": "
                            java.lang.StringBuilder r1 = r1.append(r3)
                            java.lang.StringBuilder r12 = r1.append(r12)
                            java.lang.String r5 = r12.toString()
                            java.lang.String r6 = "local_certificate_status"
                            r7 = 0
                            r8 = r11
                            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
                            r9 = 4
                            r10 = 0
                            r11.label = r2
                            java.lang.Object r12 = org.informatika.sirekap.ui.security.CertificateViewModel.setGenerationFailed$default(r4, r5, r6, r7, r8, r9, r10)
                            if (r12 != r0) goto Ld1
                            return r0
                        Ld1:
                            kotlin.Unit r12 = kotlin.Unit.INSTANCE
                            return r12
                        */
                        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.security.CertificateViewModel$getGeneratedCertificate$2.AnonymousClass1.C00331.C00341.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        if (BuildersKt.withContext(Dispatchers.getIO(), new C00341(this.this$0, this.$context, null), this) == coroutine_suspended) {
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
                BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(certificateViewModel), null, null, new C00331(certificateViewModel, context2, null), 3, null);
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.security.CertificateViewModel$getGeneratedCertificate$2.2
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
            @DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel$getGeneratedCertificate$2$2$1", f = "CertificateViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: org.informatika.sirekap.ui.security.CertificateViewModel$getGeneratedCertificate$2$2$1 */
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
