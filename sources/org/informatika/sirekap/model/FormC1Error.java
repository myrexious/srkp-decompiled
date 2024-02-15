package org.informatika.sirekap.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FormC1Error.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\b\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001a"}, d2 = {"Lorg/informatika/sirekap/model/FormC1Error;", "", "idImage", "", "formType", "", "errorType", "error", "(Ljava/lang/String;IILjava/lang/String;)V", "getError", "()Ljava/lang/String;", "getErrorType", "()I", "getFormType", "getIdImage", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormC1Error {
    public static final Companion Companion = new Companion(null);
    public static final int FORM_C1_ERROR_TYPE_ERROR = 20;
    public static final int FORM_C1_ERROR_TYPE_WARNING = 10;
    public static final int FORM_C1_TYPE_ADMINISTRATION = 1;
    public static final int FORM_C1_TYPE_ADMINISTRATION_HAL_2 = 3;
    public static final int FORM_C1_TYPE_TABULATION = 2;
    public static final int FORM_C1_TYPE_TABULATION_PARTAI = 4;
    private final String error;
    private final int errorType;
    private final int formType;
    private final String idImage;

    public static /* synthetic */ FormC1Error copy$default(FormC1Error formC1Error, String str, int i, int i2, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = formC1Error.idImage;
        }
        if ((i3 & 2) != 0) {
            i = formC1Error.formType;
        }
        if ((i3 & 4) != 0) {
            i2 = formC1Error.errorType;
        }
        if ((i3 & 8) != 0) {
            str2 = formC1Error.error;
        }
        return formC1Error.copy(str, i, i2, str2);
    }

    public final String component1() {
        return this.idImage;
    }

    public final int component2() {
        return this.formType;
    }

    public final int component3() {
        return this.errorType;
    }

    public final String component4() {
        return this.error;
    }

    public final FormC1Error copy(String idImage, int i, int i2, String error) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(error, "error");
        return new FormC1Error(idImage, i, i2, error);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FormC1Error) {
            FormC1Error formC1Error = (FormC1Error) obj;
            return Intrinsics.areEqual(this.idImage, formC1Error.idImage) && this.formType == formC1Error.formType && this.errorType == formC1Error.errorType && Intrinsics.areEqual(this.error, formC1Error.error);
        }
        return false;
    }

    public int hashCode() {
        return (((((this.idImage.hashCode() * 31) + Integer.hashCode(this.formType)) * 31) + Integer.hashCode(this.errorType)) * 31) + this.error.hashCode();
    }

    public String toString() {
        String str = this.idImage;
        int i = this.formType;
        int i2 = this.errorType;
        return "FormC1Error(idImage=" + str + ", formType=" + i + ", errorType=" + i2 + ", error=" + this.error + ")";
    }

    public FormC1Error(String idImage, int i, int i2, String error) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(error, "error");
        this.idImage = idImage;
        this.formType = i;
        this.errorType = i2;
        this.error = error;
    }

    public final String getIdImage() {
        return this.idImage;
    }

    public final int getFormType() {
        return this.formType;
    }

    public final int getErrorType() {
        return this.errorType;
    }

    public final String getError() {
        return this.error;
    }

    /* compiled from: FormC1Error.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/informatika/sirekap/model/FormC1Error$Companion;", "", "()V", "FORM_C1_ERROR_TYPE_ERROR", "", "FORM_C1_ERROR_TYPE_WARNING", "FORM_C1_TYPE_ADMINISTRATION", "FORM_C1_TYPE_ADMINISTRATION_HAL_2", "FORM_C1_TYPE_TABULATION", "FORM_C1_TYPE_TABULATION_PARTAI", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
