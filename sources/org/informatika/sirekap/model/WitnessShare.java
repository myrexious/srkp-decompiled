package org.informatika.sirekap.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.support.security.SecurityFacade;

/* compiled from: Witness.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\u0006\u0010\u0014\u001a\u00020\u0000J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u0016"}, d2 = {"Lorg/informatika/sirekap/model/WitnessShare;", "", "noHandphone", "", "jenisPemilihan", "isShared", "", "(Ljava/lang/String;Ljava/lang/String;Z)V", "()Z", "getJenisPemilihan", "()Ljava/lang/String;", "getNoHandphone", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toDecrypted", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class WitnessShare {
    private final boolean isShared;
    private final String jenisPemilihan;
    private final String noHandphone;

    public static /* synthetic */ WitnessShare copy$default(WitnessShare witnessShare, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = witnessShare.noHandphone;
        }
        if ((i & 2) != 0) {
            str2 = witnessShare.jenisPemilihan;
        }
        if ((i & 4) != 0) {
            z = witnessShare.isShared;
        }
        return witnessShare.copy(str, str2, z);
    }

    public final String component1() {
        return this.noHandphone;
    }

    public final String component2() {
        return this.jenisPemilihan;
    }

    public final boolean component3() {
        return this.isShared;
    }

    public final WitnessShare copy(String noHandphone, String jenisPemilihan, boolean z) {
        Intrinsics.checkNotNullParameter(noHandphone, "noHandphone");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        return new WitnessShare(noHandphone, jenisPemilihan, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WitnessShare) {
            WitnessShare witnessShare = (WitnessShare) obj;
            return Intrinsics.areEqual(this.noHandphone, witnessShare.noHandphone) && Intrinsics.areEqual(this.jenisPemilihan, witnessShare.jenisPemilihan) && this.isShared == witnessShare.isShared;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((this.noHandphone.hashCode() * 31) + this.jenisPemilihan.hashCode()) * 31;
        boolean z = this.isShared;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public String toString() {
        String str = this.noHandphone;
        String str2 = this.jenisPemilihan;
        return "WitnessShare(noHandphone=" + str + ", jenisPemilihan=" + str2 + ", isShared=" + this.isShared + ")";
    }

    public WitnessShare(String noHandphone, String jenisPemilihan, boolean z) {
        Intrinsics.checkNotNullParameter(noHandphone, "noHandphone");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        this.noHandphone = noHandphone;
        this.jenisPemilihan = jenisPemilihan;
        this.isShared = z;
    }

    public final String getNoHandphone() {
        return this.noHandphone;
    }

    public final String getJenisPemilihan() {
        return this.jenisPemilihan;
    }

    public final boolean isShared() {
        return this.isShared;
    }

    public final WitnessShare toDecrypted() {
        return copy$default(this, SecurityFacade.INSTANCE.decryptCode(this.noHandphone), null, false, 6, null);
    }
}
