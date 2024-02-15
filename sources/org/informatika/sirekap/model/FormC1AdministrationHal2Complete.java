package org.informatika.sirekap.model;

import android.content.Context;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.FormC1AdministrationComplete;

/* compiled from: FormC1AdministrationHal2.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u000bHÆ\u0003JC\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!2\u0006\u0010#\u001a\u00020$J\u0006\u0010%\u001a\u00020&J\u0006\u0010'\u001a\u00020&J\u0006\u0010(\u001a\u00020&J\t\u0010)\u001a\u00020&HÖ\u0001J\u0006\u0010*\u001a\u00020\u001eJ\u0006\u0010+\u001a\u00020\u001eJ\t\u0010,\u001a\u00020-HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006."}, d2 = {"Lorg/informatika/sirekap/model/FormC1AdministrationHal2Complete;", "", "electionPage", "Lorg/informatika/sirekap/model/ElectionPage;", "form", "Lorg/informatika/sirekap/model/FormC1AdministrationHal2;", "error", "Lorg/informatika/sirekap/model/FormC1Error;", "kesesuaian", "Lorg/informatika/sirekap/model/FormC1Kesesuaian;", "kesesuaianAdministrationHal2", "Lorg/informatika/sirekap/model/FormC1KesesuaianAdministrationHal2;", "(Lorg/informatika/sirekap/model/ElectionPage;Lorg/informatika/sirekap/model/FormC1AdministrationHal2;Lorg/informatika/sirekap/model/FormC1Error;Lorg/informatika/sirekap/model/FormC1Kesesuaian;Lorg/informatika/sirekap/model/FormC1KesesuaianAdministrationHal2;)V", "getElectionPage", "()Lorg/informatika/sirekap/model/ElectionPage;", "getError", "()Lorg/informatika/sirekap/model/FormC1Error;", "getForm", "()Lorg/informatika/sirekap/model/FormC1AdministrationHal2;", "getKesesuaian", "()Lorg/informatika/sirekap/model/FormC1Kesesuaian;", "getKesesuaianAdministrationHal2", "()Lorg/informatika/sirekap/model/FormC1KesesuaianAdministrationHal2;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "getSalinanData", "", "Lorg/informatika/sirekap/model/FormC1AdministrationComplete$SectionDataPdf;", "context", "Landroid/content/Context;", "getSuratSuaraSahFinal", "", "getSuratSuaraTidakSahFinal", "getTotalSuratSuaraFinal", "hashCode", "isCheckedAll", "isVerified", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormC1AdministrationHal2Complete {
    private final ElectionPage electionPage;
    private final FormC1Error error;
    private final FormC1AdministrationHal2 form;
    private final FormC1Kesesuaian kesesuaian;
    private final FormC1KesesuaianAdministrationHal2 kesesuaianAdministrationHal2;

    public static /* synthetic */ FormC1AdministrationHal2Complete copy$default(FormC1AdministrationHal2Complete formC1AdministrationHal2Complete, ElectionPage electionPage, FormC1AdministrationHal2 formC1AdministrationHal2, FormC1Error formC1Error, FormC1Kesesuaian formC1Kesesuaian, FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2, int i, Object obj) {
        if ((i & 1) != 0) {
            electionPage = formC1AdministrationHal2Complete.electionPage;
        }
        if ((i & 2) != 0) {
            formC1AdministrationHal2 = formC1AdministrationHal2Complete.form;
        }
        FormC1AdministrationHal2 formC1AdministrationHal22 = formC1AdministrationHal2;
        if ((i & 4) != 0) {
            formC1Error = formC1AdministrationHal2Complete.error;
        }
        FormC1Error formC1Error2 = formC1Error;
        if ((i & 8) != 0) {
            formC1Kesesuaian = formC1AdministrationHal2Complete.kesesuaian;
        }
        FormC1Kesesuaian formC1Kesesuaian2 = formC1Kesesuaian;
        if ((i & 16) != 0) {
            formC1KesesuaianAdministrationHal2 = formC1AdministrationHal2Complete.kesesuaianAdministrationHal2;
        }
        return formC1AdministrationHal2Complete.copy(electionPage, formC1AdministrationHal22, formC1Error2, formC1Kesesuaian2, formC1KesesuaianAdministrationHal2);
    }

    public final ElectionPage component1() {
        return this.electionPage;
    }

    public final FormC1AdministrationHal2 component2() {
        return this.form;
    }

    public final FormC1Error component3() {
        return this.error;
    }

    public final FormC1Kesesuaian component4() {
        return this.kesesuaian;
    }

    public final FormC1KesesuaianAdministrationHal2 component5() {
        return this.kesesuaianAdministrationHal2;
    }

    public final FormC1AdministrationHal2Complete copy(ElectionPage electionPage, FormC1AdministrationHal2 formC1AdministrationHal2, FormC1Error formC1Error, FormC1Kesesuaian formC1Kesesuaian, FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2) {
        Intrinsics.checkNotNullParameter(electionPage, "electionPage");
        return new FormC1AdministrationHal2Complete(electionPage, formC1AdministrationHal2, formC1Error, formC1Kesesuaian, formC1KesesuaianAdministrationHal2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FormC1AdministrationHal2Complete) {
            FormC1AdministrationHal2Complete formC1AdministrationHal2Complete = (FormC1AdministrationHal2Complete) obj;
            return Intrinsics.areEqual(this.electionPage, formC1AdministrationHal2Complete.electionPage) && Intrinsics.areEqual(this.form, formC1AdministrationHal2Complete.form) && Intrinsics.areEqual(this.error, formC1AdministrationHal2Complete.error) && Intrinsics.areEqual(this.kesesuaian, formC1AdministrationHal2Complete.kesesuaian) && Intrinsics.areEqual(this.kesesuaianAdministrationHal2, formC1AdministrationHal2Complete.kesesuaianAdministrationHal2);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.electionPage.hashCode() * 31;
        FormC1AdministrationHal2 formC1AdministrationHal2 = this.form;
        int hashCode2 = (hashCode + (formC1AdministrationHal2 == null ? 0 : formC1AdministrationHal2.hashCode())) * 31;
        FormC1Error formC1Error = this.error;
        int hashCode3 = (hashCode2 + (formC1Error == null ? 0 : formC1Error.hashCode())) * 31;
        FormC1Kesesuaian formC1Kesesuaian = this.kesesuaian;
        int hashCode4 = (hashCode3 + (formC1Kesesuaian == null ? 0 : formC1Kesesuaian.hashCode())) * 31;
        FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2 = this.kesesuaianAdministrationHal2;
        return hashCode4 + (formC1KesesuaianAdministrationHal2 != null ? formC1KesesuaianAdministrationHal2.hashCode() : 0);
    }

    public String toString() {
        ElectionPage electionPage = this.electionPage;
        FormC1AdministrationHal2 formC1AdministrationHal2 = this.form;
        FormC1Error formC1Error = this.error;
        FormC1Kesesuaian formC1Kesesuaian = this.kesesuaian;
        return "FormC1AdministrationHal2Complete(electionPage=" + electionPage + ", form=" + formC1AdministrationHal2 + ", error=" + formC1Error + ", kesesuaian=" + formC1Kesesuaian + ", kesesuaianAdministrationHal2=" + this.kesesuaianAdministrationHal2 + ")";
    }

    public FormC1AdministrationHal2Complete(ElectionPage electionPage, FormC1AdministrationHal2 formC1AdministrationHal2, FormC1Error formC1Error, FormC1Kesesuaian formC1Kesesuaian, FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2) {
        Intrinsics.checkNotNullParameter(electionPage, "electionPage");
        this.electionPage = electionPage;
        this.form = formC1AdministrationHal2;
        this.error = formC1Error;
        this.kesesuaian = formC1Kesesuaian;
        this.kesesuaianAdministrationHal2 = formC1KesesuaianAdministrationHal2;
    }

    public final ElectionPage getElectionPage() {
        return this.electionPage;
    }

    public final FormC1AdministrationHal2 getForm() {
        return this.form;
    }

    public final FormC1Error getError() {
        return this.error;
    }

    public final FormC1Kesesuaian getKesesuaian() {
        return this.kesesuaian;
    }

    public final FormC1KesesuaianAdministrationHal2 getKesesuaianAdministrationHal2() {
        return this.kesesuaianAdministrationHal2;
    }

    public final boolean isCheckedAll() {
        FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2 = this.kesesuaianAdministrationHal2;
        return formC1KesesuaianAdministrationHal2 != null && formC1KesesuaianAdministrationHal2.isCheckedAll();
    }

    public final boolean isVerified() {
        return this.kesesuaian != null;
    }

    public final int getSuratSuaraSahFinal() {
        Integer suratSuaraSah;
        FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2 = this.kesesuaianAdministrationHal2;
        if (formC1KesesuaianAdministrationHal2 != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministrationHal2.getSuratSuaraSah(), (Object) false) : false) {
            Integer suratSuaraSahCorrected = this.kesesuaianAdministrationHal2.getSuratSuaraSahCorrected();
            if (suratSuaraSahCorrected == null) {
                FormC1AdministrationHal2 formC1AdministrationHal2 = this.form;
                suratSuaraSahCorrected = formC1AdministrationHal2 != null ? formC1AdministrationHal2.getSuratSuaraSah() : null;
                if (suratSuaraSahCorrected == null) {
                    return 0;
                }
            }
            return suratSuaraSahCorrected.intValue();
        }
        FormC1AdministrationHal2 formC1AdministrationHal22 = this.form;
        if (formC1AdministrationHal22 == null || (suratSuaraSah = formC1AdministrationHal22.getSuratSuaraSah()) == null) {
            return 0;
        }
        return suratSuaraSah.intValue();
    }

    public final int getSuratSuaraTidakSahFinal() {
        Integer suratSuaraTidakSah;
        FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2 = this.kesesuaianAdministrationHal2;
        if (formC1KesesuaianAdministrationHal2 != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministrationHal2.getSuratSuaraTidakSah(), (Object) false) : false) {
            Integer suratSuaraTidakSahCorrected = this.kesesuaianAdministrationHal2.getSuratSuaraTidakSahCorrected();
            if (suratSuaraTidakSahCorrected == null) {
                FormC1AdministrationHal2 formC1AdministrationHal2 = this.form;
                suratSuaraTidakSahCorrected = formC1AdministrationHal2 != null ? formC1AdministrationHal2.getSuratSuaraTidakSah() : null;
                if (suratSuaraTidakSahCorrected == null) {
                    return 0;
                }
            }
            return suratSuaraTidakSahCorrected.intValue();
        }
        FormC1AdministrationHal2 formC1AdministrationHal22 = this.form;
        if (formC1AdministrationHal22 == null || (suratSuaraTidakSah = formC1AdministrationHal22.getSuratSuaraTidakSah()) == null) {
            return 0;
        }
        return suratSuaraTidakSah.intValue();
    }

    public final int getTotalSuratSuaraFinal() {
        Integer totalSuratSuara;
        FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2 = this.kesesuaianAdministrationHal2;
        if (formC1KesesuaianAdministrationHal2 != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministrationHal2.getTotalSuratSuara(), (Object) false) : false) {
            Integer totalSuratSuaraCorrected = this.kesesuaianAdministrationHal2.getTotalSuratSuaraCorrected();
            if (totalSuratSuaraCorrected == null) {
                FormC1AdministrationHal2 formC1AdministrationHal2 = this.form;
                totalSuratSuaraCorrected = formC1AdministrationHal2 != null ? formC1AdministrationHal2.getTotalSuratSuara() : null;
                if (totalSuratSuaraCorrected == null) {
                    return 0;
                }
            }
            return totalSuratSuaraCorrected.intValue();
        }
        FormC1AdministrationHal2 formC1AdministrationHal22 = this.form;
        if (formC1AdministrationHal22 == null || (totalSuratSuara = formC1AdministrationHal22.getTotalSuratSuara()) == null) {
            return 0;
        }
        return totalSuratSuara.intValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final List<FormC1AdministrationComplete.SectionDataPdf> getSalinanData(Context context) {
        int i;
        Intrinsics.checkNotNullParameter(context, "context");
        FormC1AdministrationHal2 formC1AdministrationHal2 = this.form;
        boolean z = formC1AdministrationHal2 != null && formC1AdministrationHal2.isLnPos();
        FormC1AdministrationHal2 formC1AdministrationHal22 = this.form;
        boolean z2 = formC1AdministrationHal22 != null && formC1AdministrationHal22.isLn();
        String jenisPemilihan = this.electionPage.getJenisPemilihan();
        switch (jenisPemilihan.hashCode()) {
            case -992700931:
                if (jenisPemilihan.equals(Election.ELECTION_PEMILIHAN_DPRD_KABKO)) {
                    i = R.raw.pdprdk_strings;
                    break;
                }
                i = R.raw.ppwp_dn_strings;
                break;
            case -992700926:
                if (jenisPemilihan.equals(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI)) {
                    i = R.raw.pdprdp_strings;
                    break;
                }
                i = R.raw.ppwp_dn_strings;
                break;
            case 3436264:
                if (jenisPemilihan.equals(Election.ELECTION_PEMILIHAN_DPD)) {
                    i = R.raw.pdpd_strings;
                    break;
                }
                i = R.raw.ppwp_dn_strings;
                break;
            case 3436278:
                if (jenisPemilihan.equals(Election.ELECTION_PEMILIHAN_DPR)) {
                    if (!z) {
                        if (!z2) {
                            i = R.raw.pdpr_dn_strings;
                            break;
                        } else {
                            i = R.raw.pdpr_ln_strings;
                            break;
                        }
                    } else {
                        i = R.raw.pdpr_lnpos_strings;
                        break;
                    }
                }
                i = R.raw.ppwp_dn_strings;
                break;
            case 3448025:
                if (jenisPemilihan.equals(Election.ELECTION_PEMILIHAN_PRESIDEN)) {
                    if (!z) {
                        if (!z2) {
                            i = R.raw.ppwp_dn_strings;
                            break;
                        } else {
                            i = R.raw.ppwp_ln_strings;
                            break;
                        }
                    } else {
                        i = R.raw.ppwp_lnpos_strings;
                        break;
                    }
                }
                i = R.raw.ppwp_dn_strings;
                break;
            default:
                i = R.raw.ppwp_dn_strings;
                break;
        }
        Gson gson = new Gson();
        InputStream openRawResource = context.getResources().openRawResource(i);
        Intrinsics.checkNotNullExpressionValue(openRawResource, "context.resources.openRawResource(jsonStringsStr)");
        InputStreamReader inputStreamReader = new InputStreamReader(openRawResource, Charsets.UTF_8);
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        try {
            String readText = TextStreamsKt.readText(bufferedReader);
            CloseableKt.closeFinally(bufferedReader, null);
            HashMap hashMap = (HashMap) gson.fromJson(readText, (Class<Object>) HashMap.class);
            FormC1AdministrationComplete.SectionDataPdf sectionDataPdf = new FormC1AdministrationComplete.SectionDataPdf("V.", "DATA SUARA DAN SUARA TIDAK SAH", null, "Jumlah", null, null, 52, null);
            List<FormC1AdministrationComplete.RowDataPdf> rows = sectionDataPdf.getRows();
            FormC1AdministrationComplete.RowDataPdf[] rowDataPdfArr = new FormC1AdministrationComplete.RowDataPdf[3];
            FormC1AdministrationComplete.RowDataPdfType rowDataPdfType = FormC1AdministrationComplete.RowDataPdfType.content;
            Object obj = hashMap != null ? hashMap.get("va") : null;
            String str = obj instanceof String ? (String) obj : null;
            rowDataPdfArr[0] = new FormC1AdministrationComplete.RowDataPdf(rowDataPdfType, "A.", str == null ? "" : str, null, null, Integer.valueOf(getSuratSuaraSahFinal()), null, 64, null);
            FormC1AdministrationComplete.RowDataPdfType rowDataPdfType2 = FormC1AdministrationComplete.RowDataPdfType.content;
            Object obj2 = hashMap != null ? hashMap.get("vb") : null;
            String str2 = obj2 instanceof String ? (String) obj2 : null;
            rowDataPdfArr[1] = new FormC1AdministrationComplete.RowDataPdf(rowDataPdfType2, "B.", str2 == null ? "" : str2, null, null, Integer.valueOf(getSuratSuaraTidakSahFinal()), null, 64, null);
            FormC1AdministrationComplete.RowDataPdfType rowDataPdfType3 = FormC1AdministrationComplete.RowDataPdfType.content;
            Object obj3 = hashMap != null ? hashMap.get("vc") : null;
            String str3 = obj3 instanceof String ? (String) obj3 : null;
            rowDataPdfArr[2] = new FormC1AdministrationComplete.RowDataPdf(rowDataPdfType3, "C.", str3 == null ? "" : str3, null, null, Integer.valueOf(getTotalSuratSuaraFinal()), null, 64, null);
            rows.addAll(CollectionsKt.listOf((Object[]) rowDataPdfArr));
            return CollectionsKt.listOf(sectionDataPdf);
        } finally {
        }
    }
}
