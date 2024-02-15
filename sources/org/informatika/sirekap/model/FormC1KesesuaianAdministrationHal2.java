package org.informatika.sirekap.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FormC1KesesuaianAdministrationHal2.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b \n\u0002\u0010 \n\u0002\b\u0004\b\u0087\b\u0018\u0000 .2\u00020\u0001:\u0001.B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\rJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0011J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0011J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0011J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u0014J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u0014J\u0010\u0010 \u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u0014J`\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010\"J\u0013\u0010#\u001a\u00020\u00062\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\nHÖ\u0001J\u0006\u0010&\u001a\u00020\u0006J\u0006\u0010'\u001a\u00020\u0006J\u0006\u0010(\u001a\u00020\u0006J\u0006\u0010)\u001a\u00020\u0006J\u000e\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060+J\u000e\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0+J\t\u0010-\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0016\u0010\u0011R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0017\u0010\u0014R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0018\u0010\u0011R\u0015\u0010\f\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0019\u0010\u0014¨\u0006/"}, d2 = {"Lorg/informatika/sirekap/model/FormC1KesesuaianAdministrationHal2;", "", "idImage", "", "(Ljava/lang/String;)V", "suratSuaraSah", "", "suratSuaraTidakSah", "totalSuratSuara", "suratSuaraSahCorrected", "", "suratSuaraTidakSahCorrected", "totalSuratSuaraCorrected", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getIdImage", "()Ljava/lang/String;", "getSuratSuaraSah", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getSuratSuaraSahCorrected", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSuratSuaraTidakSah", "getSuratSuaraTidakSahCorrected", "getTotalSuratSuara", "getTotalSuratSuaraCorrected", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lorg/informatika/sirekap/model/FormC1KesesuaianAdministrationHal2;", "equals", "other", "hashCode", "isCheckedAll", "isCheckedRowSuratSuaraSah", "isCheckedRowSuratSuaraTidakSah", "isCheckedRowTotalSuratSuara", "toIsSesuaiPerItem", "", "toKoreksiPerItem", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormC1KesesuaianAdministrationHal2 {
    public static final Companion Companion = new Companion(null);
    private final String idImage;
    private final Boolean suratSuaraSah;
    private final Integer suratSuaraSahCorrected;
    private final Boolean suratSuaraTidakSah;
    private final Integer suratSuaraTidakSahCorrected;
    private final Boolean totalSuratSuara;
    private final Integer totalSuratSuaraCorrected;

    public static /* synthetic */ FormC1KesesuaianAdministrationHal2 copy$default(FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2, String str, Boolean bool, Boolean bool2, Boolean bool3, Integer num, Integer num2, Integer num3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = formC1KesesuaianAdministrationHal2.idImage;
        }
        if ((i & 2) != 0) {
            bool = formC1KesesuaianAdministrationHal2.suratSuaraSah;
        }
        Boolean bool4 = bool;
        if ((i & 4) != 0) {
            bool2 = formC1KesesuaianAdministrationHal2.suratSuaraTidakSah;
        }
        Boolean bool5 = bool2;
        if ((i & 8) != 0) {
            bool3 = formC1KesesuaianAdministrationHal2.totalSuratSuara;
        }
        Boolean bool6 = bool3;
        if ((i & 16) != 0) {
            num = formC1KesesuaianAdministrationHal2.suratSuaraSahCorrected;
        }
        Integer num4 = num;
        if ((i & 32) != 0) {
            num2 = formC1KesesuaianAdministrationHal2.suratSuaraTidakSahCorrected;
        }
        Integer num5 = num2;
        if ((i & 64) != 0) {
            num3 = formC1KesesuaianAdministrationHal2.totalSuratSuaraCorrected;
        }
        return formC1KesesuaianAdministrationHal2.copy(str, bool4, bool5, bool6, num4, num5, num3);
    }

    public final String component1() {
        return this.idImage;
    }

    public final Boolean component2() {
        return this.suratSuaraSah;
    }

    public final Boolean component3() {
        return this.suratSuaraTidakSah;
    }

    public final Boolean component4() {
        return this.totalSuratSuara;
    }

    public final Integer component5() {
        return this.suratSuaraSahCorrected;
    }

    public final Integer component6() {
        return this.suratSuaraTidakSahCorrected;
    }

    public final Integer component7() {
        return this.totalSuratSuaraCorrected;
    }

    public final FormC1KesesuaianAdministrationHal2 copy(String idImage, Boolean bool, Boolean bool2, Boolean bool3, Integer num, Integer num2, Integer num3) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        return new FormC1KesesuaianAdministrationHal2(idImage, bool, bool2, bool3, num, num2, num3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FormC1KesesuaianAdministrationHal2) {
            FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2 = (FormC1KesesuaianAdministrationHal2) obj;
            return Intrinsics.areEqual(this.idImage, formC1KesesuaianAdministrationHal2.idImage) && Intrinsics.areEqual(this.suratSuaraSah, formC1KesesuaianAdministrationHal2.suratSuaraSah) && Intrinsics.areEqual(this.suratSuaraTidakSah, formC1KesesuaianAdministrationHal2.suratSuaraTidakSah) && Intrinsics.areEqual(this.totalSuratSuara, formC1KesesuaianAdministrationHal2.totalSuratSuara) && Intrinsics.areEqual(this.suratSuaraSahCorrected, formC1KesesuaianAdministrationHal2.suratSuaraSahCorrected) && Intrinsics.areEqual(this.suratSuaraTidakSahCorrected, formC1KesesuaianAdministrationHal2.suratSuaraTidakSahCorrected) && Intrinsics.areEqual(this.totalSuratSuaraCorrected, formC1KesesuaianAdministrationHal2.totalSuratSuaraCorrected);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.idImage.hashCode() * 31;
        Boolean bool = this.suratSuaraSah;
        int hashCode2 = (hashCode + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.suratSuaraTidakSah;
        int hashCode3 = (hashCode2 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Boolean bool3 = this.totalSuratSuara;
        int hashCode4 = (hashCode3 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
        Integer num = this.suratSuaraSahCorrected;
        int hashCode5 = (hashCode4 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.suratSuaraTidakSahCorrected;
        int hashCode6 = (hashCode5 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.totalSuratSuaraCorrected;
        return hashCode6 + (num3 != null ? num3.hashCode() : 0);
    }

    public String toString() {
        String str = this.idImage;
        Boolean bool = this.suratSuaraSah;
        Boolean bool2 = this.suratSuaraTidakSah;
        Boolean bool3 = this.totalSuratSuara;
        Integer num = this.suratSuaraSahCorrected;
        Integer num2 = this.suratSuaraTidakSahCorrected;
        return "FormC1KesesuaianAdministrationHal2(idImage=" + str + ", suratSuaraSah=" + bool + ", suratSuaraTidakSah=" + bool2 + ", totalSuratSuara=" + bool3 + ", suratSuaraSahCorrected=" + num + ", suratSuaraTidakSahCorrected=" + num2 + ", totalSuratSuaraCorrected=" + this.totalSuratSuaraCorrected + ")";
    }

    public FormC1KesesuaianAdministrationHal2(String idImage, Boolean bool, Boolean bool2, Boolean bool3, Integer num, Integer num2, Integer num3) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        this.idImage = idImage;
        this.suratSuaraSah = bool;
        this.suratSuaraTidakSah = bool2;
        this.totalSuratSuara = bool3;
        this.suratSuaraSahCorrected = num;
        this.suratSuaraTidakSahCorrected = num2;
        this.totalSuratSuaraCorrected = num3;
    }

    public final String getIdImage() {
        return this.idImage;
    }

    public final Boolean getSuratSuaraSah() {
        return this.suratSuaraSah;
    }

    public final Boolean getSuratSuaraTidakSah() {
        return this.suratSuaraTidakSah;
    }

    public final Boolean getTotalSuratSuara() {
        return this.totalSuratSuara;
    }

    public final Integer getSuratSuaraSahCorrected() {
        return this.suratSuaraSahCorrected;
    }

    public final Integer getSuratSuaraTidakSahCorrected() {
        return this.suratSuaraTidakSahCorrected;
    }

    public final Integer getTotalSuratSuaraCorrected() {
        return this.totalSuratSuaraCorrected;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FormC1KesesuaianAdministrationHal2(String idImage) {
        this(idImage, null, null, null, null, null, null);
        Intrinsics.checkNotNullParameter(idImage, "idImage");
    }

    public final boolean isCheckedRowSuratSuaraSah() {
        return this.suratSuaraSah != null;
    }

    public final boolean isCheckedRowSuratSuaraTidakSah() {
        return this.suratSuaraTidakSah != null;
    }

    public final boolean isCheckedRowTotalSuratSuara() {
        return this.totalSuratSuara != null;
    }

    public final boolean isCheckedAll() {
        return isCheckedRowSuratSuaraSah() && isCheckedRowSuratSuaraTidakSah() && isCheckedRowTotalSuratSuara();
    }

    public final List<Boolean> toIsSesuaiPerItem() {
        return CollectionsKt.listOf((Object[]) new Boolean[]{this.suratSuaraSah, this.suratSuaraTidakSah, this.totalSuratSuara});
    }

    public final List<Integer> toKoreksiPerItem() {
        return CollectionsKt.listOf((Object[]) new Integer[]{this.suratSuaraSahCorrected, this.suratSuaraTidakSahCorrected, this.totalSuratSuaraCorrected});
    }

    /* compiled from: FormC1KesesuaianAdministrationHal2.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J.\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t2\u000e\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/model/FormC1KesesuaianAdministrationHal2$Companion;", "", "()V", "createFilled", "Lorg/informatika/sirekap/model/FormC1KesesuaianAdministrationHal2;", "idImage", "", "createFromIsSesuaiPerItem", "isSesuaiPerItem", "", "", "koreksiPerItem", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FormC1KesesuaianAdministrationHal2 createFromIsSesuaiPerItem(String idImage, List<Boolean> isSesuaiPerItem, List<Integer> koreksiPerItem) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            Intrinsics.checkNotNullParameter(isSesuaiPerItem, "isSesuaiPerItem");
            Intrinsics.checkNotNullParameter(koreksiPerItem, "koreksiPerItem");
            return new FormC1KesesuaianAdministrationHal2(idImage, isSesuaiPerItem.get(0), isSesuaiPerItem.get(1), isSesuaiPerItem.get(2), koreksiPerItem.get(0), koreksiPerItem.get(1), koreksiPerItem.get(2));
        }

        public final FormC1KesesuaianAdministrationHal2 createFilled(String idImage) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            return new FormC1KesesuaianAdministrationHal2(idImage, true, true, true, null, null, null);
        }
    }
}
