package org.informatika.sirekap.ui;

import android.content.SharedPreferences;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.messaging.FirebaseMessaging;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.informatika.sirekap.di.SharedPreferencesModule;

/* compiled from: GetFcmTokenUseCase.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0010\u001a\u00020\u00112\u000e\u0010\u0012\u001a\n\u0018\u00010\u0013j\u0004\u0018\u0001`\u0014H\u0002J\u0006\u0010\u0015\u001a\u00020\u0011R\u0019\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001f\u0010\n\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u001f\u0010\r\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u001c\u0010\u000e\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000f0\u000f0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/informatika/sirekap/ui/GetFcmTokenUseCase;", "", "sharedPreferences", "Landroid/content/SharedPreferences;", "(Landroid/content/SharedPreferences;)V", "error", "Landroidx/lifecycle/MutableLiveData;", "", "getError", "()Landroidx/lifecycle/MutableLiveData;", "isLoading", "", "kotlin.jvm.PlatformType", "isSuccess", "retryCount", "", "retryOrFail", "", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "setup", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class GetFcmTokenUseCase {
    public static final Companion Companion = new Companion(null);
    private static final int MAX_RETRY = 10;
    private static final String TAG = "GetFcmTokenUseCase";
    private final MutableLiveData<String> error;
    private final MutableLiveData<Boolean> isLoading;
    private final MutableLiveData<Boolean> isSuccess;
    private final MutableLiveData<Integer> retryCount;
    private final SharedPreferences sharedPreferences;

    public GetFcmTokenUseCase(SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
        this.sharedPreferences = sharedPreferences;
        this.retryCount = new MutableLiveData<>(0);
        this.isLoading = new MutableLiveData<>(false);
        this.isSuccess = new MutableLiveData<>(false);
        this.error = new MutableLiveData<>(null);
    }

    public final MutableLiveData<Boolean> isLoading() {
        return this.isLoading;
    }

    public final MutableLiveData<Boolean> isSuccess() {
        return this.isSuccess;
    }

    public final MutableLiveData<String> getError() {
        return this.error;
    }

    public final void setup() {
        this.isLoading.postValue(true);
        this.error.postValue(null);
        this.isSuccess.postValue(false);
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener() { // from class: org.informatika.sirekap.ui.GetFcmTokenUseCase$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                GetFcmTokenUseCase.setup$lambda$1(GetFcmTokenUseCase.this, task);
            }
        });
    }

    public static final void setup$lambda$1(GetFcmTokenUseCase this$0, Task task) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(task, "task");
        if (!task.isSuccessful()) {
            this$0.retryOrFail(task.getException());
            return;
        }
        String str = (String) task.getResult();
        if (str == null) {
            str = "none";
        }
        SharedPreferences.Editor edit = this$0.sharedPreferences.edit();
        edit.putString(SharedPreferencesModule.SP_KEY_FCM_TOKEN, str);
        edit.commit();
        this$0.retryOrFail(null);
    }

    private final void retryOrFail(Exception exc) {
        this.isLoading.postValue(false);
        String string = this.sharedPreferences.getString(SharedPreferencesModule.SP_KEY_FCM_TOKEN, null);
        if (!(string == null || StringsKt.isBlank(string))) {
            this.isSuccess.postValue(true);
            return;
        }
        Integer value = this.retryCount.getValue();
        if (value != null) {
            this.retryCount.setValue(Integer.valueOf(value.intValue() + 1));
        }
        Integer value2 = this.retryCount.getValue();
        Intrinsics.checkNotNull(value2);
        if (value2.intValue() < 10) {
            setup();
            return;
        }
        FirebaseCrashlytics.getInstance().recordException(new Exception("Pastikan Anda terhubung ke jaringan Internet: " + (exc != null ? exc.getMessage() : null)));
        this.isSuccess.postValue(true);
        this.retryCount.postValue(0);
    }

    /* compiled from: GetFcmTokenUseCase.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002¨\u0006\b"}, d2 = {"Lorg/informatika/sirekap/ui/GetFcmTokenUseCase$Companion;", "", "()V", "MAX_RETRY", "", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private static /* synthetic */ void getTAG$annotations() {
        }

        private Companion() {
        }
    }
}
