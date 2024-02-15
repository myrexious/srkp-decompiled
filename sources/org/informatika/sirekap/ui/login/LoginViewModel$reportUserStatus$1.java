package org.informatika.sirekap.ui.login;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: LoginViewModel.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.login.LoginViewModel", f = "LoginViewModel.kt", i = {0, 0}, l = {52, 56, 58, 60}, m = "reportUserStatus", n = {"this", NotificationCompat.CATEGORY_STATUS}, s = {"L$0", "I$0"})
/* loaded from: classes4.dex */
public final class LoginViewModel$reportUserStatus$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ LoginViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoginViewModel$reportUserStatus$1(LoginViewModel loginViewModel, Continuation<? super LoginViewModel$reportUserStatus$1> continuation) {
        super(continuation);
        this.this$0 = loginViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.reportUserStatus(null, 0, this);
    }
}
