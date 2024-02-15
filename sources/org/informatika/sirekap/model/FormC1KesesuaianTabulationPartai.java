package org.informatika.sirekap.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FormC1KesesuaianTabulationPartai.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0016\b\u0087\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\rJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0010J0\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\bHÖ\u0001J\u0006\u0010\u001a\u001a\u00020\u0006J\u0006\u0010\u001b\u001a\u00020\u0006J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001e"}, d2 = {"Lorg/informatika/sirekap/model/FormC1KesesuaianTabulationPartai;", "", "idImage", "", "(Ljava/lang/String;)V", "suratSahPartaiDanCalon", "", "suratSahPartaiDanCalonCorrected", "", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;)V", "getIdImage", "()Ljava/lang/String;", "getSuratSahPartaiDanCalon", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getSuratSahPartaiDanCalonCorrected", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;)Lorg/informatika/sirekap/model/FormC1KesesuaianTabulationPartai;", "equals", "other", "hashCode", "isCheckedAll", "isCheckedRowSuratSahPartaiDanCalon", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormC1KesesuaianTabulationPartai {
    public static final Companion Companion = new Companion(null);
    private final String idImage;
    private final Boolean suratSahPartaiDanCalon;
    private final Integer suratSahPartaiDanCalonCorrected;

    public static /* synthetic */ FormC1KesesuaianTabulationPartai copy$default(FormC1KesesuaianTabulationPartai formC1KesesuaianTabulationPartai, String str, Boolean bool, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = formC1KesesuaianTabulationPartai.idImage;
        }
        if ((i & 2) != 0) {
            bool = formC1KesesuaianTabulationPartai.suratSahPartaiDanCalon;
        }
        if ((i & 4) != 0) {
            num = formC1KesesuaianTabulationPartai.suratSahPartaiDanCalonCorrected;
        }
        return formC1KesesuaianTabulationPartai.copy(str, bool, num);
    }

    public final String component1() {
        return this.idImage;
    }

    public final Boolean component2() {
        return this.suratSahPartaiDanCalon;
    }

    public final Integer component3() {
        return this.suratSahPartaiDanCalonCorrected;
    }

    public final FormC1KesesuaianTabulationPartai copy(String idImage, Boolean bool, Integer num) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        return new FormC1KesesuaianTabulationPartai(idImage, bool, num);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FormC1KesesuaianTabulationPartai) {
            FormC1KesesuaianTabulationPartai formC1KesesuaianTabulationPartai = (FormC1KesesuaianTabulationPartai) obj;
            return Intrinsics.areEqual(this.idImage, formC1KesesuaianTabulationPartai.idImage) && Intrinsics.areEqual(this.suratSahPartaiDanCalon, formC1KesesuaianTabulationPartai.suratSahPartaiDanCalon) && Intrinsics.areEqual(this.suratSahPartaiDanCalonCorrected, formC1KesesuaianTabulationPartai.suratSahPartaiDanCalonCorrected);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.idImage.hashCode() * 31;
        Boolean bool = this.suratSahPartaiDanCalon;
        int hashCode2 = (hashCode + (bool == null ? 0 : bool.hashCode())) * 31;
        Integer num = this.suratSahPartaiDanCalonCorrected;
        return hashCode2 + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        String str = this.idImage;
        Boolean bool = this.suratSahPartaiDanCalon;
        return "FormC1KesesuaianTabulationPartai(idImage=" + str + ", suratSahPartaiDanCalon=" + bool + ", suratSahPartaiDanCalonCorrected=" + this.suratSahPartaiDanCalonCorrected + ")";
    }

    public FormC1KesesuaianTabulationPartai(String idImage, Boolean bool, Integer num) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        this.idImage = idImage;
        this.suratSahPartaiDanCalon = bool;
        this.suratSahPartaiDanCalonCorrected = num;
    }

    public final String getIdImage() {
        return this.idImage;
    }

    public final Boolean getSuratSahPartaiDanCalon() {
        return this.suratSahPartaiDanCalon;
    }

    public final Integer getSuratSahPartaiDanCalonCorrected() {
        return this.suratSahPartaiDanCalonCorrected;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FormC1KesesuaianTabulationPartai(String idImage) {
        this(idImage, null, null);
        Intrinsics.checkNotNullParameter(idImage, "idImage");
    }

    public final boolean isCheckedRowSuratSahPartaiDanCalon() {
        return this.suratSahPartaiDanCalon != null;
    }

    public final boolean isCheckedAll() {
        return isCheckedRowSuratSahPartaiDanCalon();
    }

    /* compiled from: FormC1KesesuaianTabulationPartai.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J.\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t2\u000e\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/model/FormC1KesesuaianTabulationPartai$Companion;", "", "()V", "createFilled", "Lorg/informatika/sirekap/model/FormC1KesesuaianTabulationPartai;", "idImage", "", "createFromIsSesuaiPerItem", "isSesuaiPerItem", "", "", "koreksiPerItem", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FormC1KesesuaianTabulationPartai createFromIsSesuaiPerItem(String idImage, List<Boolean> isSesuaiPerItem, List<Integer> koreksiPerItem) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            Intrinsics.checkNotNullParameter(isSesuaiPerItem, "isSesuaiPerItem");
            Intrinsics.checkNotNullParameter(koreksiPerItem, "koreksiPerItem");
            return new FormC1KesesuaianTabulationPartai(idImage, isSesuaiPerItem.get(0), koreksiPerItem.get(0));
        }

        public final FormC1KesesuaianTabulationPartai createFilled(String idImage) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            return new FormC1KesesuaianTabulationPartai(idImage, true, null);
        }
    }
}
