package org.informatika.sirekap.ui.security;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.informatika.sirekap.MainViewModel;

/* compiled from: CertificateFragment.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/content/Context;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
final class CertificateFragment$onViewCreated$1 extends Lambda implements Function1<Context, Unit> {
    final /* synthetic */ CertificateFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CertificateFragment$onViewCreated$1(CertificateFragment certificateFragment) {
        super(1);
        this.this$0 = certificateFragment;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Context context) {
        invoke2(context);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke */
    public final void invoke2(Context it) {
        long j;
        MainViewModel mainViewModel;
        Intrinsics.checkNotNullParameter(it, "it");
        Handler handler = new Handler(Looper.getMainLooper());
        final CertificateFragment certificateFragment = this.this$0;
        Runnable runnable = new Runnable() { // from class: org.informatika.sirekap.ui.security.CertificateFragment$onViewCreated$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CertificateFragment$onViewCreated$1.invoke$lambda$0(CertificateFragment.this);
            }
        };
        j = this.this$0.DELAY_FALLBACK_MS;
        handler.postDelayed(runnable, j);
        mainViewModel = this.this$0.getMainViewModel();
        mainViewModel.getLocalAuth().postValue(true);
        this.this$0.generateCertificate(false);
    }

    public static final void invoke$lambda$0(CertificateFragment this$0) {
        CertificateViewModel certificateViewModel;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        certificateViewModel = this$0.getCertificateViewModel();
        certificateViewModel.isCheckButtonEnabled().postValue(true);
    }
}
