package org.informatika.sirekap.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmpbox.type.JobType;

/* compiled from: TpsTime.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b!\b\u0087\b\u0018\u0000 /2\u00020\u0001:\u0001/BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u000fHÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\bHÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\t\u0010%\u001a\u00020\bHÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003Jm\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÆ\u0001J\u0006\u0010)\u001a\u00020\u0003J\u0013\u0010*\u001a\u00020\u000f2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010,\u001a\u00020\u0005HÖ\u0001J\u0006\u0010-\u001a\u00020\u0003J\t\u0010.\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u000b\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014¨\u00060"}, d2 = {"Lorg/informatika/sirekap/model/TpsTime;", "", "kodeTps", "", "jenisWaktu", "", "jenisPemilihan", "startDate", "", "startTimeHour", "startTimeMinute", "endDate", "endTimeHour", "endTimeMinute", "isSubmittedOffline", "", "(Ljava/lang/String;ILjava/lang/String;JIIJIIZ)V", "getEndDate", "()J", "getEndTimeHour", "()I", "getEndTimeMinute", "()Z", "getJenisPemilihan", "()Ljava/lang/String;", "getJenisWaktu", "getKodeTps", "getStartDate", "getStartTimeHour", "getStartTimeMinute", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "endDateTimeFormatted", "equals", "other", "hashCode", "startDateTimeFormatted", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TpsTime {
    public static final Companion Companion = new Companion(null);
    public static final int JENIS_WAKTU_PEMUNGUTAN_SUARA = 0;
    public static final int JENIS_WAKTU_PENGHITUNGAN_SUARA = 1;
    private final long endDate;
    private final int endTimeHour;
    private final int endTimeMinute;
    private final boolean isSubmittedOffline;
    private final String jenisPemilihan;
    private final int jenisWaktu;
    private final String kodeTps;
    private final long startDate;
    private final int startTimeHour;
    private final int startTimeMinute;

    public final String component1() {
        return this.kodeTps;
    }

    public final boolean component10() {
        return this.isSubmittedOffline;
    }

    public final int component2() {
        return this.jenisWaktu;
    }

    public final String component3() {
        return this.jenisPemilihan;
    }

    public final long component4() {
        return this.startDate;
    }

    public final int component5() {
        return this.startTimeHour;
    }

    public final int component6() {
        return this.startTimeMinute;
    }

    public final long component7() {
        return this.endDate;
    }

    public final int component8() {
        return this.endTimeHour;
    }

    public final int component9() {
        return this.endTimeMinute;
    }

    public final TpsTime copy(String kodeTps, int i, String jenisPemilihan, long j, int i2, int i3, long j2, int i4, int i5, boolean z) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        return new TpsTime(kodeTps, i, jenisPemilihan, j, i2, i3, j2, i4, i5, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TpsTime) {
            TpsTime tpsTime = (TpsTime) obj;
            return Intrinsics.areEqual(this.kodeTps, tpsTime.kodeTps) && this.jenisWaktu == tpsTime.jenisWaktu && Intrinsics.areEqual(this.jenisPemilihan, tpsTime.jenisPemilihan) && this.startDate == tpsTime.startDate && this.startTimeHour == tpsTime.startTimeHour && this.startTimeMinute == tpsTime.startTimeMinute && this.endDate == tpsTime.endDate && this.endTimeHour == tpsTime.endTimeHour && this.endTimeMinute == tpsTime.endTimeMinute && this.isSubmittedOffline == tpsTime.isSubmittedOffline;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((((((((((((((this.kodeTps.hashCode() * 31) + Integer.hashCode(this.jenisWaktu)) * 31) + this.jenisPemilihan.hashCode()) * 31) + Long.hashCode(this.startDate)) * 31) + Integer.hashCode(this.startTimeHour)) * 31) + Integer.hashCode(this.startTimeMinute)) * 31) + Long.hashCode(this.endDate)) * 31) + Integer.hashCode(this.endTimeHour)) * 31) + Integer.hashCode(this.endTimeMinute)) * 31;
        boolean z = this.isSubmittedOffline;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public String toString() {
        String str = this.kodeTps;
        int i = this.jenisWaktu;
        String str2 = this.jenisPemilihan;
        long j = this.startDate;
        int i2 = this.startTimeHour;
        int i3 = this.startTimeMinute;
        long j2 = this.endDate;
        int i4 = this.endTimeHour;
        int i5 = this.endTimeMinute;
        return "TpsTime(kodeTps=" + str + ", jenisWaktu=" + i + ", jenisPemilihan=" + str2 + ", startDate=" + j + ", startTimeHour=" + i2 + ", startTimeMinute=" + i3 + ", endDate=" + j2 + ", endTimeHour=" + i4 + ", endTimeMinute=" + i5 + ", isSubmittedOffline=" + this.isSubmittedOffline + ")";
    }

    public TpsTime(String kodeTps, int i, String jenisPemilihan, long j, int i2, int i3, long j2, int i4, int i5, boolean z) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        this.kodeTps = kodeTps;
        this.jenisWaktu = i;
        this.jenisPemilihan = jenisPemilihan;
        this.startDate = j;
        this.startTimeHour = i2;
        this.startTimeMinute = i3;
        this.endDate = j2;
        this.endTimeHour = i4;
        this.endTimeMinute = i5;
        this.isSubmittedOffline = z;
    }

    public /* synthetic */ TpsTime(String str, int i, String str2, long j, int i2, int i3, long j2, int i4, int i5, boolean z, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, str2, j, i2, i3, j2, i4, i5, (i6 & 512) != 0 ? false : z);
    }

    public final String getKodeTps() {
        return this.kodeTps;
    }

    public final int getJenisWaktu() {
        return this.jenisWaktu;
    }

    public final String getJenisPemilihan() {
        return this.jenisPemilihan;
    }

    public final long getStartDate() {
        return this.startDate;
    }

    public final int getStartTimeHour() {
        return this.startTimeHour;
    }

    public final int getStartTimeMinute() {
        return this.startTimeMinute;
    }

    public final long getEndDate() {
        return this.endDate;
    }

    public final int getEndTimeHour() {
        return this.endTimeHour;
    }

    public final int getEndTimeMinute() {
        return this.endTimeMinute;
    }

    public final boolean isSubmittedOffline() {
        return this.isSubmittedOffline;
    }

    public final String startDateTimeFormatted() {
        String format = new SimpleDateFormat("dd MMMM yyyy", new Locale(JobType.ID)).format(Long.valueOf(new Date(this.startDate).getTime()));
        String padStart = StringsKt.padStart(String.valueOf(this.startTimeHour), 2, '0');
        return format + StringUtils.SPACE + padStart + ":" + StringsKt.padStart(String.valueOf(this.startTimeMinute), 2, '0');
    }

    public final String endDateTimeFormatted() {
        String format = new SimpleDateFormat("dd MMMM yyyy", new Locale(JobType.ID)).format(Long.valueOf(new Date(this.endDate).getTime()));
        String padStart = StringsKt.padStart(String.valueOf(this.endTimeHour), 2, '0');
        return format + StringUtils.SPACE + padStart + ":" + StringsKt.padStart(String.valueOf(this.endTimeMinute), 2, '0');
    }

    /* compiled from: TpsTime.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/model/TpsTime$Companion;", "", "()V", "JENIS_WAKTU_PEMUNGUTAN_SUARA", "", "JENIS_WAKTU_PENGHITUNGAN_SUARA", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
