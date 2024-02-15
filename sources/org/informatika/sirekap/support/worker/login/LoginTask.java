package org.informatika.sirekap.support.worker.login;

import android.content.Context;
import kotlin.Metadata;

/* compiled from: LoginTask.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, d2 = {"Lorg/informatika/sirekap/support/worker/login/LoginTask;", "", "login", "", "context", "Landroid/content/Context;", "kodeTps", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface LoginTask {
    void login(Context context, String str);

    /* compiled from: LoginTask.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void login$default(LoginTask loginTask, Context context, String str, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: login");
            }
            if ((i & 2) != 0) {
                str = null;
            }
            loginTask.login(context, str);
        }
    }
}
