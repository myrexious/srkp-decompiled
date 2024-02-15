package org.informatika.sirekap.api.response;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BsreChallengeResponsePayload.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lorg/informatika/sirekap/api/response/BsreChallengeResponsePayload;", "", "challenge", "", "(Ljava/lang/String;)V", "getChallenge", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BsreChallengeResponsePayload {
    @SerializedName("challenge")
    private final String challenge;

    public static /* synthetic */ BsreChallengeResponsePayload copy$default(BsreChallengeResponsePayload bsreChallengeResponsePayload, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bsreChallengeResponsePayload.challenge;
        }
        return bsreChallengeResponsePayload.copy(str);
    }

    public final String component1() {
        return this.challenge;
    }

    public final BsreChallengeResponsePayload copy(String challenge) {
        Intrinsics.checkNotNullParameter(challenge, "challenge");
        return new BsreChallengeResponsePayload(challenge);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BsreChallengeResponsePayload) && Intrinsics.areEqual(this.challenge, ((BsreChallengeResponsePayload) obj).challenge);
    }

    public int hashCode() {
        return this.challenge.hashCode();
    }

    public String toString() {
        return "BsreChallengeResponsePayload(challenge=" + this.challenge + ")";
    }

    public BsreChallengeResponsePayload(String challenge) {
        Intrinsics.checkNotNullParameter(challenge, "challenge");
        this.challenge = challenge;
    }

    public final String getChallenge() {
        return this.challenge;
    }
}
