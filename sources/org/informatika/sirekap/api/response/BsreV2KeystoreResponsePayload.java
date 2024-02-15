package org.informatika.sirekap.api.response;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BsreV2KeystoreResponsePayload.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0007R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lorg/informatika/sirekap/api/response/BsreV2KeystoreResponsePayload;", "", "keystore", "", "isEmergency", "", "(Ljava/lang/String;Z)V", "()Z", "getKeystore", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BsreV2KeystoreResponsePayload {
    @SerializedName("is_emergency")
    private final boolean isEmergency;
    @SerializedName("pkcs12")
    private final String keystore;

    public static /* synthetic */ BsreV2KeystoreResponsePayload copy$default(BsreV2KeystoreResponsePayload bsreV2KeystoreResponsePayload, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bsreV2KeystoreResponsePayload.keystore;
        }
        if ((i & 2) != 0) {
            z = bsreV2KeystoreResponsePayload.isEmergency;
        }
        return bsreV2KeystoreResponsePayload.copy(str, z);
    }

    public final String component1() {
        return this.keystore;
    }

    public final boolean component2() {
        return this.isEmergency;
    }

    public final BsreV2KeystoreResponsePayload copy(String keystore, boolean z) {
        Intrinsics.checkNotNullParameter(keystore, "keystore");
        return new BsreV2KeystoreResponsePayload(keystore, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BsreV2KeystoreResponsePayload) {
            BsreV2KeystoreResponsePayload bsreV2KeystoreResponsePayload = (BsreV2KeystoreResponsePayload) obj;
            return Intrinsics.areEqual(this.keystore, bsreV2KeystoreResponsePayload.keystore) && this.isEmergency == bsreV2KeystoreResponsePayload.isEmergency;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = this.keystore.hashCode() * 31;
        boolean z = this.isEmergency;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public String toString() {
        String str = this.keystore;
        return "BsreV2KeystoreResponsePayload(keystore=" + str + ", isEmergency=" + this.isEmergency + ")";
    }

    public BsreV2KeystoreResponsePayload(String keystore, boolean z) {
        Intrinsics.checkNotNullParameter(keystore, "keystore");
        this.keystore = keystore;
        this.isEmergency = z;
    }

    public final String getKeystore() {
        return this.keystore;
    }

    public final boolean isEmergency() {
        return this.isEmergency;
    }
}
