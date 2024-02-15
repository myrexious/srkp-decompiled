package org.informatika.sirekap.api.request;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CertificateGenerationRequestPayload.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lorg/informatika/sirekap/api/request/CertificateGenerationRequestPayload;", "", "firebaseId", "", "gsfId", "deviceId", "csr", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCsr", "()Ljava/lang/String;", "getDeviceId", "getFirebaseId", "getGsfId", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CertificateGenerationRequestPayload {
    @SerializedName("certificate_request")
    private final String csr;
    @SerializedName("device_id")
    private final String deviceId;
    @SerializedName("firebase_id")
    private final String firebaseId;
    @SerializedName("gsf_id")
    private final String gsfId;

    public static /* synthetic */ CertificateGenerationRequestPayload copy$default(CertificateGenerationRequestPayload certificateGenerationRequestPayload, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = certificateGenerationRequestPayload.firebaseId;
        }
        if ((i & 2) != 0) {
            str2 = certificateGenerationRequestPayload.gsfId;
        }
        if ((i & 4) != 0) {
            str3 = certificateGenerationRequestPayload.deviceId;
        }
        if ((i & 8) != 0) {
            str4 = certificateGenerationRequestPayload.csr;
        }
        return certificateGenerationRequestPayload.copy(str, str2, str3, str4);
    }

    public final String component1() {
        return this.firebaseId;
    }

    public final String component2() {
        return this.gsfId;
    }

    public final String component3() {
        return this.deviceId;
    }

    public final String component4() {
        return this.csr;
    }

    public final CertificateGenerationRequestPayload copy(String firebaseId, String gsfId, String deviceId, String csr) {
        Intrinsics.checkNotNullParameter(firebaseId, "firebaseId");
        Intrinsics.checkNotNullParameter(gsfId, "gsfId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(csr, "csr");
        return new CertificateGenerationRequestPayload(firebaseId, gsfId, deviceId, csr);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CertificateGenerationRequestPayload) {
            CertificateGenerationRequestPayload certificateGenerationRequestPayload = (CertificateGenerationRequestPayload) obj;
            return Intrinsics.areEqual(this.firebaseId, certificateGenerationRequestPayload.firebaseId) && Intrinsics.areEqual(this.gsfId, certificateGenerationRequestPayload.gsfId) && Intrinsics.areEqual(this.deviceId, certificateGenerationRequestPayload.deviceId) && Intrinsics.areEqual(this.csr, certificateGenerationRequestPayload.csr);
        }
        return false;
    }

    public int hashCode() {
        return (((((this.firebaseId.hashCode() * 31) + this.gsfId.hashCode()) * 31) + this.deviceId.hashCode()) * 31) + this.csr.hashCode();
    }

    public String toString() {
        String str = this.firebaseId;
        String str2 = this.gsfId;
        String str3 = this.deviceId;
        return "CertificateGenerationRequestPayload(firebaseId=" + str + ", gsfId=" + str2 + ", deviceId=" + str3 + ", csr=" + this.csr + ")";
    }

    public CertificateGenerationRequestPayload(String firebaseId, String gsfId, String deviceId, String csr) {
        Intrinsics.checkNotNullParameter(firebaseId, "firebaseId");
        Intrinsics.checkNotNullParameter(gsfId, "gsfId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(csr, "csr");
        this.firebaseId = firebaseId;
        this.gsfId = gsfId;
        this.deviceId = deviceId;
        this.csr = csr;
    }

    public final String getFirebaseId() {
        return this.firebaseId;
    }

    public final String getGsfId() {
        return this.gsfId;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final String getCsr() {
        return this.csr;
    }
}
