package org.informatika.sirekap.model;

import android.content.Context;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.informatika.sirekap.R;

/* compiled from: FormC1Administration.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001:\u0003DEFB5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u000bHÆ\u0003JC\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010 \u001a\u00020!J\u0006\u0010\"\u001a\u00020!J\u0006\u0010#\u001a\u00020!J\u0006\u0010$\u001a\u00020!J\u0006\u0010%\u001a\u00020!J\u0006\u0010&\u001a\u00020!J\u0006\u0010'\u001a\u00020!J\u0006\u0010(\u001a\u00020!J\u0006\u0010)\u001a\u00020!J\u0006\u0010*\u001a\u00020!J\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020-0,2\u0006\u0010.\u001a\u00020/J\u0006\u00100\u001a\u00020!J\u0006\u00101\u001a\u00020!J\u0006\u00102\u001a\u00020!J\u0006\u00103\u001a\u00020!J\u0006\u00104\u001a\u00020!J\u0006\u00105\u001a\u00020!J\u0006\u00106\u001a\u00020!J\u0006\u00107\u001a\u00020!J\u0006\u00108\u001a\u00020!J\u0006\u00109\u001a\u00020!J\u0006\u0010:\u001a\u00020!J\u0006\u0010;\u001a\u00020!J\u0006\u0010<\u001a\u00020!J\u0006\u0010=\u001a\u00020!J\u0006\u0010>\u001a\u00020!J\t\u0010?\u001a\u00020!HÖ\u0001J\u0006\u0010@\u001a\u00020\u001eJ\u0006\u0010A\u001a\u00020\u001eJ\t\u0010B\u001a\u00020CHÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006G"}, d2 = {"Lorg/informatika/sirekap/model/FormC1AdministrationComplete;", "", "electionPage", "Lorg/informatika/sirekap/model/ElectionPage;", "form", "Lorg/informatika/sirekap/model/FormC1Administration;", "error", "Lorg/informatika/sirekap/model/FormC1Error;", "kesesuaian", "Lorg/informatika/sirekap/model/FormC1Kesesuaian;", "kesesuaianAdministration", "Lorg/informatika/sirekap/model/FormC1KesesuaianAdministration;", "(Lorg/informatika/sirekap/model/ElectionPage;Lorg/informatika/sirekap/model/FormC1Administration;Lorg/informatika/sirekap/model/FormC1Error;Lorg/informatika/sirekap/model/FormC1Kesesuaian;Lorg/informatika/sirekap/model/FormC1KesesuaianAdministration;)V", "getElectionPage", "()Lorg/informatika/sirekap/model/ElectionPage;", "getError", "()Lorg/informatika/sirekap/model/FormC1Error;", "getForm", "()Lorg/informatika/sirekap/model/FormC1Administration;", "getKesesuaian", "()Lorg/informatika/sirekap/model/FormC1Kesesuaian;", "getKesesuaianAdministration", "()Lorg/informatika/sirekap/model/FormC1KesesuaianAdministration;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "getPemilihDisabilitas_LFinal", "", "getPemilihDisabilitas_PFinal", "getPemilihDpt_LFinal", "getPemilihDpt_PFinal", "getPenggunaDpk_LFinal", "getPenggunaDpk_PFinal", "getPenggunaDpt_LFinal", "getPenggunaDpt_PFinal", "getPenggunaDptb_LFinal", "getPenggunaDptb_PFinal", "getSalinanData", "", "Lorg/informatika/sirekap/model/FormC1AdministrationComplete$SectionDataPdf;", "context", "Landroid/content/Context;", "getSuratDigunakanFinal", "getSuratDikembalikanFinal", "getSuratDiterimaFinal", "getSuratKembaliPPLNFinal", "getSuratTidakDigunakanFinal", "getSuratTidakDikembalikanFinal", "getSuratTidakTerpakaiFinal", "getTotalPemilihDisabilitasFinal", "getTotalPemilihDptFinal", "getTotalPenggunaDpkFinal", "getTotalPenggunaDptFinal", "getTotalPenggunaDptbFinal", "getTotalPenggunaFinal", "getTotalPengguna_LFinal", "getTotalPengguna_PFinal", "hashCode", "isCheckedAll", "isVerified", "toString", "", "RowDataPdf", "RowDataPdfType", "SectionDataPdf", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormC1AdministrationComplete {
    private final ElectionPage electionPage;
    private final FormC1Error error;
    private final FormC1Administration form;
    private final FormC1Kesesuaian kesesuaian;
    private final FormC1KesesuaianAdministration kesesuaianAdministration;

    /* compiled from: FormC1Administration.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lorg/informatika/sirekap/model/FormC1AdministrationComplete$RowDataPdfType;", "", "(Ljava/lang/String;I)V", "header", FirebaseAnalytics.Param.CONTENT, "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public enum RowDataPdfType {
        header,
        content
    }

    public static /* synthetic */ FormC1AdministrationComplete copy$default(FormC1AdministrationComplete formC1AdministrationComplete, ElectionPage electionPage, FormC1Administration formC1Administration, FormC1Error formC1Error, FormC1Kesesuaian formC1Kesesuaian, FormC1KesesuaianAdministration formC1KesesuaianAdministration, int i, Object obj) {
        if ((i & 1) != 0) {
            electionPage = formC1AdministrationComplete.electionPage;
        }
        if ((i & 2) != 0) {
            formC1Administration = formC1AdministrationComplete.form;
        }
        FormC1Administration formC1Administration2 = formC1Administration;
        if ((i & 4) != 0) {
            formC1Error = formC1AdministrationComplete.error;
        }
        FormC1Error formC1Error2 = formC1Error;
        if ((i & 8) != 0) {
            formC1Kesesuaian = formC1AdministrationComplete.kesesuaian;
        }
        FormC1Kesesuaian formC1Kesesuaian2 = formC1Kesesuaian;
        if ((i & 16) != 0) {
            formC1KesesuaianAdministration = formC1AdministrationComplete.kesesuaianAdministration;
        }
        return formC1AdministrationComplete.copy(electionPage, formC1Administration2, formC1Error2, formC1Kesesuaian2, formC1KesesuaianAdministration);
    }

    public final ElectionPage component1() {
        return this.electionPage;
    }

    public final FormC1Administration component2() {
        return this.form;
    }

    public final FormC1Error component3() {
        return this.error;
    }

    public final FormC1Kesesuaian component4() {
        return this.kesesuaian;
    }

    public final FormC1KesesuaianAdministration component5() {
        return this.kesesuaianAdministration;
    }

    public final FormC1AdministrationComplete copy(ElectionPage electionPage, FormC1Administration formC1Administration, FormC1Error formC1Error, FormC1Kesesuaian formC1Kesesuaian, FormC1KesesuaianAdministration formC1KesesuaianAdministration) {
        Intrinsics.checkNotNullParameter(electionPage, "electionPage");
        return new FormC1AdministrationComplete(electionPage, formC1Administration, formC1Error, formC1Kesesuaian, formC1KesesuaianAdministration);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FormC1AdministrationComplete) {
            FormC1AdministrationComplete formC1AdministrationComplete = (FormC1AdministrationComplete) obj;
            return Intrinsics.areEqual(this.electionPage, formC1AdministrationComplete.electionPage) && Intrinsics.areEqual(this.form, formC1AdministrationComplete.form) && Intrinsics.areEqual(this.error, formC1AdministrationComplete.error) && Intrinsics.areEqual(this.kesesuaian, formC1AdministrationComplete.kesesuaian) && Intrinsics.areEqual(this.kesesuaianAdministration, formC1AdministrationComplete.kesesuaianAdministration);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.electionPage.hashCode() * 31;
        FormC1Administration formC1Administration = this.form;
        int hashCode2 = (hashCode + (formC1Administration == null ? 0 : formC1Administration.hashCode())) * 31;
        FormC1Error formC1Error = this.error;
        int hashCode3 = (hashCode2 + (formC1Error == null ? 0 : formC1Error.hashCode())) * 31;
        FormC1Kesesuaian formC1Kesesuaian = this.kesesuaian;
        int hashCode4 = (hashCode3 + (formC1Kesesuaian == null ? 0 : formC1Kesesuaian.hashCode())) * 31;
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        return hashCode4 + (formC1KesesuaianAdministration != null ? formC1KesesuaianAdministration.hashCode() : 0);
    }

    public String toString() {
        ElectionPage electionPage = this.electionPage;
        FormC1Administration formC1Administration = this.form;
        FormC1Error formC1Error = this.error;
        FormC1Kesesuaian formC1Kesesuaian = this.kesesuaian;
        return "FormC1AdministrationComplete(electionPage=" + electionPage + ", form=" + formC1Administration + ", error=" + formC1Error + ", kesesuaian=" + formC1Kesesuaian + ", kesesuaianAdministration=" + this.kesesuaianAdministration + ")";
    }

    public FormC1AdministrationComplete(ElectionPage electionPage, FormC1Administration formC1Administration, FormC1Error formC1Error, FormC1Kesesuaian formC1Kesesuaian, FormC1KesesuaianAdministration formC1KesesuaianAdministration) {
        Intrinsics.checkNotNullParameter(electionPage, "electionPage");
        this.electionPage = electionPage;
        this.form = formC1Administration;
        this.error = formC1Error;
        this.kesesuaian = formC1Kesesuaian;
        this.kesesuaianAdministration = formC1KesesuaianAdministration;
    }

    public final ElectionPage getElectionPage() {
        return this.electionPage;
    }

    public final FormC1Administration getForm() {
        return this.form;
    }

    public final FormC1Error getError() {
        return this.error;
    }

    public final FormC1Kesesuaian getKesesuaian() {
        return this.kesesuaian;
    }

    public final FormC1KesesuaianAdministration getKesesuaianAdministration() {
        return this.kesesuaianAdministration;
    }

    public final boolean isVerified() {
        return this.kesesuaian != null;
    }

    public final int getPemilihDpt_LFinal() {
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getPemilihDpt_L(), (Object) false) : false) {
            Integer pemilihDpt_LCorrected = this.kesesuaianAdministration.getPemilihDpt_LCorrected();
            if (pemilihDpt_LCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                pemilihDpt_LCorrected = formC1Administration != null ? Integer.valueOf(formC1Administration.getPemilihDpt_L()) : null;
                if (pemilihDpt_LCorrected == null) {
                    return 0;
                }
            }
            return pemilihDpt_LCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 != null) {
            return formC1Administration2.getPemilihDpt_L();
        }
        return 0;
    }

    public final int getPemilihDpt_PFinal() {
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getPemilihDpt_P(), (Object) false) : false) {
            Integer pemilihDpt_PCorrected = this.kesesuaianAdministration.getPemilihDpt_PCorrected();
            if (pemilihDpt_PCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                pemilihDpt_PCorrected = formC1Administration != null ? Integer.valueOf(formC1Administration.getPemilihDpt_P()) : null;
                if (pemilihDpt_PCorrected == null) {
                    return 0;
                }
            }
            return pemilihDpt_PCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 != null) {
            return formC1Administration2.getPemilihDpt_P();
        }
        return 0;
    }

    public final int getTotalPemilihDptFinal() {
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getTotalPemilihDpt(), (Object) false) : false) {
            Integer totalPemilihDptCorrected = this.kesesuaianAdministration.getTotalPemilihDptCorrected();
            if (totalPemilihDptCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                totalPemilihDptCorrected = formC1Administration != null ? Integer.valueOf(formC1Administration.getTotalPemilihDpt()) : null;
                if (totalPemilihDptCorrected == null) {
                    return 0;
                }
            }
            return totalPemilihDptCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 != null) {
            return formC1Administration2.getTotalPemilihDpt();
        }
        return 0;
    }

    public final int getPenggunaDpt_LFinal() {
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getPenggunaDpt_L(), (Object) false) : false) {
            Integer penggunaDpt_LCorrected = this.kesesuaianAdministration.getPenggunaDpt_LCorrected();
            if (penggunaDpt_LCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                penggunaDpt_LCorrected = formC1Administration != null ? Integer.valueOf(formC1Administration.getPenggunaDpt_L()) : null;
                if (penggunaDpt_LCorrected == null) {
                    return 0;
                }
            }
            return penggunaDpt_LCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 != null) {
            return formC1Administration2.getPenggunaDpt_L();
        }
        return 0;
    }

    public final int getPenggunaDpt_PFinal() {
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getPenggunaDpt_P(), (Object) false) : false) {
            Integer penggunaDpt_PCorrected = this.kesesuaianAdministration.getPenggunaDpt_PCorrected();
            if (penggunaDpt_PCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                penggunaDpt_PCorrected = formC1Administration != null ? Integer.valueOf(formC1Administration.getPenggunaDpt_P()) : null;
                if (penggunaDpt_PCorrected == null) {
                    return 0;
                }
            }
            return penggunaDpt_PCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 != null) {
            return formC1Administration2.getPenggunaDpt_P();
        }
        return 0;
    }

    public final int getTotalPenggunaDptFinal() {
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getTotalPenggunaDpt(), (Object) false) : false) {
            Integer totalPenggunaDptCorrected = this.kesesuaianAdministration.getTotalPenggunaDptCorrected();
            if (totalPenggunaDptCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                totalPenggunaDptCorrected = formC1Administration != null ? Integer.valueOf(formC1Administration.getTotalPenggunaDpt()) : null;
                if (totalPenggunaDptCorrected == null) {
                    return 0;
                }
            }
            return totalPenggunaDptCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 != null) {
            return formC1Administration2.getTotalPenggunaDpt();
        }
        return 0;
    }

    public final int getPenggunaDptb_LFinal() {
        Integer penggunaDptb_L;
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getPenggunaDptb_L(), (Object) false) : false) {
            Integer penggunaDptb_LCorrected = this.kesesuaianAdministration.getPenggunaDptb_LCorrected();
            if (penggunaDptb_LCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                penggunaDptb_LCorrected = formC1Administration != null ? formC1Administration.getPenggunaDptb_L() : null;
                if (penggunaDptb_LCorrected == null) {
                    return 0;
                }
            }
            return penggunaDptb_LCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 == null || (penggunaDptb_L = formC1Administration2.getPenggunaDptb_L()) == null) {
            return 0;
        }
        return penggunaDptb_L.intValue();
    }

    public final int getPenggunaDptb_PFinal() {
        Integer penggunaDptb_P;
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getPenggunaDptb_P(), (Object) false) : false) {
            Integer penggunaDptb_PCorrected = this.kesesuaianAdministration.getPenggunaDptb_PCorrected();
            if (penggunaDptb_PCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                penggunaDptb_PCorrected = formC1Administration != null ? formC1Administration.getPenggunaDptb_P() : null;
                if (penggunaDptb_PCorrected == null) {
                    return 0;
                }
            }
            return penggunaDptb_PCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 == null || (penggunaDptb_P = formC1Administration2.getPenggunaDptb_P()) == null) {
            return 0;
        }
        return penggunaDptb_P.intValue();
    }

    public final int getTotalPenggunaDptbFinal() {
        Integer totalPenggunaDptb;
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getTotalPenggunaDptb(), (Object) false) : false) {
            Integer totalPenggunaDptbCorrected = this.kesesuaianAdministration.getTotalPenggunaDptbCorrected();
            if (totalPenggunaDptbCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                totalPenggunaDptbCorrected = formC1Administration != null ? formC1Administration.getTotalPenggunaDptb() : null;
                if (totalPenggunaDptbCorrected == null) {
                    return 0;
                }
            }
            return totalPenggunaDptbCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 == null || (totalPenggunaDptb = formC1Administration2.getTotalPenggunaDptb()) == null) {
            return 0;
        }
        return totalPenggunaDptb.intValue();
    }

    public final int getPenggunaDpk_LFinal() {
        Integer penggunaDpk_L;
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getPenggunaDpk_L(), (Object) false) : false) {
            Integer penggunaDpk_LCorrected = this.kesesuaianAdministration.getPenggunaDpk_LCorrected();
            if (penggunaDpk_LCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                penggunaDpk_LCorrected = formC1Administration != null ? formC1Administration.getPenggunaDpk_L() : null;
                if (penggunaDpk_LCorrected == null) {
                    return 0;
                }
            }
            return penggunaDpk_LCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 == null || (penggunaDpk_L = formC1Administration2.getPenggunaDpk_L()) == null) {
            return 0;
        }
        return penggunaDpk_L.intValue();
    }

    public final int getPenggunaDpk_PFinal() {
        Integer penggunaDpk_P;
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getPenggunaDpk_P(), (Object) false) : false) {
            Integer penggunaDpk_PCorrected = this.kesesuaianAdministration.getPenggunaDpk_PCorrected();
            if (penggunaDpk_PCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                penggunaDpk_PCorrected = formC1Administration != null ? formC1Administration.getPenggunaDpk_P() : null;
                if (penggunaDpk_PCorrected == null) {
                    return 0;
                }
            }
            return penggunaDpk_PCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 == null || (penggunaDpk_P = formC1Administration2.getPenggunaDpk_P()) == null) {
            return 0;
        }
        return penggunaDpk_P.intValue();
    }

    public final int getTotalPenggunaDpkFinal() {
        Integer totalPenggunaDpk;
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getTotalPenggunaDpk(), (Object) false) : false) {
            Integer totalPenggunaDpkCorrected = this.kesesuaianAdministration.getTotalPenggunaDpkCorrected();
            if (totalPenggunaDpkCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                totalPenggunaDpkCorrected = formC1Administration != null ? formC1Administration.getTotalPenggunaDpk() : null;
                if (totalPenggunaDpkCorrected == null) {
                    return 0;
                }
            }
            return totalPenggunaDpkCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 == null || (totalPenggunaDpk = formC1Administration2.getTotalPenggunaDpk()) == null) {
            return 0;
        }
        return totalPenggunaDpk.intValue();
    }

    public final int getTotalPengguna_LFinal() {
        Integer totalPengguna_L;
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getTotalPengguna_L(), (Object) false) : false) {
            Integer totalPengguna_LCorrected = this.kesesuaianAdministration.getTotalPengguna_LCorrected();
            if (totalPengguna_LCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                totalPengguna_LCorrected = formC1Administration != null ? formC1Administration.getTotalPengguna_L() : null;
                if (totalPengguna_LCorrected == null) {
                    return 0;
                }
            }
            return totalPengguna_LCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 == null || (totalPengguna_L = formC1Administration2.getTotalPengguna_L()) == null) {
            return 0;
        }
        return totalPengguna_L.intValue();
    }

    public final int getTotalPengguna_PFinal() {
        Integer totalPengguna_P;
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getTotalPengguna_P(), (Object) false) : false) {
            Integer totalPengguna_PCorrected = this.kesesuaianAdministration.getTotalPengguna_PCorrected();
            if (totalPengguna_PCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                totalPengguna_PCorrected = formC1Administration != null ? formC1Administration.getTotalPengguna_P() : null;
                if (totalPengguna_PCorrected == null) {
                    return 0;
                }
            }
            return totalPengguna_PCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 == null || (totalPengguna_P = formC1Administration2.getTotalPengguna_P()) == null) {
            return 0;
        }
        return totalPengguna_P.intValue();
    }

    public final int getTotalPenggunaFinal() {
        Integer totalPengguna;
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getTotalPengguna(), (Object) false) : false) {
            Integer totalPenggunaCorrected = this.kesesuaianAdministration.getTotalPenggunaCorrected();
            if (totalPenggunaCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                totalPenggunaCorrected = formC1Administration != null ? formC1Administration.getTotalPengguna() : null;
                if (totalPenggunaCorrected == null) {
                    return 0;
                }
            }
            return totalPenggunaCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 == null || (totalPengguna = formC1Administration2.getTotalPengguna()) == null) {
            return 0;
        }
        return totalPengguna.intValue();
    }

    public final int getSuratDiterimaFinal() {
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getSuratDiterima(), (Object) false) : false) {
            Integer suratDiterimaCorrected = this.kesesuaianAdministration.getSuratDiterimaCorrected();
            if (suratDiterimaCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                suratDiterimaCorrected = formC1Administration != null ? Integer.valueOf(formC1Administration.getSuratDiterima()) : null;
                if (suratDiterimaCorrected == null) {
                    return 0;
                }
            }
            return suratDiterimaCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 != null) {
            return formC1Administration2.getSuratDiterima();
        }
        return 0;
    }

    public final int getSuratDikembalikanFinal() {
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getSuratDikembalikan(), (Object) false) : false) {
            Integer suratDikembalikanCorrected = this.kesesuaianAdministration.getSuratDikembalikanCorrected();
            if (suratDikembalikanCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                suratDikembalikanCorrected = formC1Administration != null ? Integer.valueOf(formC1Administration.getSuratDikembalikan()) : null;
                if (suratDikembalikanCorrected == null) {
                    return 0;
                }
            }
            return suratDikembalikanCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 != null) {
            return formC1Administration2.getSuratDikembalikan();
        }
        return 0;
    }

    public final int getSuratTidakDigunakanFinal() {
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getSuratTidakDigunakan(), (Object) false) : false) {
            Integer suratTidakDigunakanCorrected = this.kesesuaianAdministration.getSuratTidakDigunakanCorrected();
            if (suratTidakDigunakanCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                suratTidakDigunakanCorrected = formC1Administration != null ? Integer.valueOf(formC1Administration.getSuratTidakDigunakan()) : null;
                if (suratTidakDigunakanCorrected == null) {
                    return 0;
                }
            }
            return suratTidakDigunakanCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 != null) {
            return formC1Administration2.getSuratTidakDigunakan();
        }
        return 0;
    }

    public final int getSuratDigunakanFinal() {
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getSuratDigunakan(), (Object) false) : false) {
            Integer suratDigunakanCorrected = this.kesesuaianAdministration.getSuratDigunakanCorrected();
            if (suratDigunakanCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                suratDigunakanCorrected = formC1Administration != null ? Integer.valueOf(formC1Administration.getSuratDigunakan()) : null;
                if (suratDigunakanCorrected == null) {
                    return 0;
                }
            }
            return suratDigunakanCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 != null) {
            return formC1Administration2.getSuratDigunakan();
        }
        return 0;
    }

    public final int getSuratKembaliPPLNFinal() {
        Integer suratKembaliPPLN;
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getSuratKembaliPPLN(), (Object) false) : false) {
            Integer suratKembaliPPLNCorrected = this.kesesuaianAdministration.getSuratKembaliPPLNCorrected();
            if (suratKembaliPPLNCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                suratKembaliPPLNCorrected = formC1Administration != null ? formC1Administration.getSuratKembaliPPLN() : null;
                if (suratKembaliPPLNCorrected == null) {
                    return 0;
                }
            }
            return suratKembaliPPLNCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 == null || (suratKembaliPPLN = formC1Administration2.getSuratKembaliPPLN()) == null) {
            return 0;
        }
        return suratKembaliPPLN.intValue();
    }

    public final int getSuratTidakTerpakaiFinal() {
        Integer suratTidakTerpakai;
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getSuratTidakTerpakai(), (Object) false) : false) {
            Integer suratTidakTerpakaiCorrected = this.kesesuaianAdministration.getSuratTidakTerpakaiCorrected();
            if (suratTidakTerpakaiCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                suratTidakTerpakaiCorrected = formC1Administration != null ? formC1Administration.getSuratTidakTerpakai() : null;
                if (suratTidakTerpakaiCorrected == null) {
                    return 0;
                }
            }
            return suratTidakTerpakaiCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 == null || (suratTidakTerpakai = formC1Administration2.getSuratTidakTerpakai()) == null) {
            return 0;
        }
        return suratTidakTerpakai.intValue();
    }

    public final int getSuratTidakDikembalikanFinal() {
        Integer suratTidakDikembalikan;
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getSuratTidakDikembalikan(), (Object) false) : false) {
            Integer suratTidakDikembalikanCorrected = this.kesesuaianAdministration.getSuratTidakDikembalikanCorrected();
            if (suratTidakDikembalikanCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                suratTidakDikembalikanCorrected = formC1Administration != null ? formC1Administration.getSuratTidakDikembalikan() : null;
                if (suratTidakDikembalikanCorrected == null) {
                    return 0;
                }
            }
            return suratTidakDikembalikanCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 == null || (suratTidakDikembalikan = formC1Administration2.getSuratTidakDikembalikan()) == null) {
            return 0;
        }
        return suratTidakDikembalikan.intValue();
    }

    public final int getPemilihDisabilitas_LFinal() {
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getPemilihDisabilitas_L(), (Object) false) : false) {
            Integer pemilihDisabilitas_LCorrected = this.kesesuaianAdministration.getPemilihDisabilitas_LCorrected();
            if (pemilihDisabilitas_LCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                pemilihDisabilitas_LCorrected = formC1Administration != null ? Integer.valueOf(formC1Administration.getPemilihDisabilitas_L()) : null;
                if (pemilihDisabilitas_LCorrected == null) {
                    return 0;
                }
            }
            return pemilihDisabilitas_LCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 != null) {
            return formC1Administration2.getPemilihDisabilitas_L();
        }
        return 0;
    }

    public final int getPemilihDisabilitas_PFinal() {
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getPemilihDisabilitas_P(), (Object) false) : false) {
            Integer pemilihDisabilitas_PCorrected = this.kesesuaianAdministration.getPemilihDisabilitas_PCorrected();
            if (pemilihDisabilitas_PCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                pemilihDisabilitas_PCorrected = formC1Administration != null ? Integer.valueOf(formC1Administration.getPemilihDisabilitas_P()) : null;
                if (pemilihDisabilitas_PCorrected == null) {
                    return 0;
                }
            }
            return pemilihDisabilitas_PCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 != null) {
            return formC1Administration2.getPemilihDisabilitas_P();
        }
        return 0;
    }

    public final int getTotalPemilihDisabilitasFinal() {
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        if (formC1KesesuaianAdministration != null ? Intrinsics.areEqual((Object) formC1KesesuaianAdministration.getTotalPemilihDisabilitas(), (Object) false) : false) {
            Integer totalPemilihDisabilitasCorrected = this.kesesuaianAdministration.getTotalPemilihDisabilitasCorrected();
            if (totalPemilihDisabilitasCorrected == null) {
                FormC1Administration formC1Administration = this.form;
                totalPemilihDisabilitasCorrected = formC1Administration != null ? Integer.valueOf(formC1Administration.getTotalPemilihDisabilitas()) : null;
                if (totalPemilihDisabilitasCorrected == null) {
                    return 0;
                }
            }
            return totalPemilihDisabilitasCorrected.intValue();
        }
        FormC1Administration formC1Administration2 = this.form;
        if (formC1Administration2 != null) {
            return formC1Administration2.getTotalPemilihDisabilitas();
        }
        return 0;
    }

    public final boolean isCheckedAll() {
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.kesesuaianAdministration;
        return formC1KesesuaianAdministration != null && formC1KesesuaianAdministration.isCheckedAll();
    }

    /* compiled from: FormC1Administration.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003JO\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006!"}, d2 = {"Lorg/informatika/sirekap/model/FormC1AdministrationComplete$SectionDataPdf;", "", "number", "", "title", "rows", "", "Lorg/informatika/sirekap/model/FormC1AdministrationComplete$RowDataPdf;", "totalLabel", "maleLabel", "femaleLabel", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFemaleLabel", "()Ljava/lang/String;", "getMaleLabel", "getNumber", "getRows", "()Ljava/util/List;", "getTitle", "getTotalLabel", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class SectionDataPdf {
        private final String femaleLabel;
        private final String maleLabel;
        private final String number;
        private final List<RowDataPdf> rows;
        private final String title;
        private final String totalLabel;

        public static /* synthetic */ SectionDataPdf copy$default(SectionDataPdf sectionDataPdf, String str, String str2, List list, String str3, String str4, String str5, int i, Object obj) {
            if ((i & 1) != 0) {
                str = sectionDataPdf.number;
            }
            if ((i & 2) != 0) {
                str2 = sectionDataPdf.title;
            }
            String str6 = str2;
            List<RowDataPdf> list2 = list;
            if ((i & 4) != 0) {
                list2 = sectionDataPdf.rows;
            }
            List list3 = list2;
            if ((i & 8) != 0) {
                str3 = sectionDataPdf.totalLabel;
            }
            String str7 = str3;
            if ((i & 16) != 0) {
                str4 = sectionDataPdf.maleLabel;
            }
            String str8 = str4;
            if ((i & 32) != 0) {
                str5 = sectionDataPdf.femaleLabel;
            }
            return sectionDataPdf.copy(str, str6, list3, str7, str8, str5);
        }

        public final String component1() {
            return this.number;
        }

        public final String component2() {
            return this.title;
        }

        public final List<RowDataPdf> component3() {
            return this.rows;
        }

        public final String component4() {
            return this.totalLabel;
        }

        public final String component5() {
            return this.maleLabel;
        }

        public final String component6() {
            return this.femaleLabel;
        }

        public final SectionDataPdf copy(String number, String title, List<RowDataPdf> rows, String totalLabel, String str, String str2) {
            Intrinsics.checkNotNullParameter(number, "number");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(rows, "rows");
            Intrinsics.checkNotNullParameter(totalLabel, "totalLabel");
            return new SectionDataPdf(number, title, rows, totalLabel, str, str2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof SectionDataPdf) {
                SectionDataPdf sectionDataPdf = (SectionDataPdf) obj;
                return Intrinsics.areEqual(this.number, sectionDataPdf.number) && Intrinsics.areEqual(this.title, sectionDataPdf.title) && Intrinsics.areEqual(this.rows, sectionDataPdf.rows) && Intrinsics.areEqual(this.totalLabel, sectionDataPdf.totalLabel) && Intrinsics.areEqual(this.maleLabel, sectionDataPdf.maleLabel) && Intrinsics.areEqual(this.femaleLabel, sectionDataPdf.femaleLabel);
            }
            return false;
        }

        public int hashCode() {
            int hashCode = ((((((this.number.hashCode() * 31) + this.title.hashCode()) * 31) + this.rows.hashCode()) * 31) + this.totalLabel.hashCode()) * 31;
            String str = this.maleLabel;
            int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.femaleLabel;
            return hashCode2 + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            String str = this.number;
            String str2 = this.title;
            List<RowDataPdf> list = this.rows;
            String str3 = this.totalLabel;
            String str4 = this.maleLabel;
            return "SectionDataPdf(number=" + str + ", title=" + str2 + ", rows=" + list + ", totalLabel=" + str3 + ", maleLabel=" + str4 + ", femaleLabel=" + this.femaleLabel + ")";
        }

        public SectionDataPdf(String number, String title, List<RowDataPdf> rows, String totalLabel, String str, String str2) {
            Intrinsics.checkNotNullParameter(number, "number");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(rows, "rows");
            Intrinsics.checkNotNullParameter(totalLabel, "totalLabel");
            this.number = number;
            this.title = title;
            this.rows = rows;
            this.totalLabel = totalLabel;
            this.maleLabel = str;
            this.femaleLabel = str2;
        }

        public final String getNumber() {
            return this.number;
        }

        public final String getTitle() {
            return this.title;
        }

        public /* synthetic */ SectionDataPdf(String str, String str2, ArrayList arrayList, String str3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i & 4) != 0 ? new ArrayList() : arrayList, str3, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : str5);
        }

        public final List<RowDataPdf> getRows() {
            return this.rows;
        }

        public final String getTotalLabel() {
            return this.totalLabel;
        }

        public final String getMaleLabel() {
            return this.maleLabel;
        }

        public final String getFemaleLabel() {
            return this.femaleLabel;
        }
    }

    /* compiled from: FormC1Administration.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001c\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u001f\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010 \u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010!\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010\u0011J^\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u0010#J\u0013\u0010$\u001a\u00020\f2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\bHÖ\u0001J\t\u0010'\u001a\u00020\u0005HÖ\u0001R\u0015\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u000b\u0010\u0011R\u0011\u0010\u0013\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0015\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0015\u0010\n\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0018\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006("}, d2 = {"Lorg/informatika/sirekap/model/FormC1AdministrationComplete$RowDataPdf;", "", "type", "Lorg/informatika/sirekap/model/FormC1AdministrationComplete$RowDataPdfType;", "number", "", "title", "male", "", "female", "total", "isSesuai", "", "(Lorg/informatika/sirekap/model/FormC1AdministrationComplete$RowDataPdfType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;)V", "getFemale", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "isSesuaiFormatted", "()Ljava/lang/String;", "getMale", "getNumber", "getTitle", "getTotal", "getType", "()Lorg/informatika/sirekap/model/FormC1AdministrationComplete$RowDataPdfType;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Lorg/informatika/sirekap/model/FormC1AdministrationComplete$RowDataPdfType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;)Lorg/informatika/sirekap/model/FormC1AdministrationComplete$RowDataPdf;", "equals", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class RowDataPdf {
        private final Integer female;
        private final Boolean isSesuai;
        private final Integer male;
        private final String number;
        private final String title;
        private final Integer total;
        private final RowDataPdfType type;

        public static /* synthetic */ RowDataPdf copy$default(RowDataPdf rowDataPdf, RowDataPdfType rowDataPdfType, String str, String str2, Integer num, Integer num2, Integer num3, Boolean bool, int i, Object obj) {
            if ((i & 1) != 0) {
                rowDataPdfType = rowDataPdf.type;
            }
            if ((i & 2) != 0) {
                str = rowDataPdf.number;
            }
            String str3 = str;
            if ((i & 4) != 0) {
                str2 = rowDataPdf.title;
            }
            String str4 = str2;
            if ((i & 8) != 0) {
                num = rowDataPdf.male;
            }
            Integer num4 = num;
            if ((i & 16) != 0) {
                num2 = rowDataPdf.female;
            }
            Integer num5 = num2;
            if ((i & 32) != 0) {
                num3 = rowDataPdf.total;
            }
            Integer num6 = num3;
            if ((i & 64) != 0) {
                bool = rowDataPdf.isSesuai;
            }
            return rowDataPdf.copy(rowDataPdfType, str3, str4, num4, num5, num6, bool);
        }

        public final RowDataPdfType component1() {
            return this.type;
        }

        public final String component2() {
            return this.number;
        }

        public final String component3() {
            return this.title;
        }

        public final Integer component4() {
            return this.male;
        }

        public final Integer component5() {
            return this.female;
        }

        public final Integer component6() {
            return this.total;
        }

        public final Boolean component7() {
            return this.isSesuai;
        }

        public final RowDataPdf copy(RowDataPdfType type, String str, String title, Integer num, Integer num2, Integer num3, Boolean bool) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(title, "title");
            return new RowDataPdf(type, str, title, num, num2, num3, bool);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof RowDataPdf) {
                RowDataPdf rowDataPdf = (RowDataPdf) obj;
                return this.type == rowDataPdf.type && Intrinsics.areEqual(this.number, rowDataPdf.number) && Intrinsics.areEqual(this.title, rowDataPdf.title) && Intrinsics.areEqual(this.male, rowDataPdf.male) && Intrinsics.areEqual(this.female, rowDataPdf.female) && Intrinsics.areEqual(this.total, rowDataPdf.total) && Intrinsics.areEqual(this.isSesuai, rowDataPdf.isSesuai);
            }
            return false;
        }

        public int hashCode() {
            int hashCode = this.type.hashCode() * 31;
            String str = this.number;
            int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.title.hashCode()) * 31;
            Integer num = this.male;
            int hashCode3 = (hashCode2 + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.female;
            int hashCode4 = (hashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
            Integer num3 = this.total;
            int hashCode5 = (hashCode4 + (num3 == null ? 0 : num3.hashCode())) * 31;
            Boolean bool = this.isSesuai;
            return hashCode5 + (bool != null ? bool.hashCode() : 0);
        }

        public String toString() {
            RowDataPdfType rowDataPdfType = this.type;
            String str = this.number;
            String str2 = this.title;
            Integer num = this.male;
            Integer num2 = this.female;
            Integer num3 = this.total;
            return "RowDataPdf(type=" + rowDataPdfType + ", number=" + str + ", title=" + str2 + ", male=" + num + ", female=" + num2 + ", total=" + num3 + ", isSesuai=" + this.isSesuai + ")";
        }

        public RowDataPdf(RowDataPdfType type, String str, String title, Integer num, Integer num2, Integer num3, Boolean bool) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(title, "title");
            this.type = type;
            this.number = str;
            this.title = title;
            this.male = num;
            this.female = num2;
            this.total = num3;
            this.isSesuai = bool;
        }

        public /* synthetic */ RowDataPdf(RowDataPdfType rowDataPdfType, String str, String str2, Integer num, Integer num2, Integer num3, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(rowDataPdfType, str, str2, (i & 8) != 0 ? null : num, (i & 16) != 0 ? null : num2, (i & 32) != 0 ? null : num3, (i & 64) != 0 ? null : bool);
        }

        public final RowDataPdfType getType() {
            return this.type;
        }

        public final String getNumber() {
            return this.number;
        }

        public final String getTitle() {
            return this.title;
        }

        public final Integer getMale() {
            return this.male;
        }

        public final Integer getFemale() {
            return this.female;
        }

        public final Integer getTotal() {
            return this.total;
        }

        public final Boolean isSesuai() {
            return this.isSesuai;
        }

        public final String isSesuaiFormatted() {
            Boolean bool = this.isSesuai;
            return bool == null ? "-" : bool.booleanValue() ? "Ya" : "Tidak";
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final List<SectionDataPdf> getSalinanData(Context context) {
        int i;
        Intrinsics.checkNotNullParameter(context, "context");
        FormC1Administration formC1Administration = this.form;
        boolean z = formC1Administration != null && formC1Administration.isLnPos();
        FormC1Administration formC1Administration2 = this.form;
        boolean z2 = formC1Administration2 != null && formC1Administration2.isLn();
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
            SectionDataPdf sectionDataPdf = new SectionDataPdf("I.", "DATA PEMILIH DAN PENGGUNA HAK PILIH", null, "Jumlah (L+P)", "L", "P", 4, null);
            if (z) {
                List<RowDataPdf> rows = sectionDataPdf.getRows();
                RowDataPdf[] rowDataPdfArr = new RowDataPdf[7];
                rowDataPdfArr[0] = new RowDataPdf(RowDataPdfType.header, "A.", "DATA PEMILIH", null, null, null, null, 120, null);
                RowDataPdfType rowDataPdfType = RowDataPdfType.content;
                Object obj = hashMap != null ? hashMap.get("ia") : null;
                String str = obj instanceof String ? (String) obj : null;
                rowDataPdfArr[1] = new RowDataPdf(rowDataPdfType, null, str == null ? "" : str, Integer.valueOf(getPemilihDpt_LFinal()), Integer.valueOf(getPemilihDpt_PFinal()), Integer.valueOf(getTotalPemilihDptFinal()), null, 64, null);
                rowDataPdfArr[2] = new RowDataPdf(RowDataPdfType.header, "B.", "PENGGUNA HAK PILIH", null, null, null, null, 120, null);
                RowDataPdfType rowDataPdfType2 = RowDataPdfType.content;
                Object obj2 = hashMap != null ? hashMap.get("ib1") : null;
                String str2 = obj2 instanceof String ? (String) obj2 : null;
                rowDataPdfArr[3] = new RowDataPdf(rowDataPdfType2, "1.", str2 == null ? "" : str2, Integer.valueOf(getPenggunaDpt_LFinal()), Integer.valueOf(getPenggunaDpt_PFinal()), Integer.valueOf(getTotalPenggunaDptFinal()), null, 64, null);
                RowDataPdfType rowDataPdfType3 = RowDataPdfType.content;
                Object obj3 = hashMap != null ? hashMap.get("ib2") : null;
                String str3 = obj3 instanceof String ? (String) obj3 : null;
                rowDataPdfArr[4] = new RowDataPdf(rowDataPdfType3, "2.", str3 == null ? "" : str3, Integer.valueOf(getPenggunaDptb_LFinal()), Integer.valueOf(getPenggunaDptb_PFinal()), Integer.valueOf(getTotalPenggunaDptbFinal()), null, 64, null);
                RowDataPdfType rowDataPdfType4 = RowDataPdfType.content;
                Object obj4 = hashMap != null ? hashMap.get("ib3") : null;
                String str4 = obj4 instanceof String ? (String) obj4 : null;
                rowDataPdfArr[5] = new RowDataPdf(rowDataPdfType4, "3.", str4 == null ? "" : str4, Integer.valueOf(getPenggunaDpk_LFinal()), Integer.valueOf(getPenggunaDpk_PFinal()), Integer.valueOf(getTotalPenggunaDpkFinal()), null, 64, null);
                RowDataPdfType rowDataPdfType5 = RowDataPdfType.content;
                Object obj5 = hashMap != null ? hashMap.get("ib4") : null;
                String str5 = obj5 instanceof String ? (String) obj5 : null;
                rowDataPdfArr[6] = new RowDataPdf(rowDataPdfType5, "4.", str5 == null ? "" : str5, Integer.valueOf(getTotalPengguna_LFinal()), Integer.valueOf(getTotalPengguna_PFinal()), Integer.valueOf(getTotalPenggunaFinal()), null, 64, null);
                rows.addAll(CollectionsKt.listOf((Object[]) rowDataPdfArr));
            } else {
                List<RowDataPdf> rows2 = sectionDataPdf.getRows();
                RowDataPdf[] rowDataPdfArr2 = new RowDataPdf[7];
                rowDataPdfArr2[0] = new RowDataPdf(RowDataPdfType.header, "A.", "DATA PEMILIH", null, null, null, null, 120, null);
                RowDataPdfType rowDataPdfType6 = RowDataPdfType.content;
                Object obj6 = hashMap != null ? hashMap.get("ia") : null;
                String str6 = obj6 instanceof String ? (String) obj6 : null;
                rowDataPdfArr2[1] = new RowDataPdf(rowDataPdfType6, null, str6 == null ? "" : str6, Integer.valueOf(getPemilihDpt_LFinal()), Integer.valueOf(getPemilihDpt_PFinal()), Integer.valueOf(getTotalPemilihDptFinal()), null, 64, null);
                rowDataPdfArr2[2] = new RowDataPdf(RowDataPdfType.header, "B.", "PENGGUNA HAK PILIH", null, null, null, null, 120, null);
                RowDataPdfType rowDataPdfType7 = RowDataPdfType.content;
                Object obj7 = hashMap != null ? hashMap.get("ib1") : null;
                String str7 = obj7 instanceof String ? (String) obj7 : null;
                rowDataPdfArr2[3] = new RowDataPdf(rowDataPdfType7, "1.", str7 == null ? "" : str7, Integer.valueOf(getPenggunaDpt_LFinal()), Integer.valueOf(getPenggunaDpt_PFinal()), Integer.valueOf(getTotalPenggunaDptFinal()), null, 64, null);
                RowDataPdfType rowDataPdfType8 = RowDataPdfType.content;
                Object obj8 = hashMap != null ? hashMap.get("ib2") : null;
                String str8 = obj8 instanceof String ? (String) obj8 : null;
                rowDataPdfArr2[4] = new RowDataPdf(rowDataPdfType8, "2.", str8 == null ? "" : str8, Integer.valueOf(getPenggunaDptb_LFinal()), Integer.valueOf(getPenggunaDptb_PFinal()), Integer.valueOf(getTotalPenggunaDptbFinal()), null, 64, null);
                RowDataPdfType rowDataPdfType9 = RowDataPdfType.content;
                Object obj9 = hashMap != null ? hashMap.get("ib3") : null;
                String str9 = obj9 instanceof String ? (String) obj9 : null;
                rowDataPdfArr2[5] = new RowDataPdf(rowDataPdfType9, "3.", str9 == null ? "" : str9, Integer.valueOf(getPenggunaDpk_LFinal()), Integer.valueOf(getPenggunaDpk_PFinal()), Integer.valueOf(getTotalPenggunaDpkFinal()), null, 64, null);
                RowDataPdfType rowDataPdfType10 = RowDataPdfType.content;
                Object obj10 = hashMap != null ? hashMap.get("ib4") : null;
                String str10 = obj10 instanceof String ? (String) obj10 : null;
                rowDataPdfArr2[6] = new RowDataPdf(rowDataPdfType10, "4.", str10 == null ? "" : str10, Integer.valueOf(getTotalPengguna_LFinal()), Integer.valueOf(getTotalPengguna_PFinal()), Integer.valueOf(getTotalPenggunaFinal()), null, 64, null);
                rows2.addAll(CollectionsKt.listOf((Object[]) rowDataPdfArr2));
            }
            SectionDataPdf sectionDataPdf2 = new SectionDataPdf("II.", "DATA PENGGUNAAN SURAT SUARA", null, "Jumlah", null, null, 52, null);
            if (z) {
                List<RowDataPdf> rows3 = sectionDataPdf2.getRows();
                RowDataPdf[] rowDataPdfArr3 = new RowDataPdf[7];
                RowDataPdfType rowDataPdfType11 = RowDataPdfType.content;
                Object obj11 = hashMap != null ? hashMap.get("ii1") : null;
                String str11 = obj11 instanceof String ? (String) obj11 : null;
                rowDataPdfArr3[0] = new RowDataPdf(rowDataPdfType11, "1.", str11 == null ? "" : str11, null, null, Integer.valueOf(getSuratDiterimaFinal()), null, 64, null);
                RowDataPdfType rowDataPdfType12 = RowDataPdfType.content;
                Object obj12 = hashMap != null ? hashMap.get("ii2") : null;
                String str12 = obj12 instanceof String ? (String) obj12 : null;
                rowDataPdfArr3[1] = new RowDataPdf(rowDataPdfType12, "2.", str12 == null ? "" : str12, null, null, Integer.valueOf(getSuratDigunakanFinal()), null, 64, null);
                RowDataPdfType rowDataPdfType13 = RowDataPdfType.content;
                Object obj13 = hashMap != null ? hashMap.get("ii3") : null;
                String str13 = obj13 instanceof String ? (String) obj13 : null;
                rowDataPdfArr3[2] = new RowDataPdf(rowDataPdfType13, "3.", str13 == null ? "" : str13, null, null, Integer.valueOf(getSuratDikembalikanFinal()), null, 64, null);
                RowDataPdfType rowDataPdfType14 = RowDataPdfType.content;
                Object obj14 = hashMap != null ? hashMap.get("ii4") : null;
                String str14 = obj14 instanceof String ? (String) obj14 : null;
                rowDataPdfArr3[3] = new RowDataPdf(rowDataPdfType14, "4.", str14 == null ? "" : str14, null, null, Integer.valueOf(getSuratTidakDigunakanFinal()), null, 64, null);
                RowDataPdfType rowDataPdfType15 = RowDataPdfType.content;
                Object obj15 = hashMap != null ? hashMap.get("ii4a") : null;
                String str15 = obj15 instanceof String ? (String) obj15 : null;
                rowDataPdfArr3[4] = new RowDataPdf(rowDataPdfType15, "a.", str15 == null ? "" : str15, null, null, Integer.valueOf(getSuratKembaliPPLNFinal()), null, 64, null);
                RowDataPdfType rowDataPdfType16 = RowDataPdfType.content;
                Object obj16 = hashMap != null ? hashMap.get("ii4b") : null;
                String str16 = obj16 instanceof String ? (String) obj16 : null;
                rowDataPdfArr3[5] = new RowDataPdf(rowDataPdfType16, "b.", str16 == null ? "" : str16, null, null, Integer.valueOf(getSuratTidakTerpakaiFinal()), null, 64, null);
                RowDataPdfType rowDataPdfType17 = RowDataPdfType.content;
                Object obj17 = hashMap != null ? hashMap.get("ii4c") : null;
                String str17 = obj17 instanceof String ? (String) obj17 : null;
                rowDataPdfArr3[6] = new RowDataPdf(rowDataPdfType17, "c.", str17 == null ? "" : str17, null, null, Integer.valueOf(getSuratTidakDikembalikanFinal()), null, 64, null);
                rows3.addAll(CollectionsKt.listOf((Object[]) rowDataPdfArr3));
            } else {
                List<RowDataPdf> rows4 = sectionDataPdf2.getRows();
                RowDataPdf[] rowDataPdfArr4 = new RowDataPdf[4];
                RowDataPdfType rowDataPdfType18 = RowDataPdfType.content;
                Object obj18 = hashMap != null ? hashMap.get("ii1") : null;
                String str18 = obj18 instanceof String ? (String) obj18 : null;
                rowDataPdfArr4[0] = new RowDataPdf(rowDataPdfType18, "1.", str18 == null ? "" : str18, null, null, Integer.valueOf(getSuratDiterimaFinal()), null, 64, null);
                RowDataPdfType rowDataPdfType19 = RowDataPdfType.content;
                Object obj19 = hashMap != null ? hashMap.get("ii2") : null;
                String str19 = obj19 instanceof String ? (String) obj19 : null;
                rowDataPdfArr4[1] = new RowDataPdf(rowDataPdfType19, "2.", str19 == null ? "" : str19, null, null, Integer.valueOf(getSuratDigunakanFinal()), null, 64, null);
                RowDataPdfType rowDataPdfType20 = RowDataPdfType.content;
                Object obj20 = hashMap != null ? hashMap.get("ii3") : null;
                String str20 = obj20 instanceof String ? (String) obj20 : null;
                rowDataPdfArr4[2] = new RowDataPdf(rowDataPdfType20, "3.", str20 == null ? "" : str20, null, null, Integer.valueOf(getSuratDikembalikanFinal()), null, 64, null);
                RowDataPdfType rowDataPdfType21 = RowDataPdfType.content;
                Object obj21 = hashMap != null ? hashMap.get("ii4") : null;
                String str21 = obj21 instanceof String ? (String) obj21 : null;
                rowDataPdfArr4[3] = new RowDataPdf(rowDataPdfType21, "4.", str21 == null ? "" : str21, null, null, Integer.valueOf(getSuratTidakDigunakanFinal()), null, 64, null);
                rows4.addAll(CollectionsKt.listOf((Object[]) rowDataPdfArr4));
            }
            SectionDataPdf sectionDataPdf3 = new SectionDataPdf("III.", "DATA PEMILIH DISABILITAS", null, "Jumlah (L+P)", "L", "P", 4, null);
            List<RowDataPdf> rows5 = sectionDataPdf3.getRows();
            RowDataPdfType rowDataPdfType22 = RowDataPdfType.content;
            Object obj22 = hashMap != null ? hashMap.get("iii") : null;
            String str22 = obj22 instanceof String ? (String) obj22 : null;
            rows5.addAll(CollectionsKt.listOf(new RowDataPdf(rowDataPdfType22, null, str22 == null ? "" : str22, Integer.valueOf(getPemilihDisabilitas_LFinal()), Integer.valueOf(getPemilihDisabilitas_PFinal()), Integer.valueOf(getTotalPemilihDisabilitasFinal()), null, 64, null)));
            return CollectionsKt.listOf((Object[]) new SectionDataPdf[]{sectionDataPdf, sectionDataPdf2, sectionDataPdf3});
        } finally {
        }
    }
}
