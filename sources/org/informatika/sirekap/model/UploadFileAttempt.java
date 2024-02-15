package org.informatika.sirekap.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xmpbox.type.JobType;

/* compiled from: UploadFileAttempt.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\b\u0087\b\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0013\u001a\u00020\bHÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0015\u001a\u00020\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u001a"}, d2 = {"Lorg/informatika/sirekap/model/UploadFileAttempt;", "", JobType.ID, "", "type", "attempt", "", "isSuccess", "", "(Ljava/lang/String;Ljava/lang/String;IZ)V", "getAttempt", "()I", "getId", "()Ljava/lang/String;", "()Z", "getType", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UploadFileAttempt {
    public static final Companion Companion = new Companion(null);
    public static final String FILE_TYPE_FORM_C_REKAP = "form_c_rekap";
    public static final String FILE_TYPE_SPECIAL_OCCURRENCE = "kejadian_khusus";
    public static final String FILE_TYPE_WITNESS_ATTENDANCE = "daftar_hadir";
    private final int attempt;

    /* renamed from: id */
    private final String f70id;
    private final boolean isSuccess;
    private final String type;

    public static /* synthetic */ UploadFileAttempt copy$default(UploadFileAttempt uploadFileAttempt, String str, String str2, int i, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = uploadFileAttempt.f70id;
        }
        if ((i2 & 2) != 0) {
            str2 = uploadFileAttempt.type;
        }
        if ((i2 & 4) != 0) {
            i = uploadFileAttempt.attempt;
        }
        if ((i2 & 8) != 0) {
            z = uploadFileAttempt.isSuccess;
        }
        return uploadFileAttempt.copy(str, str2, i, z);
    }

    public final String component1() {
        return this.f70id;
    }

    public final String component2() {
        return this.type;
    }

    public final int component3() {
        return this.attempt;
    }

    public final boolean component4() {
        return this.isSuccess;
    }

    public final UploadFileAttempt copy(String id2, String type, int i, boolean z) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(type, "type");
        return new UploadFileAttempt(id2, type, i, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UploadFileAttempt) {
            UploadFileAttempt uploadFileAttempt = (UploadFileAttempt) obj;
            return Intrinsics.areEqual(this.f70id, uploadFileAttempt.f70id) && Intrinsics.areEqual(this.type, uploadFileAttempt.type) && this.attempt == uploadFileAttempt.attempt && this.isSuccess == uploadFileAttempt.isSuccess;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((this.f70id.hashCode() * 31) + this.type.hashCode()) * 31) + Integer.hashCode(this.attempt)) * 31;
        boolean z = this.isSuccess;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public String toString() {
        String str = this.f70id;
        String str2 = this.type;
        int i = this.attempt;
        return "UploadFileAttempt(id=" + str + ", type=" + str2 + ", attempt=" + i + ", isSuccess=" + this.isSuccess + ")";
    }

    public UploadFileAttempt(String id2, String type, int i, boolean z) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(type, "type");
        this.f70id = id2;
        this.type = type;
        this.attempt = i;
        this.isSuccess = z;
    }

    public /* synthetic */ UploadFileAttempt(String str, String str2, int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i, (i2 & 8) != 0 ? false : z);
    }

    public final String getId() {
        return this.f70id;
    }

    public final String getType() {
        return this.type;
    }

    public final int getAttempt() {
        return this.attempt;
    }

    public final boolean isSuccess() {
        return this.isSuccess;
    }

    /* compiled from: UploadFileAttempt.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lorg/informatika/sirekap/model/UploadFileAttempt$Companion;", "", "()V", "FILE_TYPE_FORM_C_REKAP", "", "FILE_TYPE_SPECIAL_OCCURRENCE", "FILE_TYPE_WITNESS_ATTENDANCE", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
