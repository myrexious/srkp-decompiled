package org.informatika.sirekap.model;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.R;
import org.informatika.sirekap.support.ElectionUtil;

/* compiled from: FormC1Administration.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\bx\n\u0002\u0010 \n\u0002\b\u0003\b\u0087\b\u0018\u0000 \u0084\u00012\u00020\u0001:\u0002\u0084\u0001B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007B\u0085\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\t\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u0019\u001a\u00020\t\u0012\u0006\u0010\u001a\u001a\u00020\t\u0012\u0006\u0010\u001b\u001a\u00020\t\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u001f\u001a\u00020\t\u0012\u0006\u0010 \u001a\u00020\t\u0012\u0006\u0010!\u001a\u00020\t\u0012\u0006\u0010\"\u001a\u00020\t¢\u0006\u0002\u0010#J\t\u0010D\u001a\u00020\u0003HÆ\u0003J\t\u0010E\u001a\u00020\tHÆ\u0003J\u0010\u0010F\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010.J\u0010\u0010G\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010.J\u0010\u0010H\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010.J\u0010\u0010I\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010.J\u0010\u0010J\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010.J\u0010\u0010K\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010.J\u0010\u0010L\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010.J\u0010\u0010M\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010.J\u0010\u0010N\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010.J\t\u0010O\u001a\u00020\u0005HÆ\u0003J\t\u0010P\u001a\u00020\tHÆ\u0003J\t\u0010Q\u001a\u00020\tHÆ\u0003J\t\u0010R\u001a\u00020\tHÆ\u0003J\u0010\u0010S\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010.J\u0010\u0010T\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010.J\u0010\u0010U\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010.J\t\u0010V\u001a\u00020\tHÆ\u0003J\t\u0010W\u001a\u00020\tHÆ\u0003J\t\u0010X\u001a\u00020\tHÆ\u0003J\t\u0010Y\u001a\u00020\tHÆ\u0003J\t\u0010Z\u001a\u00020\u0005HÆ\u0003J\t\u0010[\u001a\u00020\tHÆ\u0003J\t\u0010\\\u001a\u00020\tHÆ\u0003J\t\u0010]\u001a\u00020\tHÆ\u0003J\t\u0010^\u001a\u00020\tHÆ\u0003J\t\u0010_\u001a\u00020\tHÆ\u0003J\t\u0010`\u001a\u00020\tHÆ\u0003JÈ\u0002\u0010a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\t2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0019\u001a\u00020\t2\b\b\u0002\u0010\u001a\u001a\u00020\t2\b\b\u0002\u0010\u001b\u001a\u00020\t2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u001f\u001a\u00020\t2\b\b\u0002\u0010 \u001a\u00020\t2\b\b\u0002\u0010!\u001a\u00020\t2\b\b\u0002\u0010\"\u001a\u00020\tHÆ\u0001¢\u0006\u0002\u0010bJ\u0013\u0010c\u001a\u00020\u00052\b\u0010d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010e\u001a\u00020\tHÖ\u0001J\u0016\u0010f\u001a\u00020\u00052\u0006\u0010g\u001a\u00020\t2\u0006\u0010h\u001a\u00020\tJ\u0016\u0010i\u001a\u00020\u00052\u0006\u0010j\u001a\u00020\t2\u0006\u0010k\u001a\u00020\tJ6\u0010l\u001a\u00020\u00052\u0006\u0010g\u001a\u00020\t2\u0006\u0010h\u001a\u00020\t2\u0006\u0010j\u001a\u00020\t2\u0006\u0010k\u001a\u00020\t2\u0006\u0010m\u001a\u00020\t2\u0006\u0010n\u001a\u00020\tJ&\u0010o\u001a\u00020\u00052\u0006\u0010p\u001a\u00020\t2\u0006\u0010m\u001a\u00020\t2\u0006\u0010q\u001a\u00020\t2\u0006\u0010r\u001a\u00020\tJ\u0016\u0010s\u001a\u00020\u00052\u0006\u0010t\u001a\u00020\t2\u0006\u0010p\u001a\u00020\tJ\u0016\u0010u\u001a\u00020\u00052\u0006\u0010v\u001a\u00020\t2\u0006\u0010w\u001a\u00020\tJ&\u0010x\u001a\u00020\u00052\u0006\u0010w\u001a\u00020\t2\u0006\u0010v\u001a\u00020\t2\u0006\u0010t\u001a\u00020\t2\u0006\u0010y\u001a\u00020\tJ&\u0010z\u001a\u00020\u00052\u0006\u0010y\u001a\u00020\t2\u0006\u0010{\u001a\u00020\t2\u0006\u0010|\u001a\u00020\t2\u0006\u0010}\u001a\u00020\tJ\u0016\u0010~\u001a\u00020\u00052\u0006\u0010y\u001a\u00020\t2\u0006\u0010w\u001a\u00020\tJ\u0016\u0010\u007f\u001a\u00020\u00052\u0006\u0010m\u001a\u00020\t2\u0006\u0010n\u001a\u00020\tJ'\u0010\u0080\u0001\u001a\u00020\u00052\u0006\u0010p\u001a\u00020\t2\u0006\u0010m\u001a\u00020\t2\u0006\u0010q\u001a\u00020\t2\u0006\u0010r\u001a\u00020\tJ\u0012\u0010\u0081\u0001\u001a\r\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\u0082\u0001J\n\u0010\u0083\u0001\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010(R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010(R\u0011\u0010 \u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b)\u0010%R\u0011\u0010!\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b*\u0010%R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b+\u0010%R\u0011\u0010\u000b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b,\u0010%R\u0015\u0010\u0013\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010/\u001a\u0004\b-\u0010.R\u0015\u0010\u0014\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010/\u001a\u0004\b0\u0010.R\u0011\u0010\r\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b1\u0010%R\u0011\u0010\u000e\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b2\u0010%R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010/\u001a\u0004\b3\u0010.R\u0015\u0010\u0011\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010/\u001a\u0004\b4\u0010.R\u0011\u0010\u001f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b5\u0010%R\u0011\u0010\u001a\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b6\u0010%R\u0011\u0010\u0019\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b7\u0010%R\u0015\u0010\u001d\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010/\u001a\u0004\b8\u0010.R\u0011\u0010\u001b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b9\u0010%R\u0015\u0010\u001c\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010/\u001a\u0004\b:\u0010.R\u0015\u0010\u001e\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010/\u001a\u0004\b;\u0010.R\u0011\u0010\"\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b<\u0010%R\u0011\u0010\f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b=\u0010%R\u0015\u0010\u0018\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010/\u001a\u0004\b>\u0010.R\u0015\u0010\u0015\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010/\u001a\u0004\b?\u0010.R\u0011\u0010\u000f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b@\u0010%R\u0015\u0010\u0012\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010/\u001a\u0004\bA\u0010.R\u0015\u0010\u0016\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010/\u001a\u0004\bB\u0010.R\u0015\u0010\u0017\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010/\u001a\u0004\bC\u0010.¨\u0006\u0085\u0001"}, d2 = {"Lorg/informatika/sirekap/model/FormC1Administration;", "", "idImage", "", "isLn", "", "isLnPos", "(Ljava/lang/String;ZZ)V", "formType", "", "pemilihDpt_L", "pemilihDpt_P", "totalPemilihDpt", "penggunaDpt_L", "penggunaDpt_P", "totalPenggunaDpt", "penggunaDptb_L", "penggunaDptb_P", "totalPenggunaDptb", "penggunaDpk_L", "penggunaDpk_P", "totalPenggunaDpk", "totalPengguna_L", "totalPengguna_P", "totalPengguna", "suratDiterima", "suratDikembalikan", "suratTidakDigunakan", "suratTidakDikembalikan", "suratKembaliPPLN", "suratTidakTerpakai", "suratDigunakan", "pemilihDisabilitas_L", "pemilihDisabilitas_P", "totalPemilihDisabilitas", "(Ljava/lang/String;ZZIIIIIIILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;IIILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;IIII)V", "getFormType", "()I", "getIdImage", "()Ljava/lang/String;", "()Z", "getPemilihDisabilitas_L", "getPemilihDisabilitas_P", "getPemilihDpt_L", "getPemilihDpt_P", "getPenggunaDpk_L", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getPenggunaDpk_P", "getPenggunaDpt_L", "getPenggunaDpt_P", "getPenggunaDptb_L", "getPenggunaDptb_P", "getSuratDigunakan", "getSuratDikembalikan", "getSuratDiterima", "getSuratKembaliPPLN", "getSuratTidakDigunakan", "getSuratTidakDikembalikan", "getSuratTidakTerpakai", "getTotalPemilihDisabilitas", "getTotalPemilihDpt", "getTotalPengguna", "getTotalPenggunaDpk", "getTotalPenggunaDpt", "getTotalPenggunaDptb", "getTotalPengguna_L", "getTotalPengguna_P", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;ZZIIIIIIILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;IIILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;IIII)Lorg/informatika/sirekap/model/FormC1Administration;", "equals", "other", "hashCode", "isPenggunaDpt_LValid", "penggunaDpt_LFinal", "pemilihDpt_LFinal", "isPenggunaDpt_PValid", "penggunaDpt_PFinal", "pemilihDpt_PFinal", "isRowPenggunaDptValid", "totalPenggunaDptFinal", "totalPemilihDptFinal", "isRowTotalPenggunaValid", "totalPenggunaFinal", "totalPenggunaDpkFinal", "totalPenggunaDptbFinal", "isSuratDigunakanValid", "suratDigunakanFinal", "isSuratDikembalikanValid", "suratDikembalikanFinal", "suratDiterimaFinal", "isSuratDiterimaValid", "suratTidakDigunakanFinal", "isSuratTidakDigunakanLnPosValid", "suratKembaliPPLNFinal", "suratTidakTerpakaiFinal", "suratTidakDikembalikanFinal", "isSuratTidakDigunakanValid", "isTotalPenggunaDptValid", "isTotalPenggunaValid", "toNilaiPerItem", "", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormC1Administration {
    public static final String FORM_C1_ROW_PEMILIH_DISABILITAS = "pemilih_disabilitas";
    public static final String FORM_C1_ROW_PEMILIH_DPPH = "FORM_C1_ROW_PEMILIH_DPPH";
    public static final String FORM_C1_ROW_PEMILIH_DPT = "pemilih_dpt";
    public static final String FORM_C1_ROW_PEMILIH_DPTB = "FORM_C1_ROW_PEMILIH_DPTB";
    public static final String FORM_C1_ROW_PEMILIH_TOTAL = "FORM_C1_ROW_PEMILIH_TOTAL";
    public static final String FORM_C1_ROW_PENGGUNA_DISABILITAS = "FORM_C1_ROW_PENGGUNA_DISABILITAS";
    public static final String FORM_C1_ROW_PENGGUNA_DPK = "pengguna_dpk";
    public static final String FORM_C1_ROW_PENGGUNA_DPPH = "FORM_C1_ROW_PENGGUNA_DPPH";
    public static final String FORM_C1_ROW_PENGGUNA_DPT = "pengguna_dpt";
    public static final String FORM_C1_ROW_PENGGUNA_DPTB = "pengguna_dptb";
    public static final String FORM_C1_ROW_PENGGUNA_TOTAL = "pengguna_total";
    public static final String FORM_C1_ROW_SURAT_DIGUNAKAN = "surat_digunakan";
    public static final String FORM_C1_ROW_SURAT_DIKEMBALIKAN = "surat_dikembalikan";
    public static final String FORM_C1_ROW_SURAT_DITERIMA = "surat_diterima";
    public static final String FORM_C1_ROW_SURAT_KEMBALI_PPLN = "surat_kembali_ppln";
    public static final String FORM_C1_ROW_SURAT_TIDAK_DIGUNAKAN = "surat_tidak_digunakan";
    public static final String FORM_C1_ROW_SURAT_TIDAK_DIKEMBALIKAN = "surat_tidak_dikembalikan";
    public static final String FORM_C1_ROW_SURAT_TIDAK_TERPAKAI = "surat_tidak_terpakai";
    public static final double MAX_PENGGUNA = 512.5d;
    private final int formType;
    private final String idImage;
    private final boolean isLn;
    private final boolean isLnPos;
    private final int pemilihDisabilitas_L;
    private final int pemilihDisabilitas_P;
    private final int pemilihDpt_L;
    private final int pemilihDpt_P;
    private final Integer penggunaDpk_L;
    private final Integer penggunaDpk_P;
    private final int penggunaDpt_L;
    private final int penggunaDpt_P;
    private final Integer penggunaDptb_L;
    private final Integer penggunaDptb_P;
    private final int suratDigunakan;
    private final int suratDikembalikan;
    private final int suratDiterima;
    private final Integer suratKembaliPPLN;
    private final int suratTidakDigunakan;
    private final Integer suratTidakDikembalikan;
    private final Integer suratTidakTerpakai;
    private final int totalPemilihDisabilitas;
    private final int totalPemilihDpt;
    private final Integer totalPengguna;
    private final Integer totalPenggunaDpk;
    private final int totalPenggunaDpt;
    private final Integer totalPenggunaDptb;
    private final Integer totalPengguna_L;
    private final Integer totalPengguna_P;
    public static final Companion Companion = new Companion(null);
    private static final int penggunaDptInvalidMessage = R.string.pengguna_dpt_calc_invalid;
    private static final int suratDikembalikanInvalidMessage = R.string.surat_dikembalikan_calc_invalid;
    private static final int suratTidakDigunakanInvalidMessage = R.string.surat_tidak_digunakan_calc_invalid;
    private static final int suratDiterimaInvalidMessage = R.string.surat_diterima_calc_invalid;
    private static final int totalPenggunaInvalidMessage = R.string.total_pengguna_calc_invalid;
    private static final int suratTidakDigunakanLnPosInvalidMessage = R.string.surat_tidak_digunakan_lnpos_calc_invalid;
    private static final int suratDigunakanInvalidMessage = R.string.surat_digunakan_calc_invalid;

    public final String component1() {
        return this.idImage;
    }

    public final int component10() {
        return this.totalPenggunaDpt;
    }

    public final Integer component11() {
        return this.penggunaDptb_L;
    }

    public final Integer component12() {
        return this.penggunaDptb_P;
    }

    public final Integer component13() {
        return this.totalPenggunaDptb;
    }

    public final Integer component14() {
        return this.penggunaDpk_L;
    }

    public final Integer component15() {
        return this.penggunaDpk_P;
    }

    public final Integer component16() {
        return this.totalPenggunaDpk;
    }

    public final Integer component17() {
        return this.totalPengguna_L;
    }

    public final Integer component18() {
        return this.totalPengguna_P;
    }

    public final Integer component19() {
        return this.totalPengguna;
    }

    public final boolean component2() {
        return this.isLn;
    }

    public final int component20() {
        return this.suratDiterima;
    }

    public final int component21() {
        return this.suratDikembalikan;
    }

    public final int component22() {
        return this.suratTidakDigunakan;
    }

    public final Integer component23() {
        return this.suratTidakDikembalikan;
    }

    public final Integer component24() {
        return this.suratKembaliPPLN;
    }

    public final Integer component25() {
        return this.suratTidakTerpakai;
    }

    public final int component26() {
        return this.suratDigunakan;
    }

    public final int component27() {
        return this.pemilihDisabilitas_L;
    }

    public final int component28() {
        return this.pemilihDisabilitas_P;
    }

    public final int component29() {
        return this.totalPemilihDisabilitas;
    }

    public final boolean component3() {
        return this.isLnPos;
    }

    public final int component4() {
        return this.formType;
    }

    public final int component5() {
        return this.pemilihDpt_L;
    }

    public final int component6() {
        return this.pemilihDpt_P;
    }

    public final int component7() {
        return this.totalPemilihDpt;
    }

    public final int component8() {
        return this.penggunaDpt_L;
    }

    public final int component9() {
        return this.penggunaDpt_P;
    }

    public final FormC1Administration copy(String idImage, boolean z, boolean z2, int i, int i2, int i3, int i4, int i5, int i6, int i7, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7, Integer num8, Integer num9, int i8, int i9, int i10, Integer num10, Integer num11, Integer num12, int i11, int i12, int i13, int i14) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        return new FormC1Administration(idImage, z, z2, i, i2, i3, i4, i5, i6, i7, num, num2, num3, num4, num5, num6, num7, num8, num9, i8, i9, i10, num10, num11, num12, i11, i12, i13, i14);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FormC1Administration) {
            FormC1Administration formC1Administration = (FormC1Administration) obj;
            return Intrinsics.areEqual(this.idImage, formC1Administration.idImage) && this.isLn == formC1Administration.isLn && this.isLnPos == formC1Administration.isLnPos && this.formType == formC1Administration.formType && this.pemilihDpt_L == formC1Administration.pemilihDpt_L && this.pemilihDpt_P == formC1Administration.pemilihDpt_P && this.totalPemilihDpt == formC1Administration.totalPemilihDpt && this.penggunaDpt_L == formC1Administration.penggunaDpt_L && this.penggunaDpt_P == formC1Administration.penggunaDpt_P && this.totalPenggunaDpt == formC1Administration.totalPenggunaDpt && Intrinsics.areEqual(this.penggunaDptb_L, formC1Administration.penggunaDptb_L) && Intrinsics.areEqual(this.penggunaDptb_P, formC1Administration.penggunaDptb_P) && Intrinsics.areEqual(this.totalPenggunaDptb, formC1Administration.totalPenggunaDptb) && Intrinsics.areEqual(this.penggunaDpk_L, formC1Administration.penggunaDpk_L) && Intrinsics.areEqual(this.penggunaDpk_P, formC1Administration.penggunaDpk_P) && Intrinsics.areEqual(this.totalPenggunaDpk, formC1Administration.totalPenggunaDpk) && Intrinsics.areEqual(this.totalPengguna_L, formC1Administration.totalPengguna_L) && Intrinsics.areEqual(this.totalPengguna_P, formC1Administration.totalPengguna_P) && Intrinsics.areEqual(this.totalPengguna, formC1Administration.totalPengguna) && this.suratDiterima == formC1Administration.suratDiterima && this.suratDikembalikan == formC1Administration.suratDikembalikan && this.suratTidakDigunakan == formC1Administration.suratTidakDigunakan && Intrinsics.areEqual(this.suratTidakDikembalikan, formC1Administration.suratTidakDikembalikan) && Intrinsics.areEqual(this.suratKembaliPPLN, formC1Administration.suratKembaliPPLN) && Intrinsics.areEqual(this.suratTidakTerpakai, formC1Administration.suratTidakTerpakai) && this.suratDigunakan == formC1Administration.suratDigunakan && this.pemilihDisabilitas_L == formC1Administration.pemilihDisabilitas_L && this.pemilihDisabilitas_P == formC1Administration.pemilihDisabilitas_P && this.totalPemilihDisabilitas == formC1Administration.totalPemilihDisabilitas;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = this.idImage.hashCode() * 31;
        boolean z = this.isLn;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode + i) * 31;
        boolean z2 = this.isLnPos;
        int hashCode2 = (((((((((((((((i2 + (z2 ? 1 : z2 ? 1 : 0)) * 31) + Integer.hashCode(this.formType)) * 31) + Integer.hashCode(this.pemilihDpt_L)) * 31) + Integer.hashCode(this.pemilihDpt_P)) * 31) + Integer.hashCode(this.totalPemilihDpt)) * 31) + Integer.hashCode(this.penggunaDpt_L)) * 31) + Integer.hashCode(this.penggunaDpt_P)) * 31) + Integer.hashCode(this.totalPenggunaDpt)) * 31;
        Integer num = this.penggunaDptb_L;
        int hashCode3 = (hashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.penggunaDptb_P;
        int hashCode4 = (hashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.totalPenggunaDptb;
        int hashCode5 = (hashCode4 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Integer num4 = this.penggunaDpk_L;
        int hashCode6 = (hashCode5 + (num4 == null ? 0 : num4.hashCode())) * 31;
        Integer num5 = this.penggunaDpk_P;
        int hashCode7 = (hashCode6 + (num5 == null ? 0 : num5.hashCode())) * 31;
        Integer num6 = this.totalPenggunaDpk;
        int hashCode8 = (hashCode7 + (num6 == null ? 0 : num6.hashCode())) * 31;
        Integer num7 = this.totalPengguna_L;
        int hashCode9 = (hashCode8 + (num7 == null ? 0 : num7.hashCode())) * 31;
        Integer num8 = this.totalPengguna_P;
        int hashCode10 = (hashCode9 + (num8 == null ? 0 : num8.hashCode())) * 31;
        Integer num9 = this.totalPengguna;
        int hashCode11 = (((((((hashCode10 + (num9 == null ? 0 : num9.hashCode())) * 31) + Integer.hashCode(this.suratDiterima)) * 31) + Integer.hashCode(this.suratDikembalikan)) * 31) + Integer.hashCode(this.suratTidakDigunakan)) * 31;
        Integer num10 = this.suratTidakDikembalikan;
        int hashCode12 = (hashCode11 + (num10 == null ? 0 : num10.hashCode())) * 31;
        Integer num11 = this.suratKembaliPPLN;
        int hashCode13 = (hashCode12 + (num11 == null ? 0 : num11.hashCode())) * 31;
        Integer num12 = this.suratTidakTerpakai;
        return ((((((((hashCode13 + (num12 != null ? num12.hashCode() : 0)) * 31) + Integer.hashCode(this.suratDigunakan)) * 31) + Integer.hashCode(this.pemilihDisabilitas_L)) * 31) + Integer.hashCode(this.pemilihDisabilitas_P)) * 31) + Integer.hashCode(this.totalPemilihDisabilitas);
    }

    public final boolean isPenggunaDpt_LValid(int i, int i2) {
        return i <= i2;
    }

    public final boolean isPenggunaDpt_PValid(int i, int i2) {
        return i <= i2;
    }

    public final boolean isSuratDigunakanValid(int i, int i2) {
        return i == i2;
    }

    public final boolean isSuratDikembalikanValid(int i, int i2) {
        return i <= i2;
    }

    public final boolean isSuratDiterimaValid(int i, int i2, int i3, int i4) {
        return i == (i2 + i3) + i4;
    }

    public final boolean isSuratTidakDigunakanLnPosValid(int i, int i2, int i3, int i4) {
        return i == (i2 + i3) + i4;
    }

    public final boolean isSuratTidakDigunakanValid(int i, int i2) {
        return i <= i2;
    }

    public final boolean isTotalPenggunaDptValid(int i, int i2) {
        return i <= i2;
    }

    public final boolean isTotalPenggunaValid(int i, int i2, int i3, int i4) {
        return i == (i2 + i3) + i4;
    }

    public String toString() {
        String str = this.idImage;
        boolean z = this.isLn;
        boolean z2 = this.isLnPos;
        int i = this.formType;
        int i2 = this.pemilihDpt_L;
        int i3 = this.pemilihDpt_P;
        int i4 = this.totalPemilihDpt;
        int i5 = this.penggunaDpt_L;
        int i6 = this.penggunaDpt_P;
        int i7 = this.totalPenggunaDpt;
        Integer num = this.penggunaDptb_L;
        Integer num2 = this.penggunaDptb_P;
        Integer num3 = this.totalPenggunaDptb;
        Integer num4 = this.penggunaDpk_L;
        Integer num5 = this.penggunaDpk_P;
        Integer num6 = this.totalPenggunaDpk;
        Integer num7 = this.totalPengguna_L;
        Integer num8 = this.totalPengguna_P;
        Integer num9 = this.totalPengguna;
        int i8 = this.suratDiterima;
        int i9 = this.suratDikembalikan;
        int i10 = this.suratTidakDigunakan;
        Integer num10 = this.suratTidakDikembalikan;
        Integer num11 = this.suratKembaliPPLN;
        Integer num12 = this.suratTidakTerpakai;
        int i11 = this.suratDigunakan;
        int i12 = this.pemilihDisabilitas_L;
        int i13 = this.pemilihDisabilitas_P;
        return "FormC1Administration(idImage=" + str + ", isLn=" + z + ", isLnPos=" + z2 + ", formType=" + i + ", pemilihDpt_L=" + i2 + ", pemilihDpt_P=" + i3 + ", totalPemilihDpt=" + i4 + ", penggunaDpt_L=" + i5 + ", penggunaDpt_P=" + i6 + ", totalPenggunaDpt=" + i7 + ", penggunaDptb_L=" + num + ", penggunaDptb_P=" + num2 + ", totalPenggunaDptb=" + num3 + ", penggunaDpk_L=" + num4 + ", penggunaDpk_P=" + num5 + ", totalPenggunaDpk=" + num6 + ", totalPengguna_L=" + num7 + ", totalPengguna_P=" + num8 + ", totalPengguna=" + num9 + ", suratDiterima=" + i8 + ", suratDikembalikan=" + i9 + ", suratTidakDigunakan=" + i10 + ", suratTidakDikembalikan=" + num10 + ", suratKembaliPPLN=" + num11 + ", suratTidakTerpakai=" + num12 + ", suratDigunakan=" + i11 + ", pemilihDisabilitas_L=" + i12 + ", pemilihDisabilitas_P=" + i13 + ", totalPemilihDisabilitas=" + this.totalPemilihDisabilitas + ")";
    }

    public FormC1Administration(String idImage, boolean z, boolean z2, int i, int i2, int i3, int i4, int i5, int i6, int i7, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7, Integer num8, Integer num9, int i8, int i9, int i10, Integer num10, Integer num11, Integer num12, int i11, int i12, int i13, int i14) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        this.idImage = idImage;
        this.isLn = z;
        this.isLnPos = z2;
        this.formType = i;
        this.pemilihDpt_L = i2;
        this.pemilihDpt_P = i3;
        this.totalPemilihDpt = i4;
        this.penggunaDpt_L = i5;
        this.penggunaDpt_P = i6;
        this.totalPenggunaDpt = i7;
        this.penggunaDptb_L = num;
        this.penggunaDptb_P = num2;
        this.totalPenggunaDptb = num3;
        this.penggunaDpk_L = num4;
        this.penggunaDpk_P = num5;
        this.totalPenggunaDpk = num6;
        this.totalPengguna_L = num7;
        this.totalPengguna_P = num8;
        this.totalPengguna = num9;
        this.suratDiterima = i8;
        this.suratDikembalikan = i9;
        this.suratTidakDigunakan = i10;
        this.suratTidakDikembalikan = num10;
        this.suratKembaliPPLN = num11;
        this.suratTidakTerpakai = num12;
        this.suratDigunakan = i11;
        this.pemilihDisabilitas_L = i12;
        this.pemilihDisabilitas_P = i13;
        this.totalPemilihDisabilitas = i14;
    }

    public final String getIdImage() {
        return this.idImage;
    }

    public final boolean isLn() {
        return this.isLn;
    }

    public final boolean isLnPos() {
        return this.isLnPos;
    }

    public final int getFormType() {
        return this.formType;
    }

    public final int getPemilihDpt_L() {
        return this.pemilihDpt_L;
    }

    public final int getPemilihDpt_P() {
        return this.pemilihDpt_P;
    }

    public final int getTotalPemilihDpt() {
        return this.totalPemilihDpt;
    }

    public final int getPenggunaDpt_L() {
        return this.penggunaDpt_L;
    }

    public final int getPenggunaDpt_P() {
        return this.penggunaDpt_P;
    }

    public final int getTotalPenggunaDpt() {
        return this.totalPenggunaDpt;
    }

    public final Integer getPenggunaDptb_L() {
        return this.penggunaDptb_L;
    }

    public final Integer getPenggunaDptb_P() {
        return this.penggunaDptb_P;
    }

    public final Integer getTotalPenggunaDptb() {
        return this.totalPenggunaDptb;
    }

    public final Integer getPenggunaDpk_L() {
        return this.penggunaDpk_L;
    }

    public final Integer getPenggunaDpk_P() {
        return this.penggunaDpk_P;
    }

    public final Integer getTotalPenggunaDpk() {
        return this.totalPenggunaDpk;
    }

    public final Integer getTotalPengguna_L() {
        return this.totalPengguna_L;
    }

    public final Integer getTotalPengguna_P() {
        return this.totalPengguna_P;
    }

    public final Integer getTotalPengguna() {
        return this.totalPengguna;
    }

    public final int getSuratDiterima() {
        return this.suratDiterima;
    }

    public final int getSuratDikembalikan() {
        return this.suratDikembalikan;
    }

    public final int getSuratTidakDigunakan() {
        return this.suratTidakDigunakan;
    }

    public final Integer getSuratTidakDikembalikan() {
        return this.suratTidakDikembalikan;
    }

    public final Integer getSuratKembaliPPLN() {
        return this.suratKembaliPPLN;
    }

    public final Integer getSuratTidakTerpakai() {
        return this.suratTidakTerpakai;
    }

    public final int getSuratDigunakan() {
        return this.suratDigunakan;
    }

    public final int getPemilihDisabilitas_L() {
        return this.pemilihDisabilitas_L;
    }

    public final int getPemilihDisabilitas_P() {
        return this.pemilihDisabilitas_P;
    }

    public final int getTotalPemilihDisabilitas() {
        return this.totalPemilihDisabilitas;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FormC1Administration(String idImage, boolean z, boolean z2) {
        this(idImage, z, z2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Intrinsics.checkNotNullParameter(idImage, "idImage");
    }

    public final boolean isRowPenggunaDptValid(int i, int i2, int i3, int i4, int i5, int i6) {
        return isPenggunaDpt_LValid(i, i2) && isPenggunaDpt_PValid(i3, i4) && isTotalPenggunaDptValid(i5, i6);
    }

    public final boolean isRowTotalPenggunaValid(int i, int i2, int i3, int i4) {
        return isTotalPenggunaValid(i, i2, i3, i4);
    }

    public final List<Integer> toNilaiPerItem() {
        if (this.isLnPos) {
            return CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(this.pemilihDpt_L), Integer.valueOf(this.pemilihDpt_P), Integer.valueOf(this.totalPemilihDpt), Integer.valueOf(this.penggunaDpt_L), Integer.valueOf(this.penggunaDpt_P), Integer.valueOf(this.totalPenggunaDpt), this.penggunaDptb_L, this.penggunaDptb_P, this.totalPenggunaDptb, this.penggunaDpk_L, this.penggunaDpk_P, this.totalPenggunaDpk, this.totalPengguna_L, this.totalPengguna_P, this.totalPengguna, Integer.valueOf(this.suratDiterima), Integer.valueOf(this.suratDigunakan), Integer.valueOf(this.suratDikembalikan), Integer.valueOf(this.suratTidakDigunakan), this.suratKembaliPPLN, this.suratTidakTerpakai, this.suratTidakDikembalikan, Integer.valueOf(this.pemilihDisabilitas_L), Integer.valueOf(this.pemilihDisabilitas_P), Integer.valueOf(this.totalPemilihDisabilitas)});
        }
        return CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(this.pemilihDpt_L), Integer.valueOf(this.pemilihDpt_P), Integer.valueOf(this.totalPemilihDpt), Integer.valueOf(this.penggunaDpt_L), Integer.valueOf(this.penggunaDpt_P), Integer.valueOf(this.totalPenggunaDpt), this.penggunaDptb_L, this.penggunaDptb_P, this.totalPenggunaDptb, this.penggunaDpk_L, this.penggunaDpk_P, this.totalPenggunaDpk, this.totalPengguna_L, this.totalPengguna_P, this.totalPengguna, Integer.valueOf(this.suratDiterima), Integer.valueOf(this.suratDigunakan), Integer.valueOf(this.suratDikembalikan), Integer.valueOf(this.suratTidakDigunakan), Integer.valueOf(this.pemilihDisabilitas_L), Integer.valueOf(this.pemilihDisabilitas_P), Integer.valueOf(this.totalPemilihDisabilitas)});
    }

    /* compiled from: FormC1Administration.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J8\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020\u00042\u0010\b\u0002\u0010/\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u000100J.\u00101\u001a\u00020)2\u0006\u00102\u001a\u0002032\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020\u0004J$\u00104\u001a\b\u0012\u0004\u0012\u00020\u0019052\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u0019002\u0006\u00107\u001a\u00020\u0019H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u0011\u0010\u001e\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001bR\u0011\u0010 \u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001bR\u0011\u0010\"\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001bR\u0011\u0010$\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001bR\u0011\u0010&\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001b¨\u00068"}, d2 = {"Lorg/informatika/sirekap/model/FormC1Administration$Companion;", "", "()V", "FORM_C1_ROW_PEMILIH_DISABILITAS", "", FormC1Administration.FORM_C1_ROW_PEMILIH_DPPH, "FORM_C1_ROW_PEMILIH_DPT", FormC1Administration.FORM_C1_ROW_PEMILIH_DPTB, FormC1Administration.FORM_C1_ROW_PEMILIH_TOTAL, FormC1Administration.FORM_C1_ROW_PENGGUNA_DISABILITAS, "FORM_C1_ROW_PENGGUNA_DPK", FormC1Administration.FORM_C1_ROW_PENGGUNA_DPPH, "FORM_C1_ROW_PENGGUNA_DPT", "FORM_C1_ROW_PENGGUNA_DPTB", "FORM_C1_ROW_PENGGUNA_TOTAL", FormC1AdministrationHal2.FORM_C1_ROW_SURAT_DIGUNAKAN, "FORM_C1_ROW_SURAT_DIKEMBALIKAN", "FORM_C1_ROW_SURAT_DITERIMA", "FORM_C1_ROW_SURAT_KEMBALI_PPLN", "FORM_C1_ROW_SURAT_TIDAK_DIGUNAKAN", "FORM_C1_ROW_SURAT_TIDAK_DIKEMBALIKAN", "FORM_C1_ROW_SURAT_TIDAK_TERPAKAI", "MAX_PENGGUNA", "", "penggunaDptInvalidMessage", "", "getPenggunaDptInvalidMessage", "()I", "suratDigunakanInvalidMessage", "getSuratDigunakanInvalidMessage", "suratDikembalikanInvalidMessage", "getSuratDikembalikanInvalidMessage", "suratDiterimaInvalidMessage", "getSuratDiterimaInvalidMessage", "suratTidakDigunakanInvalidMessage", "getSuratTidakDigunakanInvalidMessage", "suratTidakDigunakanLnPosInvalidMessage", "getSuratTidakDigunakanLnPosInvalidMessage", "totalPenggunaInvalidMessage", "getTotalPenggunaInvalidMessage", "createEmpty", "Lorg/informatika/sirekap/model/FormC1Administration;", "idImage", "isLn", "", "isLnPos", "jenisPemilihan", "predictedNumbers", "", "createFromFormC1AdministrationPayload", "payload", "Lorg/informatika/sirekap/model/FormC1AdministrationPayload;", "getImagePayloadData", "", "input", "segmentLength", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final List<Integer> getImagePayloadData(List<Integer> list, int i) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < 3; i2++) {
                int i3 = i2 * i;
                arrayList.add(Integer.valueOf(ElectionUtil.joinThreeNumbers(list.subList(i3, i3 + i))));
            }
            return arrayList;
        }

        public final FormC1Administration createFromFormC1AdministrationPayload(FormC1AdministrationPayload payload, String idImage, boolean z, boolean z2, String jenisPemilihan) {
            Intrinsics.checkNotNullParameter(payload, "payload");
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            int size = payload.getI().getHasilPrediksi().get(0).size() / 3;
            if (z2) {
                List<Integer> imagePayloadData = FormC1Administration.Companion.getImagePayloadData(payload.getI().getHasilPrediksi().get(0), size);
                int intValue = imagePayloadData.get(0).intValue();
                int intValue2 = imagePayloadData.get(1).intValue();
                int intValue3 = imagePayloadData.get(2).intValue();
                List<Integer> imagePayloadData2 = FormC1Administration.Companion.getImagePayloadData(payload.getI().getHasilPrediksi().get(1), size);
                int intValue4 = imagePayloadData2.get(0).intValue();
                int intValue5 = imagePayloadData2.get(1).intValue();
                int intValue6 = imagePayloadData2.get(2).intValue();
                List<Integer> imagePayloadData3 = FormC1Administration.Companion.getImagePayloadData(payload.getI().getHasilPrediksi().get(2), size);
                int intValue7 = imagePayloadData3.get(0).intValue();
                int intValue8 = imagePayloadData3.get(1).intValue();
                int intValue9 = imagePayloadData3.get(2).intValue();
                List<Integer> imagePayloadData4 = FormC1Administration.Companion.getImagePayloadData(payload.getI().getHasilPrediksi().get(3), size);
                int intValue10 = imagePayloadData4.get(0).intValue();
                int intValue11 = imagePayloadData4.get(1).intValue();
                int intValue12 = imagePayloadData4.get(2).intValue();
                List<Integer> imagePayloadData5 = FormC1Administration.Companion.getImagePayloadData(payload.getI().getHasilPrediksi().get(4), size);
                int intValue13 = imagePayloadData5.get(0).intValue();
                int intValue14 = imagePayloadData5.get(1).intValue();
                int intValue15 = imagePayloadData5.get(2).intValue();
                int joinThreeNumbers = ElectionUtil.joinThreeNumbers(payload.getII().getHasilPrediksi().get(0).subList(0, size));
                int joinThreeNumbers2 = ElectionUtil.joinThreeNumbers(payload.getII().getHasilPrediksi().get(1).subList(0, size));
                int joinThreeNumbers3 = ElectionUtil.joinThreeNumbers(payload.getII().getHasilPrediksi().get(2).subList(0, size));
                int joinThreeNumbers4 = ElectionUtil.joinThreeNumbers(payload.getII().getHasilPrediksi().get(3).subList(0, size));
                int joinThreeNumbers5 = ElectionUtil.joinThreeNumbers(payload.getII().getHasilPrediksi().get(4).subList(0, size));
                int joinThreeNumbers6 = ElectionUtil.joinThreeNumbers(payload.getII().getHasilPrediksi().get(5).subList(0, size));
                int joinThreeNumbers7 = ElectionUtil.joinThreeNumbers(payload.getII().getHasilPrediksi().get(6).subList(0, size));
                List<Integer> imagePayloadData6 = FormC1Administration.Companion.getImagePayloadData(payload.getIII().getHasilPrediksi().get(0), size);
                return new FormC1Administration(idImage, z, z2, 1, intValue, intValue2, intValue3, intValue4, intValue5, intValue6, Integer.valueOf(intValue7), Integer.valueOf(intValue8), Integer.valueOf(intValue9), Integer.valueOf(intValue10), Integer.valueOf(intValue11), Integer.valueOf(intValue12), Integer.valueOf(intValue13), Integer.valueOf(intValue14), Integer.valueOf(intValue15), joinThreeNumbers, joinThreeNumbers3, joinThreeNumbers4, Integer.valueOf(joinThreeNumbers7), Integer.valueOf(joinThreeNumbers5), Integer.valueOf(joinThreeNumbers6), joinThreeNumbers2, imagePayloadData6.get(0).intValue(), imagePayloadData6.get(1).intValue(), imagePayloadData6.get(2).intValue());
            }
            List<Integer> imagePayloadData7 = FormC1Administration.Companion.getImagePayloadData(payload.getI().getHasilPrediksi().get(0), size);
            int intValue16 = imagePayloadData7.get(0).intValue();
            int intValue17 = imagePayloadData7.get(1).intValue();
            int intValue18 = imagePayloadData7.get(2).intValue();
            List<Integer> imagePayloadData8 = FormC1Administration.Companion.getImagePayloadData(payload.getI().getHasilPrediksi().get(1), size);
            int intValue19 = imagePayloadData8.get(0).intValue();
            int intValue20 = imagePayloadData8.get(1).intValue();
            int intValue21 = imagePayloadData8.get(2).intValue();
            List<Integer> imagePayloadData9 = FormC1Administration.Companion.getImagePayloadData(payload.getI().getHasilPrediksi().get(2), size);
            int intValue22 = imagePayloadData9.get(0).intValue();
            int intValue23 = imagePayloadData9.get(1).intValue();
            int intValue24 = imagePayloadData9.get(2).intValue();
            List<Integer> imagePayloadData10 = FormC1Administration.Companion.getImagePayloadData(payload.getI().getHasilPrediksi().get(3), size);
            int intValue25 = imagePayloadData10.get(0).intValue();
            int intValue26 = imagePayloadData10.get(1).intValue();
            int intValue27 = imagePayloadData10.get(2).intValue();
            List<Integer> imagePayloadData11 = FormC1Administration.Companion.getImagePayloadData(payload.getI().getHasilPrediksi().get(4), size);
            int intValue28 = imagePayloadData11.get(0).intValue();
            int intValue29 = imagePayloadData11.get(1).intValue();
            int intValue30 = imagePayloadData11.get(2).intValue();
            int joinThreeNumbers8 = ElectionUtil.joinThreeNumbers(payload.getII().getHasilPrediksi().get(0).subList(0, size));
            int joinThreeNumbers9 = ElectionUtil.joinThreeNumbers(payload.getII().getHasilPrediksi().get(1).subList(0, size));
            int joinThreeNumbers10 = ElectionUtil.joinThreeNumbers(payload.getII().getHasilPrediksi().get(2).subList(0, size));
            int joinThreeNumbers11 = ElectionUtil.joinThreeNumbers(payload.getII().getHasilPrediksi().get(3).subList(0, size));
            List<Integer> imagePayloadData12 = FormC1Administration.Companion.getImagePayloadData(payload.getIII().getHasilPrediksi().get(0), size);
            return new FormC1Administration(idImage, z, z2, 1, intValue16, intValue17, intValue18, intValue19, intValue20, intValue21, Integer.valueOf(intValue22), Integer.valueOf(intValue23), Integer.valueOf(intValue24), Integer.valueOf(intValue25), Integer.valueOf(intValue26), Integer.valueOf(intValue27), Integer.valueOf(intValue28), Integer.valueOf(intValue29), Integer.valueOf(intValue30), joinThreeNumbers8, joinThreeNumbers10, joinThreeNumbers11, null, null, null, joinThreeNumbers9, imagePayloadData12.get(0).intValue(), imagePayloadData12.get(1).intValue(), imagePayloadData12.get(2).intValue());
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ FormC1Administration createEmpty$default(Companion companion, String str, boolean z, boolean z2, String str2, List list, int i, Object obj) {
            List<Integer> list2 = list;
            if ((i & 16) != 0) {
                list2 = null;
            }
            return companion.createEmpty(str, z, z2, str2, list2);
        }

        public final FormC1Administration createEmpty(String idImage, boolean z, boolean z2, String jenisPemilihan, List<Integer> list) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            if (z2) {
                return new FormC1Administration(idImage, z, z2, 1, list != null ? list.get(0).intValue() : 0, list != null ? list.get(1).intValue() : 0, list != null ? list.get(2).intValue() : 0, list != null ? list.get(3).intValue() : 0, list != null ? list.get(4).intValue() : 0, list != null ? list.get(5).intValue() : 0, Integer.valueOf(list != null ? list.get(6).intValue() : 0), Integer.valueOf(list != null ? list.get(7).intValue() : 0), Integer.valueOf(list != null ? list.get(8).intValue() : 0), Integer.valueOf(list != null ? list.get(9).intValue() : 0), Integer.valueOf(list != null ? list.get(10).intValue() : 0), Integer.valueOf(list != null ? list.get(11).intValue() : 0), Integer.valueOf(list != null ? list.get(12).intValue() : 0), Integer.valueOf(list != null ? list.get(13).intValue() : 0), Integer.valueOf(list != null ? list.get(14).intValue() : 0), list != null ? list.get(15).intValue() : 0, list != null ? list.get(16).intValue() : 0, list != null ? list.get(17).intValue() : 0, Integer.valueOf(list != null ? list.get(18).intValue() : 0), Integer.valueOf(list != null ? list.get(19).intValue() : 0), Integer.valueOf(list != null ? list.get(20).intValue() : 0), list != null ? list.get(21).intValue() : 0, list != null ? list.get(22).intValue() : 0, list != null ? list.get(23).intValue() : 0, list != null ? list.get(24).intValue() : 0);
            }
            int intValue = list != null ? list.get(0).intValue() : 0;
            int intValue2 = list != null ? list.get(1).intValue() : 0;
            int intValue3 = list != null ? list.get(2).intValue() : 0;
            int intValue4 = list != null ? list.get(3).intValue() : 0;
            int intValue5 = list != null ? list.get(4).intValue() : 0;
            int intValue6 = list != null ? list.get(5).intValue() : 0;
            int intValue7 = list != null ? list.get(6).intValue() : 0;
            int intValue8 = list != null ? list.get(7).intValue() : 0;
            int intValue9 = list != null ? list.get(8).intValue() : 0;
            int intValue10 = list != null ? list.get(9).intValue() : 0;
            int intValue11 = list != null ? list.get(10).intValue() : 0;
            int intValue12 = list != null ? list.get(11).intValue() : 0;
            int intValue13 = list != null ? list.get(12).intValue() : 0;
            int intValue14 = list != null ? list.get(13).intValue() : 0;
            int intValue15 = list != null ? list.get(14).intValue() : 0;
            return new FormC1Administration(idImage, z, z2, 1, intValue, intValue2, intValue3, intValue4, intValue5, intValue6, Integer.valueOf(intValue7), Integer.valueOf(intValue8), Integer.valueOf(intValue9), Integer.valueOf(intValue10), Integer.valueOf(intValue11), Integer.valueOf(intValue12), Integer.valueOf(intValue13), Integer.valueOf(intValue14), Integer.valueOf(intValue15), list != null ? list.get(15).intValue() : 0, list != null ? list.get(17).intValue() : 0, list != null ? list.get(18).intValue() : 0, null, null, null, list != null ? list.get(16).intValue() : 0, list != null ? list.get(19).intValue() : 0, list != null ? list.get(20).intValue() : 0, list != null ? list.get(21).intValue() : 0);
        }

        public final int getPenggunaDptInvalidMessage() {
            return FormC1Administration.penggunaDptInvalidMessage;
        }

        public final int getSuratDikembalikanInvalidMessage() {
            return FormC1Administration.suratDikembalikanInvalidMessage;
        }

        public final int getSuratTidakDigunakanInvalidMessage() {
            return FormC1Administration.suratTidakDigunakanInvalidMessage;
        }

        public final int getSuratDiterimaInvalidMessage() {
            return FormC1Administration.suratDiterimaInvalidMessage;
        }

        public final int getTotalPenggunaInvalidMessage() {
            return FormC1Administration.totalPenggunaInvalidMessage;
        }

        public final int getSuratTidakDigunakanLnPosInvalidMessage() {
            return FormC1Administration.suratTidakDigunakanLnPosInvalidMessage;
        }

        public final int getSuratDigunakanInvalidMessage() {
            return FormC1Administration.suratDigunakanInvalidMessage;
        }
    }
}
