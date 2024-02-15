package org.informatika.sirekap.support.messaging;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CertificateGenerationMessage.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/informatika/sirekap/support/messaging/CertificateGenerationResponse;", "", NotificationCompat.CATEGORY_STATUS, "", "transactionId", "(Ljava/lang/String;Ljava/lang/String;)V", "getStatus", "()Ljava/lang/String;", "getTransactionId", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CertificateGenerationResponse {
    private final String status;
    private final String transactionId;

    public static /* synthetic */ CertificateGenerationResponse copy$default(CertificateGenerationResponse certificateGenerationResponse, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = certificateGenerationResponse.status;
        }
        if ((i & 2) != 0) {
            str2 = certificateGenerationResponse.transactionId;
        }
        return certificateGenerationResponse.copy(str, str2);
    }

    public final String component1() {
        return this.status;
    }

    public final String component2() {
        return this.transactionId;
    }

    public final CertificateGenerationResponse copy(String status, String str) {
        Intrinsics.checkNotNullParameter(status, "status");
        return new CertificateGenerationResponse(status, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CertificateGenerationResponse) {
            CertificateGenerationResponse certificateGenerationResponse = (CertificateGenerationResponse) obj;
            return Intrinsics.areEqual(this.status, certificateGenerationResponse.status) && Intrinsics.areEqual(this.transactionId, certificateGenerationResponse.transactionId);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.status.hashCode() * 31;
        String str = this.transactionId;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        String str = this.status;
        return "CertificateGenerationResponse(status=" + str + ", transactionId=" + this.transactionId + ")";
    }

    public CertificateGenerationResponse(String status, String str) {
        Intrinsics.checkNotNullParameter(status, "status");
        this.status = status;
        this.transactionId = str;
    }

    public /* synthetic */ CertificateGenerationResponse(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2);
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getTransactionId() {
        return this.transactionId;
    }
}
