package org.informatika.sirekap.support.messaging;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CertificateGenerationMessage.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/support/messaging/CertificateGenerationMessage;", "", "()V", "certificateResponse", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/support/messaging/CertificateGenerationResponse;", "getCertificateResponse", "()Landroidx/lifecycle/LiveData;", "certificateResponseLivedata", "Landroidx/lifecycle/MutableLiveData;", "changeState", "", "response", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CertificateGenerationMessage {
    public static final CertificateGenerationMessage INSTANCE = new CertificateGenerationMessage();
    private static final MutableLiveData<CertificateGenerationResponse> certificateResponseLivedata = new MutableLiveData<>();

    private CertificateGenerationMessage() {
    }

    public final LiveData<CertificateGenerationResponse> getCertificateResponse() {
        return certificateResponseLivedata;
    }

    public final void changeState(CertificateGenerationResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        certificateResponseLivedata.postValue(response);
    }
}
