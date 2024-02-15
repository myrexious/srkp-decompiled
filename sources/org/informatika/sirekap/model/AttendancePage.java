package org.informatika.sirekap.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xmpbox.type.JobType;

/* compiled from: AttendancePage.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001c\b\u0087\b\u0018\u0000 '2\u00020\u0001:\u0001'B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\tHÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\fHÆ\u0003JO\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010#\u001a\u00020\f2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\tHÖ\u0001J\t\u0010&\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006("}, d2 = {"Lorg/informatika/sirekap/model/AttendancePage;", "", JobType.ID, "", "kodeTps", "", "photoPath", "croppedPhotoPath", "urutan", "", "hashDocumentCropped", "checked", "", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Z)V", "getChecked", "()Z", "getCroppedPhotoPath", "()Ljava/lang/String;", "setCroppedPhotoPath", "(Ljava/lang/String;)V", "getHashDocumentCropped", "getId", "()J", "getKodeTps", "getPhotoPath", "getUrutan", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AttendancePage {
    public static final Companion Companion = new Companion(null);
    public static final String ELECTION_ATTENDANCE_LIST = "daftar_hadir";
    private final boolean checked;
    private String croppedPhotoPath;
    private final String hashDocumentCropped;

    /* renamed from: id */
    private final long f60id;
    private final String kodeTps;
    private final String photoPath;
    private final int urutan;

    public final long component1() {
        return this.f60id;
    }

    public final String component2() {
        return this.kodeTps;
    }

    public final String component3() {
        return this.photoPath;
    }

    public final String component4() {
        return this.croppedPhotoPath;
    }

    public final int component5() {
        return this.urutan;
    }

    public final String component6() {
        return this.hashDocumentCropped;
    }

    public final boolean component7() {
        return this.checked;
    }

    public final AttendancePage copy(long j, String kodeTps, String photoPath, String croppedPhotoPath, int i, String hashDocumentCropped, boolean z) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(photoPath, "photoPath");
        Intrinsics.checkNotNullParameter(croppedPhotoPath, "croppedPhotoPath");
        Intrinsics.checkNotNullParameter(hashDocumentCropped, "hashDocumentCropped");
        return new AttendancePage(j, kodeTps, photoPath, croppedPhotoPath, i, hashDocumentCropped, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AttendancePage) {
            AttendancePage attendancePage = (AttendancePage) obj;
            return this.f60id == attendancePage.f60id && Intrinsics.areEqual(this.kodeTps, attendancePage.kodeTps) && Intrinsics.areEqual(this.photoPath, attendancePage.photoPath) && Intrinsics.areEqual(this.croppedPhotoPath, attendancePage.croppedPhotoPath) && this.urutan == attendancePage.urutan && Intrinsics.areEqual(this.hashDocumentCropped, attendancePage.hashDocumentCropped) && this.checked == attendancePage.checked;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((((((((Long.hashCode(this.f60id) * 31) + this.kodeTps.hashCode()) * 31) + this.photoPath.hashCode()) * 31) + this.croppedPhotoPath.hashCode()) * 31) + Integer.hashCode(this.urutan)) * 31) + this.hashDocumentCropped.hashCode()) * 31;
        boolean z = this.checked;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public String toString() {
        long j = this.f60id;
        String str = this.kodeTps;
        String str2 = this.photoPath;
        String str3 = this.croppedPhotoPath;
        int i = this.urutan;
        String str4 = this.hashDocumentCropped;
        return "AttendancePage(id=" + j + ", kodeTps=" + str + ", photoPath=" + str2 + ", croppedPhotoPath=" + str3 + ", urutan=" + i + ", hashDocumentCropped=" + str4 + ", checked=" + this.checked + ")";
    }

    public AttendancePage(long j, String kodeTps, String photoPath, String croppedPhotoPath, int i, String hashDocumentCropped, boolean z) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(photoPath, "photoPath");
        Intrinsics.checkNotNullParameter(croppedPhotoPath, "croppedPhotoPath");
        Intrinsics.checkNotNullParameter(hashDocumentCropped, "hashDocumentCropped");
        this.f60id = j;
        this.kodeTps = kodeTps;
        this.photoPath = photoPath;
        this.croppedPhotoPath = croppedPhotoPath;
        this.urutan = i;
        this.hashDocumentCropped = hashDocumentCropped;
        this.checked = z;
    }

    public /* synthetic */ AttendancePage(long j, String str, String str2, String str3, int i, String str4, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, str, str2, str3, i, str4, (i2 & 64) != 0 ? false : z);
    }

    public final long getId() {
        return this.f60id;
    }

    public final String getKodeTps() {
        return this.kodeTps;
    }

    public final String getPhotoPath() {
        return this.photoPath;
    }

    public final String getCroppedPhotoPath() {
        return this.croppedPhotoPath;
    }

    public final void setCroppedPhotoPath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.croppedPhotoPath = str;
    }

    public final int getUrutan() {
        return this.urutan;
    }

    public final String getHashDocumentCropped() {
        return this.hashDocumentCropped;
    }

    public final boolean getChecked() {
        return this.checked;
    }

    /* compiled from: AttendancePage.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lorg/informatika/sirekap/model/AttendancePage$Companion;", "", "()V", "ELECTION_ATTENDANCE_LIST", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
