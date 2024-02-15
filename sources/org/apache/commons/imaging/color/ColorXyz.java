package org.apache.commons.imaging.color;

import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes2.dex */
public final class ColorXyz {
    public final double X;
    public final double Y;
    public final double Z;
    public static final ColorXyz BLACK = new ColorXyz(0.0d, 0.0d, 0.0d);
    public static final ColorXyz WHITE = new ColorXyz(95.05d, 100.0d, 108.9d);
    public static final ColorXyz RED = new ColorXyz(41.24d, 21.26d, 1.93d);
    public static final ColorXyz GREEN = new ColorXyz(35.76d, 71.52d, 11.92d);
    public static final ColorXyz BLUE = new ColorXyz(18.05d, 7.22d, 95.05d);

    public ColorXyz(double d, double d2, double d3) {
        this.X = d;
        this.Y = d2;
        this.Z = d3;
    }

    public String toString() {
        return "{X: " + this.X + ", Y: " + this.Y + ", Z: " + this.Z + StringSubstitutor.DEFAULT_VAR_END;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ColorXyz colorXyz = (ColorXyz) obj;
        return Double.compare(colorXyz.X, this.X) == 0 && Double.compare(colorXyz.Y, this.Y) == 0 && Double.compare(colorXyz.Z, this.Z) == 0;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.X);
        long doubleToLongBits2 = Double.doubleToLongBits(this.Y);
        long doubleToLongBits3 = Double.doubleToLongBits(this.Z);
        return (((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + ((int) ((doubleToLongBits3 >>> 32) ^ doubleToLongBits3));
    }
}
