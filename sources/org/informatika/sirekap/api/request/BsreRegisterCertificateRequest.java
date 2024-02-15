package org.informatika.sirekap.api.request;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BsreRegisterCertificateRequest.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J#\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lorg/informatika/sirekap/api/request/BsreRegisterCertificateRequest;", "", "transactionId", "", "certificates", "", "(Ljava/lang/String;Ljava/util/List;)V", "getCertificates", "()Ljava/util/List;", "getTransactionId", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BsreRegisterCertificateRequest {
    @SerializedName("certificates")
    private final List<String> certificates;
    @SerializedName(FirebaseAnalytics.Param.TRANSACTION_ID)
    private final String transactionId;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BsreRegisterCertificateRequest copy$default(BsreRegisterCertificateRequest bsreRegisterCertificateRequest, String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bsreRegisterCertificateRequest.transactionId;
        }
        if ((i & 2) != 0) {
            list = bsreRegisterCertificateRequest.certificates;
        }
        return bsreRegisterCertificateRequest.copy(str, list);
    }

    public final String component1() {
        return this.transactionId;
    }

    public final List<String> component2() {
        return this.certificates;
    }

    public final BsreRegisterCertificateRequest copy(String transactionId, List<String> certificates) {
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        Intrinsics.checkNotNullParameter(certificates, "certificates");
        return new BsreRegisterCertificateRequest(transactionId, certificates);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BsreRegisterCertificateRequest) {
            BsreRegisterCertificateRequest bsreRegisterCertificateRequest = (BsreRegisterCertificateRequest) obj;
            return Intrinsics.areEqual(this.transactionId, bsreRegisterCertificateRequest.transactionId) && Intrinsics.areEqual(this.certificates, bsreRegisterCertificateRequest.certificates);
        }
        return false;
    }

    public int hashCode() {
        return (this.transactionId.hashCode() * 31) + this.certificates.hashCode();
    }

    public String toString() {
        String str = this.transactionId;
        return "BsreRegisterCertificateRequest(transactionId=" + str + ", certificates=" + this.certificates + ")";
    }

    public BsreRegisterCertificateRequest(String transactionId, List<String> certificates) {
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        Intrinsics.checkNotNullParameter(certificates, "certificates");
        this.transactionId = transactionId;
        this.certificates = certificates;
    }

    public final String getTransactionId() {
        return this.transactionId;
    }

    public final List<String> getCertificates() {
        return this.certificates;
    }
}
