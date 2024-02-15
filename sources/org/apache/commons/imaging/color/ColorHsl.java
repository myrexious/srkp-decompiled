package org.apache.commons.imaging.color;

import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes2.dex */
public final class ColorHsl {
    public final double H;
    public final double L;
    public final double S;
    public static final ColorHsl BLACK = new ColorHsl(0.0d, 0.0d, 0.0d);
    public static final ColorHsl WHITE = new ColorHsl(0.0d, 0.0d, 100.0d);
    public static final ColorHsl RED = new ColorHsl(0.0d, 100.0d, 100.0d);
    public static final ColorHsl GREEN = new ColorHsl(120.0d, 100.0d, 100.0d);
    public static final ColorHsl BLUE = new ColorHsl(240.0d, 100.0d, 100.0d);

    public ColorHsl(double d, double d2, double d3) {
        this.H = d;
        this.S = d2;
        this.L = d3;
    }

    public String toString() {
        return "{H: " + this.H + ", S: " + this.S + ", L: " + this.L + StringSubstitutor.DEFAULT_VAR_END;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ColorHsl colorHsl = (ColorHsl) obj;
        return Double.compare(colorHsl.H, this.H) == 0 && Double.compare(colorHsl.L, this.L) == 0 && Double.compare(colorHsl.S, this.S) == 0;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.H);
        long doubleToLongBits2 = Double.doubleToLongBits(this.S);
        long doubleToLongBits3 = Double.doubleToLongBits(this.L);
        return (((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + ((int) ((doubleToLongBits3 >>> 32) ^ doubleToLongBits3));
    }
}
