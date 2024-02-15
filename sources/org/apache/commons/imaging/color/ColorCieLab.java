package org.apache.commons.imaging.color;

import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes2.dex */
public final class ColorCieLab {
    public final double L;
    public final double a;
    public final double b;
    public static final ColorCieLab BLACK = new ColorCieLab(0.0d, 0.0d, 0.0d);
    public static final ColorCieLab WHITE = new ColorCieLab(100.0d, 0.0d, 0.0d);
    public static final ColorCieLab RED = new ColorCieLab(53.0d, 80.0d, 67.0d);
    public static final ColorCieLab GREEN = new ColorCieLab(88.0d, -86.0d, 83.0d);
    public static final ColorCieLab BLUE = new ColorCieLab(32.0d, 79.0d, -108.0d);

    public ColorCieLab(double d, double d2, double d3) {
        this.L = d;
        this.a = d2;
        this.b = d3;
    }

    public String toString() {
        return "{L: " + this.L + ", a: " + this.a + ", b: " + this.b + StringSubstitutor.DEFAULT_VAR_END;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ColorCieLab colorCieLab = (ColorCieLab) obj;
        return Double.compare(colorCieLab.L, this.L) == 0 && Double.compare(colorCieLab.a, this.a) == 0 && Double.compare(colorCieLab.b, this.b) == 0;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.L);
        long doubleToLongBits2 = Double.doubleToLongBits(this.a);
        long doubleToLongBits3 = Double.doubleToLongBits(this.b);
        return (((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + ((int) ((doubleToLongBits3 >>> 32) ^ doubleToLongBits3));
    }
}
