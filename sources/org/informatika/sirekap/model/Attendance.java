package org.informatika.sirekap.model;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.R;

/* compiled from: Attendance.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003JS\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u00052\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0007HÖ\u0001J\u0006\u0010!\u001a\u00020\u0005J\u0006\u0010\"\u001a\u00020\u0005J\u0006\u0010#\u001a\u00020\u0005J\u000e\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020&J\t\u0010'\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\r\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006("}, d2 = {"Lorg/informatika/sirekap/model/Attendance;", "", "kodeTps", "", "isPdf", "", "uploadPdfStatus", "", "isUploadedPdf", "isUploadedPdfOffline", "pdfFilePath", "pdfFileHash", "(Ljava/lang/String;ZIZZLjava/lang/String;Ljava/lang/String;)V", "hideUploadPdfOfflineButton", "getHideUploadPdfOfflineButton", "()Z", "getKodeTps", "()Ljava/lang/String;", "getPdfFileHash", "getPdfFilePath", "getUploadPdfStatus", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "isLoadingUploadPdf", "isShowUploadPdfButton", "isSuccessUploadPdf", "pdfUploadSuccessDescription", "context", "Landroid/content/Context;", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Attendance {
    private final boolean isPdf;
    private final boolean isUploadedPdf;
    private final boolean isUploadedPdfOffline;
    private final String kodeTps;
    private final String pdfFileHash;
    private final String pdfFilePath;
    private final int uploadPdfStatus;

    public static /* synthetic */ Attendance copy$default(Attendance attendance, String str, boolean z, int i, boolean z2, boolean z3, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = attendance.kodeTps;
        }
        if ((i2 & 2) != 0) {
            z = attendance.isPdf;
        }
        boolean z4 = z;
        if ((i2 & 4) != 0) {
            i = attendance.uploadPdfStatus;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            z2 = attendance.isUploadedPdf;
        }
        boolean z5 = z2;
        if ((i2 & 16) != 0) {
            z3 = attendance.isUploadedPdfOffline;
        }
        boolean z6 = z3;
        if ((i2 & 32) != 0) {
            str2 = attendance.pdfFilePath;
        }
        String str4 = str2;
        if ((i2 & 64) != 0) {
            str3 = attendance.pdfFileHash;
        }
        return attendance.copy(str, z4, i3, z5, z6, str4, str3);
    }

    public final String component1() {
        return this.kodeTps;
    }

    public final boolean component2() {
        return this.isPdf;
    }

    public final int component3() {
        return this.uploadPdfStatus;
    }

    public final boolean component4() {
        return this.isUploadedPdf;
    }

    public final boolean component5() {
        return this.isUploadedPdfOffline;
    }

    public final String component6() {
        return this.pdfFilePath;
    }

    public final String component7() {
        return this.pdfFileHash;
    }

    public final Attendance copy(String kodeTps, boolean z, int i, boolean z2, boolean z3, String str, String str2) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        return new Attendance(kodeTps, z, i, z2, z3, str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Attendance) {
            Attendance attendance = (Attendance) obj;
            return Intrinsics.areEqual(this.kodeTps, attendance.kodeTps) && this.isPdf == attendance.isPdf && this.uploadPdfStatus == attendance.uploadPdfStatus && this.isUploadedPdf == attendance.isUploadedPdf && this.isUploadedPdfOffline == attendance.isUploadedPdfOffline && Intrinsics.areEqual(this.pdfFilePath, attendance.pdfFilePath) && Intrinsics.areEqual(this.pdfFileHash, attendance.pdfFileHash);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = this.kodeTps.hashCode() * 31;
        boolean z = this.isPdf;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int hashCode2 = (((hashCode + i) * 31) + Integer.hashCode(this.uploadPdfStatus)) * 31;
        boolean z2 = this.isUploadedPdf;
        int i2 = z2;
        if (z2 != 0) {
            i2 = 1;
        }
        int i3 = (hashCode2 + i2) * 31;
        boolean z3 = this.isUploadedPdfOffline;
        int i4 = (i3 + (z3 ? 1 : z3 ? 1 : 0)) * 31;
        String str = this.pdfFilePath;
        int hashCode3 = (i4 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.pdfFileHash;
        return hashCode3 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        String str = this.kodeTps;
        boolean z = this.isPdf;
        int i = this.uploadPdfStatus;
        boolean z2 = this.isUploadedPdf;
        boolean z3 = this.isUploadedPdfOffline;
        String str2 = this.pdfFilePath;
        return "Attendance(kodeTps=" + str + ", isPdf=" + z + ", uploadPdfStatus=" + i + ", isUploadedPdf=" + z2 + ", isUploadedPdfOffline=" + z3 + ", pdfFilePath=" + str2 + ", pdfFileHash=" + this.pdfFileHash + ")";
    }

    public Attendance(String kodeTps, boolean z, int i, boolean z2, boolean z3, String str, String str2) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        this.kodeTps = kodeTps;
        this.isPdf = z;
        this.uploadPdfStatus = i;
        this.isUploadedPdf = z2;
        this.isUploadedPdfOffline = z3;
        this.pdfFilePath = str;
        this.pdfFileHash = str2;
    }

    public /* synthetic */ Attendance(String str, boolean z, int i, boolean z2, boolean z3, String str2, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? -1 : i, (i2 & 8) != 0 ? false : z2, (i2 & 16) == 0 ? z3 : false, (i2 & 32) != 0 ? null : str2, (i2 & 64) == 0 ? str3 : null);
    }

    public final String getKodeTps() {
        return this.kodeTps;
    }

    public final boolean isPdf() {
        return this.isPdf;
    }

    public final int getUploadPdfStatus() {
        return this.uploadPdfStatus;
    }

    public final boolean isUploadedPdf() {
        return this.isUploadedPdf;
    }

    public final boolean isUploadedPdfOffline() {
        return this.isUploadedPdfOffline;
    }

    public final String getPdfFilePath() {
        return this.pdfFilePath;
    }

    public final String getPdfFileHash() {
        return this.pdfFileHash;
    }

    public final boolean isShowUploadPdfButton() {
        return this.isPdf && !this.isUploadedPdf;
    }

    public final boolean isLoadingUploadPdf() {
        return this.uploadPdfStatus == 2;
    }

    public final boolean isSuccessUploadPdf() {
        return this.isUploadedPdf;
    }

    public final String pdfUploadSuccessDescription(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.isUploadedPdfOffline) {
            String string = context.getString(R.string.pdf_uploaded_offline);
            Intrinsics.checkNotNullExpressionValue(string, "{\n            context.ge…loaded_offline)\n        }");
            return string;
        }
        String string2 = context.getString(R.string.pdf_uploaded);
        Intrinsics.checkNotNullExpressionValue(string2, "{\n            context.ge…g.pdf_uploaded)\n        }");
        return string2;
    }

    public final boolean getHideUploadPdfOfflineButton() {
        return !(this.isPdf && this.isUploadedPdf && this.isUploadedPdfOffline && !isLoadingUploadPdf());
    }
}
