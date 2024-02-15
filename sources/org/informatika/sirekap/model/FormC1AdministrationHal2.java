package org.informatika.sirekap.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.FormC1AdministrationHal2Payload;
import org.informatika.sirekap.support.ElectionUtil;

/* compiled from: FormC1AdministrationHal2.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u001f\n\u0002\u0010 \n\u0002\b\u0003\b\u0087\b\u0018\u0000 +2\u00020\u0001:\u0001+B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\f\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\tHÆ\u0003J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0014J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0014J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0014JZ\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\u00052\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\tHÖ\u0001J\u001e\u0010$\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020\t2\u0006\u0010'\u001a\u00020\tJ\u000e\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0)J\t\u0010*\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0012R\u0015\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0016\u0010\u0014R\u0015\u0010\f\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0017\u0010\u0014¨\u0006,"}, d2 = {"Lorg/informatika/sirekap/model/FormC1AdministrationHal2;", "", "idImage", "", "isLn", "", "isLnPos", "(Ljava/lang/String;ZZ)V", "formType", "", "suratSuaraSah", "suratSuaraTidakSah", "totalSuratSuara", "(Ljava/lang/String;ZZILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getFormType", "()I", "getIdImage", "()Ljava/lang/String;", "()Z", "getSuratSuaraSah", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSuratSuaraTidakSah", "getTotalSuratSuara", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;ZZILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lorg/informatika/sirekap/model/FormC1AdministrationHal2;", "equals", "other", "hashCode", "isTotalSuratSuaraValid", "suratSuaraSahFinal", "suratSuaraTidakSahFinal", "totalSuratSuaraFinal", "toNilaiPerItem", "", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormC1AdministrationHal2 {
    public static final String FORM_C1_ROW_CANDIDATE = "FORM_C1_ROW_CANDIDATE_";
    public static final String FORM_C1_ROW_SURAT_DIGUNAKAN = "FORM_C1_ROW_SURAT_DIGUNAKAN";
    public static final String FORM_C1_ROW_SURAT_SUARA_SAH = "jumlah_suara_sah";
    public static final String FORM_C1_ROW_SURAT_SUARA_TIDAK_SAH = "jumlah_suara_tidak_sah";
    public static final String FORM_C1_ROW_TOTAL_PEMILIH = "FORM_C1_ROW_TOTAL_PEMILIH";
    public static final String FORM_C1_ROW_TOTAL_SURAT_SUARA = "jumlah_seluruh_suara";
    private final int formType;
    private final String idImage;
    private final boolean isLn;
    private final boolean isLnPos;
    private final Integer suratSuaraSah;
    private final Integer suratSuaraTidakSah;
    private final Integer totalSuratSuara;
    public static final Companion Companion = new Companion(null);
    private static final int totalSuratSuaraInvalidMessage = R.string.total_suara_calc_invalid;

    public static /* synthetic */ FormC1AdministrationHal2 copy$default(FormC1AdministrationHal2 formC1AdministrationHal2, String str, boolean z, boolean z2, int i, Integer num, Integer num2, Integer num3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = formC1AdministrationHal2.idImage;
        }
        if ((i2 & 2) != 0) {
            z = formC1AdministrationHal2.isLn;
        }
        boolean z3 = z;
        if ((i2 & 4) != 0) {
            z2 = formC1AdministrationHal2.isLnPos;
        }
        boolean z4 = z2;
        if ((i2 & 8) != 0) {
            i = formC1AdministrationHal2.formType;
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            num = formC1AdministrationHal2.suratSuaraSah;
        }
        Integer num4 = num;
        if ((i2 & 32) != 0) {
            num2 = formC1AdministrationHal2.suratSuaraTidakSah;
        }
        Integer num5 = num2;
        if ((i2 & 64) != 0) {
            num3 = formC1AdministrationHal2.totalSuratSuara;
        }
        return formC1AdministrationHal2.copy(str, z3, z4, i3, num4, num5, num3);
    }

    public final String component1() {
        return this.idImage;
    }

    public final boolean component2() {
        return this.isLn;
    }

    public final boolean component3() {
        return this.isLnPos;
    }

    public final int component4() {
        return this.formType;
    }

    public final Integer component5() {
        return this.suratSuaraSah;
    }

    public final Integer component6() {
        return this.suratSuaraTidakSah;
    }

    public final Integer component7() {
        return this.totalSuratSuara;
    }

    public final FormC1AdministrationHal2 copy(String idImage, boolean z, boolean z2, int i, Integer num, Integer num2, Integer num3) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        return new FormC1AdministrationHal2(idImage, z, z2, i, num, num2, num3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FormC1AdministrationHal2) {
            FormC1AdministrationHal2 formC1AdministrationHal2 = (FormC1AdministrationHal2) obj;
            return Intrinsics.areEqual(this.idImage, formC1AdministrationHal2.idImage) && this.isLn == formC1AdministrationHal2.isLn && this.isLnPos == formC1AdministrationHal2.isLnPos && this.formType == formC1AdministrationHal2.formType && Intrinsics.areEqual(this.suratSuaraSah, formC1AdministrationHal2.suratSuaraSah) && Intrinsics.areEqual(this.suratSuaraTidakSah, formC1AdministrationHal2.suratSuaraTidakSah) && Intrinsics.areEqual(this.totalSuratSuara, formC1AdministrationHal2.totalSuratSuara);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = this.idImage.hashCode() * 31;
        boolean z = this.isLn;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode + i) * 31;
        boolean z2 = this.isLnPos;
        int hashCode2 = (((i2 + (z2 ? 1 : z2 ? 1 : 0)) * 31) + Integer.hashCode(this.formType)) * 31;
        Integer num = this.suratSuaraSah;
        int hashCode3 = (hashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.suratSuaraTidakSah;
        int hashCode4 = (hashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.totalSuratSuara;
        return hashCode4 + (num3 != null ? num3.hashCode() : 0);
    }

    public final boolean isTotalSuratSuaraValid(int i, int i2, int i3) {
        return i3 == i + i2;
    }

    public String toString() {
        String str = this.idImage;
        boolean z = this.isLn;
        boolean z2 = this.isLnPos;
        int i = this.formType;
        Integer num = this.suratSuaraSah;
        Integer num2 = this.suratSuaraTidakSah;
        return "FormC1AdministrationHal2(idImage=" + str + ", isLn=" + z + ", isLnPos=" + z2 + ", formType=" + i + ", suratSuaraSah=" + num + ", suratSuaraTidakSah=" + num2 + ", totalSuratSuara=" + this.totalSuratSuara + ")";
    }

    public FormC1AdministrationHal2(String idImage, boolean z, boolean z2, int i, Integer num, Integer num2, Integer num3) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        this.idImage = idImage;
        this.isLn = z;
        this.isLnPos = z2;
        this.formType = i;
        this.suratSuaraSah = num;
        this.suratSuaraTidakSah = num2;
        this.totalSuratSuara = num3;
    }

    public final String getIdImage() {
        return this.idImage;
    }

    public final boolean isLn() {
        return this.isLn;
    }

    public final boolean isLnPos() {
        return this.isLnPos;
    }

    public final int getFormType() {
        return this.formType;
    }

    public final Integer getSuratSuaraSah() {
        return this.suratSuaraSah;
    }

    public final Integer getSuratSuaraTidakSah() {
        return this.suratSuaraTidakSah;
    }

    public final Integer getTotalSuratSuara() {
        return this.totalSuratSuara;
    }

    public final List<Integer> toNilaiPerItem() {
        return CollectionsKt.listOf((Object[]) new Integer[]{this.suratSuaraSah, this.suratSuaraTidakSah, this.totalSuratSuara});
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FormC1AdministrationHal2(String idImage, boolean z, boolean z2) {
        this(idImage, z, z2, 3, null, null, null);
        Intrinsics.checkNotNullParameter(idImage, "idImage");
    }

    /* compiled from: FormC1AdministrationHal2.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J0\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0015J&\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lorg/informatika/sirekap/model/FormC1AdministrationHal2$Companion;", "", "()V", "FORM_C1_ROW_CANDIDATE", "", FormC1AdministrationHal2.FORM_C1_ROW_SURAT_DIGUNAKAN, "FORM_C1_ROW_SURAT_SUARA_SAH", "FORM_C1_ROW_SURAT_SUARA_TIDAK_SAH", FormC1AdministrationHal2.FORM_C1_ROW_TOTAL_PEMILIH, "FORM_C1_ROW_TOTAL_SURAT_SUARA", "totalSuratSuaraInvalidMessage", "", "getTotalSuratSuaraInvalidMessage", "()I", "createEmpty", "Lorg/informatika/sirekap/model/FormC1AdministrationHal2;", "idImage", "isLn", "", "isLnPos", "predictedNumber", "", "createFromFormC1AdministrationHal2Payload", "payload", "Lorg/informatika/sirekap/model/FormC1AdministrationHal2Payload;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FormC1AdministrationHal2 createFromFormC1AdministrationHal2Payload(FormC1AdministrationHal2Payload payload, String idImage, boolean z, boolean z2) {
            Integer num;
            Integer num2;
            Integer num3;
            Intrinsics.checkNotNullParameter(payload, "payload");
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            FormC1AdministrationHal2Payload.FormC1AdministrationHal2PayloadDataSuara dataSuara = payload.getDataSuara();
            List<List<Integer>> predComb = dataSuara != null ? dataSuara.getPredComb() : null;
            if (predComb != null) {
                Integer valueOf = CollectionsKt.getLastIndex(predComb) >= 0 ? Integer.valueOf(ElectionUtil.joinThreeNumbers(predComb.get(0))) : null;
                Integer valueOf2 = CollectionsKt.getLastIndex(predComb) >= 1 ? Integer.valueOf(ElectionUtil.joinThreeNumbers(predComb.get(1))) : null;
                num3 = CollectionsKt.getLastIndex(predComb) >= 2 ? Integer.valueOf(ElectionUtil.joinThreeNumbers(predComb.get(2))) : null;
                num = valueOf;
                num2 = valueOf2;
            } else {
                num = null;
                num2 = null;
                num3 = null;
            }
            return new FormC1AdministrationHal2(idImage, z, z2, 3, num, num2, num3);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ FormC1AdministrationHal2 createEmpty$default(Companion companion, String str, boolean z, boolean z2, List list, int i, Object obj) {
            if ((i & 8) != 0) {
                list = null;
            }
            return companion.createEmpty(str, z, z2, list);
        }

        public final FormC1AdministrationHal2 createEmpty(String idImage, boolean z, boolean z2, List<Integer> list) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            return new FormC1AdministrationHal2(idImage, z, z2, 3, Integer.valueOf(list != null ? list.get(0).intValue() : 0), Integer.valueOf(list != null ? list.get(1).intValue() : 0), Integer.valueOf(list != null ? list.get(2).intValue() : 0));
        }

        public final int getTotalSuratSuaraInvalidMessage() {
            return FormC1AdministrationHal2.totalSuratSuaraInvalidMessage;
        }
    }
}
