package org.informatika.sirekap.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FormC1AdministrationPayload.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u001aB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J3\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lorg/informatika/sirekap/model/FormC1AdministrationPayload;", "", "I", "Lorg/informatika/sirekap/model/FormC1AdministrationPayload$FormC1AdministrationPayloadDetail;", "II", "III", "error", "", "(Lorg/informatika/sirekap/model/FormC1AdministrationPayload$FormC1AdministrationPayloadDetail;Lorg/informatika/sirekap/model/FormC1AdministrationPayload$FormC1AdministrationPayloadDetail;Lorg/informatika/sirekap/model/FormC1AdministrationPayload$FormC1AdministrationPayloadDetail;Ljava/lang/String;)V", "getI", "()Lorg/informatika/sirekap/model/FormC1AdministrationPayload$FormC1AdministrationPayloadDetail;", "getII", "getIII", "getError", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "FormC1AdministrationPayloadDetail", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormC1AdministrationPayload {
    @SerializedName("I")
    private final FormC1AdministrationPayloadDetail I;
    @SerializedName("II")
    private final FormC1AdministrationPayloadDetail II;
    @SerializedName("III")
    private final FormC1AdministrationPayloadDetail III;
    @SerializedName("error")
    private final String error;

    public static /* synthetic */ FormC1AdministrationPayload copy$default(FormC1AdministrationPayload formC1AdministrationPayload, FormC1AdministrationPayloadDetail formC1AdministrationPayloadDetail, FormC1AdministrationPayloadDetail formC1AdministrationPayloadDetail2, FormC1AdministrationPayloadDetail formC1AdministrationPayloadDetail3, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            formC1AdministrationPayloadDetail = formC1AdministrationPayload.I;
        }
        if ((i & 2) != 0) {
            formC1AdministrationPayloadDetail2 = formC1AdministrationPayload.II;
        }
        if ((i & 4) != 0) {
            formC1AdministrationPayloadDetail3 = formC1AdministrationPayload.III;
        }
        if ((i & 8) != 0) {
            str = formC1AdministrationPayload.error;
        }
        return formC1AdministrationPayload.copy(formC1AdministrationPayloadDetail, formC1AdministrationPayloadDetail2, formC1AdministrationPayloadDetail3, str);
    }

    public final FormC1AdministrationPayloadDetail component1() {
        return this.I;
    }

    public final FormC1AdministrationPayloadDetail component2() {
        return this.II;
    }

    public final FormC1AdministrationPayloadDetail component3() {
        return this.III;
    }

    public final String component4() {
        return this.error;
    }

    public final FormC1AdministrationPayload copy(FormC1AdministrationPayloadDetail I, FormC1AdministrationPayloadDetail II, FormC1AdministrationPayloadDetail III, String str) {
        Intrinsics.checkNotNullParameter(I, "I");
        Intrinsics.checkNotNullParameter(II, "II");
        Intrinsics.checkNotNullParameter(III, "III");
        return new FormC1AdministrationPayload(I, II, III, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FormC1AdministrationPayload) {
            FormC1AdministrationPayload formC1AdministrationPayload = (FormC1AdministrationPayload) obj;
            return Intrinsics.areEqual(this.I, formC1AdministrationPayload.I) && Intrinsics.areEqual(this.II, formC1AdministrationPayload.II) && Intrinsics.areEqual(this.III, formC1AdministrationPayload.III) && Intrinsics.areEqual(this.error, formC1AdministrationPayload.error);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((((this.I.hashCode() * 31) + this.II.hashCode()) * 31) + this.III.hashCode()) * 31;
        String str = this.error;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        FormC1AdministrationPayloadDetail formC1AdministrationPayloadDetail = this.I;
        FormC1AdministrationPayloadDetail formC1AdministrationPayloadDetail2 = this.II;
        FormC1AdministrationPayloadDetail formC1AdministrationPayloadDetail3 = this.III;
        return "FormC1AdministrationPayload(I=" + formC1AdministrationPayloadDetail + ", II=" + formC1AdministrationPayloadDetail2 + ", III=" + formC1AdministrationPayloadDetail3 + ", error=" + this.error + ")";
    }

    public FormC1AdministrationPayload(FormC1AdministrationPayloadDetail I, FormC1AdministrationPayloadDetail II, FormC1AdministrationPayloadDetail III, String str) {
        Intrinsics.checkNotNullParameter(I, "I");
        Intrinsics.checkNotNullParameter(II, "II");
        Intrinsics.checkNotNullParameter(III, "III");
        this.I = I;
        this.II = II;
        this.III = III;
        this.error = str;
    }

    public final FormC1AdministrationPayloadDetail getI() {
        return this.I;
    }

    public final FormC1AdministrationPayloadDetail getII() {
        return this.II;
    }

    public final FormC1AdministrationPayloadDetail getIII() {
        return this.III;
    }

    public final String getError() {
        return this.error;
    }

    /* compiled from: FormC1AdministrationPayload.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0003\u0012\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00030\u0003¢\u0006\u0002\u0010\u0007J\u0015\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0003HÆ\u0003J\u0015\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00030\u0003HÆ\u0003J5\u0010\r\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00032\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00030\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\"\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00030\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\"\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0014"}, d2 = {"Lorg/informatika/sirekap/model/FormC1AdministrationPayload$FormC1AdministrationPayloadDetail;", "", "HasilPrediksi", "", "", "ConfidenceScore", "", "(Ljava/util/List;Ljava/util/List;)V", "getConfidenceScore", "()Ljava/util/List;", "getHasilPrediksi", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class FormC1AdministrationPayloadDetail {
        @SerializedName("ConfidenceScore")
        private final List<List<Float>> ConfidenceScore;
        @SerializedName("HasilPrediksi")
        private final List<List<Integer>> HasilPrediksi;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ FormC1AdministrationPayloadDetail copy$default(FormC1AdministrationPayloadDetail formC1AdministrationPayloadDetail, List list, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                list = formC1AdministrationPayloadDetail.HasilPrediksi;
            }
            if ((i & 2) != 0) {
                list2 = formC1AdministrationPayloadDetail.ConfidenceScore;
            }
            return formC1AdministrationPayloadDetail.copy(list, list2);
        }

        public final List<List<Integer>> component1() {
            return this.HasilPrediksi;
        }

        public final List<List<Float>> component2() {
            return this.ConfidenceScore;
        }

        public final FormC1AdministrationPayloadDetail copy(List<? extends List<Integer>> HasilPrediksi, List<? extends List<Float>> ConfidenceScore) {
            Intrinsics.checkNotNullParameter(HasilPrediksi, "HasilPrediksi");
            Intrinsics.checkNotNullParameter(ConfidenceScore, "ConfidenceScore");
            return new FormC1AdministrationPayloadDetail(HasilPrediksi, ConfidenceScore);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof FormC1AdministrationPayloadDetail) {
                FormC1AdministrationPayloadDetail formC1AdministrationPayloadDetail = (FormC1AdministrationPayloadDetail) obj;
                return Intrinsics.areEqual(this.HasilPrediksi, formC1AdministrationPayloadDetail.HasilPrediksi) && Intrinsics.areEqual(this.ConfidenceScore, formC1AdministrationPayloadDetail.ConfidenceScore);
            }
            return false;
        }

        public int hashCode() {
            return (this.HasilPrediksi.hashCode() * 31) + this.ConfidenceScore.hashCode();
        }

        public String toString() {
            List<List<Integer>> list = this.HasilPrediksi;
            return "FormC1AdministrationPayloadDetail(HasilPrediksi=" + list + ", ConfidenceScore=" + this.ConfidenceScore + ")";
        }

        /* JADX WARN: Multi-variable type inference failed */
        public FormC1AdministrationPayloadDetail(List<? extends List<Integer>> HasilPrediksi, List<? extends List<Float>> ConfidenceScore) {
            Intrinsics.checkNotNullParameter(HasilPrediksi, "HasilPrediksi");
            Intrinsics.checkNotNullParameter(ConfidenceScore, "ConfidenceScore");
            this.HasilPrediksi = HasilPrediksi;
            this.ConfidenceScore = ConfidenceScore;
        }

        public final List<List<Integer>> getHasilPrediksi() {
            return this.HasilPrediksi;
        }

        public final List<List<Float>> getConfidenceScore() {
            return this.ConfidenceScore;
        }
    }
}
