package org.apache.commons.imaging.color;

import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes2.dex */
public final class ColorCmyk {
    public final double C;
    public final double K;
    public final double M;
    public final double Y;
    public static final ColorCmyk CYAN = new ColorCmyk(100.0d, 0.0d, 0.0d, 0.0d);
    public static final ColorCmyk MAGENTA = new ColorCmyk(0.0d, 100.0d, 0.0d, 0.0d);
    public static final ColorCmyk YELLOW = new ColorCmyk(0.0d, 0.0d, 100.0d, 0.0d);
    public static final ColorCmyk BLACK = new ColorCmyk(0.0d, 0.0d, 0.0d, 100.0d);
    public static final ColorCmyk WHITE = new ColorCmyk(0.0d, 0.0d, 0.0d, 0.0d);
    public static final ColorCmyk RED = new ColorCmyk(0.0d, 100.0d, 100.0d, 0.0d);
    public static final ColorCmyk GREEN = new ColorCmyk(100.0d, 0.0d, 100.0d, 0.0d);
    public static final ColorCmyk BLUE = new ColorCmyk(100.0d, 100.0d, 0.0d, 0.0d);

    public ColorCmyk(double d, double d2, double d3, double d4) {
        this.C = d;
        this.M = d2;
        this.Y = d3;
        this.K = d4;
    }

    public String toString() {
        return "{C: " + this.C + ", M: " + this.M + ", Y: " + this.Y + ", K: " + this.K + StringSubstitutor.DEFAULT_VAR_END;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ColorCmyk colorCmyk = (ColorCmyk) obj;
        return Double.compare(colorCmyk.C, this.C) == 0 && Double.compare(colorCmyk.K, this.K) == 0 && Double.compare(colorCmyk.M, this.M) == 0 && Double.compare(colorCmyk.Y, this.Y) == 0;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.C);
        long doubleToLongBits2 = Double.doubleToLongBits(this.M);
        long doubleToLongBits3 = Double.doubleToLongBits(this.Y);
        long doubleToLongBits4 = Double.doubleToLongBits(this.K);
        return (((((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + ((int) (doubleToLongBits3 ^ (doubleToLongBits3 >>> 32)))) * 31) + ((int) ((doubleToLongBits4 >>> 32) ^ doubleToLongBits4));
    }
}
