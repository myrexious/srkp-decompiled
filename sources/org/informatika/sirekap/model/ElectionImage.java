package org.informatika.sirekap.model;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xmpbox.type.JobType;

/* compiled from: ElectionImage.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\b\u0018\u0000 )2\u00020\u0001:\u0001)BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\fJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003JP\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u000e\u0010 \u001a\u00020\u00032\u0006\u0010!\u001a\u00020\"J\u000e\u0010#\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\"J\u000e\u0010$\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\"J\t\u0010%\u001a\u00020\u0006HÖ\u0001J\u0006\u0010&\u001a\u00020\u001eJ\u0006\u0010'\u001a\u00020\u001eJ\t\u0010(\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006*"}, d2 = {"Lorg/informatika/sirekap/model/ElectionImage;", "", JobType.ID, "", "name", NotificationCompat.CATEGORY_STATUS, "", "electionPemilihan", "electionPageNumber", "invalidReason", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "getElectionPageNumber", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getElectionPemilihan", "()Ljava/lang/String;", "getId", "getInvalidReason", "getName", "getStatus", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Lorg/informatika/sirekap/model/ElectionImage;", "equals", "", "other", "getElectionPageNumberFormatted", "context", "Landroid/content/Context;", "getElectionPemilihanFormatted", "getInvalidReasonFormatted", "hashCode", "isInvalid", "isValid", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ElectionImage {
    public static final Companion Companion = new Companion(null);
    public static final int ELECTION_IMAGE_STATUS_INVALID = 4;
    public static final int ELECTION_IMAGE_STATUS_NOT_SENT = 1;
    public static final int ELECTION_IMAGE_STATUS_PROCESSED = 2;
    public static final int ELECTION_IMAGE_STATUS_SCANNED = 5;
    public static final int ELECTION_IMAGE_STATUS_VALID = 3;
    private final Integer electionPageNumber;
    private final String electionPemilihan;

    /* renamed from: id */
    private final String f63id;
    private final String invalidReason;
    private final String name;
    private final int status;

    public static /* synthetic */ ElectionImage copy$default(ElectionImage electionImage, String str, String str2, int i, String str3, Integer num, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = electionImage.f63id;
        }
        if ((i2 & 2) != 0) {
            str2 = electionImage.name;
        }
        String str5 = str2;
        if ((i2 & 4) != 0) {
            i = electionImage.status;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            str3 = electionImage.electionPemilihan;
        }
        String str6 = str3;
        if ((i2 & 16) != 0) {
            num = electionImage.electionPageNumber;
        }
        Integer num2 = num;
        if ((i2 & 32) != 0) {
            str4 = electionImage.invalidReason;
        }
        return electionImage.copy(str, str5, i3, str6, num2, str4);
    }

    public final String component1() {
        return this.f63id;
    }

    public final String component2() {
        return this.name;
    }

    public final int component3() {
        return this.status;
    }

    public final String component4() {
        return this.electionPemilihan;
    }

    public final Integer component5() {
        return this.electionPageNumber;
    }

    public final String component6() {
        return this.invalidReason;
    }

    public final ElectionImage copy(String id2, String name, int i, String str, Integer num, String str2) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        return new ElectionImage(id2, name, i, str, num, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ElectionImage) {
            ElectionImage electionImage = (ElectionImage) obj;
            return Intrinsics.areEqual(this.f63id, electionImage.f63id) && Intrinsics.areEqual(this.name, electionImage.name) && this.status == electionImage.status && Intrinsics.areEqual(this.electionPemilihan, electionImage.electionPemilihan) && Intrinsics.areEqual(this.electionPageNumber, electionImage.electionPageNumber) && Intrinsics.areEqual(this.invalidReason, electionImage.invalidReason);
        }
        return false;
    }

    public final String getElectionPemilihanFormatted(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return "Jenis Pemilihan: Presiden dan Wakil Presiden";
    }

    public int hashCode() {
        int hashCode = ((((this.f63id.hashCode() * 31) + this.name.hashCode()) * 31) + Integer.hashCode(this.status)) * 31;
        String str = this.electionPemilihan;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        Integer num = this.electionPageNumber;
        int hashCode3 = (hashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        String str2 = this.invalidReason;
        return hashCode3 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        String str = this.f63id;
        String str2 = this.name;
        int i = this.status;
        String str3 = this.electionPemilihan;
        Integer num = this.electionPageNumber;
        return "ElectionImage(id=" + str + ", name=" + str2 + ", status=" + i + ", electionPemilihan=" + str3 + ", electionPageNumber=" + num + ", invalidReason=" + this.invalidReason + ")";
    }

    public ElectionImage(String id2, String name, int i, String str, Integer num, String str2) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        this.f63id = id2;
        this.name = name;
        this.status = i;
        this.electionPemilihan = str;
        this.electionPageNumber = num;
        this.invalidReason = str2;
    }

    public /* synthetic */ ElectionImage(String str, String str2, int i, String str3, Integer num, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? null : num, (i2 & 32) != 0 ? null : str4);
    }

    public final String getId() {
        return this.f63id;
    }

    public final String getName() {
        return this.name;
    }

    public final int getStatus() {
        return this.status;
    }

    public final String getElectionPemilihan() {
        return this.electionPemilihan;
    }

    public final Integer getElectionPageNumber() {
        return this.electionPageNumber;
    }

    public final String getInvalidReason() {
        return this.invalidReason;
    }

    public final boolean isValid() {
        return this.status == 3;
    }

    public final boolean isInvalid() {
        return this.status == 4;
    }

    public final String getElectionPageNumberFormatted(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return "Nomor Halaman: " + this.electionPageNumber;
    }

    public final String getInvalidReasonFormatted(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return "Alasan: " + this.invalidReason;
    }

    /* compiled from: ElectionImage.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/informatika/sirekap/model/ElectionImage$Companion;", "", "()V", "ELECTION_IMAGE_STATUS_INVALID", "", "ELECTION_IMAGE_STATUS_NOT_SENT", "ELECTION_IMAGE_STATUS_PROCESSED", "ELECTION_IMAGE_STATUS_SCANNED", "ELECTION_IMAGE_STATUS_VALID", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
