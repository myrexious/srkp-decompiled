package org.apache.commons.imaging.color;

import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes2.dex */
public final class ColorCmy {
    public final double C;
    public final double M;
    public final double Y;
    public static final ColorCmy CYAN = new ColorCmy(100.0d, 0.0d, 0.0d);
    public static final ColorCmy MAGENTA = new ColorCmy(0.0d, 100.0d, 0.0d);
    public static final ColorCmy YELLOW = new ColorCmy(0.0d, 0.0d, 100.0d);
    public static final ColorCmy BLACK = new ColorCmy(100.0d, 100.0d, 100.0d);
    public static final ColorCmy WHITE = new ColorCmy(0.0d, 0.0d, 0.0d);
    public static final ColorCmy RED = new ColorCmy(0.0d, 100.0d, 100.0d);
    public static final ColorCmy GREEN = new ColorCmy(100.0d, 0.0d, 100.0d);
    public static final ColorCmy BLUE = new ColorCmy(100.0d, 100.0d, 0.0d);

    public ColorCmy(double d, double d2, double d3) {
        this.C = d;
        this.M = d2;
        this.Y = d3;
    }

    public String toString() {
        return "{C: " + this.C + ", M: " + this.M + ", Y: " + this.Y + StringSubstitutor.DEFAULT_VAR_END;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ColorCmy colorCmy = (ColorCmy) obj;
        return Double.compare(colorCmy.C, this.C) == 0 && Double.compare(colorCmy.M, this.M) == 0 && Double.compare(colorCmy.Y, this.Y) == 0;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.C);
        long doubleToLongBits2 = Double.doubleToLongBits(this.M);
        long doubleToLongBits3 = Double.doubleToLongBits(this.Y);
        return (((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + ((int) ((doubleToLongBits3 >>> 32) ^ doubleToLongBits3));
    }
}
