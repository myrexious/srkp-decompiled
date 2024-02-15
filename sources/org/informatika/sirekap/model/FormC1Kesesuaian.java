package org.informatika.sirekap.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FormC1Kesesuaian.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J)\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0015"}, d2 = {"Lorg/informatika/sirekap/model/FormC1Kesesuaian;", "", "idImage", "", "isSesuai", "", "komentar", "(Ljava/lang/String;ZLjava/lang/String;)V", "getIdImage", "()Ljava/lang/String;", "()Z", "getKomentar", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormC1Kesesuaian {
    private final String idImage;
    private final boolean isSesuai;
    private final String komentar;

    public static /* synthetic */ FormC1Kesesuaian copy$default(FormC1Kesesuaian formC1Kesesuaian, String str, boolean z, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = formC1Kesesuaian.idImage;
        }
        if ((i & 2) != 0) {
            z = formC1Kesesuaian.isSesuai;
        }
        if ((i & 4) != 0) {
            str2 = formC1Kesesuaian.komentar;
        }
        return formC1Kesesuaian.copy(str, z, str2);
    }

    public final String component1() {
        return this.idImage;
    }

    public final boolean component2() {
        return this.isSesuai;
    }

    public final String component3() {
        return this.komentar;
    }

    public final FormC1Kesesuaian copy(String idImage, boolean z, String str) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        return new FormC1Kesesuaian(idImage, z, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FormC1Kesesuaian) {
            FormC1Kesesuaian formC1Kesesuaian = (FormC1Kesesuaian) obj;
            return Intrinsics.areEqual(this.idImage, formC1Kesesuaian.idImage) && this.isSesuai == formC1Kesesuaian.isSesuai && Intrinsics.areEqual(this.komentar, formC1Kesesuaian.komentar);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = this.idImage.hashCode() * 31;
        boolean z = this.isSesuai;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode + i) * 31;
        String str = this.komentar;
        return i2 + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        String str = this.idImage;
        boolean z = this.isSesuai;
        return "FormC1Kesesuaian(idImage=" + str + ", isSesuai=" + z + ", komentar=" + this.komentar + ")";
    }

    public FormC1Kesesuaian(String idImage, boolean z, String str) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        this.idImage = idImage;
        this.isSesuai = z;
        this.komentar = str;
    }

    public final String getIdImage() {
        return this.idImage;
    }

    public final boolean isSesuai() {
        return this.isSesuai;
    }

    public final String getKomentar() {
        return this.komentar;
    }
}
