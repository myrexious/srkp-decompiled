package org.informatika.sirekap.api.request;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BsreKeystoreRequestPayload.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lorg/informatika/sirekap/api/request/BsreKeystoreRequestPayload;", "", "transactionId", "", "challengeToken", "publicKey", "signature", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getChallengeToken", "()Ljava/lang/String;", "getPublicKey", "getSignature", "getTransactionId", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BsreKeystoreRequestPayload {
    @SerializedName("challenge_token")
    private final String challengeToken;
    @SerializedName("public_key")
    private final String publicKey;
    @SerializedName("signature")
    private final String signature;
    @SerializedName(FirebaseAnalytics.Param.TRANSACTION_ID)
    private final String transactionId;

    public static /* synthetic */ BsreKeystoreRequestPayload copy$default(BsreKeystoreRequestPayload bsreKeystoreRequestPayload, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bsreKeystoreRequestPayload.transactionId;
        }
        if ((i & 2) != 0) {
            str2 = bsreKeystoreRequestPayload.challengeToken;
        }
        if ((i & 4) != 0) {
            str3 = bsreKeystoreRequestPayload.publicKey;
        }
        if ((i & 8) != 0) {
            str4 = bsreKeystoreRequestPayload.signature;
        }
        return bsreKeystoreRequestPayload.copy(str, str2, str3, str4);
    }

    public final String component1() {
        return this.transactionId;
    }

    public final String component2() {
        return this.challengeToken;
    }

    public final String component3() {
        return this.publicKey;
    }

    public final String component4() {
        return this.signature;
    }

    public final BsreKeystoreRequestPayload copy(String transactionId, String challengeToken, String publicKey, String signature) {
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        Intrinsics.checkNotNullParameter(challengeToken, "challengeToken");
        Intrinsics.checkNotNullParameter(publicKey, "publicKey");
        Intrinsics.checkNotNullParameter(signature, "signature");
        return new BsreKeystoreRequestPayload(transactionId, challengeToken, publicKey, signature);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BsreKeystoreRequestPayload) {
            BsreKeystoreRequestPayload bsreKeystoreRequestPayload = (BsreKeystoreRequestPayload) obj;
            return Intrinsics.areEqual(this.transactionId, bsreKeystoreRequestPayload.transactionId) && Intrinsics.areEqual(this.challengeToken, bsreKeystoreRequestPayload.challengeToken) && Intrinsics.areEqual(this.publicKey, bsreKeystoreRequestPayload.publicKey) && Intrinsics.areEqual(this.signature, bsreKeystoreRequestPayload.signature);
        }
        return false;
    }

    public int hashCode() {
        return (((((this.transactionId.hashCode() * 31) + this.challengeToken.hashCode()) * 31) + this.publicKey.hashCode()) * 31) + this.signature.hashCode();
    }

    public String toString() {
        String str = this.transactionId;
        String str2 = this.challengeToken;
        String str3 = this.publicKey;
        return "BsreKeystoreRequestPayload(transactionId=" + str + ", challengeToken=" + str2 + ", publicKey=" + str3 + ", signature=" + this.signature + ")";
    }

    public BsreKeystoreRequestPayload(String transactionId, String challengeToken, String publicKey, String signature) {
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        Intrinsics.checkNotNullParameter(challengeToken, "challengeToken");
        Intrinsics.checkNotNullParameter(publicKey, "publicKey");
        Intrinsics.checkNotNullParameter(signature, "signature");
        this.transactionId = transactionId;
        this.challengeToken = challengeToken;
        this.publicKey = publicKey;
        this.signature = signature;
    }

    public final String getTransactionId() {
        return this.transactionId;
    }

    public final String getChallengeToken() {
        return this.challengeToken;
    }

    public final String getPublicKey() {
        return this.publicKey;
    }

    public final String getSignature() {
        return this.signature;
    }
}
