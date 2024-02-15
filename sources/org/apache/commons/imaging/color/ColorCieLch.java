package org.apache.commons.imaging.color;

import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes2.dex */
public final class ColorCieLch {
    public final double C;
    public final double L;
    public final double h;
    public static final ColorCieLch BLACK = new ColorCieLch(0.0d, 0.0d, 0.0d);
    public static final ColorCieLch WHITE = new ColorCieLch(100.0d, 0.0d, 297.0d);
    public static final ColorCieLch RED = new ColorCieLch(53.0d, 80.0d, 67.0d);
    public static final ColorCieLch GREEN = new ColorCieLch(88.0d, -86.0d, 83.0d);
    public static final ColorCieLch BLUE = new ColorCieLch(32.0d, 79.0d, -108.0d);

    public ColorCieLch(double d, double d2, double d3) {
        this.L = d;
        this.C = d2;
        this.h = d3;
    }

    public String toString() {
        return "{L: " + this.L + ", C: " + this.C + ", h: " + this.h + StringSubstitutor.DEFAULT_VAR_END;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ColorCieLch colorCieLch = (ColorCieLch) obj;
        return Double.compare(colorCieLch.C, this.C) == 0 && Double.compare(colorCieLch.h, this.h) == 0 && Double.compare(colorCieLch.L, this.L) == 0;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.L);
        long doubleToLongBits2 = Double.doubleToLongBits(this.C);
        long doubleToLongBits3 = Double.doubleToLongBits(this.h);
        return (((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + ((int) ((doubleToLongBits3 >>> 32) ^ doubleToLongBits3));
    }
}
