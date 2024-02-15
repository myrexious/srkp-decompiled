package org.informatika.sirekap.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xmpbox.type.JobType;

/* compiled from: Tps.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lorg/informatika/sirekap/model/Kecamatan;", "", JobType.ID, "", "name", "parent", "Lorg/informatika/sirekap/model/KotaKabupaten;", "(Ljava/lang/String;Ljava/lang/String;Lorg/informatika/sirekap/model/KotaKabupaten;)V", "getId", "()Ljava/lang/String;", "getName", "getParent", "()Lorg/informatika/sirekap/model/KotaKabupaten;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Kecamatan {

    /* renamed from: id */
    private final String f65id;
    private final String name;
    private final KotaKabupaten parent;

    public static /* synthetic */ Kecamatan copy$default(Kecamatan kecamatan, String str, String str2, KotaKabupaten kotaKabupaten, int i, Object obj) {
        if ((i & 1) != 0) {
            str = kecamatan.f65id;
        }
        if ((i & 2) != 0) {
            str2 = kecamatan.name;
        }
        if ((i & 4) != 0) {
            kotaKabupaten = kecamatan.parent;
        }
        return kecamatan.copy(str, str2, kotaKabupaten);
    }

    public final String component1() {
        return this.f65id;
    }

    public final String component2() {
        return this.name;
    }

    public final KotaKabupaten component3() {
        return this.parent;
    }

    public final Kecamatan copy(String id2, String str, KotaKabupaten parent) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(parent, "parent");
        return new Kecamatan(id2, str, parent);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Kecamatan) {
            Kecamatan kecamatan = (Kecamatan) obj;
            return Intrinsics.areEqual(this.f65id, kecamatan.f65id) && Intrinsics.areEqual(this.name, kecamatan.name) && Intrinsics.areEqual(this.parent, kecamatan.parent);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.f65id.hashCode() * 31;
        String str = this.name;
        return ((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.parent.hashCode();
    }

    public String toString() {
        String str = this.f65id;
        String str2 = this.name;
        return "Kecamatan(id=" + str + ", name=" + str2 + ", parent=" + this.parent + ")";
    }

    public Kecamatan(String id2, String str, KotaKabupaten parent) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(parent, "parent");
        this.f65id = id2;
        this.name = str;
        this.parent = parent;
    }

    public final String getId() {
        return this.f65id;
    }

    public final String getName() {
        return this.name;
    }

    public final KotaKabupaten getParent() {
        return this.parent;
    }
}
