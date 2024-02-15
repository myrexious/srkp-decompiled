package org.informatika.sirekap.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FormC1TabulationPayload.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001:\u0003\u001e\u001f B-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\tHÆ\u0003J9\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\tHÖ\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0018\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006!"}, d2 = {"Lorg/informatika/sirekap/model/FormC1TabulationPayload;", "", "DataSuara", "Lorg/informatika/sirekap/model/FormC1TabulationPayload$FormC1TabulationPayloadDataSuara;", "JumlahSuara", "Lorg/informatika/sirekap/model/FormC1TabulationPayload$FormC1TabulationPayloadJumlahSuara;", "SuaraPaslon", "Lorg/informatika/sirekap/model/FormC1TabulationPayload$FormC1TabulationPayloadSuaraPaslon;", "error", "", "(Lorg/informatika/sirekap/model/FormC1TabulationPayload$FormC1TabulationPayloadDataSuara;Lorg/informatika/sirekap/model/FormC1TabulationPayload$FormC1TabulationPayloadJumlahSuara;Lorg/informatika/sirekap/model/FormC1TabulationPayload$FormC1TabulationPayloadSuaraPaslon;Ljava/lang/String;)V", "getDataSuara", "()Lorg/informatika/sirekap/model/FormC1TabulationPayload$FormC1TabulationPayloadDataSuara;", "getJumlahSuara", "()Lorg/informatika/sirekap/model/FormC1TabulationPayload$FormC1TabulationPayloadJumlahSuara;", "getSuaraPaslon", "()Lorg/informatika/sirekap/model/FormC1TabulationPayload$FormC1TabulationPayloadSuaraPaslon;", "getError", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "FormC1TabulationPayloadDataSuara", "FormC1TabulationPayloadJumlahSuara", "FormC1TabulationPayloadSuaraPaslon", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormC1TabulationPayload {
    @SerializedName("DataSuara")
    private final FormC1TabulationPayloadDataSuara DataSuara;
    @SerializedName("JumlahSuara")
    private final FormC1TabulationPayloadJumlahSuara JumlahSuara;
    @SerializedName("SuaraPaslon")
    private final FormC1TabulationPayloadSuaraPaslon SuaraPaslon;
    @SerializedName("error")
    private final String error;

    public static /* synthetic */ FormC1TabulationPayload copy$default(FormC1TabulationPayload formC1TabulationPayload, FormC1TabulationPayloadDataSuara formC1TabulationPayloadDataSuara, FormC1TabulationPayloadJumlahSuara formC1TabulationPayloadJumlahSuara, FormC1TabulationPayloadSuaraPaslon formC1TabulationPayloadSuaraPaslon, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            formC1TabulationPayloadDataSuara = formC1TabulationPayload.DataSuara;
        }
        if ((i & 2) != 0) {
            formC1TabulationPayloadJumlahSuara = formC1TabulationPayload.JumlahSuara;
        }
        if ((i & 4) != 0) {
            formC1TabulationPayloadSuaraPaslon = formC1TabulationPayload.SuaraPaslon;
        }
        if ((i & 8) != 0) {
            str = formC1TabulationPayload.error;
        }
        return formC1TabulationPayload.copy(formC1TabulationPayloadDataSuara, formC1TabulationPayloadJumlahSuara, formC1TabulationPayloadSuaraPaslon, str);
    }

    public final FormC1TabulationPayloadDataSuara component1() {
        return this.DataSuara;
    }

    public final FormC1TabulationPayloadJumlahSuara component2() {
        return this.JumlahSuara;
    }

    public final FormC1TabulationPayloadSuaraPaslon component3() {
        return this.SuaraPaslon;
    }

    public final String component4() {
        return this.error;
    }

    public final FormC1TabulationPayload copy(FormC1TabulationPayloadDataSuara formC1TabulationPayloadDataSuara, FormC1TabulationPayloadJumlahSuara formC1TabulationPayloadJumlahSuara, FormC1TabulationPayloadSuaraPaslon formC1TabulationPayloadSuaraPaslon, String str) {
        return new FormC1TabulationPayload(formC1TabulationPayloadDataSuara, formC1TabulationPayloadJumlahSuara, formC1TabulationPayloadSuaraPaslon, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FormC1TabulationPayload) {
            FormC1TabulationPayload formC1TabulationPayload = (FormC1TabulationPayload) obj;
            return Intrinsics.areEqual(this.DataSuara, formC1TabulationPayload.DataSuara) && Intrinsics.areEqual(this.JumlahSuara, formC1TabulationPayload.JumlahSuara) && Intrinsics.areEqual(this.SuaraPaslon, formC1TabulationPayload.SuaraPaslon) && Intrinsics.areEqual(this.error, formC1TabulationPayload.error);
        }
        return false;
    }

    public int hashCode() {
        FormC1TabulationPayloadDataSuara formC1TabulationPayloadDataSuara = this.DataSuara;
        int hashCode = (formC1TabulationPayloadDataSuara == null ? 0 : formC1TabulationPayloadDataSuara.hashCode()) * 31;
        FormC1TabulationPayloadJumlahSuara formC1TabulationPayloadJumlahSuara = this.JumlahSuara;
        int hashCode2 = (hashCode + (formC1TabulationPayloadJumlahSuara == null ? 0 : formC1TabulationPayloadJumlahSuara.hashCode())) * 31;
        FormC1TabulationPayloadSuaraPaslon formC1TabulationPayloadSuaraPaslon = this.SuaraPaslon;
        int hashCode3 = (hashCode2 + (formC1TabulationPayloadSuaraPaslon == null ? 0 : formC1TabulationPayloadSuaraPaslon.hashCode())) * 31;
        String str = this.error;
        return hashCode3 + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        FormC1TabulationPayloadDataSuara formC1TabulationPayloadDataSuara = this.DataSuara;
        FormC1TabulationPayloadJumlahSuara formC1TabulationPayloadJumlahSuara = this.JumlahSuara;
        FormC1TabulationPayloadSuaraPaslon formC1TabulationPayloadSuaraPaslon = this.SuaraPaslon;
        return "FormC1TabulationPayload(DataSuara=" + formC1TabulationPayloadDataSuara + ", JumlahSuara=" + formC1TabulationPayloadJumlahSuara + ", SuaraPaslon=" + formC1TabulationPayloadSuaraPaslon + ", error=" + this.error + ")";
    }

    public FormC1TabulationPayload(FormC1TabulationPayloadDataSuara formC1TabulationPayloadDataSuara, FormC1TabulationPayloadJumlahSuara formC1TabulationPayloadJumlahSuara, FormC1TabulationPayloadSuaraPaslon formC1TabulationPayloadSuaraPaslon, String str) {
        this.DataSuara = formC1TabulationPayloadDataSuara;
        this.JumlahSuara = formC1TabulationPayloadJumlahSuara;
        this.SuaraPaslon = formC1TabulationPayloadSuaraPaslon;
        this.error = str;
    }

    public final FormC1TabulationPayloadDataSuara getDataSuara() {
        return this.DataSuara;
    }

    public final FormC1TabulationPayloadJumlahSuara getJumlahSuara() {
        return this.JumlahSuara;
    }

    public final FormC1TabulationPayloadSuaraPaslon getSuaraPaslon() {
        return this.SuaraPaslon;
    }

    public final String getError() {
        return this.error;
    }

    /* compiled from: FormC1TabulationPayload.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0003¢\u0006\u0002\u0010\u0005J\u0015\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0003HÆ\u0003J\u001f\u0010\t\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u0004HÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\"\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lorg/informatika/sirekap/model/FormC1TabulationPayload$FormC1TabulationPayloadDataSuara;", "", "PredComb", "", "", "(Ljava/util/List;)V", "getPredComb", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class FormC1TabulationPayloadDataSuara {
        @SerializedName("PredComb")
        private final List<List<Integer>> PredComb;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ FormC1TabulationPayloadDataSuara copy$default(FormC1TabulationPayloadDataSuara formC1TabulationPayloadDataSuara, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = formC1TabulationPayloadDataSuara.PredComb;
            }
            return formC1TabulationPayloadDataSuara.copy(list);
        }

        public final List<List<Integer>> component1() {
            return this.PredComb;
        }

        public final FormC1TabulationPayloadDataSuara copy(List<? extends List<Integer>> PredComb) {
            Intrinsics.checkNotNullParameter(PredComb, "PredComb");
            return new FormC1TabulationPayloadDataSuara(PredComb);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof FormC1TabulationPayloadDataSuara) && Intrinsics.areEqual(this.PredComb, ((FormC1TabulationPayloadDataSuara) obj).PredComb);
        }

        public int hashCode() {
            return this.PredComb.hashCode();
        }

        public String toString() {
            return "FormC1TabulationPayloadDataSuara(PredComb=" + this.PredComb + ")";
        }

        /* JADX WARN: Multi-variable type inference failed */
        public FormC1TabulationPayloadDataSuara(List<? extends List<Integer>> PredComb) {
            Intrinsics.checkNotNullParameter(PredComb, "PredComb");
            this.PredComb = PredComb;
        }

        public final List<List<Integer>> getPredComb() {
            return this.PredComb;
        }
    }

    /* compiled from: FormC1TabulationPayload.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0003¢\u0006\u0002\u0010\u0005J\u0015\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0003HÆ\u0003J\u001f\u0010\t\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u0004HÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\"\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lorg/informatika/sirekap/model/FormC1TabulationPayload$FormC1TabulationPayloadJumlahSuara;", "", "PredComb", "", "", "(Ljava/util/List;)V", "getPredComb", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class FormC1TabulationPayloadJumlahSuara {
        @SerializedName("PredComb")
        private final List<List<Integer>> PredComb;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ FormC1TabulationPayloadJumlahSuara copy$default(FormC1TabulationPayloadJumlahSuara formC1TabulationPayloadJumlahSuara, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = formC1TabulationPayloadJumlahSuara.PredComb;
            }
            return formC1TabulationPayloadJumlahSuara.copy(list);
        }

        public final List<List<Integer>> component1() {
            return this.PredComb;
        }

        public final FormC1TabulationPayloadJumlahSuara copy(List<? extends List<Integer>> PredComb) {
            Intrinsics.checkNotNullParameter(PredComb, "PredComb");
            return new FormC1TabulationPayloadJumlahSuara(PredComb);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof FormC1TabulationPayloadJumlahSuara) && Intrinsics.areEqual(this.PredComb, ((FormC1TabulationPayloadJumlahSuara) obj).PredComb);
        }

        public int hashCode() {
            return this.PredComb.hashCode();
        }

        public String toString() {
            return "FormC1TabulationPayloadJumlahSuara(PredComb=" + this.PredComb + ")";
        }

        /* JADX WARN: Multi-variable type inference failed */
        public FormC1TabulationPayloadJumlahSuara(List<? extends List<Integer>> PredComb) {
            Intrinsics.checkNotNullParameter(PredComb, "PredComb");
            this.PredComb = PredComb;
        }

        public final List<List<Integer>> getPredComb() {
            return this.PredComb;
        }
    }

    /* compiled from: FormC1TabulationPayload.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0003¢\u0006\u0002\u0010\u0005J\u0015\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0003HÆ\u0003J\u001f\u0010\t\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u0004HÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\"\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lorg/informatika/sirekap/model/FormC1TabulationPayload$FormC1TabulationPayloadSuaraPaslon;", "", "PredComb", "", "", "(Ljava/util/List;)V", "getPredComb", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class FormC1TabulationPayloadSuaraPaslon {
        @SerializedName("PredComb")
        private final List<List<Integer>> PredComb;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ FormC1TabulationPayloadSuaraPaslon copy$default(FormC1TabulationPayloadSuaraPaslon formC1TabulationPayloadSuaraPaslon, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = formC1TabulationPayloadSuaraPaslon.PredComb;
            }
            return formC1TabulationPayloadSuaraPaslon.copy(list);
        }

        public final List<List<Integer>> component1() {
            return this.PredComb;
        }

        public final FormC1TabulationPayloadSuaraPaslon copy(List<? extends List<Integer>> PredComb) {
            Intrinsics.checkNotNullParameter(PredComb, "PredComb");
            return new FormC1TabulationPayloadSuaraPaslon(PredComb);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof FormC1TabulationPayloadSuaraPaslon) && Intrinsics.areEqual(this.PredComb, ((FormC1TabulationPayloadSuaraPaslon) obj).PredComb);
        }

        public int hashCode() {
            return this.PredComb.hashCode();
        }

        public String toString() {
            return "FormC1TabulationPayloadSuaraPaslon(PredComb=" + this.PredComb + ")";
        }

        /* JADX WARN: Multi-variable type inference failed */
        public FormC1TabulationPayloadSuaraPaslon(List<? extends List<Integer>> PredComb) {
            Intrinsics.checkNotNullParameter(PredComb, "PredComb");
            this.PredComb = PredComb;
        }

        public final List<List<Integer>> getPredComb() {
            return this.PredComb;
        }
    }
}
