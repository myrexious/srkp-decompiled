package org.informatika.sirekap.ui.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.apache.commons.lang3.BooleanUtils;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.repository.CertmanRepository;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import org.informatika.sirekap.usecase.AuthRequestUseCase;
import org.tensorflow.lite.schema.BuiltinOperator;

/* compiled from: DashboardViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.dashboard.DashboardViewModel$updateInitializationStatus$1", f = "DashboardViewModel.kt", i = {}, l = {BuiltinOperator.ASSIGN_VARIABLE, BuiltinOperator.BUCKETIZE}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class DashboardViewModel$updateInitializationStatus$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ DashboardViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DashboardViewModel$updateInitializationStatus$1(DashboardViewModel dashboardViewModel, Continuation<? super DashboardViewModel$updateInitializationStatus$1> continuation) {
        super(2, continuation);
        this.this$0 = dashboardViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DashboardViewModel$updateInitializationStatus$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DashboardViewModel$updateInitializationStatus$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Context context;
        SharedPreferences sharedPreferences;
        CertmanRepository certmanRepository;
        EncryptedSharedPreferences encryptedSharedPreferences;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
        } catch (Exception e) {
            String message = e.getMessage();
            Exception exc = e;
            Log.e("DashboardViewModel", message, exc);
            FirebaseCrashlytics.getInstance().recordException(exc);
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AuthRequestUseCase authRepositoryUseCase = this.this$0.getAuthRepositoryUseCase();
            context = this.this$0.context;
            this.label = 1;
            if (authRepositoryUseCase.startRefreshToken(context, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            if (i == 2) {
                ResultKt.throwOnFailure(obj);
                encryptedSharedPreferences = this.this$0.encryptedSharedPreferences;
                encryptedSharedPreferences.putStringEncrypted(SharedPreferencesModule.SP_KEY_HAS_UPDATE_INITIALIZATION_STATUS, BooleanUtils.TRUE);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        sharedPreferences = this.this$0.sharedPreferences;
        int i2 = sharedPreferences.getInt(SharedPreferencesModule.SP_STATUS_INITIALIZATION, 7);
        certmanRepository = this.this$0.certmanRepository;
        this.label = 2;
        if (certmanRepository.setUserDashboardStatus(i2, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        encryptedSharedPreferences = this.this$0.encryptedSharedPreferences;
        encryptedSharedPreferences.putStringEncrypted(SharedPreferencesModule.SP_KEY_HAS_UPDATE_INITIALIZATION_STATUS, BooleanUtils.TRUE);
        return Unit.INSTANCE;
    }
}