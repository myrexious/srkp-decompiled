package org.informatika.sirekap.ui.verify;

import android.content.Context;
import androidx.core.content.ContextCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.xmpbox.type.JobType;
import org.informatika.sirekap.R;

/* compiled from: FormC1ListItem.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\bH\n\u0002\u0018\u0002\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001Bõ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\u001bJ\t\u00108\u001a\u00020\u0003HÆ\u0003J\u0010\u00109\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010 J\u0010\u0010:\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001dJ\u0010\u0010;\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001dJ\u0010\u0010<\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001dJ\u0010\u0010=\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001dJ\u0010\u0010>\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010 J\u000b\u0010?\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010B\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001dJ\u0010\u0010C\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001dJ\u000b\u0010D\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010E\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010 J\u0010\u0010F\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010 J\t\u0010G\u001a\u00020\u0003HÆ\u0003J\u0010\u0010H\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001dJ\u0010\u0010I\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001dJ\t\u0010J\u001a\u00020\u0005HÆ\u0003J\t\u0010K\u001a\u00020\u000bHÆ\u0003J\u0010\u0010L\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010 J\u0010\u0010M\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010 J\u008e\u0002\u0010N\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u000bHÆ\u0001¢\u0006\u0002\u0010OJ\u0013\u0010P\u001a\u00020\u000b2\b\u0010Q\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0010\u0010R\u001a\u0004\u0018\u00010\u00032\u0006\u0010S\u001a\u00020TJ\u000e\u0010U\u001a\u00020\u00032\u0006\u0010V\u001a\u00020\u000bJ\u0016\u0010W\u001a\u00020\u00052\u0006\u0010S\u001a\u00020T2\u0006\u0010V\u001a\u00020\u000bJ\u000e\u0010X\u001a\u00020\u00032\u0006\u0010V\u001a\u00020\u000bJ\u0016\u0010Y\u001a\u00020\u00052\u0006\u0010S\u001a\u00020T2\u0006\u0010V\u001a\u00020\u000bJ\u000e\u0010Z\u001a\u00020\u00032\u0006\u0010V\u001a\u00020\u000bJ\u0016\u0010[\u001a\u00020\u00052\u0006\u0010S\u001a\u00020T2\u0006\u0010V\u001a\u00020\u000bJ\t\u0010\\\u001a\u00020\u0005HÖ\u0001J\u0006\u0010]\u001a\u00020\u000bJ\b\u0010^\u001a\u00020\u000bH\u0002J\t\u0010_\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0017\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001dR\u0015\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010!\u001a\u0004\b\u001f\u0010 R\u0015\u0010\r\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010!\u001a\u0004\b\"\u0010 R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010!\u001a\u0004\b#\u0010 R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b$\u0010\u001dR\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b%\u0010\u001dR\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b(\u0010\u001dR\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010'R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010'R\u0015\u0010\u0013\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010!\u001a\u0004\b+\u0010 R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010'R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010'R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b.\u0010\u001dR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010/R\u0015\u0010\u0019\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010!\u001a\u0004\b\u0019\u0010 R\u0015\u0010\u001a\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010!\u001a\u0004\b\u001a\u0010 R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b0\u0010\u001dR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b1\u0010'R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b2\u0010\u001dR\u0011\u00103\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b4\u0010'R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b5\u0010\u001dR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b6\u00107¨\u0006`"}, d2 = {"Lorg/informatika/sirekap/ui/verify/FormC1ListItem;", "", JobType.ID, "", BooleanUtils.NO, "", "label", OperatorName.LINE_TO, "p", "total", "isChecked", "", "checkL", "checkP", "checkTotal", FirebaseAnalytics.Param.INDEX, "correctedL", "correctedP", "correctedTotal", "crossItemArithmeticValid", "crossItemArithmeticInvalidMessage", "croppedPhotoPath", "correctedPhotoPath", "candidateNum", "electionType", "isLN", "isPOS", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;IZLjava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "getCandidateNum", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCheckL", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getCheckP", "getCheckTotal", "getCorrectedL", "getCorrectedP", "getCorrectedPhotoPath", "()Ljava/lang/String;", "getCorrectedTotal", "getCroppedPhotoPath", "getCrossItemArithmeticInvalidMessage", "getCrossItemArithmeticValid", "getElectionType", "getId", "getIndex", "()Z", "getL", "getLabel", "getNo", "noString", "getNoString", "getP", "getTotal", "()I", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;IZLjava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lorg/informatika/sirekap/ui/verify/FormC1ListItem;", "equals", "other", "getArithmeticErrorMesssage", "context", "Landroid/content/Context;", "getLFinalValue", "showCorrection", "getLTextColor", "getPFinalValue", "getPTextColor", "getTotalFinalValue", "getTotalTextColor", "hashCode", "isAllArithmeticsValid", "isTotalValid", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class FormC1ListItem {
    private final Integer candidateNum;
    private final Boolean checkL;
    private final Boolean checkP;
    private final Boolean checkTotal;
    private final Integer correctedL;
    private final Integer correctedP;
    private final String correctedPhotoPath;
    private final Integer correctedTotal;
    private final String croppedPhotoPath;
    private final String crossItemArithmeticInvalidMessage;
    private final Boolean crossItemArithmeticValid;
    private final String electionType;

    /* renamed from: id */
    private final String f75id;
    private final Integer index;
    private final boolean isChecked;
    private final Boolean isLN;
    private final Boolean isPOS;
    private final Integer l;
    private final String label;
    private final Integer no;
    private final String noString;
    private final Integer p;
    private final int total;

    public final String component1() {
        return this.f75id;
    }

    public final Boolean component10() {
        return this.checkTotal;
    }

    public final Integer component11() {
        return this.index;
    }

    public final Integer component12() {
        return this.correctedL;
    }

    public final Integer component13() {
        return this.correctedP;
    }

    public final Integer component14() {
        return this.correctedTotal;
    }

    public final Boolean component15() {
        return this.crossItemArithmeticValid;
    }

    public final String component16() {
        return this.crossItemArithmeticInvalidMessage;
    }

    public final String component17() {
        return this.croppedPhotoPath;
    }

    public final String component18() {
        return this.correctedPhotoPath;
    }

    public final Integer component19() {
        return this.candidateNum;
    }

    public final Integer component2() {
        return this.no;
    }

    public final String component20() {
        return this.electionType;
    }

    public final Boolean component21() {
        return this.isLN;
    }

    public final Boolean component22() {
        return this.isPOS;
    }

    public final String component3() {
        return this.label;
    }

    public final Integer component4() {
        return this.l;
    }

    public final Integer component5() {
        return this.p;
    }

    public final int component6() {
        return this.total;
    }

    public final boolean component7() {
        return this.isChecked;
    }

    public final Boolean component8() {
        return this.checkL;
    }

    public final Boolean component9() {
        return this.checkP;
    }

    public final FormC1ListItem copy(String id2, Integer num, String label, Integer num2, Integer num3, int i, boolean z, Boolean bool, Boolean bool2, Boolean bool3, Integer num4, Integer num5, Integer num6, Integer num7, Boolean bool4, String str, String str2, String str3, Integer num8, String str4, Boolean bool5, Boolean bool6) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(label, "label");
        return new FormC1ListItem(id2, num, label, num2, num3, i, z, bool, bool2, bool3, num4, num5, num6, num7, bool4, str, str2, str3, num8, str4, bool5, bool6);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FormC1ListItem) {
            FormC1ListItem formC1ListItem = (FormC1ListItem) obj;
            return Intrinsics.areEqual(this.f75id, formC1ListItem.f75id) && Intrinsics.areEqual(this.no, formC1ListItem.no) && Intrinsics.areEqual(this.label, formC1ListItem.label) && Intrinsics.areEqual(this.l, formC1ListItem.l) && Intrinsics.areEqual(this.p, formC1ListItem.p) && this.total == formC1ListItem.total && this.isChecked == formC1ListItem.isChecked && Intrinsics.areEqual(this.checkL, formC1ListItem.checkL) && Intrinsics.areEqual(this.checkP, formC1ListItem.checkP) && Intrinsics.areEqual(this.checkTotal, formC1ListItem.checkTotal) && Intrinsics.areEqual(this.index, formC1ListItem.index) && Intrinsics.areEqual(this.correctedL, formC1ListItem.correctedL) && Intrinsics.areEqual(this.correctedP, formC1ListItem.correctedP) && Intrinsics.areEqual(this.correctedTotal, formC1ListItem.correctedTotal) && Intrinsics.areEqual(this.crossItemArithmeticValid, formC1ListItem.crossItemArithmeticValid) && Intrinsics.areEqual(this.crossItemArithmeticInvalidMessage, formC1ListItem.crossItemArithmeticInvalidMessage) && Intrinsics.areEqual(this.croppedPhotoPath, formC1ListItem.croppedPhotoPath) && Intrinsics.areEqual(this.correctedPhotoPath, formC1ListItem.correctedPhotoPath) && Intrinsics.areEqual(this.candidateNum, formC1ListItem.candidateNum) && Intrinsics.areEqual(this.electionType, formC1ListItem.electionType) && Intrinsics.areEqual(this.isLN, formC1ListItem.isLN) && Intrinsics.areEqual(this.isPOS, formC1ListItem.isPOS);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = this.f75id.hashCode() * 31;
        Integer num = this.no;
        int hashCode2 = (((hashCode + (num == null ? 0 : num.hashCode())) * 31) + this.label.hashCode()) * 31;
        Integer num2 = this.l;
        int hashCode3 = (hashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.p;
        int hashCode4 = (((hashCode3 + (num3 == null ? 0 : num3.hashCode())) * 31) + Integer.hashCode(this.total)) * 31;
        boolean z = this.isChecked;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode4 + i) * 31;
        Boolean bool = this.checkL;
        int hashCode5 = (i2 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.checkP;
        int hashCode6 = (hashCode5 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Boolean bool3 = this.checkTotal;
        int hashCode7 = (hashCode6 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
        Integer num4 = this.index;
        int hashCode8 = (hashCode7 + (num4 == null ? 0 : num4.hashCode())) * 31;
        Integer num5 = this.correctedL;
        int hashCode9 = (hashCode8 + (num5 == null ? 0 : num5.hashCode())) * 31;
        Integer num6 = this.correctedP;
        int hashCode10 = (hashCode9 + (num6 == null ? 0 : num6.hashCode())) * 31;
        Integer num7 = this.correctedTotal;
        int hashCode11 = (hashCode10 + (num7 == null ? 0 : num7.hashCode())) * 31;
        Boolean bool4 = this.crossItemArithmeticValid;
        int hashCode12 = (hashCode11 + (bool4 == null ? 0 : bool4.hashCode())) * 31;
        String str = this.crossItemArithmeticInvalidMessage;
        int hashCode13 = (hashCode12 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.croppedPhotoPath;
        int hashCode14 = (hashCode13 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.correctedPhotoPath;
        int hashCode15 = (hashCode14 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Integer num8 = this.candidateNum;
        int hashCode16 = (hashCode15 + (num8 == null ? 0 : num8.hashCode())) * 31;
        String str4 = this.electionType;
        int hashCode17 = (hashCode16 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Boolean bool5 = this.isLN;
        int hashCode18 = (hashCode17 + (bool5 == null ? 0 : bool5.hashCode())) * 31;
        Boolean bool6 = this.isPOS;
        return hashCode18 + (bool6 != null ? bool6.hashCode() : 0);
    }

    public String toString() {
        String str = this.f75id;
        Integer num = this.no;
        String str2 = this.label;
        Integer num2 = this.l;
        Integer num3 = this.p;
        int i = this.total;
        boolean z = this.isChecked;
        Boolean bool = this.checkL;
        Boolean bool2 = this.checkP;
        Boolean bool3 = this.checkTotal;
        Integer num4 = this.index;
        Integer num5 = this.correctedL;
        Integer num6 = this.correctedP;
        Integer num7 = this.correctedTotal;
        Boolean bool4 = this.crossItemArithmeticValid;
        String str3 = this.crossItemArithmeticInvalidMessage;
        String str4 = this.croppedPhotoPath;
        String str5 = this.correctedPhotoPath;
        Integer num8 = this.candidateNum;
        String str6 = this.electionType;
        Boolean bool5 = this.isLN;
        return "FormC1ListItem(id=" + str + ", no=" + num + ", label=" + str2 + ", l=" + num2 + ", p=" + num3 + ", total=" + i + ", isChecked=" + z + ", checkL=" + bool + ", checkP=" + bool2 + ", checkTotal=" + bool3 + ", index=" + num4 + ", correctedL=" + num5 + ", correctedP=" + num6 + ", correctedTotal=" + num7 + ", crossItemArithmeticValid=" + bool4 + ", crossItemArithmeticInvalidMessage=" + str3 + ", croppedPhotoPath=" + str4 + ", correctedPhotoPath=" + str5 + ", candidateNum=" + num8 + ", electionType=" + str6 + ", isLN=" + bool5 + ", isPOS=" + this.isPOS + ")";
    }

    public FormC1ListItem(String id2, Integer num, String label, Integer num2, Integer num3, int i, boolean z, Boolean bool, Boolean bool2, Boolean bool3, Integer num4, Integer num5, Integer num6, Integer num7, Boolean bool4, String str, String str2, String str3, Integer num8, String str4, Boolean bool5, Boolean bool6) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(label, "label");
        this.f75id = id2;
        this.no = num;
        this.label = label;
        this.l = num2;
        this.p = num3;
        this.total = i;
        this.isChecked = z;
        this.checkL = bool;
        this.checkP = bool2;
        this.checkTotal = bool3;
        this.index = num4;
        this.correctedL = num5;
        this.correctedP = num6;
        this.correctedTotal = num7;
        this.crossItemArithmeticValid = bool4;
        this.crossItemArithmeticInvalidMessage = str;
        this.croppedPhotoPath = str2;
        this.correctedPhotoPath = str3;
        this.candidateNum = num8;
        this.electionType = str4;
        this.isLN = bool5;
        this.isPOS = bool6;
        this.noString = num + ".";
    }

    public /* synthetic */ FormC1ListItem(String str, Integer num, String str2, Integer num2, Integer num3, int i, boolean z, Boolean bool, Boolean bool2, Boolean bool3, Integer num4, Integer num5, Integer num6, Integer num7, Boolean bool4, String str3, String str4, String str5, Integer num8, String str6, Boolean bool5, Boolean bool6, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, num, str2, num2, num3, i, (i2 & 64) != 0 ? false : z, (i2 & 128) != 0 ? null : bool, (i2 & 256) != 0 ? null : bool2, (i2 & 512) != 0 ? null : bool3, (i2 & 1024) != 0 ? null : num4, (i2 & 2048) != 0 ? null : num5, (i2 & 4096) != 0 ? null : num6, (i2 & 8192) != 0 ? null : num7, bool4, str3, (65536 & i2) != 0 ? null : str4, (131072 & i2) != 0 ? null : str5, (262144 & i2) != 0 ? null : num8, (524288 & i2) != 0 ? null : str6, (1048576 & i2) != 0 ? null : bool5, (i2 & 2097152) != 0 ? null : bool6);
    }

    public final String getId() {
        return this.f75id;
    }

    public final Integer getNo() {
        return this.no;
    }

    public final String getLabel() {
        return this.label;
    }

    public final Integer getL() {
        return this.l;
    }

    public final Integer getP() {
        return this.p;
    }

    public final int getTotal() {
        return this.total;
    }

    public final boolean isChecked() {
        return this.isChecked;
    }

    public final Boolean getCheckL() {
        return this.checkL;
    }

    public final Boolean getCheckP() {
        return this.checkP;
    }

    public final Boolean getCheckTotal() {
        return this.checkTotal;
    }

    public final Integer getIndex() {
        return this.index;
    }

    public final Integer getCorrectedL() {
        return this.correctedL;
    }

    public final Integer getCorrectedP() {
        return this.correctedP;
    }

    public final Integer getCorrectedTotal() {
        return this.correctedTotal;
    }

    public final Boolean getCrossItemArithmeticValid() {
        return this.crossItemArithmeticValid;
    }

    public final String getCrossItemArithmeticInvalidMessage() {
        return this.crossItemArithmeticInvalidMessage;
    }

    public final String getCroppedPhotoPath() {
        return this.croppedPhotoPath;
    }

    public final String getCorrectedPhotoPath() {
        return this.correctedPhotoPath;
    }

    public final Integer getCandidateNum() {
        return this.candidateNum;
    }

    public final String getElectionType() {
        return this.electionType;
    }

    public final Boolean isLN() {
        return this.isLN;
    }

    public final Boolean isPOS() {
        return this.isPOS;
    }

    public final String getNoString() {
        return this.noString;
    }

    public final boolean isAllArithmeticsValid() {
        return isTotalValid() && Intrinsics.areEqual((Object) this.crossItemArithmeticValid, (Object) true);
    }

    public final String getArithmeticErrorMesssage(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (!isTotalValid()) {
            return context.getString(R.string.item_form_chasil_total_invalid);
        }
        if (Intrinsics.areEqual((Object) this.crossItemArithmeticValid, (Object) true)) {
            return null;
        }
        return this.crossItemArithmeticInvalidMessage;
    }

    public final String getLFinalValue(boolean z) {
        Integer num;
        if (z && this.isChecked) {
            if (Intrinsics.areEqual((Object) this.checkL, (Object) false) && (num = this.correctedL) != null) {
                return num.toString();
            }
            return String.valueOf(this.l);
        }
        return String.valueOf(this.l);
    }

    private final boolean isTotalValid() {
        Integer num = this.correctedL;
        if (num == null) {
            num = this.l;
        }
        Integer num2 = this.correctedP;
        if (num2 == null) {
            num2 = this.p;
        }
        Integer num3 = this.correctedTotal;
        return num == null || num2 == null || num.intValue() + num2.intValue() == (num3 != null ? num3.intValue() : this.total);
    }

    public final String getPFinalValue(boolean z) {
        Integer num;
        if (z && this.isChecked) {
            if (Intrinsics.areEqual((Object) this.checkP, (Object) false) && (num = this.correctedP) != null) {
                return num.toString();
            }
            return String.valueOf(this.p);
        }
        return String.valueOf(this.p);
    }

    public final String getTotalFinalValue(boolean z) {
        Integer num;
        if (z && this.isChecked) {
            if (Intrinsics.areEqual((Object) this.checkTotal, (Object) false) && (num = this.correctedTotal) != null) {
                return num.toString();
            }
            return String.valueOf(this.total);
        }
        return String.valueOf(this.total);
    }

    public final int getLTextColor(Context context, boolean z) {
        int i;
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.isChecked) {
            if (Intrinsics.areEqual((Object) this.checkL, (Object) true)) {
                i = R.color.colorSuccess;
            } else if (this.correctedL != null && z) {
                i = R.color.colorPrimary;
            } else {
                i = R.color.colorError;
            }
        } else {
            i = R.color.textColorPrimary;
        }
        return ContextCompat.getColor(context, i);
    }

    public final int getPTextColor(Context context, boolean z) {
        int i;
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.isChecked) {
            if (Intrinsics.areEqual((Object) this.checkP, (Object) true)) {
                i = R.color.colorSuccess;
            } else if (this.correctedP != null && z) {
                i = R.color.colorPrimary;
            } else {
                i = R.color.colorError;
            }
        } else {
            i = R.color.textColorPrimary;
        }
        return ContextCompat.getColor(context, i);
    }

    public final int getTotalTextColor(Context context, boolean z) {
        int i;
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.isChecked) {
            if (Intrinsics.areEqual((Object) this.checkTotal, (Object) true)) {
                i = R.color.colorSuccess;
            } else if (this.correctedTotal != null && z) {
                i = R.color.colorPrimary;
            } else {
                i = R.color.colorError;
            }
        } else {
            i = R.color.textColorPrimary;
        }
        return ContextCompat.getColor(context, i);
    }
}
