package org.informatika.sirekap.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xmpbox.type.JobType;

/* compiled from: Tps.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lorg/informatika/sirekap/model/Kelurahan;", "", JobType.ID, "", "name", "parent", "Lorg/informatika/sirekap/model/Kecamatan;", "(Ljava/lang/String;Ljava/lang/String;Lorg/informatika/sirekap/model/Kecamatan;)V", "getId", "()Ljava/lang/String;", "getName", "getParent", "()Lorg/informatika/sirekap/model/Kecamatan;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Kelurahan {

    /* renamed from: id */
    private final String f66id;
    private final String name;
    private final Kecamatan parent;

    public static /* synthetic */ Kelurahan copy$default(Kelurahan kelurahan, String str, String str2, Kecamatan kecamatan, int i, Object obj) {
        if ((i & 1) != 0) {
            str = kelurahan.f66id;
        }
        if ((i & 2) != 0) {
            str2 = kelurahan.name;
        }
        if ((i & 4) != 0) {
            kecamatan = kelurahan.parent;
        }
        return kelurahan.copy(str, str2, kecamatan);
    }

    public final String component1() {
        return this.f66id;
    }

    public final String component2() {
        return this.name;
    }

    public final Kecamatan component3() {
        return this.parent;
    }

    public final Kelurahan copy(String id2, String str, Kecamatan parent) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(parent, "parent");
        return new Kelurahan(id2, str, parent);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Kelurahan) {
            Kelurahan kelurahan = (Kelurahan) obj;
            return Intrinsics.areEqual(this.f66id, kelurahan.f66id) && Intrinsics.areEqual(this.name, kelurahan.name) && Intrinsics.areEqual(this.parent, kelurahan.parent);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.f66id.hashCode() * 31;
        String str = this.name;
        return ((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.parent.hashCode();
    }

    public String toString() {
        String str = this.f66id;
        String str2 = this.name;
        return "Kelurahan(id=" + str + ", name=" + str2 + ", parent=" + this.parent + ")";
    }

    public Kelurahan(String id2, String str, Kecamatan parent) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(parent, "parent");
        this.f66id = id2;
        this.name = str;
        this.parent = parent;
    }

    public final String getId() {
        return this.f66id;
    }

    public final String getName() {
        return this.name;
    }

    public final Kecamatan getParent() {
        return this.parent;
    }
}
