package org.informatika.sirekap.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FormC1AdministrationHal2Payload.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001:\u0002\u0019\u001aB#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0007HÆ\u0003J-\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0007HÖ\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lorg/informatika/sirekap/model/FormC1AdministrationHal2Payload;", "", "DataSuara", "Lorg/informatika/sirekap/model/FormC1AdministrationHal2Payload$FormC1AdministrationHal2PayloadDataSuara;", "SuaraPaslon", "Lorg/informatika/sirekap/model/FormC1AdministrationHal2Payload$FormC1AdministrationHal2PayloadSuaraPaslon;", "error", "", "(Lorg/informatika/sirekap/model/FormC1AdministrationHal2Payload$FormC1AdministrationHal2PayloadDataSuara;Lorg/informatika/sirekap/model/FormC1AdministrationHal2Payload$FormC1AdministrationHal2PayloadSuaraPaslon;Ljava/lang/String;)V", "getDataSuara", "()Lorg/informatika/sirekap/model/FormC1AdministrationHal2Payload$FormC1AdministrationHal2PayloadDataSuara;", "getSuaraPaslon", "()Lorg/informatika/sirekap/model/FormC1AdministrationHal2Payload$FormC1AdministrationHal2PayloadSuaraPaslon;", "getError", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "FormC1AdministrationHal2PayloadDataSuara", "FormC1AdministrationHal2PayloadSuaraPaslon", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormC1AdministrationHal2Payload {
    @SerializedName("DataSuara")
    private final FormC1AdministrationHal2PayloadDataSuara DataSuara;
    @SerializedName("SuaraPaslon")
    private final FormC1AdministrationHal2PayloadSuaraPaslon SuaraPaslon;
    @SerializedName("error")
    private final String error;

    public static /* synthetic */ FormC1AdministrationHal2Payload copy$default(FormC1AdministrationHal2Payload formC1AdministrationHal2Payload, FormC1AdministrationHal2PayloadDataSuara formC1AdministrationHal2PayloadDataSuara, FormC1AdministrationHal2PayloadSuaraPaslon formC1AdministrationHal2PayloadSuaraPaslon, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            formC1AdministrationHal2PayloadDataSuara = formC1AdministrationHal2Payload.DataSuara;
        }
        if ((i & 2) != 0) {
            formC1AdministrationHal2PayloadSuaraPaslon = formC1AdministrationHal2Payload.SuaraPaslon;
        }
        if ((i & 4) != 0) {
            str = formC1AdministrationHal2Payload.error;
        }
        return formC1AdministrationHal2Payload.copy(formC1AdministrationHal2PayloadDataSuara, formC1AdministrationHal2PayloadSuaraPaslon, str);
    }

    public final FormC1AdministrationHal2PayloadDataSuara component1() {
        return this.DataSuara;
    }

    public final FormC1AdministrationHal2PayloadSuaraPaslon component2() {
        return this.SuaraPaslon;
    }

    public final String component3() {
        return this.error;
    }

    public final FormC1AdministrationHal2Payload copy(FormC1AdministrationHal2PayloadDataSuara formC1AdministrationHal2PayloadDataSuara, FormC1AdministrationHal2PayloadSuaraPaslon formC1AdministrationHal2PayloadSuaraPaslon, String str) {
        return new FormC1AdministrationHal2Payload(formC1AdministrationHal2PayloadDataSuara, formC1AdministrationHal2PayloadSuaraPaslon, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FormC1AdministrationHal2Payload) {
            FormC1AdministrationHal2Payload formC1AdministrationHal2Payload = (FormC1AdministrationHal2Payload) obj;
            return Intrinsics.areEqual(this.DataSuara, formC1AdministrationHal2Payload.DataSuara) && Intrinsics.areEqual(this.SuaraPaslon, formC1AdministrationHal2Payload.SuaraPaslon) && Intrinsics.areEqual(this.error, formC1AdministrationHal2Payload.error);
        }
        return false;
    }

    public int hashCode() {
        FormC1AdministrationHal2PayloadDataSuara formC1AdministrationHal2PayloadDataSuara = this.DataSuara;
        int hashCode = (formC1AdministrationHal2PayloadDataSuara == null ? 0 : formC1AdministrationHal2PayloadDataSuara.hashCode()) * 31;
        FormC1AdministrationHal2PayloadSuaraPaslon formC1AdministrationHal2PayloadSuaraPaslon = this.SuaraPaslon;
        int hashCode2 = (hashCode + (formC1AdministrationHal2PayloadSuaraPaslon == null ? 0 : formC1AdministrationHal2PayloadSuaraPaslon.hashCode())) * 31;
        String str = this.error;
        return hashCode2 + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        FormC1AdministrationHal2PayloadDataSuara formC1AdministrationHal2PayloadDataSuara = this.DataSuara;
        FormC1AdministrationHal2PayloadSuaraPaslon formC1AdministrationHal2PayloadSuaraPaslon = this.SuaraPaslon;
        return "FormC1AdministrationHal2Payload(DataSuara=" + formC1AdministrationHal2PayloadDataSuara + ", SuaraPaslon=" + formC1AdministrationHal2PayloadSuaraPaslon + ", error=" + this.error + ")";
    }

    public FormC1AdministrationHal2Payload(FormC1AdministrationHal2PayloadDataSuara formC1AdministrationHal2PayloadDataSuara, FormC1AdministrationHal2PayloadSuaraPaslon formC1AdministrationHal2PayloadSuaraPaslon, String str) {
        this.DataSuara = formC1AdministrationHal2PayloadDataSuara;
        this.SuaraPaslon = formC1AdministrationHal2PayloadSuaraPaslon;
        this.error = str;
    }

    public final FormC1AdministrationHal2PayloadDataSuara getDataSuara() {
        return this.DataSuara;
    }

    public final FormC1AdministrationHal2PayloadSuaraPaslon getSuaraPaslon() {
        return this.SuaraPaslon;
    }

    public final String getError() {
        return this.error;
    }

    /* compiled from: FormC1AdministrationHal2Payload.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0003¢\u0006\u0002\u0010\u0005J\u0015\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0003HÆ\u0003J\u001f\u0010\t\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u0004HÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\"\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lorg/informatika/sirekap/model/FormC1AdministrationHal2Payload$FormC1AdministrationHal2PayloadDataSuara;", "", "PredComb", "", "", "(Ljava/util/List;)V", "getPredComb", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class FormC1AdministrationHal2PayloadDataSuara {
        @SerializedName("PredComb")
        private final List<List<Integer>> PredComb;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ FormC1AdministrationHal2PayloadDataSuara copy$default(FormC1AdministrationHal2PayloadDataSuara formC1AdministrationHal2PayloadDataSuara, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = formC1AdministrationHal2PayloadDataSuara.PredComb;
            }
            return formC1AdministrationHal2PayloadDataSuara.copy(list);
        }

        public final List<List<Integer>> component1() {
            return this.PredComb;
        }

        public final FormC1AdministrationHal2PayloadDataSuara copy(List<? extends List<Integer>> PredComb) {
            Intrinsics.checkNotNullParameter(PredComb, "PredComb");
            return new FormC1AdministrationHal2PayloadDataSuara(PredComb);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof FormC1AdministrationHal2PayloadDataSuara) && Intrinsics.areEqual(this.PredComb, ((FormC1AdministrationHal2PayloadDataSuara) obj).PredComb);
        }

        public int hashCode() {
            return this.PredComb.hashCode();
        }

        public String toString() {
            return "FormC1AdministrationHal2PayloadDataSuara(PredComb=" + this.PredComb + ")";
        }

        /* JADX WARN: Multi-variable type inference failed */
        public FormC1AdministrationHal2PayloadDataSuara(List<? extends List<Integer>> PredComb) {
            Intrinsics.checkNotNullParameter(PredComb, "PredComb");
            this.PredComb = PredComb;
        }

        public final List<List<Integer>> getPredComb() {
            return this.PredComb;
        }
    }

    /* compiled from: FormC1AdministrationHal2Payload.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0003¢\u0006\u0002\u0010\u0005J\u0015\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0003HÆ\u0003J\u001f\u0010\t\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u0004HÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\"\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lorg/informatika/sirekap/model/FormC1AdministrationHal2Payload$FormC1AdministrationHal2PayloadSuaraPaslon;", "", "PredComb", "", "", "(Ljava/util/List;)V", "getPredComb", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class FormC1AdministrationHal2PayloadSuaraPaslon {
        @SerializedName("PredComb")
        private final List<List<Integer>> PredComb;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ FormC1AdministrationHal2PayloadSuaraPaslon copy$default(FormC1AdministrationHal2PayloadSuaraPaslon formC1AdministrationHal2PayloadSuaraPaslon, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = formC1AdministrationHal2PayloadSuaraPaslon.PredComb;
            }
            return formC1AdministrationHal2PayloadSuaraPaslon.copy(list);
        }

        public final List<List<Integer>> component1() {
            return this.PredComb;
        }

        public final FormC1AdministrationHal2PayloadSuaraPaslon copy(List<? extends List<Integer>> PredComb) {
            Intrinsics.checkNotNullParameter(PredComb, "PredComb");
            return new FormC1AdministrationHal2PayloadSuaraPaslon(PredComb);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof FormC1AdministrationHal2PayloadSuaraPaslon) && Intrinsics.areEqual(this.PredComb, ((FormC1AdministrationHal2PayloadSuaraPaslon) obj).PredComb);
        }

        public int hashCode() {
            return this.PredComb.hashCode();
        }

        public String toString() {
            return "FormC1AdministrationHal2PayloadSuaraPaslon(PredComb=" + this.PredComb + ")";
        }

        /* JADX WARN: Multi-variable type inference failed */
        public FormC1AdministrationHal2PayloadSuaraPaslon(List<? extends List<Integer>> PredComb) {
            Intrinsics.checkNotNullParameter(PredComb, "PredComb");
            this.PredComb = PredComb;
        }

        public final List<List<Integer>> getPredComb() {
            return this.PredComb;
        }
    }
}
