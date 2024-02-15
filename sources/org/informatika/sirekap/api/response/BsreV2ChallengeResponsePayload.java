package org.informatika.sirekap.api.response;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BsreV2ChallengeResponsePayload.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/informatika/sirekap/api/response/BsreV2ChallengeResponsePayload;", "", "challenge", "", "ip", "(Ljava/lang/String;Ljava/lang/String;)V", "getChallenge", "()Ljava/lang/String;", "getIp", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BsreV2ChallengeResponsePayload {
    @SerializedName("challenge")
    private final String challenge;
    @SerializedName("ip")
    private final String ip;

    public static /* synthetic */ BsreV2ChallengeResponsePayload copy$default(BsreV2ChallengeResponsePayload bsreV2ChallengeResponsePayload, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bsreV2ChallengeResponsePayload.challenge;
        }
        if ((i & 2) != 0) {
            str2 = bsreV2ChallengeResponsePayload.ip;
        }
        return bsreV2ChallengeResponsePayload.copy(str, str2);
    }

    public final String component1() {
        return this.challenge;
    }

    public final String component2() {
        return this.ip;
    }

    public final BsreV2ChallengeResponsePayload copy(String challenge, String ip) {
        Intrinsics.checkNotNullParameter(challenge, "challenge");
        Intrinsics.checkNotNullParameter(ip, "ip");
        return new BsreV2ChallengeResponsePayload(challenge, ip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BsreV2ChallengeResponsePayload) {
            BsreV2ChallengeResponsePayload bsreV2ChallengeResponsePayload = (BsreV2ChallengeResponsePayload) obj;
            return Intrinsics.areEqual(this.challenge, bsreV2ChallengeResponsePayload.challenge) && Intrinsics.areEqual(this.ip, bsreV2ChallengeResponsePayload.ip);
        }
        return false;
    }

    public int hashCode() {
        return (this.challenge.hashCode() * 31) + this.ip.hashCode();
    }

    public String toString() {
        String str = this.challenge;
        return "BsreV2ChallengeResponsePayload(challenge=" + str + ", ip=" + this.ip + ")";
    }

    public BsreV2ChallengeResponsePayload(String challenge, String ip) {
        Intrinsics.checkNotNullParameter(challenge, "challenge");
        Intrinsics.checkNotNullParameter(ip, "ip");
        this.challenge = challenge;
        this.ip = ip;
    }

    public final String getChallenge() {
        return this.challenge;
    }

    public final String getIp() {
        return this.ip;
    }
}
