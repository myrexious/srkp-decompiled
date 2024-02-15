package org.informatika.sirekap.api.request;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BsreV2KeystoreRequestPayload.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lorg/informatika/sirekap/api/request/BsreV2KeystoreRequestPayload;", "", "transactionId", "", "challengeToken", "requestData", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getChallengeToken", "()Ljava/lang/String;", "getRequestData", "getTransactionId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BsreV2KeystoreRequestPayload {
    @SerializedName("challenge_token")
    private final String challengeToken;
    @SerializedName("request_data")
    private final String requestData;
    @SerializedName(FirebaseAnalytics.Param.TRANSACTION_ID)
    private final String transactionId;

    public static /* synthetic */ BsreV2KeystoreRequestPayload copy$default(BsreV2KeystoreRequestPayload bsreV2KeystoreRequestPayload, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bsreV2KeystoreRequestPayload.transactionId;
        }
        if ((i & 2) != 0) {
            str2 = bsreV2KeystoreRequestPayload.challengeToken;
        }
        if ((i & 4) != 0) {
            str3 = bsreV2KeystoreRequestPayload.requestData;
        }
        return bsreV2KeystoreRequestPayload.copy(str, str2, str3);
    }

    public final String component1() {
        return this.transactionId;
    }

    public final String component2() {
        return this.challengeToken;
    }

    public final String component3() {
        return this.requestData;
    }

    public final BsreV2KeystoreRequestPayload copy(String transactionId, String challengeToken, String requestData) {
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        Intrinsics.checkNotNullParameter(challengeToken, "challengeToken");
        Intrinsics.checkNotNullParameter(requestData, "requestData");
        return new BsreV2KeystoreRequestPayload(transactionId, challengeToken, requestData);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BsreV2KeystoreRequestPayload) {
            BsreV2KeystoreRequestPayload bsreV2KeystoreRequestPayload = (BsreV2KeystoreRequestPayload) obj;
            return Intrinsics.areEqual(this.transactionId, bsreV2KeystoreRequestPayload.transactionId) && Intrinsics.areEqual(this.challengeToken, bsreV2KeystoreRequestPayload.challengeToken) && Intrinsics.areEqual(this.requestData, bsreV2KeystoreRequestPayload.requestData);
        }
        return false;
    }

    public int hashCode() {
        return (((this.transactionId.hashCode() * 31) + this.challengeToken.hashCode()) * 31) + this.requestData.hashCode();
    }

    public String toString() {
        String str = this.transactionId;
        String str2 = this.challengeToken;
        return "BsreV2KeystoreRequestPayload(transactionId=" + str + ", challengeToken=" + str2 + ", requestData=" + this.requestData + ")";
    }

    public BsreV2KeystoreRequestPayload(String transactionId, String challengeToken, String requestData) {
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        Intrinsics.checkNotNullParameter(challengeToken, "challengeToken");
        Intrinsics.checkNotNullParameter(requestData, "requestData");
        this.transactionId = transactionId;
        this.challengeToken = challengeToken;
        this.requestData = requestData;
    }

    public final String getTransactionId() {
        return this.transactionId;
    }

    public final String getChallengeToken() {
        return this.challengeToken;
    }

    public final String getRequestData() {
        return this.requestData;
    }
}
