package org.informatika.sirekap.model;

import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Candidate.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b/\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0087\b\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u0085\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\u0006\u0010\f\u001a\u00020\b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0013J\t\u0010(\u001a\u00020\u0004HÆ\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0018J\u000b\u0010*\u001a\u0004\u0018\u00010\bHÆ\u0003J\u0010\u0010+\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u0010,\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u001fJ\t\u0010-\u001a\u00020\u0006HÆ\u0003J\t\u0010.\u001a\u00020\bHÆ\u0003J\t\u0010/\u001a\u00020\bHÆ\u0003J\t\u00100\u001a\u00020\u0004HÆ\u0003J\t\u00101\u001a\u00020\u0004HÆ\u0003J\t\u00102\u001a\u00020\bHÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\bHÆ\u0003J\u0010\u00104\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u009c\u0001\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0002\u00106J\u0013\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010:\u001a\u00020\bJ\t\u0010;\u001a\u00020\u0004HÖ\u0001J\u0006\u0010<\u001a\u00020\u0000J\u0006\u0010=\u001a\u00020\u0000J\t\u0010>\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u0004¢\u0006\n\n\u0002\u0010 \u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\f\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0015R\u0013\u0010\r\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0015R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001dR\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u0004¢\u0006\n\n\u0002\u0010 \u001a\u0004\b%\u0010\u001fR\u0011\u0010\n\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001dR\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u0004¢\u0006\n\n\u0002\u0010 \u001a\u0004\b'\u0010\u001f¨\u0006?"}, d2 = {"Lorg/informatika/sirekap/model/Candidate;", "", "()V", FirebaseAnalytics.Param.INDEX, "", "idPilihan", "", "electionId", "", "electionPemilihan", "noUrutPencalonan", "noUrutLabel", "namaCalonKepala", "namaCalonWakil", "indexPartai", "idPartai", "namaPartai", "noUrutPencalonanPartai", "noUrutLabelPartai", "(IJLjava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getElectionId", "()Ljava/lang/String;", "getElectionPemilihan", "getIdPartai", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getIdPilihan", "()J", "getIndex", "()I", "getIndexPartai", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getNamaCalonKepala", "getNamaCalonWakil", "getNamaPartai", "getNoUrutLabel", "getNoUrutLabelPartai", "getNoUrutPencalonan", "getNoUrutPencalonanPartai", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(IJLjava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Lorg/informatika/sirekap/model/Candidate;", "equals", "", "other", "getNama", "hashCode", "toDecrypted", "toEncrypted", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Candidate {
    private final String electionId;
    private final String electionPemilihan;
    private final Long idPartai;
    private final long idPilihan;
    private final int index;
    private final Integer indexPartai;
    private final String namaCalonKepala;
    private final String namaCalonWakil;
    private final String namaPartai;
    private final int noUrutLabel;
    private final Integer noUrutLabelPartai;
    private final int noUrutPencalonan;
    private final Integer noUrutPencalonanPartai;

    public static /* synthetic */ Candidate copy$default(Candidate candidate, int i, long j, String str, String str2, int i2, int i3, String str3, String str4, Integer num, Long l, String str5, Integer num2, Integer num3, int i4, Object obj) {
        return candidate.copy((i4 & 1) != 0 ? candidate.index : i, (i4 & 2) != 0 ? candidate.idPilihan : j, (i4 & 4) != 0 ? candidate.electionId : str, (i4 & 8) != 0 ? candidate.electionPemilihan : str2, (i4 & 16) != 0 ? candidate.noUrutPencalonan : i2, (i4 & 32) != 0 ? candidate.noUrutLabel : i3, (i4 & 64) != 0 ? candidate.namaCalonKepala : str3, (i4 & 128) != 0 ? candidate.namaCalonWakil : str4, (i4 & 256) != 0 ? candidate.indexPartai : num, (i4 & 512) != 0 ? candidate.idPartai : l, (i4 & 1024) != 0 ? candidate.namaPartai : str5, (i4 & 2048) != 0 ? candidate.noUrutPencalonanPartai : num2, (i4 & 4096) != 0 ? candidate.noUrutLabelPartai : num3);
    }

    public final int component1() {
        return this.index;
    }

    public final Long component10() {
        return this.idPartai;
    }

    public final String component11() {
        return this.namaPartai;
    }

    public final Integer component12() {
        return this.noUrutPencalonanPartai;
    }

    public final Integer component13() {
        return this.noUrutLabelPartai;
    }

    public final long component2() {
        return this.idPilihan;
    }

    public final String component3() {
        return this.electionId;
    }

    public final String component4() {
        return this.electionPemilihan;
    }

    public final int component5() {
        return this.noUrutPencalonan;
    }

    public final int component6() {
        return this.noUrutLabel;
    }

    public final String component7() {
        return this.namaCalonKepala;
    }

    public final String component8() {
        return this.namaCalonWakil;
    }

    public final Integer component9() {
        return this.indexPartai;
    }

    public final Candidate copy(int i, long j, String electionId, String electionPemilihan, int i2, int i3, String namaCalonKepala, String str, Integer num, Long l, String str2, Integer num2, Integer num3) {
        Intrinsics.checkNotNullParameter(electionId, "electionId");
        Intrinsics.checkNotNullParameter(electionPemilihan, "electionPemilihan");
        Intrinsics.checkNotNullParameter(namaCalonKepala, "namaCalonKepala");
        return new Candidate(i, j, electionId, electionPemilihan, i2, i3, namaCalonKepala, str, num, l, str2, num2, num3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Candidate) {
            Candidate candidate = (Candidate) obj;
            return this.index == candidate.index && this.idPilihan == candidate.idPilihan && Intrinsics.areEqual(this.electionId, candidate.electionId) && Intrinsics.areEqual(this.electionPemilihan, candidate.electionPemilihan) && this.noUrutPencalonan == candidate.noUrutPencalonan && this.noUrutLabel == candidate.noUrutLabel && Intrinsics.areEqual(this.namaCalonKepala, candidate.namaCalonKepala) && Intrinsics.areEqual(this.namaCalonWakil, candidate.namaCalonWakil) && Intrinsics.areEqual(this.indexPartai, candidate.indexPartai) && Intrinsics.areEqual(this.idPartai, candidate.idPartai) && Intrinsics.areEqual(this.namaPartai, candidate.namaPartai) && Intrinsics.areEqual(this.noUrutPencalonanPartai, candidate.noUrutPencalonanPartai) && Intrinsics.areEqual(this.noUrutLabelPartai, candidate.noUrutLabelPartai);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((((((((((((Integer.hashCode(this.index) * 31) + Long.hashCode(this.idPilihan)) * 31) + this.electionId.hashCode()) * 31) + this.electionPemilihan.hashCode()) * 31) + Integer.hashCode(this.noUrutPencalonan)) * 31) + Integer.hashCode(this.noUrutLabel)) * 31) + this.namaCalonKepala.hashCode()) * 31;
        String str = this.namaCalonWakil;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        Integer num = this.indexPartai;
        int hashCode3 = (hashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        Long l = this.idPartai;
        int hashCode4 = (hashCode3 + (l == null ? 0 : l.hashCode())) * 31;
        String str2 = this.namaPartai;
        int hashCode5 = (hashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num2 = this.noUrutPencalonanPartai;
        int hashCode6 = (hashCode5 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.noUrutLabelPartai;
        return hashCode6 + (num3 != null ? num3.hashCode() : 0);
    }

    public String toString() {
        int i = this.index;
        long j = this.idPilihan;
        String str = this.electionId;
        String str2 = this.electionPemilihan;
        int i2 = this.noUrutPencalonan;
        int i3 = this.noUrutLabel;
        String str3 = this.namaCalonKepala;
        String str4 = this.namaCalonWakil;
        Integer num = this.indexPartai;
        Long l = this.idPartai;
        String str5 = this.namaPartai;
        Integer num2 = this.noUrutPencalonanPartai;
        return "Candidate(index=" + i + ", idPilihan=" + j + ", electionId=" + str + ", electionPemilihan=" + str2 + ", noUrutPencalonan=" + i2 + ", noUrutLabel=" + i3 + ", namaCalonKepala=" + str3 + ", namaCalonWakil=" + str4 + ", indexPartai=" + num + ", idPartai=" + l + ", namaPartai=" + str5 + ", noUrutPencalonanPartai=" + num2 + ", noUrutLabelPartai=" + this.noUrutLabelPartai + ")";
    }

    public Candidate(int i, long j, String electionId, String electionPemilihan, int i2, int i3, String namaCalonKepala, String str, Integer num, Long l, String str2, Integer num2, Integer num3) {
        Intrinsics.checkNotNullParameter(electionId, "electionId");
        Intrinsics.checkNotNullParameter(electionPemilihan, "electionPemilihan");
        Intrinsics.checkNotNullParameter(namaCalonKepala, "namaCalonKepala");
        this.index = i;
        this.idPilihan = j;
        this.electionId = electionId;
        this.electionPemilihan = electionPemilihan;
        this.noUrutPencalonan = i2;
        this.noUrutLabel = i3;
        this.namaCalonKepala = namaCalonKepala;
        this.namaCalonWakil = str;
        this.indexPartai = num;
        this.idPartai = l;
        this.namaPartai = str2;
        this.noUrutPencalonanPartai = num2;
        this.noUrutLabelPartai = num3;
    }

    public /* synthetic */ Candidate(int i, long j, String str, String str2, int i2, int i3, String str3, String str4, Integer num, Long l, String str5, Integer num2, Integer num3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, j, str, str2, i2, i3, str3, (i4 & 128) != 0 ? null : str4, (i4 & 256) != 0 ? null : num, (i4 & 512) != 0 ? null : l, (i4 & 1024) != 0 ? null : str5, (i4 & 2048) != 0 ? null : num2, (i4 & 4096) != 0 ? null : num3);
    }

    public final int getIndex() {
        return this.index;
    }

    public final long getIdPilihan() {
        return this.idPilihan;
    }

    public final String getElectionId() {
        return this.electionId;
    }

    public final String getElectionPemilihan() {
        return this.electionPemilihan;
    }

    public final int getNoUrutPencalonan() {
        return this.noUrutPencalonan;
    }

    public final int getNoUrutLabel() {
        return this.noUrutLabel;
    }

    public final String getNamaCalonKepala() {
        return this.namaCalonKepala;
    }

    public final String getNamaCalonWakil() {
        return this.namaCalonWakil;
    }

    public final Integer getIndexPartai() {
        return this.indexPartai;
    }

    public final Long getIdPartai() {
        return this.idPartai;
    }

    public final String getNamaPartai() {
        return this.namaPartai;
    }

    public final Integer getNoUrutPencalonanPartai() {
        return this.noUrutPencalonanPartai;
    }

    public final Integer getNoUrutLabelPartai() {
        return this.noUrutLabelPartai;
    }

    public Candidate() {
        this(0, 0L, "", "", 0, 0, "", null, null, null, null, null, null);
    }

    public final String getNama() {
        String str = this.electionPemilihan;
        switch (str.hashCode()) {
            case -992700931:
                if (str.equals(Election.ELECTION_PEMILIHAN_DPRD_KABKO)) {
                    return this.namaCalonKepala;
                }
                break;
            case -992700926:
                if (str.equals(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI)) {
                    return this.namaCalonKepala;
                }
                break;
            case 3436264:
                if (str.equals(Election.ELECTION_PEMILIHAN_DPD)) {
                    return this.namaCalonKepala;
                }
                break;
            case 3436278:
                if (str.equals(Election.ELECTION_PEMILIHAN_DPR)) {
                    return this.namaCalonKepala;
                }
                break;
            case 3448025:
                if (str.equals(Election.ELECTION_PEMILIHAN_PRESIDEN)) {
                    String str2 = this.namaCalonKepala;
                    return str2 + " & " + this.namaCalonWakil;
                }
                break;
        }
        return "";
    }

    public final Candidate toEncrypted() {
        return copy$default(this, 0, 0L, null, null, 0, 0, null, null, null, null, null, null, null, 8191, null);
    }

    public final Candidate toDecrypted() {
        return copy$default(this, 0, 0L, null, null, 0, 0, null, null, null, null, null, null, null, 8191, null);
    }
}
