package org.informatika.sirekap.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.FormC1TabulationPayload;
import org.informatika.sirekap.support.ElectionUtil;

/* compiled from: FormC1TabulationPartai.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\b\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\rJ.\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\r¨\u0006\u001a"}, d2 = {"Lorg/informatika/sirekap/model/FormC1TabulationPartai;", "", "idImage", "", "formType", "", "suratSahPartaiDanCalon", "(Ljava/lang/String;ILjava/lang/Integer;)V", "getFormType", "()I", "getIdImage", "()Ljava/lang/String;", "getSuratSahPartaiDanCalon", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;ILjava/lang/Integer;)Lorg/informatika/sirekap/model/FormC1TabulationPartai;", "equals", "", "other", "hashCode", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormC1TabulationPartai {
    public static final String FORM_C1_ROW_CANDIDATE = "FORM_C1_ROW_CANDIDATE_";
    public static final String FORM_C1_ROW_SUARA_SAH_PARTAI_CALON = "FORM_C1_ROW_SUARA_SAH_PARTAI_CALON";
    private final int formType;
    private final String idImage;
    private final Integer suratSahPartaiDanCalon;
    public static final Companion Companion = new Companion(null);
    private static final int totalSuratSuaraInvalidMessage = R.string.total_suara_calc_invalid;

    public static /* synthetic */ FormC1TabulationPartai copy$default(FormC1TabulationPartai formC1TabulationPartai, String str, int i, Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = formC1TabulationPartai.idImage;
        }
        if ((i2 & 2) != 0) {
            i = formC1TabulationPartai.formType;
        }
        if ((i2 & 4) != 0) {
            num = formC1TabulationPartai.suratSahPartaiDanCalon;
        }
        return formC1TabulationPartai.copy(str, i, num);
    }

    public final String component1() {
        return this.idImage;
    }

    public final int component2() {
        return this.formType;
    }

    public final Integer component3() {
        return this.suratSahPartaiDanCalon;
    }

    public final FormC1TabulationPartai copy(String idImage, int i, Integer num) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        return new FormC1TabulationPartai(idImage, i, num);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FormC1TabulationPartai) {
            FormC1TabulationPartai formC1TabulationPartai = (FormC1TabulationPartai) obj;
            return Intrinsics.areEqual(this.idImage, formC1TabulationPartai.idImage) && this.formType == formC1TabulationPartai.formType && Intrinsics.areEqual(this.suratSahPartaiDanCalon, formC1TabulationPartai.suratSahPartaiDanCalon);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((this.idImage.hashCode() * 31) + Integer.hashCode(this.formType)) * 31;
        Integer num = this.suratSahPartaiDanCalon;
        return hashCode + (num == null ? 0 : num.hashCode());
    }

    public String toString() {
        String str = this.idImage;
        int i = this.formType;
        return "FormC1TabulationPartai(idImage=" + str + ", formType=" + i + ", suratSahPartaiDanCalon=" + this.suratSahPartaiDanCalon + ")";
    }

    public FormC1TabulationPartai(String idImage, int i, Integer num) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        this.idImage = idImage;
        this.formType = i;
        this.suratSahPartaiDanCalon = num;
    }

    public final String getIdImage() {
        return this.idImage;
    }

    public final int getFormType() {
        return this.formType;
    }

    public final Integer getSuratSahPartaiDanCalon() {
        return this.suratSahPartaiDanCalon;
    }

    /* compiled from: FormC1TabulationPartai.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u0007J\u0016\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0011"}, d2 = {"Lorg/informatika/sirekap/model/FormC1TabulationPartai$Companion;", "", "()V", "FORM_C1_ROW_CANDIDATE", "", FormC1TabulationPartai.FORM_C1_ROW_SUARA_SAH_PARTAI_CALON, "totalSuratSuaraInvalidMessage", "", "getTotalSuratSuaraInvalidMessage", "()I", "createEmpty", "Lorg/informatika/sirekap/model/FormC1TabulationPartai;", "idImage", "predictedJumlahSuara", "createFromFormC1TabulationPayload", "payload", "Lorg/informatika/sirekap/model/FormC1TabulationPayload;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FormC1TabulationPartai createFromFormC1TabulationPayload(FormC1TabulationPayload payload, String idImage) {
            List<Integer> list;
            Intrinsics.checkNotNullParameter(payload, "payload");
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            FormC1TabulationPayload.FormC1TabulationPayloadJumlahSuara jumlahSuara = payload.getJumlahSuara();
            Integer num = null;
            List<List<Integer>> predComb = jumlahSuara != null ? jumlahSuara.getPredComb() : null;
            if (predComb != null && (list = predComb.get(0)) != null) {
                num = Integer.valueOf(ElectionUtil.joinThreeNumbers(list));
            }
            return new FormC1TabulationPartai(idImage, 4, num);
        }

        public static /* synthetic */ FormC1TabulationPartai createEmpty$default(Companion companion, String str, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 0;
            }
            return companion.createEmpty(str, i);
        }

        public final FormC1TabulationPartai createEmpty(String idImage, int i) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            return new FormC1TabulationPartai(idImage, 4, Integer.valueOf(i));
        }

        public final int getTotalSuratSuaraInvalidMessage() {
            return FormC1TabulationPartai.totalSuratSuaraInvalidMessage;
        }
    }
}
