package org.apache.commons.imaging.color;

import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes2.dex */
public final class ColorHsv {
    public final double H;
    public final double S;
    public final double V;
    public static final ColorHsv BLACK = new ColorHsv(0.0d, 0.0d, 0.0d);
    public static final ColorHsv WHITE = new ColorHsv(0.0d, 0.0d, 100.0d);
    public static final ColorHsv RED = new ColorHsv(0.0d, 100.0d, 100.0d);
    public static final ColorHsv GREEN = new ColorHsv(120.0d, 100.0d, 100.0d);
    public static final ColorHsv BLUE = new ColorHsv(240.0d, 100.0d, 100.0d);

    public ColorHsv(double d, double d2, double d3) {
        this.H = d;
        this.S = d2;
        this.V = d3;
    }

    public String toString() {
        return "{H: " + this.H + ", S: " + this.S + ", V: " + this.V + StringSubstitutor.DEFAULT_VAR_END;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ColorHsv colorHsv = (ColorHsv) obj;
        return Double.compare(colorHsv.H, this.H) == 0 && Double.compare(colorHsv.S, this.S) == 0 && Double.compare(colorHsv.V, this.V) == 0;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.H);
        long doubleToLongBits2 = Double.doubleToLongBits(this.S);
        long doubleToLongBits3 = Double.doubleToLongBits(this.V);
        return (((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + ((int) ((doubleToLongBits3 >>> 32) ^ doubleToLongBits3));
    }
}
