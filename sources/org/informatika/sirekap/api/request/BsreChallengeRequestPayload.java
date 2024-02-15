package org.informatika.sirekap.api.request;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BsreChallengeRequestPayload.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lorg/informatika/sirekap/api/request/BsreChallengeRequestPayload;", "", "gsfId", "", "deviceId", "transactionId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDeviceId", "()Ljava/lang/String;", "getGsfId", "getTransactionId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BsreChallengeRequestPayload {
    @SerializedName("device_id")
    private final String deviceId;
    @SerializedName("gsf_id")
    private final String gsfId;
    @SerializedName(FirebaseAnalytics.Param.TRANSACTION_ID)
    private final String transactionId;

    public static /* synthetic */ BsreChallengeRequestPayload copy$default(BsreChallengeRequestPayload bsreChallengeRequestPayload, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bsreChallengeRequestPayload.gsfId;
        }
        if ((i & 2) != 0) {
            str2 = bsreChallengeRequestPayload.deviceId;
        }
        if ((i & 4) != 0) {
            str3 = bsreChallengeRequestPayload.transactionId;
        }
        return bsreChallengeRequestPayload.copy(str, str2, str3);
    }

    public final String component1() {
        return this.gsfId;
    }

    public final String component2() {
        return this.deviceId;
    }

    public final String component3() {
        return this.transactionId;
    }

    public final BsreChallengeRequestPayload copy(String gsfId, String deviceId, String transactionId) {
        Intrinsics.checkNotNullParameter(gsfId, "gsfId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        return new BsreChallengeRequestPayload(gsfId, deviceId, transactionId);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BsreChallengeRequestPayload) {
            BsreChallengeRequestPayload bsreChallengeRequestPayload = (BsreChallengeRequestPayload) obj;
            return Intrinsics.areEqual(this.gsfId, bsreChallengeRequestPayload.gsfId) && Intrinsics.areEqual(this.deviceId, bsreChallengeRequestPayload.deviceId) && Intrinsics.areEqual(this.transactionId, bsreChallengeRequestPayload.transactionId);
        }
        return false;
    }

    public int hashCode() {
        return (((this.gsfId.hashCode() * 31) + this.deviceId.hashCode()) * 31) + this.transactionId.hashCode();
    }

    public String toString() {
        String str = this.gsfId;
        String str2 = this.deviceId;
        return "BsreChallengeRequestPayload(gsfId=" + str + ", deviceId=" + str2 + ", transactionId=" + this.transactionId + ")";
    }

    public BsreChallengeRequestPayload(String gsfId, String deviceId, String transactionId) {
        Intrinsics.checkNotNullParameter(gsfId, "gsfId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        this.gsfId = gsfId;
        this.deviceId = deviceId;
        this.transactionId = transactionId;
    }

    public final String getGsfId() {
        return this.gsfId;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final String getTransactionId() {
        return this.transactionId;
    }
}
