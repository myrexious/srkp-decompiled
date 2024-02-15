package org.informatika.sirekap.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UploadImageAttempt.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\bHÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0015\u001a\u00020\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u0019"}, d2 = {"Lorg/informatika/sirekap/model/UploadImageAttempt;", "", "electionPageId", "", "jenisImage", "", "attempt", "isSuccess", "", "(Ljava/lang/String;IIZ)V", "getAttempt", "()I", "getElectionPageId", "()Ljava/lang/String;", "()Z", "getJenisImage", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UploadImageAttempt {
    private final int attempt;
    private final String electionPageId;
    private final boolean isSuccess;
    private final int jenisImage;

    public static /* synthetic */ UploadImageAttempt copy$default(UploadImageAttempt uploadImageAttempt, String str, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = uploadImageAttempt.electionPageId;
        }
        if ((i3 & 2) != 0) {
            i = uploadImageAttempt.jenisImage;
        }
        if ((i3 & 4) != 0) {
            i2 = uploadImageAttempt.attempt;
        }
        if ((i3 & 8) != 0) {
            z = uploadImageAttempt.isSuccess;
        }
        return uploadImageAttempt.copy(str, i, i2, z);
    }

    public final String component1() {
        return this.electionPageId;
    }

    public final int component2() {
        return this.jenisImage;
    }

    public final int component3() {
        return this.attempt;
    }

    public final boolean component4() {
        return this.isSuccess;
    }

    public final UploadImageAttempt copy(String electionPageId, int i, int i2, boolean z) {
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        return new UploadImageAttempt(electionPageId, i, i2, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UploadImageAttempt) {
            UploadImageAttempt uploadImageAttempt = (UploadImageAttempt) obj;
            return Intrinsics.areEqual(this.electionPageId, uploadImageAttempt.electionPageId) && this.jenisImage == uploadImageAttempt.jenisImage && this.attempt == uploadImageAttempt.attempt && this.isSuccess == uploadImageAttempt.isSuccess;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((this.electionPageId.hashCode() * 31) + Integer.hashCode(this.jenisImage)) * 31) + Integer.hashCode(this.attempt)) * 31;
        boolean z = this.isSuccess;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public String toString() {
        String str = this.electionPageId;
        int i = this.jenisImage;
        int i2 = this.attempt;
        return "UploadImageAttempt(electionPageId=" + str + ", jenisImage=" + i + ", attempt=" + i2 + ", isSuccess=" + this.isSuccess + ")";
    }

    public UploadImageAttempt(String electionPageId, int i, int i2, boolean z) {
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        this.electionPageId = electionPageId;
        this.jenisImage = i;
        this.attempt = i2;
        this.isSuccess = z;
    }

    public /* synthetic */ UploadImageAttempt(String str, int i, int i2, boolean z, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, i2, (i3 & 8) != 0 ? false : z);
    }

    public final String getElectionPageId() {
        return this.electionPageId;
    }

    public final int getJenisImage() {
        return this.jenisImage;
    }

    public final int getAttempt() {
        return this.attempt;
    }

    public final boolean isSuccess() {
        return this.isSuccess;
    }
}
