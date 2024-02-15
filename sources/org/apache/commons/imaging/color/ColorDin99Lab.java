package org.apache.commons.imaging.color;

import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes2.dex */
public final class ColorDin99Lab {
    public final double L99;
    public final double a99;
    public final double b99;

    public ColorDin99Lab(double d, double d2, double d3) {
        this.L99 = d;
        this.a99 = d2;
        this.b99 = d3;
    }

    public String toString() {
        return "{L: " + this.L99 + ", a: " + this.a99 + ", b: " + this.b99 + StringSubstitutor.DEFAULT_VAR_END;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ColorDin99Lab colorDin99Lab = (ColorDin99Lab) obj;
        return Double.compare(colorDin99Lab.L99, this.L99) == 0 && Double.compare(colorDin99Lab.a99, this.a99) == 0 && Double.compare(colorDin99Lab.b99, this.b99) == 0;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.L99);
        long doubleToLongBits2 = Double.doubleToLongBits(this.a99);
        long doubleToLongBits3 = Double.doubleToLongBits(this.b99);
        return (((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + ((int) ((doubleToLongBits3 >>> 32) ^ doubleToLongBits3));
    }
}
