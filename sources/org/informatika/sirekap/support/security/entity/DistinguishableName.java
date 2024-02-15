package org.informatika.sirekap.support.security.entity;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DistinguishableName.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lorg/informatika/sirekap/support/security/entity/DistinguishableName;", "", "commonName", "", "userId", "locality", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCommonName", "()Ljava/lang/String;", "getLocality", "getUserId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DistinguishableName {
    private final String commonName;
    private final String locality;
    private final String userId;

    public static /* synthetic */ DistinguishableName copy$default(DistinguishableName distinguishableName, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = distinguishableName.commonName;
        }
        if ((i & 2) != 0) {
            str2 = distinguishableName.userId;
        }
        if ((i & 4) != 0) {
            str3 = distinguishableName.locality;
        }
        return distinguishableName.copy(str, str2, str3);
    }

    public final String component1() {
        return this.commonName;
    }

    public final String component2() {
        return this.userId;
    }

    public final String component3() {
        return this.locality;
    }

    public final DistinguishableName copy(String commonName, String userId, String locality) {
        Intrinsics.checkNotNullParameter(commonName, "commonName");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(locality, "locality");
        return new DistinguishableName(commonName, userId, locality);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DistinguishableName) {
            DistinguishableName distinguishableName = (DistinguishableName) obj;
            return Intrinsics.areEqual(this.commonName, distinguishableName.commonName) && Intrinsics.areEqual(this.userId, distinguishableName.userId) && Intrinsics.areEqual(this.locality, distinguishableName.locality);
        }
        return false;
    }

    public int hashCode() {
        return (((this.commonName.hashCode() * 31) + this.userId.hashCode()) * 31) + this.locality.hashCode();
    }

    public String toString() {
        String str = this.commonName;
        String str2 = this.userId;
        return "DistinguishableName(commonName=" + str + ", userId=" + str2 + ", locality=" + this.locality + ")";
    }

    public DistinguishableName(String commonName, String userId, String locality) {
        Intrinsics.checkNotNullParameter(commonName, "commonName");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(locality, "locality");
        this.commonName = commonName;
        this.userId = userId;
        this.locality = locality;
    }

    public final String getCommonName() {
        return this.commonName;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final String getLocality() {
        return this.locality;
    }
}
