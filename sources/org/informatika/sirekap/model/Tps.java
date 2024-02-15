package org.informatika.sirekap.model;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.informatika.sirekap.support.ElectionUtil;

/* compiled from: Tps.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0006HÆ\u0003J)\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010,\u001a\u00020\t2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010.\u001a\u00020\u0003J\t\u0010/\u001a\u000200HÖ\u0001J\b\u00101\u001a\u00020\u0003H\u0016R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\b\u0010\nR\u0011\u0010\u000b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\f\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\u0011\u0010\r\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\r\u0010\nR\u0011\u0010\u000e\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\nR\u0011\u0010\u000f\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\nR\u0011\u0010\u0010\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\nR\u0011\u0010\u0011\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\nR\u0011\u0010\u0012\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\nR\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u00188F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b \u0010\u001eR\u0011\u0010!\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\"\u0010\u001eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001eR\u0013\u0010$\u001a\u0004\u0018\u00010%8F¢\u0006\u0006\u001a\u0004\b&\u0010'¨\u00062"}, d2 = {"Lorg/informatika/sirekap/model/Tps;", "", "kodeTps", "", "name", "kelurahan", "Lorg/informatika/sirekap/model/Kelurahan;", "(Ljava/lang/String;Ljava/lang/String;Lorg/informatika/sirekap/model/Kelurahan;)V", "isAceh", "", "()Z", "isLn", "isLnPos", "isPapua", "isPapuaBarat", "isPapuaBaratDaya", "isPapuaPegunungan", "isPapuaSelatan", "isPapuaTengah", "kabko", "Lorg/informatika/sirekap/model/KotaKabupaten;", "getKabko", "()Lorg/informatika/sirekap/model/KotaKabupaten;", "kecamatan", "Lorg/informatika/sirekap/model/Kecamatan;", "getKecamatan", "()Lorg/informatika/sirekap/model/Kecamatan;", "getKelurahan", "()Lorg/informatika/sirekap/model/Kelurahan;", "getKodeTps", "()Ljava/lang/String;", "kodeTpsFormatted", "getKodeTpsFormatted", "kodeTpsFormattedProfile", "getKodeTpsFormattedProfile", "getName", "provinsi", "Lorg/informatika/sirekap/model/Provinsi;", "getProvinsi", "()Lorg/informatika/sirekap/model/Provinsi;", "component1", "component2", "component3", "copy", "equals", "other", "getWilayahString", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Tps {
    private final Kelurahan kelurahan;
    private final String kodeTps;
    private final String name;

    public static /* synthetic */ Tps copy$default(Tps tps, String str, String str2, Kelurahan kelurahan, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tps.kodeTps;
        }
        if ((i & 2) != 0) {
            str2 = tps.name;
        }
        if ((i & 4) != 0) {
            kelurahan = tps.kelurahan;
        }
        return tps.copy(str, str2, kelurahan);
    }

    public final String component1() {
        return this.kodeTps;
    }

    public final String component2() {
        return this.name;
    }

    public final Kelurahan component3() {
        return this.kelurahan;
    }

    public final Tps copy(String kodeTps, String name, Kelurahan kelurahan) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(name, "name");
        return new Tps(kodeTps, name, kelurahan);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Tps) {
            Tps tps = (Tps) obj;
            return Intrinsics.areEqual(this.kodeTps, tps.kodeTps) && Intrinsics.areEqual(this.name, tps.name) && Intrinsics.areEqual(this.kelurahan, tps.kelurahan);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((this.kodeTps.hashCode() * 31) + this.name.hashCode()) * 31;
        Kelurahan kelurahan = this.kelurahan;
        return hashCode + (kelurahan == null ? 0 : kelurahan.hashCode());
    }

    public Tps(String kodeTps, String name, Kelurahan kelurahan) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(name, "name");
        this.kodeTps = kodeTps;
        this.name = name;
        this.kelurahan = kelurahan;
    }

    public final String getKodeTps() {
        return this.kodeTps;
    }

    public final String getName() {
        return this.name;
    }

    public final Kelurahan getKelurahan() {
        return this.kelurahan;
    }

    public final String getKodeTpsFormatted() {
        return ElectionUtil.extractKodeTpsReal(this.kodeTps);
    }

    public final String getKodeTpsFormattedProfile() {
        return StringsKt.replace$default(this.kodeTps, ElectionUtil.KODE_TPS_SEPARATOR, " - ", false, 4, (Object) null);
    }

    public final Kecamatan getKecamatan() {
        Kelurahan kelurahan = this.kelurahan;
        if (kelurahan != null) {
            return kelurahan.getParent();
        }
        return null;
    }

    public final KotaKabupaten getKabko() {
        Kecamatan kecamatan = getKecamatan();
        if (kecamatan != null) {
            return kecamatan.getParent();
        }
        return null;
    }

    public final Provinsi getProvinsi() {
        KotaKabupaten kabko = getKabko();
        if (kabko != null) {
            return kabko.getParent();
        }
        return null;
    }

    public String toString() {
        if (StringsKt.startsWith(this.name, "TPS", true)) {
            return this.name;
        }
        return "TPS " + this.name;
    }

    public final String getWilayahString() {
        Kelurahan kelurahan = this.kelurahan;
        String name = kelurahan != null ? kelurahan.getName() : null;
        Kecamatan kecamatan = getKecamatan();
        String name2 = kecamatan != null ? kecamatan.getName() : null;
        KotaKabupaten kabko = getKabko();
        String name3 = kabko != null ? kabko.getName() : null;
        Provinsi provinsi = getProvinsi();
        return name + ", " + name2 + ", " + name3 + ", " + (provinsi != null ? provinsi.getName() : null);
    }

    public final boolean isLn() {
        return StringsKt.startsWith$default(getKodeTpsFormatted(), "99", false, 2, (Object) null);
    }

    public final boolean isLnPos() {
        if (isLn()) {
            String lowerCase = this.name.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            return StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) "pos", false, 2, (Object) null);
        }
        return false;
    }

    public final boolean isAceh() {
        return StringsKt.startsWith$default(getKodeTpsFormatted(), "11", false, 2, (Object) null);
    }

    public final boolean isPapua() {
        return StringsKt.startsWith$default(getKodeTpsFormatted(), "91", false, 2, (Object) null);
    }

    public final boolean isPapuaBarat() {
        return StringsKt.startsWith$default(getKodeTpsFormatted(), "92", false, 2, (Object) null);
    }

    public final boolean isPapuaSelatan() {
        return StringsKt.startsWith$default(getKodeTpsFormatted(), "93", false, 2, (Object) null);
    }

    public final boolean isPapuaTengah() {
        return StringsKt.startsWith$default(getKodeTpsFormatted(), "94", false, 2, (Object) null);
    }

    public final boolean isPapuaPegunungan() {
        return StringsKt.startsWith$default(getKodeTpsFormatted(), "95", false, 2, (Object) null);
    }

    public final boolean isPapuaBaratDaya() {
        return StringsKt.startsWith$default(getKodeTpsFormatted(), "96", false, 2, (Object) null);
    }
}
