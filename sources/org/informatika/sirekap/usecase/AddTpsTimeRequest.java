package org.informatika.sirekap.usecase;

import java.util.Date;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddTpsTimeUseCase.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u001d\b\u0086\b\u0018\u00002\u00020\u0001Be\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\u0015\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\u0015\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\t\u0010#\u001a\u00020\bHÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u000fHÆ\u0003J{\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\u00052\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÆ\u0001J\u0013\u0010(\u001a\u00020\u000f2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020\bHÖ\u0001J\t\u0010+\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0017R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0011\u0010\u000b\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016¨\u0006,"}, d2 = {"Lorg/informatika/sirekap/usecase/AddTpsTimeRequest;", "", "kodeTps", "", "startDate", "Ljava/util/Date;", "startTime", "Lkotlin/Pair;", "", "endDate", "endTime", "jenisWaktu", "jenisPemilihan", "deviceId", "isOffline", "", "(Ljava/lang/String;Ljava/util/Date;Lkotlin/Pair;Ljava/util/Date;Lkotlin/Pair;ILjava/lang/String;Ljava/lang/String;Z)V", "getDeviceId", "()Ljava/lang/String;", "getEndDate", "()Ljava/util/Date;", "getEndTime", "()Lkotlin/Pair;", "()Z", "getJenisPemilihan", "getJenisWaktu", "()I", "getKodeTps", "getStartDate", "getStartTime", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class AddTpsTimeRequest {
    private final String deviceId;
    private final Date endDate;
    private final Pair<Integer, Integer> endTime;
    private final boolean isOffline;
    private final String jenisPemilihan;
    private final int jenisWaktu;
    private final String kodeTps;
    private final Date startDate;
    private final Pair<Integer, Integer> startTime;

    public final String component1() {
        return this.kodeTps;
    }

    public final Date component2() {
        return this.startDate;
    }

    public final Pair<Integer, Integer> component3() {
        return this.startTime;
    }

    public final Date component4() {
        return this.endDate;
    }

    public final Pair<Integer, Integer> component5() {
        return this.endTime;
    }

    public final int component6() {
        return this.jenisWaktu;
    }

    public final String component7() {
        return this.jenisPemilihan;
    }

    public final String component8() {
        return this.deviceId;
    }

    public final boolean component9() {
        return this.isOffline;
    }

    public final AddTpsTimeRequest copy(String kodeTps, Date startDate, Pair<Integer, Integer> startTime, Date endDate, Pair<Integer, Integer> endTime, int i, String jenisPemilihan, String deviceId, boolean z) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(startTime, "startTime");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(endTime, "endTime");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        return new AddTpsTimeRequest(kodeTps, startDate, startTime, endDate, endTime, i, jenisPemilihan, deviceId, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AddTpsTimeRequest) {
            AddTpsTimeRequest addTpsTimeRequest = (AddTpsTimeRequest) obj;
            return Intrinsics.areEqual(this.kodeTps, addTpsTimeRequest.kodeTps) && Intrinsics.areEqual(this.startDate, addTpsTimeRequest.startDate) && Intrinsics.areEqual(this.startTime, addTpsTimeRequest.startTime) && Intrinsics.areEqual(this.endDate, addTpsTimeRequest.endDate) && Intrinsics.areEqual(this.endTime, addTpsTimeRequest.endTime) && this.jenisWaktu == addTpsTimeRequest.jenisWaktu && Intrinsics.areEqual(this.jenisPemilihan, addTpsTimeRequest.jenisPemilihan) && Intrinsics.areEqual(this.deviceId, addTpsTimeRequest.deviceId) && this.isOffline == addTpsTimeRequest.isOffline;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((((((((((((this.kodeTps.hashCode() * 31) + this.startDate.hashCode()) * 31) + this.startTime.hashCode()) * 31) + this.endDate.hashCode()) * 31) + this.endTime.hashCode()) * 31) + Integer.hashCode(this.jenisWaktu)) * 31) + this.jenisPemilihan.hashCode()) * 31) + this.deviceId.hashCode()) * 31;
        boolean z = this.isOffline;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public String toString() {
        String str = this.kodeTps;
        Date date = this.startDate;
        Pair<Integer, Integer> pair = this.startTime;
        Date date2 = this.endDate;
        Pair<Integer, Integer> pair2 = this.endTime;
        int i = this.jenisWaktu;
        String str2 = this.jenisPemilihan;
        String str3 = this.deviceId;
        return "AddTpsTimeRequest(kodeTps=" + str + ", startDate=" + date + ", startTime=" + pair + ", endDate=" + date2 + ", endTime=" + pair2 + ", jenisWaktu=" + i + ", jenisPemilihan=" + str2 + ", deviceId=" + str3 + ", isOffline=" + this.isOffline + ")";
    }

    public AddTpsTimeRequest(String kodeTps, Date startDate, Pair<Integer, Integer> startTime, Date endDate, Pair<Integer, Integer> endTime, int i, String jenisPemilihan, String deviceId, boolean z) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(startTime, "startTime");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(endTime, "endTime");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        this.kodeTps = kodeTps;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.jenisWaktu = i;
        this.jenisPemilihan = jenisPemilihan;
        this.deviceId = deviceId;
        this.isOffline = z;
    }

    public final String getKodeTps() {
        return this.kodeTps;
    }

    public final Date getStartDate() {
        return this.startDate;
    }

    public final Pair<Integer, Integer> getStartTime() {
        return this.startTime;
    }

    public final Date getEndDate() {
        return this.endDate;
    }

    public final Pair<Integer, Integer> getEndTime() {
        return this.endTime;
    }

    public final int getJenisWaktu() {
        return this.jenisWaktu;
    }

    public final String getJenisPemilihan() {
        return this.jenisPemilihan;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final boolean isOffline() {
        return this.isOffline;
    }
}
