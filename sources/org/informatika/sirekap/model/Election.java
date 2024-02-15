package org.informatika.sirekap.model;

import android.content.Context;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xmpbox.type.JobType;
import org.informatika.sirekap.R;

/* compiled from: Election.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b8\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0087\b\u0018\u0000 V2\u00020\u0001:\u0001VB\u0097\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0012\u001a\u00020\r\u0012\b\b\u0002\u0010\u0013\u001a\u00020\r\u0012\b\b\u0002\u0010\u0014\u001a\u00020\r¢\u0006\u0002\u0010\u0015J\t\u00102\u001a\u00020\u0003HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00106\u001a\u00020\rHÆ\u0003J\t\u00107\u001a\u00020\rHÆ\u0003J\t\u00108\u001a\u00020\rHÆ\u0003J\t\u00109\u001a\u00020\u0005HÆ\u0003J\t\u0010:\u001a\u00020\u0003HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010<\u001a\u00020\u0005HÆ\u0003J\t\u0010=\u001a\u00020\nHÆ\u0003J\t\u0010>\u001a\u00020\u0003HÆ\u0003J\t\u0010?\u001a\u00020\rHÆ\u0003J\t\u0010@\u001a\u00020\u0005HÆ\u0003J§\u0001\u0010A\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0012\u001a\u00020\r2\b\b\u0002\u0010\u0013\u001a\u00020\r2\b\b\u0002\u0010\u0014\u001a\u00020\rHÆ\u0001J\u0013\u0010B\u001a\u00020\r2\b\u0010C\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u000e\u0010D\u001a\u00020\u00032\u0006\u0010E\u001a\u00020FJ\u000e\u0010G\u001a\u00020\u00032\u0006\u0010E\u001a\u00020FJ\u000e\u0010H\u001a\u00020\u00032\u0006\u0010E\u001a\u00020FJ\u000e\u0010I\u001a\u00020\u00032\u0006\u0010E\u001a\u00020FJ\u0006\u0010J\u001a\u00020\u0003J\u0006\u0010K\u001a\u00020\u0003J\b\u0010L\u001a\u0004\u0018\u00010\u0003J\u000e\u0010M\u001a\u00020\u00032\u0006\u0010E\u001a\u00020FJ\t\u0010N\u001a\u00020\u0005HÖ\u0001J\u0006\u0010O\u001a\u00020\rJ\u0006\u0010P\u001a\u00020\rJ\u0006\u0010Q\u001a\u00020\rJ\u0006\u0010R\u001a\u00020\rJ\u000e\u0010S\u001a\u00020\u00032\u0006\u0010E\u001a\u00020FJ\u0006\u0010T\u001a\u00020\rJ\t\u0010U\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0019\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017R\u0011\u0010\u001d\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u001bR\u0011\u0010\u001e\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001bR\u0011\u0010\u001f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001bR\u0011\u0010 \u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b \u0010\u001bR\u0011\u0010!\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b!\u0010\u001bR\u0011\u0010\"\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\"\u0010\u001bR\u0011\u0010#\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b#\u0010\u001bR\u0011\u0010$\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b$\u0010\u001bR\u0011\u0010%\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b%\u0010\u001bR\u0011\u0010\u0012\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u001bR\u0011\u0010\u0013\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u001bR\u0011\u0010\u0014\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u001bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b(\u0010'R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0017R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0017R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0017R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0017\"\u0004\b-\u0010.R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b1\u0010'¨\u0006W"}, d2 = {"Lorg/informatika/sirekap/model/Election;", "", JobType.ID, "", "jenis", "", "pemilihan", "dapil", "jmlLembar", "tps", "Lorg/informatika/sirekap/model/Tps;", "electionType", "isCreatePdf", "", "uploadPdfStatus", "pdfFilePath", "pdfFileHash", "pdfWitnessFilePath", "isUploadedPdf", "isUploadedPdfOffline", "isZipped", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;ILorg/informatika/sirekap/model/Tps;Ljava/lang/String;ZILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZ)V", "getDapil", "()Ljava/lang/String;", "getElectionType", "hideUploadPdfOfflineButton", "getHideUploadPdfOfflineButton", "()Z", "getId", "isAceh", "isLn", "isLnPos", "isPapua", "isPapuaBarat", "isPapuaBaratDaya", "isPapuaPegunungan", "isPapuaSelatan", "isPapuaTengah", "getJenis", "()I", "getJmlLembar", "getPdfFileHash", "getPdfFilePath", "getPdfWitnessFilePath", "getPemilihan", "setPemilihan", "(Ljava/lang/String;)V", "getTps", "()Lorg/informatika/sirekap/model/Tps;", "getUploadPdfStatus", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "getCreatePdfInstruction", "context", "Landroid/content/Context;", "getDescription", "getElectionTypeDescription", "getElectionTypeDescriptionShort", "getNumberPaslonString", "getPaslonString", "getRegion", "getType", "hashCode", "isLoadingUploadPdf", "isPsu", "isShowUploadPdfButton", "isSuccessUploadPdf", "salinanPdfUploadSuccessDescription", "showPartaiLabel", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Election {
    public static final int ELECTION_JENIS_BUPATI = 2;
    public static final int ELECTION_JENIS_GUBERNUR = 3;
    public static final int ELECTION_JENIS_WALIKOTA = 1;
    public static final String ELECTION_PEMILIHAN_DPD = "pdpd";
    public static final String ELECTION_PEMILIHAN_DPR = "pdpr";
    public static final String ELECTION_PEMILIHAN_DPRD_KABKO = "pdprdk";
    public static final String ELECTION_PEMILIHAN_DPRD_PROVINSI = "pdprdp";
    public static final String ELECTION_PEMILIHAN_KOTAKAB = "pkwkk";
    public static final String ELECTION_PEMILIHAN_PRESIDEN = "ppwp";
    public static final String ELECTION_PEMILIHAN_PROVINSI = "pkwkp";
    public static final String ELECTION_TYPE_NORMAL = "R";
    public static final String ELECTION_TYPE_PSU_LANJUTAN = "L";
    public static final String ELECTION_TYPE_PSU_SUSULAN = "S";
    public static final String ELECTION_TYPE_PSU_ULANGAN = "U";
    private final String dapil;
    private final String electionType;

    /* renamed from: id */
    private final String f62id;
    private final boolean isCreatePdf;
    private final boolean isUploadedPdf;
    private final boolean isUploadedPdfOffline;
    private final boolean isZipped;
    private final int jenis;
    private final int jmlLembar;
    private final String pdfFileHash;
    private final String pdfFilePath;
    private final String pdfWitnessFilePath;
    private String pemilihan;
    private final Tps tps;
    private final int uploadPdfStatus;
    public static final Companion Companion = new Companion(null);
    private static final List<String> ALL_ELECTION_TYPES = CollectionsKt.listOf((Object[]) new String[]{"R", "U", "S", "L"});

    public final String component1() {
        return this.f62id;
    }

    public final String component10() {
        return this.pdfFilePath;
    }

    public final String component11() {
        return this.pdfFileHash;
    }

    public final String component12() {
        return this.pdfWitnessFilePath;
    }

    public final boolean component13() {
        return this.isUploadedPdf;
    }

    public final boolean component14() {
        return this.isUploadedPdfOffline;
    }

    public final boolean component15() {
        return this.isZipped;
    }

    public final int component2() {
        return this.jenis;
    }

    public final String component3() {
        return this.pemilihan;
    }

    public final String component4() {
        return this.dapil;
    }

    public final int component5() {
        return this.jmlLembar;
    }

    public final Tps component6() {
        return this.tps;
    }

    public final String component7() {
        return this.electionType;
    }

    public final boolean component8() {
        return this.isCreatePdf;
    }

    public final int component9() {
        return this.uploadPdfStatus;
    }

    public final Election copy(String id2, int i, String pemilihan, String str, int i2, Tps tps, String electionType, boolean z, int i3, String str2, String str3, String str4, boolean z2, boolean z3, boolean z4) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(pemilihan, "pemilihan");
        Intrinsics.checkNotNullParameter(tps, "tps");
        Intrinsics.checkNotNullParameter(electionType, "electionType");
        return new Election(id2, i, pemilihan, str, i2, tps, electionType, z, i3, str2, str3, str4, z2, z3, z4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Election) {
            Election election = (Election) obj;
            return Intrinsics.areEqual(this.f62id, election.f62id) && this.jenis == election.jenis && Intrinsics.areEqual(this.pemilihan, election.pemilihan) && Intrinsics.areEqual(this.dapil, election.dapil) && this.jmlLembar == election.jmlLembar && Intrinsics.areEqual(this.tps, election.tps) && Intrinsics.areEqual(this.electionType, election.electionType) && this.isCreatePdf == election.isCreatePdf && this.uploadPdfStatus == election.uploadPdfStatus && Intrinsics.areEqual(this.pdfFilePath, election.pdfFilePath) && Intrinsics.areEqual(this.pdfFileHash, election.pdfFileHash) && Intrinsics.areEqual(this.pdfWitnessFilePath, election.pdfWitnessFilePath) && this.isUploadedPdf == election.isUploadedPdf && this.isUploadedPdfOffline == election.isUploadedPdfOffline && this.isZipped == election.isZipped;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((this.f62id.hashCode() * 31) + Integer.hashCode(this.jenis)) * 31) + this.pemilihan.hashCode()) * 31;
        String str = this.dapil;
        int hashCode2 = (((((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + Integer.hashCode(this.jmlLembar)) * 31) + this.tps.hashCode()) * 31) + this.electionType.hashCode()) * 31;
        boolean z = this.isCreatePdf;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int hashCode3 = (((hashCode2 + i) * 31) + Integer.hashCode(this.uploadPdfStatus)) * 31;
        String str2 = this.pdfFilePath;
        int hashCode4 = (hashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.pdfFileHash;
        int hashCode5 = (hashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.pdfWitnessFilePath;
        int hashCode6 = (hashCode5 + (str4 != null ? str4.hashCode() : 0)) * 31;
        boolean z2 = this.isUploadedPdf;
        int i2 = z2;
        if (z2 != 0) {
            i2 = 1;
        }
        int i3 = (hashCode6 + i2) * 31;
        boolean z3 = this.isUploadedPdfOffline;
        int i4 = z3;
        if (z3 != 0) {
            i4 = 1;
        }
        int i5 = (i3 + i4) * 31;
        boolean z4 = this.isZipped;
        return i5 + (z4 ? 1 : z4 ? 1 : 0);
    }

    public String toString() {
        String str = this.f62id;
        int i = this.jenis;
        String str2 = this.pemilihan;
        String str3 = this.dapil;
        int i2 = this.jmlLembar;
        Tps tps = this.tps;
        String str4 = this.electionType;
        boolean z = this.isCreatePdf;
        int i3 = this.uploadPdfStatus;
        String str5 = this.pdfFilePath;
        String str6 = this.pdfFileHash;
        String str7 = this.pdfWitnessFilePath;
        boolean z2 = this.isUploadedPdf;
        boolean z3 = this.isUploadedPdfOffline;
        return "Election(id=" + str + ", jenis=" + i + ", pemilihan=" + str2 + ", dapil=" + str3 + ", jmlLembar=" + i2 + ", tps=" + tps + ", electionType=" + str4 + ", isCreatePdf=" + z + ", uploadPdfStatus=" + i3 + ", pdfFilePath=" + str5 + ", pdfFileHash=" + str6 + ", pdfWitnessFilePath=" + str7 + ", isUploadedPdf=" + z2 + ", isUploadedPdfOffline=" + z3 + ", isZipped=" + this.isZipped + ")";
    }

    public Election(String id2, int i, String pemilihan, String str, int i2, Tps tps, String electionType, boolean z, int i3, String str2, String str3, String str4, boolean z2, boolean z3, boolean z4) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(pemilihan, "pemilihan");
        Intrinsics.checkNotNullParameter(tps, "tps");
        Intrinsics.checkNotNullParameter(electionType, "electionType");
        this.f62id = id2;
        this.jenis = i;
        this.pemilihan = pemilihan;
        this.dapil = str;
        this.jmlLembar = i2;
        this.tps = tps;
        this.electionType = electionType;
        this.isCreatePdf = z;
        this.uploadPdfStatus = i3;
        this.pdfFilePath = str2;
        this.pdfFileHash = str3;
        this.pdfWitnessFilePath = str4;
        this.isUploadedPdf = z2;
        this.isUploadedPdfOffline = z3;
        this.isZipped = z4;
    }

    public /* synthetic */ Election(String str, int i, String str2, String str3, int i2, Tps tps, String str4, boolean z, int i3, String str5, String str6, String str7, boolean z2, boolean z3, boolean z4, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, str2, (i4 & 8) != 0 ? null : str3, i2, tps, str4, (i4 & 128) != 0 ? false : z, (i4 & 256) != 0 ? -1 : i3, (i4 & 512) != 0 ? null : str5, (i4 & 1024) != 0 ? null : str6, (i4 & 2048) != 0 ? null : str7, (i4 & 4096) != 0 ? false : z2, (i4 & 8192) != 0 ? false : z3, (i4 & 16384) != 0 ? false : z4);
    }

    public final String getId() {
        return this.f62id;
    }

    public final int getJenis() {
        return this.jenis;
    }

    public final String getPemilihan() {
        return this.pemilihan;
    }

    public final void setPemilihan(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.pemilihan = str;
    }

    public final String getDapil() {
        return this.dapil;
    }

    public final int getJmlLembar() {
        return this.jmlLembar;
    }

    public final Tps getTps() {
        return this.tps;
    }

    public final String getElectionType() {
        return this.electionType;
    }

    public final boolean isCreatePdf() {
        return this.isCreatePdf;
    }

    public final int getUploadPdfStatus() {
        return this.uploadPdfStatus;
    }

    public final String getPdfFilePath() {
        return this.pdfFilePath;
    }

    public final String getPdfFileHash() {
        return this.pdfFileHash;
    }

    public final String getPdfWitnessFilePath() {
        return this.pdfWitnessFilePath;
    }

    public final boolean isUploadedPdf() {
        return this.isUploadedPdf;
    }

    public final boolean isUploadedPdfOffline() {
        return this.isUploadedPdfOffline;
    }

    public final boolean isZipped() {
        return this.isZipped;
    }

    public final boolean getHideUploadPdfOfflineButton() {
        return !(this.isZipped && this.isUploadedPdf && this.isUploadedPdfOffline && !isLoadingUploadPdf());
    }

    public final boolean isLn() {
        return this.tps.isLn();
    }

    public final boolean isLnPos() {
        return this.tps.isLnPos();
    }

    public final boolean isAceh() {
        return this.tps.isAceh();
    }

    public final boolean isPapua() {
        return this.tps.isPapua();
    }

    public final boolean isPapuaBarat() {
        return this.tps.isPapuaBarat();
    }

    public final boolean isPapuaSelatan() {
        return this.tps.isPapuaSelatan();
    }

    public final boolean isPapuaTengah() {
        return this.tps.isPapuaTengah();
    }

    public final boolean isPapuaPegunungan() {
        return this.tps.isPapuaPegunungan();
    }

    public final boolean isPapuaBaratDaya() {
        return this.tps.isPapuaBaratDaya();
    }

    public final boolean isShowUploadPdfButton() {
        return this.isZipped && !this.isUploadedPdf;
    }

    public final boolean isLoadingUploadPdf() {
        return this.uploadPdfStatus == 2;
    }

    public final boolean isSuccessUploadPdf() {
        return this.isUploadedPdf;
    }

    public final String salinanPdfUploadSuccessDescription(Context context) {
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

    /* JADX WARN: Removed duplicated region for block: B:43:0x0025 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0043 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0046 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String getPaslonString() {
        /*
            r2 = this;
            java.lang.String r0 = r2.pemilihan
            int r1 = r0.hashCode()
            switch(r1) {
                case -992700931: goto L3a;
                case -992700926: goto L31;
                case 3436278: goto L28;
                case 3448025: goto L1c;
                case 106739772: goto L13;
                case 106739777: goto La;
                default: goto L9;
            }
        L9:
            goto L46
        La:
            java.lang.String r1 = "pkwkp"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L46
            goto L25
        L13:
            java.lang.String r1 = "pkwkk"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L25
            goto L46
        L1c:
            java.lang.String r1 = "ppwp"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L25
            goto L46
        L25:
            java.lang.String r0 = "Pasangan Calon"
            goto L48
        L28:
            java.lang.String r1 = "pdpr"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L43
            goto L46
        L31:
            java.lang.String r1 = "pdprdp"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L43
            goto L46
        L3a:
            java.lang.String r1 = "pdprdk"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L43
            goto L46
        L43:
            java.lang.String r0 = "Caleg"
            goto L48
        L46:
            java.lang.String r0 = "Calon"
        L48:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.model.Election.getPaslonString():java.lang.String");
    }

    public final String getNumberPaslonString() {
        return "Jumlah " + getPaslonString();
    }

    public final boolean showPartaiLabel() {
        return Intrinsics.areEqual(this.pemilihan, ELECTION_PEMILIHAN_DPR) || Intrinsics.areEqual(this.pemilihan, ELECTION_PEMILIHAN_DPRD_PROVINSI) || Intrinsics.areEqual(this.pemilihan, ELECTION_PEMILIHAN_DPRD_KABKO);
    }

    /* compiled from: Election.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0005R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/informatika/sirekap/model/Election$Companion;", "", "()V", "ALL_ELECTION_TYPES", "", "", "getALL_ELECTION_TYPES", "()Ljava/util/List;", "ELECTION_JENIS_BUPATI", "", "ELECTION_JENIS_GUBERNUR", "ELECTION_JENIS_WALIKOTA", "ELECTION_PEMILIHAN_DPD", "ELECTION_PEMILIHAN_DPR", "ELECTION_PEMILIHAN_DPRD_KABKO", "ELECTION_PEMILIHAN_DPRD_PROVINSI", "ELECTION_PEMILIHAN_KOTAKAB", "ELECTION_PEMILIHAN_PRESIDEN", "ELECTION_PEMILIHAN_PROVINSI", "ELECTION_TYPE_NORMAL", "ELECTION_TYPE_PSU_LANJUTAN", "ELECTION_TYPE_PSU_SUSULAN", "ELECTION_TYPE_PSU_ULANGAN", "getElectionTypeDescriptionShort", "context", "Landroid/content/Context;", "electionType", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<String> getALL_ELECTION_TYPES() {
            return Election.ALL_ELECTION_TYPES;
        }

        public final String getElectionTypeDescriptionShort(Context context, String electionType) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(electionType, "electionType");
            int hashCode = electionType.hashCode();
            if (hashCode != 76) {
                if (hashCode != 83) {
                    if (hashCode == 85 && electionType.equals("U")) {
                        String string = context.getString(R.string.election_type_psu_ulangan_description_short);
                        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…langan_description_short)");
                        return string;
                    }
                } else if (electionType.equals("S")) {
                    String string2 = context.getString(R.string.election_type_psu_susulan_description_short);
                    Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.stri…usulan_description_short)");
                    return string2;
                }
            } else if (electionType.equals("L")) {
                String string3 = context.getString(R.string.election_type_psu_lanjutan_description_short);
                Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.stri…njutan_description_short)");
                return string3;
            }
            return "";
        }
    }

    public final String getType(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        int i = this.jenis;
        if (i == 1) {
            String string = context.getString(R.string.election_type_walikota);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.election_type_walikota)");
            return string;
        } else if (i == 2) {
            String string2 = context.getString(R.string.election_type_walikota);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.election_type_walikota)");
            return string2;
        } else {
            String string3 = context.getString(R.string.election_type_gubernur);
            Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.election_type_gubernur)");
            return string3;
        }
    }

    public final String getRegion() {
        return Intrinsics.areEqual(this.pemilihan, ELECTION_PEMILIHAN_PRESIDEN) ? "-" : this.dapil;
    }

    public final String getDescription(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = this.pemilihan;
        switch (str.hashCode()) {
            case -992700931:
                if (str.equals(ELECTION_PEMILIHAN_DPRD_KABKO)) {
                    String string = isAceh() ? context.getString(R.string.election_type_pdprdk_aceh) : (isPapua() || isPapuaBarat() || isPapuaSelatan() || isPapuaTengah() || isPapuaPegunungan() || isPapuaBaratDaya()) ? context.getString(R.string.election_type_pdprdk_papua) : context.getString(R.string.election_type_pdprdk, this.dapil);
                    Intrinsics.checkNotNullExpressionValue(string, "{\n                if (is…          }\n            }");
                    return string;
                }
                break;
            case -992700926:
                if (str.equals(ELECTION_PEMILIHAN_DPRD_PROVINSI)) {
                    String string2 = isPapua() ? context.getString(R.string.election_type_pdprdp_papua) : isPapuaBarat() ? context.getString(R.string.election_type_pdprdp_papua_barat) : isPapuaSelatan() ? context.getString(R.string.election_type_pdprdp_papua_selatan) : isPapuaTengah() ? context.getString(R.string.election_type_pdprdp_papua_tengah) : isPapuaPegunungan() ? context.getString(R.string.election_type_pdprdp_papua_pegunungan) : isPapuaBaratDaya() ? context.getString(R.string.election_type_pdprdp_papua_barat_daya) : isAceh() ? context.getString(R.string.election_type_pdprdp_aceh) : context.getString(R.string.election_type_pdprdp, this.dapil);
                    Intrinsics.checkNotNullExpressionValue(string2, "{\n                if (is…          }\n            }");
                    return string2;
                }
                break;
            case 3436264:
                if (str.equals(ELECTION_PEMILIHAN_DPD)) {
                    String string3 = context.getString(R.string.election_type_pdpd, this.dapil);
                    Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.stri…lection_type_pdpd, dapil)");
                    return string3;
                }
                break;
            case 3436278:
                if (str.equals(ELECTION_PEMILIHAN_DPR)) {
                    String string4 = context.getString(R.string.election_type_pdpr);
                    Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.string.election_type_pdpr)");
                    return string4;
                }
                break;
            case 3448025:
                if (str.equals(ELECTION_PEMILIHAN_PRESIDEN)) {
                    String string5 = context.getString(R.string.election_type_pilpres);
                    Intrinsics.checkNotNullExpressionValue(string5, "context.getString(R.string.election_type_pilpres)");
                    return string5;
                }
                break;
        }
        return "-";
    }

    public final boolean isPsu() {
        return !Intrinsics.areEqual(this.electionType, "R");
    }

    public final String getElectionTypeDescription(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = this.electionType;
        int hashCode = str.hashCode();
        if (hashCode != 76) {
            if (hashCode != 83) {
                if (hashCode == 85 && str.equals("U")) {
                    String string = context.getString(R.string.election_type_psu_ulangan_description);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…_psu_ulangan_description)");
                    return string;
                }
            } else if (str.equals("S")) {
                String string2 = context.getString(R.string.election_type_psu_susulan_description);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.stri…_psu_susulan_description)");
                return string2;
            }
        } else if (str.equals("L")) {
            String string3 = context.getString(R.string.election_type_psu_lanjutan_description);
            Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.stri…psu_lanjutan_description)");
            return string3;
        }
        String string4 = context.getString(R.string.election_type_normal_description);
        Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.stri…_type_normal_description)");
        return string4;
    }

    public final String getElectionTypeDescriptionShort(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = this.electionType;
        int hashCode = str.hashCode();
        if (hashCode != 76) {
            if (hashCode != 83) {
                if (hashCode == 85 && str.equals("U")) {
                    String string = context.getString(R.string.election_type_psu_ulangan_description_short);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…langan_description_short)");
                    return string;
                }
            } else if (str.equals("S")) {
                String string2 = context.getString(R.string.election_type_psu_susulan_description_short);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.stri…usulan_description_short)");
                return string2;
            }
        } else if (str.equals("L")) {
            String string3 = context.getString(R.string.election_type_psu_lanjutan_description_short);
            Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.stri…njutan_description_short)");
            return string3;
        }
        return "";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final String getCreatePdfInstruction(Context context) {
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        int i = R.string.create_pdf_instruction_general;
        Object[] objArr = new Object[1];
        String str2 = this.pemilihan;
        switch (str2.hashCode()) {
            case -992700931:
                if (str2.equals(ELECTION_PEMILIHAN_DPRD_KABKO)) {
                    str = context.getString(R.string.election_type_pdprdk_general);
                    break;
                }
                str = "";
                break;
            case -992700926:
                if (str2.equals(ELECTION_PEMILIHAN_DPRD_PROVINSI)) {
                    str = context.getString(R.string.election_type_pdprdp_general);
                    break;
                }
                str = "";
                break;
            case 3436264:
                if (str2.equals(ELECTION_PEMILIHAN_DPD)) {
                    str = context.getString(R.string.election_type_pdpd_general);
                    break;
                }
                str = "";
                break;
            case 3436278:
                if (str2.equals(ELECTION_PEMILIHAN_DPR)) {
                    str = context.getString(R.string.election_type_pdpr_general);
                    break;
                }
                str = "";
                break;
            case 3448025:
                if (str2.equals(ELECTION_PEMILIHAN_PRESIDEN)) {
                    str = context.getString(R.string.election_type_pilpres);
                    break;
                }
                str = "";
                break;
            default:
                str = "";
                break;
        }
        objArr[0] = str;
        String string = context.getString(i, objArr);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(\n     …\"\n            }\n        )");
        return string;
    }
}
