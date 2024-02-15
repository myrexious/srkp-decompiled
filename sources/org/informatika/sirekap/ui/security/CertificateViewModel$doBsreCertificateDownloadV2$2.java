package org.informatika.sirekap.ui.security;

import android.content.Context;
import androidx.lifecycle.ViewModelKt;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
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
import org.informatika.sirekap.model.KeyState;
import org.informatika.sirekap.model.SecurityProperties;
import org.informatika.sirekap.support.security.AndroidKeystoreFacade;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* compiled from: CertificateViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel$doBsreCertificateDownloadV2$2", f = "CertificateViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class CertificateViewModel$doBsreCertificateDownloadV2$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Certificate[] $chained;
    final /* synthetic */ Context $context;
    final /* synthetic */ PrivateKey $privateKey;
    int label;
    final /* synthetic */ CertificateViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CertificateViewModel$doBsreCertificateDownloadV2$2(CertificateViewModel certificateViewModel, Context context, PrivateKey privateKey, Certificate[] certificateArr, Continuation<? super CertificateViewModel$doBsreCertificateDownloadV2$2> continuation) {
        super(2, continuation);
        this.this$0 = certificateViewModel;
        this.$context = context;
        this.$privateKey = privateKey;
        this.$chained = certificateArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CertificateViewModel$doBsreCertificateDownloadV2$2(this.this$0, this.$context, this.$privateKey, this.$chained, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CertificateViewModel$doBsreCertificateDownloadV2$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
        final PrivateKey privateKey = this.$privateKey;
        final Certificate[] certificateArr = this.$chained;
        final CertificateViewModel certificateViewModel2 = this.this$0;
        final Context context3 = this.$context;
        authRequestUseCase.startRefreshToken(context, new Function1<ActiveProfile, Unit>() { // from class: org.informatika.sirekap.ui.security.CertificateViewModel$doBsreCertificateDownloadV2$2.1
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
            @DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel$doBsreCertificateDownloadV2$2$1$1", f = "CertificateViewModel.kt", i = {}, l = {546}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: org.informatika.sirekap.ui.security.CertificateViewModel$doBsreCertificateDownloadV2$2$1$1 */
            /* loaded from: classes4.dex */
            public static final class C00271 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Certificate[] $chained;
                final /* synthetic */ Context $context;
                final /* synthetic */ PrivateKey $privateKey;
                int label;
                final /* synthetic */ CertificateViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00271(Context context, PrivateKey privateKey, Certificate[] certificateArr, CertificateViewModel certificateViewModel, Continuation<? super C00271> continuation) {
                    super(2, continuation);
                    this.$context = context;
                    this.$privateKey = privateKey;
                    this.$chained = certificateArr;
                    this.this$0 = certificateViewModel;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00271(this.$context, this.$privateKey, this.$chained, this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C00271) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* compiled from: CertificateViewModel.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel$doBsreCertificateDownloadV2$2$1$1$1", f = "CertificateViewModel.kt", i = {}, l = {549, 553}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: org.informatika.sirekap.ui.security.CertificateViewModel$doBsreCertificateDownloadV2$2$1$1$1 */
                /* loaded from: classes4.dex */
                public static final class C00281 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ Certificate[] $chained;
                    final /* synthetic */ Context $context;
                    final /* synthetic */ PrivateKey $privateKey;
                    int label;
                    final /* synthetic */ CertificateViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C00281(Context context, PrivateKey privateKey, Certificate[] certificateArr, CertificateViewModel certificateViewModel, Continuation<? super C00281> continuation) {
                        super(2, continuation);
                        this.$context = context;
                        this.$privateKey = privateKey;
                        this.$chained = certificateArr;
                        this.this$0 = certificateViewModel;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new C00281(this.$context, this.$privateKey, this.$chained, this.this$0, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C00281) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object generationFailed;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        try {
                        } catch (Exception e) {
                            FirebaseCrashlytics.getInstance().recordException(e);
                            CertificateViewModel certificateViewModel = this.this$0;
                            String message = e.getMessage();
                            this.label = 2;
                            generationFailed = certificateViewModel.setGenerationFailed("Gagal mendapatkan Sertifikat Digital: " + message, SecurityProperties.BSRE_CERTIFICATE_STATUS, KeyState.DOWNLOAD_FAILED, this);
                            if (generationFailed == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            AndroidKeystoreFacade.INSTANCE.setPrivateKeyEntry(this.$context, AndroidKeystoreFacade.BSRE_ALIAS_NAME, this.$privateKey, this.$chained);
                            this.label = 1;
                            if (this.this$0.doBsreRegistrationV2(ArraysKt.toList(this.$chained), this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else if (i != 1) {
                            if (i == 2) {
                                ResultKt.throwOnFailure(obj);
                                this.this$0.isLoading().postValue(Boxing.boxBoolean(false));
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        } else {
                            ResultKt.throwOnFailure(obj);
                        }
                        this.this$0.isSuccess().postValue(Boxing.boxBoolean(true));
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
                        if (BuildersKt.withContext(Dispatchers.getIO(), new C00281(this.$context, this.$privateKey, this.$chained, this.this$0, null), this) == coroutine_suspended) {
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
                BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(certificateViewModel), null, null, new C00271(context2, privateKey, certificateArr, certificateViewModel, null), 3, null);
            }
        }, new Function1<Exception, Unit>() { // from class: org.informatika.sirekap.ui.security.CertificateViewModel$doBsreCertificateDownloadV2$2.2
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
            @DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel$doBsreCertificateDownloadV2$2$2$1", f = "CertificateViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: org.informatika.sirekap.ui.security.CertificateViewModel$doBsreCertificateDownloadV2$2$2$1 */
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
