package org.informatika.sirekap.model;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.informatika.sirekap.support.security.SecurityFacade;

/* compiled from: Witness.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\u0006\u0010\u001b\u001a\u00020\u0017J\u0006\u0010\u001c\u001a\u00020\u0000J\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u0003J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e¨\u0006 "}, d2 = {"Lorg/informatika/sirekap/model/WitnessPemeriksa;", "", "noHandphone", "", "jenisPemilihan", "idPilihan", "", "url", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;)V", "getIdPilihan", "()J", "setIdPilihan", "(J)V", "getJenisPemilihan", "()Ljava/lang/String;", "getNoHandphone", "getUrl", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "isLocal", "toDecrypted", "toEncrypted", "noHandphoneEncrypted", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class WitnessPemeriksa {
    @SerializedName("idPilihan")
    private long idPilihan;
    @SerializedName("jenisPemilihan")
    private final String jenisPemilihan;
    @SerializedName("noHandphone")
    private final String noHandphone;
    @SerializedName("url")
    private final String url;

    public static /* synthetic */ WitnessPemeriksa copy$default(WitnessPemeriksa witnessPemeriksa, String str, String str2, long j, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = witnessPemeriksa.noHandphone;
        }
        if ((i & 2) != 0) {
            str2 = witnessPemeriksa.jenisPemilihan;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            j = witnessPemeriksa.idPilihan;
        }
        long j2 = j;
        if ((i & 8) != 0) {
            str3 = witnessPemeriksa.url;
        }
        return witnessPemeriksa.copy(str, str4, j2, str3);
    }

    public final String component1() {
        return this.noHandphone;
    }

    public final String component2() {
        return this.jenisPemilihan;
    }

    public final long component3() {
        return this.idPilihan;
    }

    public final String component4() {
        return this.url;
    }

    public final WitnessPemeriksa copy(String noHandphone, String jenisPemilihan, long j, String url) {
        Intrinsics.checkNotNullParameter(noHandphone, "noHandphone");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(url, "url");
        return new WitnessPemeriksa(noHandphone, jenisPemilihan, j, url);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WitnessPemeriksa) {
            WitnessPemeriksa witnessPemeriksa = (WitnessPemeriksa) obj;
            return Intrinsics.areEqual(this.noHandphone, witnessPemeriksa.noHandphone) && Intrinsics.areEqual(this.jenisPemilihan, witnessPemeriksa.jenisPemilihan) && this.idPilihan == witnessPemeriksa.idPilihan && Intrinsics.areEqual(this.url, witnessPemeriksa.url);
        }
        return false;
    }

    public int hashCode() {
        return (((((this.noHandphone.hashCode() * 31) + this.jenisPemilihan.hashCode()) * 31) + Long.hashCode(this.idPilihan)) * 31) + this.url.hashCode();
    }

    public String toString() {
        String str = this.noHandphone;
        String str2 = this.jenisPemilihan;
        long j = this.idPilihan;
        return "WitnessPemeriksa(noHandphone=" + str + ", jenisPemilihan=" + str2 + ", idPilihan=" + j + ", url=" + this.url + ")";
    }

    public WitnessPemeriksa(String noHandphone, String jenisPemilihan, long j, String url) {
        Intrinsics.checkNotNullParameter(noHandphone, "noHandphone");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(url, "url");
        this.noHandphone = noHandphone;
        this.jenisPemilihan = jenisPemilihan;
        this.idPilihan = j;
        this.url = url;
    }

    public final String getNoHandphone() {
        return this.noHandphone;
    }

    public final String getJenisPemilihan() {
        return this.jenisPemilihan;
    }

    public final long getIdPilihan() {
        return this.idPilihan;
    }

    public final void setIdPilihan(long j) {
        this.idPilihan = j;
    }

    public final String getUrl() {
        return this.url;
    }

    public final WitnessPemeriksa toEncrypted(String noHandphoneEncrypted) {
        Intrinsics.checkNotNullParameter(noHandphoneEncrypted, "noHandphoneEncrypted");
        return copy$default(this, noHandphoneEncrypted, null, 0L, null, 14, null);
    }

    public final WitnessPemeriksa toDecrypted() {
        return copy$default(this, SecurityFacade.INSTANCE.decryptCode(this.noHandphone), null, 0L, null, 14, null);
    }

    public final boolean isLocal() {
        return StringsKt.isBlank(this.url);
    }
}
