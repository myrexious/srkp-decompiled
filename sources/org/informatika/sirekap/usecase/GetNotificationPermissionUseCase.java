package org.informatika.sirekap.usecase;

import android.os.Build;
import androidx.activity.result.ActivityResultLauncher;
import androidx.lifecycle.MutableLiveData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GetNotificationPermissionUseCase.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005J\u0014\u0010\u000e\u001a\u00020\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010R\u0019\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001f\u0010\b\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/informatika/sirekap/usecase/GetNotificationPermissionUseCase;", "", "()V", "error", "Landroidx/lifecycle/MutableLiveData;", "", "getError", "()Landroidx/lifecycle/MutableLiveData;", "isLoading", "", "kotlin.jvm.PlatformType", "setError", "", "_error", "start", "launcher", "Landroidx/activity/result/ActivityResultLauncher;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class GetNotificationPermissionUseCase {
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<String> error = new MutableLiveData<>(null);

    public final MutableLiveData<Boolean> isLoading() {
        return this.isLoading;
    }

    public final MutableLiveData<String> getError() {
        return this.error;
    }

    public final void start(ActivityResultLauncher<String> launcher) {
        Intrinsics.checkNotNullParameter(launcher, "launcher");
        this.error.postValue(null);
        if (Build.VERSION.SDK_INT >= 33) {
            launcher.launch("android.permission.POST_NOTIFICATIONS");
        } else {
            this.error.postValue("Requires Android >= Tiramisu (33)");
        }
    }

    public final void setError(String _error) {
        Intrinsics.checkNotNullParameter(_error, "_error");
        this.error.postValue(_error);
    }
}
