package org.informatika.sirekap.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xmpbox.type.JobType;

/* compiled from: Tps.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/model/KotaKabupaten;", "", JobType.ID, "", "name", "parent", "Lorg/informatika/sirekap/model/Provinsi;", "(Ljava/lang/String;Ljava/lang/String;Lorg/informatika/sirekap/model/Provinsi;)V", "getId", "()Ljava/lang/String;", "getName", "getParent", "()Lorg/informatika/sirekap/model/Provinsi;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class KotaKabupaten {

    /* renamed from: id */
    private final String f67id;
    private final String name;
    private final Provinsi parent;

    public KotaKabupaten(String id2, String str, Provinsi parent) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(parent, "parent");
        this.f67id = id2;
        this.name = str;
        this.parent = parent;
    }

    public final String getId() {
        return this.f67id;
    }

    public final String getName() {
        return this.name;
    }

    public final Provinsi getParent() {
        return this.parent;
    }
}
