package org.informatika.sirekap.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xmpbox.type.JobType;

/* compiled from: SpecialOccurrencePage.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 #2\u00020\u0001:\u0001#B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\nHÆ\u0003JE\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\n2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000f¨\u0006$"}, d2 = {"Lorg/informatika/sirekap/model/SpecialOccurrencePage;", "", JobType.ID, "", "kodeTps", "", "photoPath", "croppedPhotoPath", "hashDocumentCropped", "checked", "", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getChecked", "()Z", "getCroppedPhotoPath", "()Ljava/lang/String;", "setCroppedPhotoPath", "(Ljava/lang/String;)V", "getHashDocumentCropped", "getId", "()J", "getKodeTps", "getPhotoPath", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SpecialOccurrencePage {
    public static final Companion Companion = new Companion(null);
    public static final String ELECTION_SPECIAL_OCCURRENCE = "kejadian_khusus";
    private final boolean checked;
    private String croppedPhotoPath;
    private final String hashDocumentCropped;

    /* renamed from: id */
    private final long f69id;
    private final String kodeTps;
    private final String photoPath;

    public final long component1() {
        return this.f69id;
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

    public final String component5() {
        return this.hashDocumentCropped;
    }

    public final boolean component6() {
        return this.checked;
    }

    public final SpecialOccurrencePage copy(long j, String kodeTps, String photoPath, String croppedPhotoPath, String hashDocumentCropped, boolean z) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(photoPath, "photoPath");
        Intrinsics.checkNotNullParameter(croppedPhotoPath, "croppedPhotoPath");
        Intrinsics.checkNotNullParameter(hashDocumentCropped, "hashDocumentCropped");
        return new SpecialOccurrencePage(j, kodeTps, photoPath, croppedPhotoPath, hashDocumentCropped, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SpecialOccurrencePage) {
            SpecialOccurrencePage specialOccurrencePage = (SpecialOccurrencePage) obj;
            return this.f69id == specialOccurrencePage.f69id && Intrinsics.areEqual(this.kodeTps, specialOccurrencePage.kodeTps) && Intrinsics.areEqual(this.photoPath, specialOccurrencePage.photoPath) && Intrinsics.areEqual(this.croppedPhotoPath, specialOccurrencePage.croppedPhotoPath) && Intrinsics.areEqual(this.hashDocumentCropped, specialOccurrencePage.hashDocumentCropped) && this.checked == specialOccurrencePage.checked;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((((((Long.hashCode(this.f69id) * 31) + this.kodeTps.hashCode()) * 31) + this.photoPath.hashCode()) * 31) + this.croppedPhotoPath.hashCode()) * 31) + this.hashDocumentCropped.hashCode()) * 31;
        boolean z = this.checked;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public String toString() {
        long j = this.f69id;
        String str = this.kodeTps;
        String str2 = this.photoPath;
        String str3 = this.croppedPhotoPath;
        String str4 = this.hashDocumentCropped;
        return "SpecialOccurrencePage(id=" + j + ", kodeTps=" + str + ", photoPath=" + str2 + ", croppedPhotoPath=" + str3 + ", hashDocumentCropped=" + str4 + ", checked=" + this.checked + ")";
    }

    public SpecialOccurrencePage(long j, String kodeTps, String photoPath, String croppedPhotoPath, String hashDocumentCropped, boolean z) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(photoPath, "photoPath");
        Intrinsics.checkNotNullParameter(croppedPhotoPath, "croppedPhotoPath");
        Intrinsics.checkNotNullParameter(hashDocumentCropped, "hashDocumentCropped");
        this.f69id = j;
        this.kodeTps = kodeTps;
        this.photoPath = photoPath;
        this.croppedPhotoPath = croppedPhotoPath;
        this.hashDocumentCropped = hashDocumentCropped;
        this.checked = z;
    }

    public /* synthetic */ SpecialOccurrencePage(long j, String str, String str2, String str3, String str4, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, str, str2, str3, str4, (i & 32) != 0 ? false : z);
    }

    public final long getId() {
        return this.f69id;
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

    public final String getHashDocumentCropped() {
        return this.hashDocumentCropped;
    }

    public final boolean getChecked() {
        return this.checked;
    }

    /* compiled from: SpecialOccurrencePage.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lorg/informatika/sirekap/model/SpecialOccurrencePage$Companion;", "", "()V", "ELECTION_SPECIAL_OCCURRENCE", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
