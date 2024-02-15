package org.informatika.sirekap.api.response;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BsreKeystoreResponsePayload.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/informatika/sirekap/api/response/BsreKeystoreResponsePayload;", "", "keystore", "", "passphrase", "(Ljava/lang/String;Ljava/lang/String;)V", "getKeystore", "()Ljava/lang/String;", "getPassphrase", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BsreKeystoreResponsePayload {
    @SerializedName("pkcs12")
    private final String keystore;
    @SerializedName("passphrase")
    private final String passphrase;

    public static /* synthetic */ BsreKeystoreResponsePayload copy$default(BsreKeystoreResponsePayload bsreKeystoreResponsePayload, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bsreKeystoreResponsePayload.keystore;
        }
        if ((i & 2) != 0) {
            str2 = bsreKeystoreResponsePayload.passphrase;
        }
        return bsreKeystoreResponsePayload.copy(str, str2);
    }

    public final String component1() {
        return this.keystore;
    }

    public final String component2() {
        return this.passphrase;
    }

    public final BsreKeystoreResponsePayload copy(String keystore, String passphrase) {
        Intrinsics.checkNotNullParameter(keystore, "keystore");
        Intrinsics.checkNotNullParameter(passphrase, "passphrase");
        return new BsreKeystoreResponsePayload(keystore, passphrase);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BsreKeystoreResponsePayload) {
            BsreKeystoreResponsePayload bsreKeystoreResponsePayload = (BsreKeystoreResponsePayload) obj;
            return Intrinsics.areEqual(this.keystore, bsreKeystoreResponsePayload.keystore) && Intrinsics.areEqual(this.passphrase, bsreKeystoreResponsePayload.passphrase);
        }
        return false;
    }

    public int hashCode() {
        return (this.keystore.hashCode() * 31) + this.passphrase.hashCode();
    }

    public String toString() {
        String str = this.keystore;
        return "BsreKeystoreResponsePayload(keystore=" + str + ", passphrase=" + this.passphrase + ")";
    }

    public BsreKeystoreResponsePayload(String keystore, String passphrase) {
        Intrinsics.checkNotNullParameter(keystore, "keystore");
        Intrinsics.checkNotNullParameter(passphrase, "passphrase");
        this.keystore = keystore;
        this.passphrase = passphrase;
    }

    public final String getKeystore() {
        return this.keystore;
    }

    public final String getPassphrase() {
        return this.passphrase;
    }
}
