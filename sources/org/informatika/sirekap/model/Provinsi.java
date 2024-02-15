package org.informatika.sirekap.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xmpbox.type.JobType;

/* compiled from: Tps.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lorg/informatika/sirekap/model/Provinsi;", "", JobType.ID, "", "name", "(Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getName", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Provinsi {

    /* renamed from: id */
    private final String f68id;
    private final String name;

    public Provinsi(String id2, String str) {
        Intrinsics.checkNotNullParameter(id2, "id");
        this.f68id = id2;
        this.name = str;
    }

    public final String getId() {
        return this.f68id;
    }

    public final String getName() {
        return this.name;
    }
}
