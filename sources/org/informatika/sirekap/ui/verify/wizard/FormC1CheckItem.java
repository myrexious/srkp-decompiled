package org.informatika.sirekap.ui.verify.wizard;

import android.content.Context;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.Candidate;
import org.informatika.sirekap.model.FormC1Administration;
import org.informatika.sirekap.model.FormC1AdministrationComplete;
import org.informatika.sirekap.model.FormC1AdministrationHal2;
import org.informatika.sirekap.model.FormC1AdministrationHal2Complete;
import org.informatika.sirekap.model.FormC1KesesuaianAdministration;
import org.informatika.sirekap.model.FormC1KesesuaianAdministrationHal2;
import org.informatika.sirekap.model.FormC1KesesuaianTabulationCandidateVote;
import org.informatika.sirekap.model.FormC1KesesuaianTabulationPartai;
import org.informatika.sirekap.model.FormC1TabulationCandidateVote;
import org.informatika.sirekap.model.FormC1TabulationPartai;
import org.informatika.sirekap.model.FormC1TabulationPartaiWithCandidates;
import org.informatika.sirekap.model.FormC1TabulationWithCandidates;

/* compiled from: FormC1CheckItem.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b&\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\b\u0018\u0000 62\u00020\u0001:\u00016BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\rJ\u000e\u0010 \u001a\u00020\n2\u0006\u0010!\u001a\u00020\nJ\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\u0010\u0010%\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0018J\t\u0010&\u001a\u00020\u0007HÆ\u0003J\u0010\u0010'\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010(\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0018J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jh\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010+J\u0013\u0010,\u001a\u00020\n2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\r\u0010.\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u0018J\u000e\u0010/\u001a\u00020\u00032\u0006\u00100\u001a\u000201J\t\u00102\u001a\u00020\u0007HÖ\u0001J\u0006\u00103\u001a\u00020\nJ\u0006\u00104\u001a\u00020\nJ\t\u00105\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u001d\u0010\u0018R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u00067"}, d2 = {"Lorg/informatika/sirekap/ui/verify/wizard/FormC1CheckItem;", "", "row", "", "title", "subtitle", "type", "", "value", "checked", "", FirebaseAnalytics.Param.INDEX, "correctedValue", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;ILjava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;)V", "getChecked", "()Ljava/lang/Boolean;", "setChecked", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getCorrectedValue", "()Ljava/lang/String;", "setCorrectedValue", "(Ljava/lang/String;)V", "getIndex", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getRow", "getSubtitle", "getTitle", "getType", "getValue", "()I", "canGoNext", "showCorrection", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;ILjava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;)Lorg/informatika/sirekap/ui/verify/wizard/FormC1CheckItem;", "equals", "other", "getCorrectedValueInt", "getTypeText", "context", "Landroid/content/Context;", "hashCode", "isCorrectedValueValid", "needCorrection", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class FormC1CheckItem {
    public static final Companion Companion = new Companion(null);
    public static final int FORM_C1_CHECK_ITEM_TYPE_GENDER_L = 0;
    public static final int FORM_C1_CHECK_ITEM_TYPE_GENDER_P = 1;
    public static final int FORM_C1_CHECK_ITEM_TYPE_GENDER_TOTAL = 2;
    private Boolean checked;
    private String correctedValue;
    private final Integer index;
    private final String row;
    private final String subtitle;
    private final String title;
    private final Integer type;
    private final int value;

    public final String component1() {
        return this.row;
    }

    public final String component2() {
        return this.title;
    }

    public final String component3() {
        return this.subtitle;
    }

    public final Integer component4() {
        return this.type;
    }

    public final int component5() {
        return this.value;
    }

    public final Boolean component6() {
        return this.checked;
    }

    public final Integer component7() {
        return this.index;
    }

    public final String component8() {
        return this.correctedValue;
    }

    public final FormC1CheckItem copy(String row, String str, String subtitle, Integer num, int i, Boolean bool, Integer num2, String str2) {
        Intrinsics.checkNotNullParameter(row, "row");
        Intrinsics.checkNotNullParameter(subtitle, "subtitle");
        return new FormC1CheckItem(row, str, subtitle, num, i, bool, num2, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FormC1CheckItem) {
            FormC1CheckItem formC1CheckItem = (FormC1CheckItem) obj;
            return Intrinsics.areEqual(this.row, formC1CheckItem.row) && Intrinsics.areEqual(this.title, formC1CheckItem.title) && Intrinsics.areEqual(this.subtitle, formC1CheckItem.subtitle) && Intrinsics.areEqual(this.type, formC1CheckItem.type) && this.value == formC1CheckItem.value && Intrinsics.areEqual(this.checked, formC1CheckItem.checked) && Intrinsics.areEqual(this.index, formC1CheckItem.index) && Intrinsics.areEqual(this.correctedValue, formC1CheckItem.correctedValue);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.row.hashCode() * 31;
        String str = this.title;
        int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.subtitle.hashCode()) * 31;
        Integer num = this.type;
        int hashCode3 = (((hashCode2 + (num == null ? 0 : num.hashCode())) * 31) + Integer.hashCode(this.value)) * 31;
        Boolean bool = this.checked;
        int hashCode4 = (hashCode3 + (bool == null ? 0 : bool.hashCode())) * 31;
        Integer num2 = this.index;
        int hashCode5 = (hashCode4 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str2 = this.correctedValue;
        return hashCode5 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        String str = this.row;
        String str2 = this.title;
        String str3 = this.subtitle;
        Integer num = this.type;
        int i = this.value;
        Boolean bool = this.checked;
        Integer num2 = this.index;
        return "FormC1CheckItem(row=" + str + ", title=" + str2 + ", subtitle=" + str3 + ", type=" + num + ", value=" + i + ", checked=" + bool + ", index=" + num2 + ", correctedValue=" + this.correctedValue + ")";
    }

    public FormC1CheckItem(String row, String str, String subtitle, Integer num, int i, Boolean bool, Integer num2, String str2) {
        Intrinsics.checkNotNullParameter(row, "row");
        Intrinsics.checkNotNullParameter(subtitle, "subtitle");
        this.row = row;
        this.title = str;
        this.subtitle = subtitle;
        this.type = num;
        this.value = i;
        this.checked = bool;
        this.index = num2;
        this.correctedValue = str2;
    }

    public /* synthetic */ FormC1CheckItem(String str, String str2, String str3, Integer num, int i, Boolean bool, Integer num2, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, num, i, bool, (i2 & 64) != 0 ? null : num2, (i2 & 128) != 0 ? null : str4);
    }

    public final String getRow() {
        return this.row;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getSubtitle() {
        return this.subtitle;
    }

    public final Integer getType() {
        return this.type;
    }

    public final int getValue() {
        return this.value;
    }

    public final Boolean getChecked() {
        return this.checked;
    }

    public final void setChecked(Boolean bool) {
        this.checked = bool;
    }

    public final Integer getIndex() {
        return this.index;
    }

    public final String getCorrectedValue() {
        return this.correctedValue;
    }

    public final void setCorrectedValue(String str) {
        this.correctedValue = str;
    }

    public final boolean needCorrection() {
        return Intrinsics.areEqual((Object) this.checked, (Object) false);
    }

    public final boolean isCorrectedValueValid() {
        String str = this.correctedValue;
        return !(str == null || StringsKt.isBlank(str));
    }

    public final String getTypeText(Context context) {
        int i;
        Intrinsics.checkNotNullParameter(context, "context");
        Integer num = this.type;
        if (num != null && num.intValue() == 0) {
            i = R.string.male_left;
        } else {
            i = (num != null && num.intValue() == 1) ? R.string.female_center : R.string.total_right;
        }
        String string = context.getString(i);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(\n     …t\n            }\n        )");
        return string;
    }

    public final Integer getCorrectedValueInt() {
        String str = this.correctedValue;
        if (str != null) {
            return StringsKt.toIntOrNull(str);
        }
        return null;
    }

    public final boolean canGoNext(boolean z) {
        if (z) {
            if (this.checked != null) {
                if (needCorrection()) {
                    return isCorrectedValueValid();
                }
                return true;
            }
        } else if (this.checked != null) {
            return true;
        }
        return false;
    }

    /* compiled from: FormC1CheckItem.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010J\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0013J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/informatika/sirekap/ui/verify/wizard/FormC1CheckItem$Companion;", "", "()V", "FORM_C1_CHECK_ITEM_TYPE_GENDER_L", "", "FORM_C1_CHECK_ITEM_TYPE_GENDER_P", "FORM_C1_CHECK_ITEM_TYPE_GENDER_TOTAL", "createFromFormC1AdministrationComplete", "", "Lorg/informatika/sirekap/ui/verify/wizard/FormC1CheckItem;", "context", "Landroid/content/Context;", "formC1AdministrationComplete", "Lorg/informatika/sirekap/model/FormC1AdministrationComplete;", "createFromFormC1AdministrationHal2", "formC1Tabulation", "Lorg/informatika/sirekap/model/FormC1AdministrationHal2Complete;", "createFromFormC1TabulationPartaiWithCandidates", "formC1TabulationPartaiWithCandidates", "Lorg/informatika/sirekap/model/FormC1TabulationPartaiWithCandidates;", "createFromFormC1TabulationWithCandidates", "formC1TabulationWithCandidates", "Lorg/informatika/sirekap/model/FormC1TabulationWithCandidates;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<FormC1CheckItem> createFromFormC1AdministrationComplete(Context context, FormC1AdministrationComplete formC1AdministrationComplete) {
            Integer totalPengguna;
            Integer totalPengguna_P;
            Integer totalPengguna_L;
            Integer totalPenggunaDpk;
            Integer penggunaDpk_P;
            Integer penggunaDpk_L;
            Integer totalPenggunaDptb;
            Integer penggunaDptb_P;
            Integer penggunaDptb_L;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(formC1AdministrationComplete, "formC1AdministrationComplete");
            FormC1KesesuaianAdministration kesesuaianAdministration = formC1AdministrationComplete.getKesesuaianAdministration();
            if (kesesuaianAdministration == null) {
                return null;
            }
            FormC1Administration form = formC1AdministrationComplete.getForm();
            FormC1CheckItem[] formC1CheckItemArr = new FormC1CheckItem[22];
            String string = context.getString(R.string.table_title_administrasi_pemilih);
            String string2 = context.getString(R.string.administrasi_pemilih_dpt);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.stri…administrasi_pemilih_dpt)");
            formC1CheckItemArr[0] = new FormC1CheckItem(FormC1Administration.FORM_C1_ROW_PEMILIH_DPT, string, string2, 0, form != null ? form.getPemilihDpt_L() : 0, kesesuaianAdministration.getPemilihDpt_L(), null, kesesuaianAdministration.getPemilihDpt_LCorrected() != null ? kesesuaianAdministration.getPemilihDpt_LCorrected().toString() : "", 64, null);
            String string3 = context.getString(R.string.table_title_administrasi_pemilih);
            String string4 = context.getString(R.string.administrasi_pemilih_dpt);
            Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.stri…administrasi_pemilih_dpt)");
            formC1CheckItemArr[1] = new FormC1CheckItem(FormC1Administration.FORM_C1_ROW_PEMILIH_DPT, string3, string4, 1, form != null ? form.getPemilihDpt_P() : 0, kesesuaianAdministration.getPemilihDpt_P(), null, kesesuaianAdministration.getPemilihDpt_PCorrected() != null ? kesesuaianAdministration.getPemilihDpt_PCorrected().toString() : "", 64, null);
            String string5 = context.getString(R.string.table_title_administrasi_pemilih);
            String string6 = context.getString(R.string.administrasi_pemilih_dpt);
            Intrinsics.checkNotNullExpressionValue(string6, "context.getString(R.stri…administrasi_pemilih_dpt)");
            formC1CheckItemArr[2] = new FormC1CheckItem(FormC1Administration.FORM_C1_ROW_PEMILIH_DPT, string5, string6, 2, form != null ? form.getTotalPemilihDpt() : 0, kesesuaianAdministration.getTotalPemilihDpt(), null, kesesuaianAdministration.getTotalPemilihDptCorrected() != null ? kesesuaianAdministration.getTotalPemilihDptCorrected().toString() : "", 64, null);
            String string7 = context.getString(R.string.table_title_administrasi_pengguna);
            String string8 = context.getString(R.string.administrasi_pengguna_dpt);
            Intrinsics.checkNotNullExpressionValue(string8, "context.getString(R.stri…dministrasi_pengguna_dpt)");
            formC1CheckItemArr[3] = new FormC1CheckItem(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPT, string7, string8, 0, form != null ? form.getPenggunaDpt_L() : 0, kesesuaianAdministration.getPenggunaDpt_L(), null, kesesuaianAdministration.getPenggunaDpt_LCorrected() != null ? kesesuaianAdministration.getPenggunaDpt_LCorrected().toString() : "", 64, null);
            String string9 = context.getString(R.string.table_title_administrasi_pengguna);
            String string10 = context.getString(R.string.administrasi_pengguna_dpt);
            Intrinsics.checkNotNullExpressionValue(string10, "context.getString(R.stri…dministrasi_pengguna_dpt)");
            formC1CheckItemArr[4] = new FormC1CheckItem(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPT, string9, string10, 1, form != null ? form.getPenggunaDpt_P() : 0, kesesuaianAdministration.getPenggunaDpt_P(), null, kesesuaianAdministration.getPenggunaDpt_PCorrected() != null ? kesesuaianAdministration.getPenggunaDpt_PCorrected().toString() : "", 64, null);
            String string11 = context.getString(R.string.table_title_administrasi_pengguna);
            String string12 = context.getString(R.string.administrasi_pengguna_dpt);
            Intrinsics.checkNotNullExpressionValue(string12, "context.getString(R.stri…dministrasi_pengguna_dpt)");
            formC1CheckItemArr[5] = new FormC1CheckItem(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPT, string11, string12, 2, form != null ? form.getTotalPenggunaDpt() : 0, kesesuaianAdministration.getTotalPenggunaDpt(), null, kesesuaianAdministration.getTotalPenggunaDptCorrected() != null ? kesesuaianAdministration.getTotalPenggunaDptCorrected().toString() : "", 64, null);
            String string13 = context.getString(R.string.table_title_administrasi_pengguna);
            String string14 = context.getString(R.string.administrasi_pengguna_dptb);
            Intrinsics.checkNotNullExpressionValue(string14, "context.getString(R.stri…ministrasi_pengguna_dptb)");
            formC1CheckItemArr[6] = new FormC1CheckItem(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPTB, string13, string14, 0, (form == null || (penggunaDptb_L = form.getPenggunaDptb_L()) == null) ? 0 : penggunaDptb_L.intValue(), kesesuaianAdministration.getPenggunaDptb_L(), null, kesesuaianAdministration.getPenggunaDptb_LCorrected() != null ? kesesuaianAdministration.getPenggunaDptb_LCorrected().toString() : "", 64, null);
            String string15 = context.getString(R.string.table_title_administrasi_pengguna);
            String string16 = context.getString(R.string.administrasi_pengguna_dptb);
            Intrinsics.checkNotNullExpressionValue(string16, "context.getString(R.stri…ministrasi_pengguna_dptb)");
            formC1CheckItemArr[7] = new FormC1CheckItem(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPTB, string15, string16, 1, (form == null || (penggunaDptb_P = form.getPenggunaDptb_P()) == null) ? 0 : penggunaDptb_P.intValue(), kesesuaianAdministration.getPenggunaDptb_P(), null, kesesuaianAdministration.getPenggunaDptb_PCorrected() != null ? kesesuaianAdministration.getPenggunaDptb_PCorrected().toString() : "", 64, null);
            String string17 = context.getString(R.string.table_title_administrasi_pengguna);
            String string18 = context.getString(R.string.administrasi_pengguna_dptb);
            Intrinsics.checkNotNullExpressionValue(string18, "context.getString(R.stri…ministrasi_pengguna_dptb)");
            formC1CheckItemArr[8] = new FormC1CheckItem(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPTB, string17, string18, 2, (form == null || (totalPenggunaDptb = form.getTotalPenggunaDptb()) == null) ? 0 : totalPenggunaDptb.intValue(), kesesuaianAdministration.getTotalPenggunaDptb(), null, kesesuaianAdministration.getTotalPenggunaDptbCorrected() != null ? kesesuaianAdministration.getTotalPenggunaDptbCorrected().toString() : "", 64, null);
            String string19 = context.getString(R.string.table_title_administrasi_pengguna);
            String string20 = context.getString(R.string.administrasi_pengguna_dpk);
            Intrinsics.checkNotNullExpressionValue(string20, "context.getString(R.stri…dministrasi_pengguna_dpk)");
            formC1CheckItemArr[9] = new FormC1CheckItem(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPK, string19, string20, 0, (form == null || (penggunaDpk_L = form.getPenggunaDpk_L()) == null) ? 0 : penggunaDpk_L.intValue(), kesesuaianAdministration.getPenggunaDpk_L(), null, kesesuaianAdministration.getPenggunaDpk_LCorrected() != null ? kesesuaianAdministration.getPenggunaDpk_LCorrected().toString() : "", 64, null);
            String string21 = context.getString(R.string.table_title_administrasi_pengguna);
            String string22 = context.getString(R.string.administrasi_pengguna_dpk);
            Intrinsics.checkNotNullExpressionValue(string22, "context.getString(R.stri…dministrasi_pengguna_dpk)");
            formC1CheckItemArr[10] = new FormC1CheckItem(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPK, string21, string22, 1, (form == null || (penggunaDpk_P = form.getPenggunaDpk_P()) == null) ? 0 : penggunaDpk_P.intValue(), kesesuaianAdministration.getPenggunaDpk_P(), null, kesesuaianAdministration.getPenggunaDpk_PCorrected() != null ? kesesuaianAdministration.getPenggunaDpk_PCorrected().toString() : "", 64, null);
            String string23 = context.getString(R.string.table_title_administrasi_pengguna);
            String string24 = context.getString(R.string.administrasi_pengguna_dpk);
            Intrinsics.checkNotNullExpressionValue(string24, "context.getString(R.stri…dministrasi_pengguna_dpk)");
            formC1CheckItemArr[11] = new FormC1CheckItem(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPK, string23, string24, 2, (form == null || (totalPenggunaDpk = form.getTotalPenggunaDpk()) == null) ? 0 : totalPenggunaDpk.intValue(), kesesuaianAdministration.getTotalPenggunaDpk(), null, kesesuaianAdministration.getTotalPenggunaDpkCorrected() != null ? kesesuaianAdministration.getTotalPenggunaDpkCorrected().toString() : "", 64, null);
            String string25 = context.getString(R.string.table_title_administrasi_pengguna);
            String string26 = context.getString(R.string.administrasi_pengguna_total);
            Intrinsics.checkNotNullExpressionValue(string26, "context.getString(R.stri…inistrasi_pengguna_total)");
            formC1CheckItemArr[12] = new FormC1CheckItem(FormC1Administration.FORM_C1_ROW_PENGGUNA_TOTAL, string25, string26, 0, (form == null || (totalPengguna_L = form.getTotalPengguna_L()) == null) ? 0 : totalPengguna_L.intValue(), kesesuaianAdministration.getTotalPengguna_L(), null, kesesuaianAdministration.getTotalPengguna_LCorrected() != null ? kesesuaianAdministration.getTotalPengguna_LCorrected().toString() : "", 64, null);
            String string27 = context.getString(R.string.table_title_administrasi_pengguna);
            String string28 = context.getString(R.string.administrasi_pengguna_total);
            Intrinsics.checkNotNullExpressionValue(string28, "context.getString(R.stri…inistrasi_pengguna_total)");
            formC1CheckItemArr[13] = new FormC1CheckItem(FormC1Administration.FORM_C1_ROW_PENGGUNA_TOTAL, string27, string28, 1, (form == null || (totalPengguna_P = form.getTotalPengguna_P()) == null) ? 0 : totalPengguna_P.intValue(), kesesuaianAdministration.getTotalPengguna_P(), null, kesesuaianAdministration.getTotalPengguna_PCorrected() != null ? kesesuaianAdministration.getTotalPengguna_PCorrected().toString() : "", 64, null);
            String string29 = context.getString(R.string.table_title_administrasi_pengguna);
            String string30 = context.getString(R.string.administrasi_pengguna_total);
            Intrinsics.checkNotNullExpressionValue(string30, "context.getString(R.stri…inistrasi_pengguna_total)");
            formC1CheckItemArr[14] = new FormC1CheckItem(FormC1Administration.FORM_C1_ROW_PENGGUNA_TOTAL, string29, string30, 2, (form == null || (totalPengguna = form.getTotalPengguna()) == null) ? 0 : totalPengguna.intValue(), kesesuaianAdministration.getTotalPengguna(), null, kesesuaianAdministration.getTotalPenggunaCorrected() != null ? kesesuaianAdministration.getTotalPenggunaCorrected().toString() : "", 64, null);
            String string31 = context.getString(R.string.table_title_administrasi_surat);
            String string32 = context.getString(R.string.administrasi_surat_diterima);
            Intrinsics.checkNotNullExpressionValue(string32, "context.getString(R.stri…inistrasi_surat_diterima)");
            formC1CheckItemArr[15] = new FormC1CheckItem(FormC1Administration.FORM_C1_ROW_SURAT_DITERIMA, string31, string32, null, form != null ? form.getSuratDiterima() : 0, kesesuaianAdministration.getSuratDiterima(), null, kesesuaianAdministration.getSuratDiterimaCorrected() != null ? kesesuaianAdministration.getSuratDiterimaCorrected().toString() : "", 64, null);
            String string33 = context.getString(R.string.table_title_administrasi_surat);
            String string34 = context.getString(R.string.administrasi_surat_dikembalikan);
            Intrinsics.checkNotNullExpressionValue(string34, "context.getString(R.stri…trasi_surat_dikembalikan)");
            formC1CheckItemArr[16] = new FormC1CheckItem(FormC1Administration.FORM_C1_ROW_SURAT_DIKEMBALIKAN, string33, string34, null, form != null ? form.getSuratDikembalikan() : 0, kesesuaianAdministration.getSuratDikembalikan(), null, kesesuaianAdministration.getSuratDikembalikanCorrected() != null ? kesesuaianAdministration.getSuratDikembalikanCorrected().toString() : "", 64, null);
            String string35 = context.getString(R.string.table_title_administrasi_surat);
            String string36 = context.getString(R.string.administrasi_surat_tidak_digunakan);
            Intrinsics.checkNotNullExpressionValue(string36, "context.getString(R.stri…si_surat_tidak_digunakan)");
            formC1CheckItemArr[17] = new FormC1CheckItem(FormC1Administration.FORM_C1_ROW_SURAT_TIDAK_DIGUNAKAN, string35, string36, null, form != null ? form.getSuratTidakDigunakan() : 0, kesesuaianAdministration.getSuratTidakDigunakan(), null, kesesuaianAdministration.getSuratTidakDigunakanCorrected() != null ? kesesuaianAdministration.getSuratTidakDigunakanCorrected().toString() : "", 64, null);
            String string37 = context.getString(R.string.table_title_administrasi_surat);
            String string38 = context.getString(R.string.administrasi_surat_digunakan);
            Intrinsics.checkNotNullExpressionValue(string38, "context.getString(R.stri…nistrasi_surat_digunakan)");
            formC1CheckItemArr[18] = new FormC1CheckItem(FormC1Administration.FORM_C1_ROW_SURAT_DIGUNAKAN, string37, string38, null, form != null ? form.getSuratDigunakan() : 0, kesesuaianAdministration.getSuratDigunakan(), null, kesesuaianAdministration.getSuratDigunakanCorrected() != null ? kesesuaianAdministration.getSuratDigunakanCorrected().toString() : "", 64, null);
            String string39 = context.getString(R.string.table_title_administrasi_disabilitas);
            String string40 = context.getString(R.string.administrasi_pemilih_disabilitas);
            Intrinsics.checkNotNullExpressionValue(string40, "context.getString(R.stri…rasi_pemilih_disabilitas)");
            formC1CheckItemArr[19] = new FormC1CheckItem(FormC1Administration.FORM_C1_ROW_PEMILIH_DISABILITAS, string39, string40, 0, form != null ? form.getPemilihDisabilitas_L() : 0, kesesuaianAdministration.getPemilihDisabilitas_L(), null, kesesuaianAdministration.getPemilihDisabilitas_LCorrected() != null ? kesesuaianAdministration.getPemilihDisabilitas_LCorrected().toString() : "", 64, null);
            String string41 = context.getString(R.string.table_title_administrasi_disabilitas);
            String string42 = context.getString(R.string.administrasi_pemilih_disabilitas);
            Intrinsics.checkNotNullExpressionValue(string42, "context.getString(R.stri…rasi_pemilih_disabilitas)");
            formC1CheckItemArr[20] = new FormC1CheckItem(FormC1Administration.FORM_C1_ROW_PEMILIH_DISABILITAS, string41, string42, 1, form != null ? form.getPemilihDisabilitas_P() : 0, kesesuaianAdministration.getPemilihDisabilitas_P(), null, kesesuaianAdministration.getPemilihDisabilitas_PCorrected() != null ? kesesuaianAdministration.getPemilihDisabilitas_PCorrected().toString() : "", 64, null);
            String string43 = context.getString(R.string.table_title_administrasi_disabilitas);
            String string44 = context.getString(R.string.administrasi_pemilih_disabilitas);
            Intrinsics.checkNotNullExpressionValue(string44, "context.getString(R.stri…rasi_pemilih_disabilitas)");
            formC1CheckItemArr[21] = new FormC1CheckItem(FormC1Administration.FORM_C1_ROW_PEMILIH_DISABILITAS, string43, string44, 2, form != null ? form.getTotalPemilihDisabilitas() : 0, kesesuaianAdministration.getTotalPemilihDisabilitas(), null, kesesuaianAdministration.getTotalPemilihDisabilitasCorrected() != null ? kesesuaianAdministration.getTotalPemilihDisabilitasCorrected().toString() : "", 64, null);
            return CollectionsKt.listOf((Object[]) formC1CheckItemArr);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r6v7, types: [org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem] */
        public final List<FormC1CheckItem> createFromFormC1TabulationWithCandidates(Context context, FormC1TabulationWithCandidates formC1TabulationWithCandidates) {
            Object obj;
            Object obj2;
            boolean z;
            boolean z2;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(formC1TabulationWithCandidates, "formC1TabulationWithCandidates");
            ArrayList arrayList = new ArrayList();
            List<FormC1TabulationCandidateVote> votes = formC1TabulationWithCandidates.getFormC1Tabulation().getVotes();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(votes, 10));
            int i = 0;
            for (Object obj3 : votes) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                FormC1TabulationCandidateVote formC1TabulationCandidateVote = (FormC1TabulationCandidateVote) obj3;
                List<FormC1KesesuaianTabulationCandidateVote> kesesuaianVotes = formC1TabulationWithCandidates.getFormC1Tabulation().getKesesuaianVotes();
                if (kesesuaianVotes != null) {
                    Iterator it = formC1TabulationWithCandidates.getCandidates().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            obj = null;
                            break;
                        }
                        obj = it.next();
                        if (((Candidate) obj).getIndex() == formC1TabulationCandidateVote.getIndex()) {
                            z2 = true;
                            continue;
                        } else {
                            z2 = false;
                            continue;
                        }
                        if (z2) {
                            break;
                        }
                    }
                    Candidate candidate = (Candidate) obj;
                    Iterator it2 = kesesuaianVotes.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            obj2 = null;
                            break;
                        }
                        obj2 = it2.next();
                        if (((FormC1KesesuaianTabulationCandidateVote) obj2).getIndex() == formC1TabulationCandidateVote.getIndex()) {
                            z = true;
                            continue;
                        } else {
                            z = false;
                            continue;
                        }
                        if (z) {
                            break;
                        }
                    }
                    FormC1KesesuaianTabulationCandidateVote formC1KesesuaianTabulationCandidateVote = (FormC1KesesuaianTabulationCandidateVote) obj2;
                    r9 = new FormC1CheckItem("FORM_C1_ROW_CANDIDATE_", context.getString(R.string.table_title_perolehan_suara), candidate != null ? candidate.getNoUrutPencalonan() + ". " + candidate.getNama() : "-", null, formC1TabulationCandidateVote.getVote(), formC1KesesuaianTabulationCandidateVote != null ? formC1KesesuaianTabulationCandidateVote.getVote() : null, candidate != null ? Integer.valueOf(candidate.getIndex()) : null, (formC1KesesuaianTabulationCandidateVote != null ? formC1KesesuaianTabulationCandidateVote.getVoteCorrected() : null) != null ? formC1KesesuaianTabulationCandidateVote.getVoteCorrected().toString() : "");
                }
                arrayList2.add(r9);
                i = i2;
            }
            arrayList.addAll(CollectionsKt.filterNotNull(arrayList2));
            return arrayList;
        }

        public final List<FormC1CheckItem> createFromFormC1TabulationPartaiWithCandidates(Context context, FormC1TabulationPartaiWithCandidates formC1TabulationPartaiWithCandidates) {
            FormC1CheckItem formC1CheckItem;
            Object obj;
            Object obj2;
            FormC1CheckItem formC1CheckItem2;
            boolean z;
            boolean z2;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(formC1TabulationPartaiWithCandidates, "formC1TabulationPartaiWithCandidates");
            ArrayList arrayList = new ArrayList();
            FormC1TabulationPartai form = formC1TabulationPartaiWithCandidates.getFormC1TabulationPartai().getForm();
            FormC1KesesuaianTabulationPartai kesesuaianTabulationPartai = formC1TabulationPartaiWithCandidates.getFormC1TabulationPartai().getKesesuaianTabulationPartai();
            List<FormC1TabulationCandidateVote> votes = formC1TabulationPartaiWithCandidates.getFormC1TabulationPartai().getVotes();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(votes, 10));
            Iterator<T> it = votes.iterator();
            int i = 0;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                FormC1TabulationCandidateVote formC1TabulationCandidateVote = (FormC1TabulationCandidateVote) next;
                List<FormC1KesesuaianTabulationCandidateVote> kesesuaianVotes = formC1TabulationPartaiWithCandidates.getFormC1TabulationPartai().getKesesuaianVotes();
                if (kesesuaianVotes != null) {
                    Iterator<T> it2 = formC1TabulationPartaiWithCandidates.getCandidates().iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            obj = null;
                            break;
                        }
                        obj = it2.next();
                        if (((Candidate) obj).getIndex() == formC1TabulationCandidateVote.getIndex()) {
                            z2 = true;
                            continue;
                        } else {
                            z2 = false;
                            continue;
                        }
                        if (z2) {
                            break;
                        }
                    }
                    Candidate candidate = (Candidate) obj;
                    Iterator<T> it3 = kesesuaianVotes.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            obj2 = null;
                            break;
                        }
                        obj2 = it3.next();
                        if (((FormC1KesesuaianTabulationCandidateVote) obj2).getIndex() == formC1TabulationCandidateVote.getIndex()) {
                            z = true;
                            continue;
                        } else {
                            z = false;
                            continue;
                        }
                        if (z) {
                            break;
                        }
                    }
                    FormC1KesesuaianTabulationCandidateVote formC1KesesuaianTabulationCandidateVote = (FormC1KesesuaianTabulationCandidateVote) obj2;
                    if (i <= 0) {
                        formC1CheckItem2 = new FormC1CheckItem("FORM_C1_ROW_CANDIDATE_", context.getString(R.string.table_title_perolehan_suara), "Suara Partai", null, formC1TabulationCandidateVote.getVote(), formC1KesesuaianTabulationCandidateVote != null ? formC1KesesuaianTabulationCandidateVote.getVote() : null, candidate != null ? Integer.valueOf(candidate.getIndex()) : null, (formC1KesesuaianTabulationCandidateVote != null ? formC1KesesuaianTabulationCandidateVote.getVoteCorrected() : null) != null ? formC1KesesuaianTabulationCandidateVote.getVoteCorrected().toString() : "");
                    } else {
                        formC1CheckItem2 = new FormC1CheckItem("FORM_C1_ROW_CANDIDATE_", context.getString(R.string.table_title_perolehan_suara), candidate != null ? candidate.getNoUrutPencalonan() + ". " + candidate.getNama() : "-", null, formC1TabulationCandidateVote.getVote(), formC1KesesuaianTabulationCandidateVote != null ? formC1KesesuaianTabulationCandidateVote.getVote() : null, candidate != null ? Integer.valueOf(candidate.getIndex()) : null, (formC1KesesuaianTabulationCandidateVote != null ? formC1KesesuaianTabulationCandidateVote.getVoteCorrected() : null) != null ? formC1KesesuaianTabulationCandidateVote.getVoteCorrected().toString() : "");
                    }
                    formC1CheckItem = formC1CheckItem2;
                } else {
                    formC1CheckItem = null;
                }
                arrayList2.add(formC1CheckItem);
                i = i2;
            }
            arrayList.addAll(CollectionsKt.filterNotNull(arrayList2));
            if ((form != null ? form.getSuratSahPartaiDanCalon() : null) != null) {
                arrayList.add(new FormC1CheckItem(FormC1TabulationPartai.FORM_C1_ROW_SUARA_SAH_PARTAI_CALON, context.getString(R.string.table_title_perolehan_suara), "Jumlah Suara Sah Partai Politik dan Calon ( A.1 + A.2 )", null, form.getSuratSahPartaiDanCalon().intValue(), kesesuaianTabulationPartai != null ? kesesuaianTabulationPartai.getSuratSahPartaiDanCalon() : null, null, (kesesuaianTabulationPartai != null ? kesesuaianTabulationPartai.getSuratSahPartaiDanCalonCorrected() : null) != null ? kesesuaianTabulationPartai.getSuratSahPartaiDanCalonCorrected().toString() : "", 64, null));
            }
            return arrayList;
        }

        public final List<FormC1CheckItem> createFromFormC1AdministrationHal2(Context context, FormC1AdministrationHal2Complete formC1Tabulation) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(formC1Tabulation, "formC1Tabulation");
            ArrayList arrayList = new ArrayList();
            FormC1AdministrationHal2 form = formC1Tabulation.getForm();
            FormC1KesesuaianAdministrationHal2 kesesuaianAdministrationHal2 = formC1Tabulation.getKesesuaianAdministrationHal2();
            ArrayList arrayList2 = new ArrayList();
            if ((form != null ? form.getSuratSuaraSah() : null) != null) {
                String string = context.getString(R.string.table_title_data_suara_sah_dan_tidak_sah);
                String string2 = context.getString(R.string.tabulasi_suara_sah);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.tabulasi_suara_sah)");
                arrayList2.add(new FormC1CheckItem(FormC1AdministrationHal2.FORM_C1_ROW_SURAT_SUARA_SAH, string, string2, null, form.getSuratSuaraSah().intValue(), kesesuaianAdministrationHal2 != null ? kesesuaianAdministrationHal2.getSuratSuaraSah() : null, null, (kesesuaianAdministrationHal2 != null ? kesesuaianAdministrationHal2.getSuratSuaraSahCorrected() : null) != null ? kesesuaianAdministrationHal2.getSuratSuaraSahCorrected().toString() : "", 64, null));
            }
            if ((form != null ? form.getSuratSuaraTidakSah() : null) != null) {
                String string3 = context.getString(R.string.table_title_data_suara_sah_dan_tidak_sah);
                String string4 = context.getString(R.string.tabulasi_suara_tidak_sah);
                Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.stri…tabulasi_suara_tidak_sah)");
                arrayList2.add(new FormC1CheckItem(FormC1AdministrationHal2.FORM_C1_ROW_SURAT_SUARA_TIDAK_SAH, string3, string4, null, form.getSuratSuaraTidakSah().intValue(), kesesuaianAdministrationHal2 != null ? kesesuaianAdministrationHal2.getSuratSuaraTidakSah() : null, null, (kesesuaianAdministrationHal2 != null ? kesesuaianAdministrationHal2.getSuratSuaraTidakSahCorrected() : null) != null ? kesesuaianAdministrationHal2.getSuratSuaraTidakSahCorrected().toString() : "", 64, null));
            }
            if ((form != null ? form.getTotalSuratSuara() : null) != null) {
                String string5 = context.getString(R.string.table_title_data_suara_sah_dan_tidak_sah);
                String string6 = context.getString(R.string.tabulasi_suara_total);
                Intrinsics.checkNotNullExpressionValue(string6, "context.getString(R.string.tabulasi_suara_total)");
                arrayList2.add(new FormC1CheckItem(FormC1AdministrationHal2.FORM_C1_ROW_TOTAL_SURAT_SUARA, string5, string6, null, form.getTotalSuratSuara().intValue(), kesesuaianAdministrationHal2 != null ? kesesuaianAdministrationHal2.getTotalSuratSuara() : null, null, (kesesuaianAdministrationHal2 != null ? kesesuaianAdministrationHal2.getTotalSuratSuaraCorrected() : null) != null ? kesesuaianAdministrationHal2.getTotalSuratSuaraCorrected().toString() : "", 64, null));
            }
            arrayList.addAll(arrayList2);
            return arrayList;
        }
    }
}
