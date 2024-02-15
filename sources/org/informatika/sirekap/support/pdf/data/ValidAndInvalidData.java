package org.informatika.sirekap.support.pdf.data;

import kotlin.Metadata;

/* compiled from: ValidAndInvalidData.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lorg/informatika/sirekap/support/pdf/data/ValidAndInvalidData;", "", "validDataCount", "", "invalidDataCount", "allDataCount", "(III)V", "getAllDataCount", "()I", "getInvalidDataCount", "getValidDataCount", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ValidAndInvalidData {
    private final int allDataCount;
    private final int invalidDataCount;
    private final int validDataCount;

    public static /* synthetic */ ValidAndInvalidData copy$default(ValidAndInvalidData validAndInvalidData, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = validAndInvalidData.validDataCount;
        }
        if ((i4 & 2) != 0) {
            i2 = validAndInvalidData.invalidDataCount;
        }
        if ((i4 & 4) != 0) {
            i3 = validAndInvalidData.allDataCount;
        }
        return validAndInvalidData.copy(i, i2, i3);
    }

    public final int component1() {
        return this.validDataCount;
    }

    public final int component2() {
        return this.invalidDataCount;
    }

    public final int component3() {
        return this.allDataCount;
    }

    public final ValidAndInvalidData copy(int i, int i2, int i3) {
        return new ValidAndInvalidData(i, i2, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ValidAndInvalidData) {
            ValidAndInvalidData validAndInvalidData = (ValidAndInvalidData) obj;
            return this.validDataCount == validAndInvalidData.validDataCount && this.invalidDataCount == validAndInvalidData.invalidDataCount && this.allDataCount == validAndInvalidData.allDataCount;
        }
        return false;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.validDataCount) * 31) + Integer.hashCode(this.invalidDataCount)) * 31) + Integer.hashCode(this.allDataCount);
    }

    public String toString() {
        int i = this.validDataCount;
        int i2 = this.invalidDataCount;
        return "ValidAndInvalidData(validDataCount=" + i + ", invalidDataCount=" + i2 + ", allDataCount=" + this.allDataCount + ")";
    }

    public ValidAndInvalidData(int i, int i2, int i3) {
        this.validDataCount = i;
        this.invalidDataCount = i2;
        this.allDataCount = i3;
    }

    public final int getValidDataCount() {
        return this.validDataCount;
    }

    public final int getInvalidDataCount() {
        return this.invalidDataCount;
    }

    public final int getAllDataCount() {
        return this.allDataCount;
    }
}
